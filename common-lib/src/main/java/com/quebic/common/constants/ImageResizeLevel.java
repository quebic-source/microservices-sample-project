package com.quebic.common.constants;

public enum ImageResizeLevel {
	
	HIGH(400),
    MEDIUM(200),
    LOW(100);
	
    private final int size;

    ImageResizeLevel(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return this.size;
    }
	
}
