package com.isss.liuh.vo;

public class AllowLicense {
	private int isAllow;
	private String startTime;
	private String endTime;
	private String IMEI;
	private String remark;
	public int getIsAllow() {
		return isAllow;
	}
	public void setIsAllow(int isAllow) {
		this.isAllow = isAllow;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String string) {
		this.startTime = string;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "AllowLicense [isAllow=" + isAllow + ", startTime=" + startTime + ", endTime=" + endTime + ", IMEI="
				+ IMEI + ", remark=" + remark + "]";
	}
	
}
