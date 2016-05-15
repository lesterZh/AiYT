package com.yiqiao.model;

import java.io.Serializable;

import com.yiqiao.util.MyConstants;

public class Img implements Serializable{
	private int id;
	private String image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return MyConstants.Test_SERVICE_IP+image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
