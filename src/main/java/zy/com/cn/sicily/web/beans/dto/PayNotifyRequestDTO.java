package zy.com.cn.sicily.web.beans.dto;

import java.io.Serializable;

/**
 * @Title: PayNotifyRequestDTO.java
 * @Description: 支付回调通知请求参数
 * @version V1.0
 */
public class PayNotifyRequestDTO implements Serializable{

	private static final long serialVersionUID = -5104116865545063942L;
	/**
	 * 通知xml报文
	 */
	private String notifyXml;

	public String getNotifyXml() {
		return notifyXml;
	}

	public void setNotifyXml(String notifyXml) {
		this.notifyXml = notifyXml;
	}

	@Override
	public String toString() {
		return "PayNotifyRequestDTO [notifyXml=" + notifyXml + "]";
	}
	
}
