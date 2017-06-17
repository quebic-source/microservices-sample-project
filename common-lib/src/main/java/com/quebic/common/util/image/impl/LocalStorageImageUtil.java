package com.quebic.common.util.image.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import com.quebic.common.constants.ImageResizeLevel;
import com.quebic.common.exception.ResourceNotFoundException;
import com.quebic.common.util.image.ImageUtil;

import rx.Single;
import rx.schedulers.Schedulers;

public class LocalStorageImageUtil extends ImageUtil {

	private Logger logger = LoggerFactory.getLogger(LocalStorageImageUtil.class);

	@Override
	public Single<Object> storeFile(String id, String fileName, String fileLocation, String imageDir,
			MultipartFile multipartFile){

		return Single.create(s->{
			
			try {
				String dirStr;
				if (!StringUtils.isEmpty(id))
					dirStr = fileLocation + File.separator + imageDir + File.separator + id;
				else
					dirStr = fileLocation + File.separator + imageDir;

				Path dir = Paths.get(dirStr);
				if (!Files.exists(dir)) {
					Files.createDirectories(dir);
				}

				Path file = Paths.get(dirStr + File.separator + fileName);

				byte[] bytes = multipartFile.getBytes();
				Files.write(file, bytes);

				resizeImage(bytes, dirStr, fileName, ImageResizeLevel.HIGH.getSize());
				resizeImage(bytes, dirStr, fileName, ImageResizeLevel.MEDIUM.getSize());
				resizeImage(bytes, dirStr, fileName, ImageResizeLevel.LOW.getSize());

				s.onSuccess(fileName);;

			} catch (Exception e) {
				logger.error(e.getMessage());
				s.onError(e);
			}
			
		}).subscribeOn(Schedulers.io());
	}
	
	@Override
	public void resizeImage(byte[] bytes, String dirStr, String fileName, int targetSize) throws Exception{
    	
		BufferedImage orginalImage = ImageIO.read(new ByteArrayInputStream(bytes));
		
		String[] splt = splitFileName(fileName);
		String randomFileName = splt[0];
		String fileExtension = splt[1];
		
		String savedImage = targetSize + "-" + randomFileName + "." + fileExtension;
		
		BufferedImage scaledImage = Scalr.resize(orginalImage, targetSize);
	    ImageIO.write(scaledImage, fileExtension, new File(dirStr + File.separator + savedImage));
    	
    }

	@Override
	public ResponseEntity<StreamingResponseBody> readFile(String fileLocation, String imageDir, String id,
			String fileName) {
		StreamingResponseBody streamingResponseBody = new StreamingResponseBody() {

			@Override
			public void writeTo(OutputStream outputStream) {
				try {

					String fileStr = fileLocation + File.separator + imageDir + File.separator + id + File.separator
							+ fileName;

					RandomAccessFile file = new RandomAccessFile(fileStr, "r");
					FileChannel inChannel = file.getChannel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					while (inChannel.read(buffer) > 0) {
						buffer.flip();
						for (int i = 0; i < buffer.limit(); i++) {
							outputStream.write(buffer.get());
						}
						buffer.clear();
						outputStream.flush();
					}
					inChannel.close();
					file.close();

				} catch (IOException e) {
					logger.error("Image Not Found : error " + e.getMessage());
					throw new ResourceNotFoundException("Image Not Found : " + id + "/" + fileName);
				}
			}
		};

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "image/*");
		return new ResponseEntity<StreamingResponseBody>(streamingResponseBody, headers, HttpStatus.OK);
	}

}
