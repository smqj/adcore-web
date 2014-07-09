package com.sogou.bizdev.muse.adcoreweb.app.diso;

import java.io.Serializable;

public class AdcoreDo implements Serializable{

	private static final long serialVersionUID = -1496837467114081124L;
	private Long cpcId;
	private Long accountId;
	private String key;
	private String url;
	private String createDate;
	private String chgDate;
	private Integer maxPrice;
	
	public Long getId() {
		return cpcId;
	}
	public void setId(Long cpcId) {
		this.cpcId = cpcId;
	}
	public Long getCpcId() {
		return cpcId;
	}
	public void setCpcId(Long cpcId) {
		this.cpcId = cpcId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getChgDate() {
		return chgDate;
	}
	public void setChgDate(String chgDate) {
		this.chgDate = chgDate;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Adcore [cpcId=" + cpcId + ", accountId=" + accountId + ", key="
				+ key + ", url=" + url + ", createDate=" + createDate
				+ ", chgDate=" + chgDate + ", maxPrice=" + maxPrice + "]";
	}
}
