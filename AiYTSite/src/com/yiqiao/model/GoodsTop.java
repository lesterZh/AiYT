package com.yiqiao.model;

import java.io.Serializable;
import java.util.List;

public class GoodsTop implements Serializable{
	private String name;//名字
	private Img img;//图片
	private int salesVolume;//销量
	private List<GoodsChild> goodsChilds;//具体分类信息
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	public int getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}
	public List<GoodsChild> getGoodsChilds() {
		return goodsChilds;
	}
	public void setGoodsChilds(List<GoodsChild> goodsChilds) {
		this.goodsChilds = goodsChilds;
	}
	
	
}
