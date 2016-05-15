package com.yiqiao.model;

import java.io.Serializable;
import java.util.List;

//订单总信息
public class OrderTotalInfo implements Serializable{
	private String orderSeq;//订单号
	private String orderTime;//订单生成时间
	private String orderCancleReason;//订单取消原因
	
	public String getOrderCancleReason() {
		return orderCancleReason;
	}
	public void setOrderCancleReason(String orderCancleReason) {
		this.orderCancleReason = orderCancleReason;
	}
	private int orderState; //订单状态
	private double duesPayMoney;//应付金额
	private double realPayMoney;//实付金额
	
	private List<OrderGoodsInfo> goodsInfosList;
	private OrderInvoiceInfo orderInvoiceInfo;
	
	public List<OrderGoodsInfo> getGoodsInfosList() {
		return goodsInfosList;
	}
	public void setGoodsInfosList(List<OrderGoodsInfo> goodsInfosList) {
		this.goodsInfosList = goodsInfosList;
	}
	public OrderInvoiceInfo getOrderInvoiceInfo() {
		return orderInvoiceInfo;
	}
	public void setOrderInvoiceInfo(OrderInvoiceInfo orderInvoiceInfo) {
		this.orderInvoiceInfo = orderInvoiceInfo;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public double getDuesPayMoney() {
		return duesPayMoney;
	}
	public void setDuesPayMoney(double duesPayMoney) {
		this.duesPayMoney = duesPayMoney;
	}
	public double getRealPayMoney() {
		return realPayMoney;
	}
	public void setRealPayMoney(double realPayMoney) {
		this.realPayMoney = realPayMoney;
	}
	
	
}
