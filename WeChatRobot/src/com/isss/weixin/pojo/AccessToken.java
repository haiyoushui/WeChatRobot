package com.isss.weixin.pojo;

import java.util.Date;

/**
 * 微信通用接口凭证
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;


	private long outTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
		Date date = new Date();
		outTime = date.getTime()+(expiresIn-1)*1000;
		
	}
	public boolean getIsOutTime() {
		Date date = new Date();
		if(outTime > date.getTime()) {
			return false;
		}else {
			return true;//超出有效时间
		}
	}
}