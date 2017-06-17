package com.quebic.common.model.image;

import java.util.ArrayList;
import java.util.List;

public class ImageContainer {
	
	private int selectedImageIndex = 0;
	private List<Image> images = new ArrayList<>();
	
	public int getSelectedImageIndex() {
		return selectedImageIndex;
	}
	public void setSelectedImageIndex(int selectedImageIndex) {
		this.selectedImageIndex = selectedImageIndex;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
	
}
