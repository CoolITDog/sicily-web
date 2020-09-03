package zy.com.cn.sicily.web.beans.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import zy.com.cn.sicily.web.beans.annotate.JsonAutoDetect;
import zy.com.cn.sicily.web.beans.annotate.JsonMethod;

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

	@JsonProperty("openid")
	private String openid;

	@JsonProperty("session_key")
	@JSONField(name="session_key")
	private String sessionKey;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@Override
	public String toString() {
		return "JsCodeSessionDTO{" +
				"openid='" + openid + '\'' +
				", sessionKey='" + sessionKey + '\'' +
				'}';
	}
}
