package com.quebic.auth.api.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class UserImageDto {

	private List<MultipartFile> profileImagesFiles = new ArrayList<>();
	private List<MultipartFile> coverImagesFiles = new ArrayList<>();
	
	public List<MultipartFile> getProfileImagesFiles() {
		return profileImagesFiles;
	}
	public void setProfileImagesFiles(List<MultipartFile> profileImagesFiles) {
		this.profileImagesFiles = profileImagesFiles;
	}
	public List<MultipartFile> getCoverImagesFiles() {
		return coverImagesFiles;
	}
	public void setCoverImagesFiles(List<MultipartFile> coverImagesFiles) {
		this.coverImagesFiles = coverImagesFiles;
	}
}
