/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: SingleWithholdPayOrder.java
 * Author:   12061721
 * Date:     2016-8-2 下午04:33:12
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.ssm.dto;

import java.io.Serializable;

/**
 * 
 * 跨行单笔收支付订单 DTO 〈功能详细描述〉
 * 
 * @author 16060823
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PaymentOrderDto implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -1977137213097841755L;

	/**
	 * 系统接入方
	 */
	private String merchantNo = "70182424";

	/**
	 * 公钥索引
	 */
	private String publicKeyIndex = "0001";

	/**
	 * 接口版本号
	 */
	private String version = "2.0";

	/**
	 * 签名
	 */
	private String signature;

	/**
	 * 签名算法
	 */
	private String signAlgorithm = "RSA";

	/**
	 * 编码类型
	 */
	private String inputCharset = "UTF-8";

	/**
	 * 提交时间 yyyyMMddHHmmss
	 */
	private String submitTime;

	/**
	 * 银行编码
	 */
	private String bankCode;

	/**
	 * 银行卡类型
	 */
	private String cardType = "1";

	/**
	 * 银行卡信息
	 */
	private String cardInfo;

	/**
	 * 商品订单号
	 */
	private String outOrderNo;

	/**
	 * 订单类型
	 */
	private String orderType = "01";

	/**
	 * 订单金额 (分)
	 */
	private String orderAmount;

	/**
	 * 下单时间 yyyyMMddHHmmss
	 */
	private String orderTime;

	/**
	 * 币种
	 */
	private String currency = "CNY";

	/**
	 * 卖家商户号（企业专有）
	 */
	private String salerMerchantNo;

	/**
	 * 商品类型
	 */
	private String goodsType = "521405";

	/**
	 * 商品名称 base64
	 */
	private String goodsName;

	/**
	 * 分润账号集 base64
	 */
	private String royaltyParameters;

	/**
	 * 订单有效期
	 */
	private String payTimeout;

	/**
	 * 隧道字段 base64
	 */
	private String tunnelData;

	/**
	 * 备注 base64
	 */
	private String remark;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getPublicKeyIndex() {
		return publicKeyIndex;
	}

	public void setPublicKeyIndex(String publicKeyIndex) {
		this.publicKeyIndex = publicKeyIndex;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignAlgorithm() {
		return signAlgorithm;
	}

	public void setSignAlgorithm(String signAlgorithm) {
		this.signAlgorithm = signAlgorithm;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	public String getOutOrderNo() {
		return outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSalerMerchantNo() {
		return salerMerchantNo;
	}

	public void setSalerMerchantNo(String salerMerchantNo) {
		this.salerMerchantNo = salerMerchantNo;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getRoyaltyParameters() {
		return royaltyParameters;
	}

	public void setRoyaltyParameters(String royaltyParameters) {
		this.royaltyParameters = royaltyParameters;
	}

	public String getPayTimeout() {
		return payTimeout;
	}

	public void setPayTimeout(String payTimeout) {
		this.payTimeout = payTimeout;
	}

	public String getTunnelData() {
		return tunnelData;
	}

	public void setTunnelData(String tunnelData) {
		this.tunnelData = tunnelData;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
