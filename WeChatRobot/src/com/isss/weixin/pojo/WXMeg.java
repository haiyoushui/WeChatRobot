package com.isss.weixin.pojo;

public class WXMeg {
	private String fromUserName;// 信息发送者的微信号
	private String toUserName;// 信息接收者的微信号
	private String content;// 信息内容
	private String sysflag;// 标记

	public String getSysflag() {
		return sysflag;
	}

	public void setSysflag(String sysflag) {
		this.sysflag = sysflag;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "WXMeg [fromUserName=" + fromUserName + ", toUserName="
				+ toUserName + ", content=" + content + ", sysflag=" + sysflag
				+ "]";
	}
}
