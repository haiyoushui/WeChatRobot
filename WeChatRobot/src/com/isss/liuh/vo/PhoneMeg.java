package com.isss.liuh.vo;

public class PhoneMeg {
	private String IMEI;
	private String Time;
	private String Version;
	private String Remark;
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	@Override
	public String toString() {
		return "PhoneMeg [IMEI=" + IMEI + ", Time=" + Time + ", Version=" + Version + ", Remark=" + Remark + "]";
	}
	
}
