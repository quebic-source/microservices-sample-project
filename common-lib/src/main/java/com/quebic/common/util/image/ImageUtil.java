package com.quebic.common.util.image;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.quebic.common.util.image.impl.DropBoxStorageImageUtil;
import com.quebic.common.util.image.impl.LocalStorageImageUtil;

import rx.Single;

public abstract class ImageUtil {
	
	public abstract Single<Object> storeFile(String id, String fileName, String fileLocation, String imageDir, MultipartFile multipartFile);
	public abstract ResponseEntity<StreamingResponseBody> readFile(String fileLocation, String imageDir, String id, String fileName);
	public abstract void resizeImage(byte[] bytes, String dirStr, String fileName, int targetSize) throws Exception;
	
	public static ImageUtil createLocalStorageImageUtil(){
		return new LocalStorageImageUtil();
	}
	
	public static ImageUtil createDropBoxStorageImageUtil(){
		return new DropBoxStorageImageUtil();
	}
	
	protected String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	protected String[] splitFileName(String fileName) {
		int splitPoint = fileName.lastIndexOf(".");
    	String randomFileName =fileName.substring(0, splitPoint);
    	String fileExtension = fileName.substring(splitPoint + 1, fileName.length());
    	
    	return new String[]{randomFileName, fileExtension};
    }
	
	public String genarateFileName(MultipartFile file){
		
		String randomFileName = createRandomFileName();
		
		String fileExtension = getFileExtension(file.getOriginalFilename());
		
        String fileName = randomFileName + "." + fileExtension;
		
		return fileName;
		
	}
	
	protected String createRandomFileName(){
		int min = 100;
		int max = 1000000;
		return String.valueOf(ThreadLocalRandom.current().nextInt(min, max + 1));
	}
	
}
