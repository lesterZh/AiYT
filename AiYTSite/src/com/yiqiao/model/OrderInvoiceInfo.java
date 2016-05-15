package com.yiqiao.model;

import java.io.Serializable;

//发货信息
public class OrderInvoiceInfo implements Serializable{
	private String consumerName;//客户姓名
	private String tel;//电话
	private String address;//地址
	
	private String deliverTime;//发货时间
	private String receiveTime;//收货时间
	private String logisticsCompany;//物流公司
	private String logisticsSerial;//物流单号
	
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	public String getLogisticsCompany() {
		return logisticsCompany;
	}
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	public String getLogisticsSerial() {
		return logisticsSerial;
	}
	public void setLogisticsSerial(String logisticsSerial) {
		this.logisticsSerial = logisticsSerial;
	}
	
	
}
