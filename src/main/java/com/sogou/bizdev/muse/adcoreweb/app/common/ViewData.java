package com.sogou.bizdev.muse.adcoreweb.app.common;

import java.io.Serializable;
import java.util.List;

public class ViewData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2885789031677222198L;

	private Integer type;
	private String msg;
	private Object data;
	private Long total;
	public ViewData(Integer type, String msg, Object data, Long total) {
		super();
		if(null == type || null == TypeEnum.parse(type)) {
			throw new IllegalArgumentException("invalid type. " + type);
		}
		this.type = type;
		this.msg = msg;
		this.data = data;
		this.total = total;
	}
	public ViewData() {
		super();
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		if(null == type || null == TypeEnum.parse(type)) {
			throw new IllegalArgumentException("invalid type. " + type);
		}

		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	public enum TypeEnum {
		NORMAL("正常结果"),
		ERROR("错误结果"),
		NEED_LOGIN("未登陆"),
		NO_AUTH("无权限");
		private String text;
		private TypeEnum(String text) {
			this.text = text;
		}
		public String getText() {
			return text;
		}
		public static TypeEnum parse(int i) {
			return TypeEnum.values()[i];
		}
	}	
	
	public String toJson() {
		String dataFormat = "{\"flag\":\"${1}\",\"msg\":${2},\"rows\":${3},\"total\":${4}}";
		dataFormat = dataFormat.replace("${1}", type.toString());
		dataFormat = dataFormat.replace("${2}", null != msg ? JSONUtils.toJsonString(msg) : "[]");
		dataFormat = dataFormat.replace("${3}", null == data ? "[]" : JSONUtils.toJsonString(data));
		dataFormat = dataFormat.replace("${4}", total == null ? "[]" : JSONUtils.toJsonString(total));
		return dataFormat;
	}
	
}
