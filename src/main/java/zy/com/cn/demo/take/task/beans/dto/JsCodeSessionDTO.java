package zy.com.cn.demo.take.task.beans.dto;

import java.io.Serializable;

/**
 * @Title: JsCodeSessionDTO.java
 * @Description:
 * @author Alvin.zengqi  
 * @date 2018年3月5日 上午11:48:09
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */

public class JsCodeSessionDTO implements Serializable {
	
	private static final long serialVersionUID = 5543145431827278104L;

	private String openId;
	
	private String sessionKey;
	
	private String unionId;

	private Integer errCode;

	private String errMsg;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "JsCodeSessionDTO{" +
				"openId='" + openId + '\'' +
				", sessionKey='" + sessionKey + '\'' +
				", unionId='" + unionId + '\'' +
				", errCode=" + errCode +
				", errMsg='" + errMsg + '\'' +
				'}';
	}

}
