package com.isss.liuh.vo;

public class AllowLicense {
	private int isAllow;
	private String startTime;
	private String endTime;
	private String IMEI;
	private String function1;
	private String function2;
	private String function3;
	private String function4;
	private String function5;
	private String function6;
	private String function7;
	private String function8;
	private String function9;
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
	
	public String getFunction1() {
		return function1;
	}
	public void setFunction1(String function1) {
		this.function1 = function1;
	}
	public String getFunction2() {
		return function2;
	}
	public void setFunction2(String function2) {
		this.function2 = function2;
	}
	public String getFunction3() {
		return function3;
	}
	public void setFunction3(String function3) {
		this.function3 = function3;
	}
	public String getFunction4() {
		return function4;
	}
	public void setFunction4(String function4) {
		this.function4 = function4;
	}
	public String getFunction5() {
		return function5;
	}
	public void setFunction5(String function5) {
		this.function5 = function5;
	}
	public String getFunction6() {
		return function6;
	}
	public void setFunction6(String function6) {
		this.function6 = function6;
	}
	public String getFunction7() {
		return function7;
	}
	public void setFunction7(String function7) {
		this.function7 = function7;
	}
	public String getFunction8() {
		return function8;
	}
	public void setFunction8(String function8) {
		this.function8 = function8;
	}
	public String getFunction9() {
		return function9;
	}
	public void setFunction9(String function9) {
		this.function9 = function9;
	}
	@Override
	public String toString() {
		return "AllowLicense [isAllow=" + isAllow + ", startTime=" + startTime + ", endTime=" + endTime + ", IMEI="
				+ IMEI + ", remark=" + remark + "]";
	}
	
}
