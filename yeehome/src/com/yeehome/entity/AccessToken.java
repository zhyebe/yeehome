package com.yeehome.entity;

import java.io.Serializable;

public class AccessToken implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String acT;
	private String acToken;
	private String grantType;
	private String appid;
	private String secret;
	public AccessToken() {
		// TODO Auto-generated constructor stub
	}
	public AccessToken(String acToken, String grantType, String appid,
			String secret) {
		super();
		AccessToken.acT=acToken;
		this.acToken = acToken;
		this.grantType = grantType;
		this.appid = appid;
		this.secret = secret;
	}
	public String getAcToken() {
		return acToken;
	}
	public void setAcToken(String acToken) {
		AccessToken.acT=acToken;
		this.acToken = acToken;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
