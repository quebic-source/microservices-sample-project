package com.quebic.common.util.image.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.quebic.common.constants.ImageResizeLevel;
import com.quebic.common.exception.ResourceNotFoundException;
import com.quebic.common.util.image.ImageUtil;

import rx.Single;
import rx.schedulers.Schedulers;

public class DropBoxStorageImageUtil extends ImageUtil{

	private Logger logger = LoggerFactory.getLogger(DropBoxStorageImageUtil.class);
	
	private final String APP_IDENTIFIER = "xx";
	private final String ACCESS_TOKEN = "xx";
	private final String SEPARATOR = "/";
	
	@Override
	public Single<Object> storeFile(String id, String fileName, String fileLocation, String imageDir,
			MultipartFile multipartFile){
		
		return Single.create(s->{
			
			try{
			
			String dirStr;
			if (!StringUtils.isEmpty(id))
				dirStr = SEPARATOR + imageDir + SEPARATOR + id;
			else
				dirStr = SEPARATOR + imageDir;

			byte[] bytes = multipartFile.getBytes();
			InputStream inputStream = multipartFile.getInputStream();
			
	        String uploadPath = dirStr + SEPARATOR + fileName;
	        FileMetadata metadata = dropBoxSave(uploadPath, inputStream);//orginal file
	        
			resizeImage(bytes, dirStr, fileName, ImageResizeLevel.HIGH.getSize());
			resizeImage(bytes, dirStr, fileName, ImageResizeLevel.MEDIUM.getSize());
			resizeImage(bytes, dirStr, fileName, ImageResizeLevel.LOW.getSize());
			
			s.onSuccess(metadata.getName());
			
			}catch(Exception e){
				logger.error(e.getMessage());
				s.onError(e);
			}
			
		}).subscribeOn(Schedulers.io());
	}

	@Override
	public ResponseEntity<StreamingResponseBody> readFile(String fileLocation, String imageDir, String id,
			String fileName) {
		
        StreamingResponseBody streamingResponseBody = new StreamingResponseBody() {

			@Override
			public void writeTo(OutputStream outputStream) {
				try {

					String fileStr = SEPARATOR + imageDir + SEPARATOR + id + SEPARATOR + fileName;

					DbxRequestConfig config = new DbxRequestConfig(APP_IDENTIFIER);
			        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
			        client.files().download(fileStr).download(outputStream);

				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new ResourceNotFoundException("Image Not Found : " + id + "/" + fileName);
				}
			}
		};

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "image/*");
		return new ResponseEntity<StreamingResponseBody>(streamingResponseBody, headers, HttpStatus.OK);
	}

	@Override
	public void resizeImage(byte[] bytes, String dirStr, String fileName, int targetSize) throws Exception {
		BufferedImage orginalImage = ImageIO.read(new ByteArrayInputStream(bytes));
		
		String[] splt = splitFileName(fileName);
		String randomFileName = splt[0];
		String fileExtension = splt[1];
		
		String savedImageName = targetSize + "-" + randomFileName + "." + fileExtension;
		String uploadPath = dirStr + SEPARATOR + savedImageName;
		
		BufferedImage scaledImage = Scalr.resize(orginalImage, targetSize);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(scaledImage, fileExtension, os);
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		
		dropBoxSave(uploadPath, is);
		
	}
	
	private FileMetadata dropBoxSave(String uploadPath, InputStream inputStream)throws Exception{
		DbxRequestConfig config = new DbxRequestConfig(APP_IDENTIFIER);
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        return client.files().uploadBuilder(uploadPath).uploadAndFinish(inputStream);
	}

}
