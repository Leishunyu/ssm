package com.ssm.entity;

public class Yfb {
	private int id;
	private String name;
	private String orderNo;
	private String cardNo;
	private String amount;
	private String remark;
	private Long status;
	private String orderTime;
	private String bankCode;
	private String cardInfo;
	private String mobile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Yfb() {
		super();
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 *
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 *
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 *
	 */
	public String getCardInfo() {
		return cardInfo;
	}

	/**
	 *
	 */
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

}
