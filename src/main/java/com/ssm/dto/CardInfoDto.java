package com.ssm.dto;

/**
 * 
 * 卡信息 〈功能详细描述〉
 * 
 * @author 16060823
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CardInfoDto {
	/**
	 * 持卡人姓名
	 */
	private String cardHolderName;
	/**
	 * 证件类型
	 */
	private String certType = "01";
	/**
	 * 证件号
	 */
	private String certNo;
	/**
	 * 手机号
	 */
	private String mobileNo;
	/**
	 * 银行卡号
	 */
	private String cardNo;
	/**
	 * 银行卡有效期-过期时间(年)
	 */
	private String expYear;
	/**
	 * 银行卡有效期-过期时间(月)
	 */
	private String expMonth;
	/**
	 * 银行卡校验值
	 */
	private String cvv;

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public String toString() {
		return "CardInfoDto [cardHolderName=" + cardHolderName + ", certType=" + certType + ", certNo=" + certNo
				+ ", mobileNo=" + mobileNo + ", cardNo=" + cardNo + ", expYear=" + expYear + ", expMonth=" + expMonth
				+ ", cvv=" + cvv + "]";
	}

}
