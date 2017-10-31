package com.isss.liuh.vo;

public class AllowLicense {
	private int isAllow;
	private String startTime;
	private String endTime;
	private String IMEI;
	private int function1;
	private int function2;
	private int function3;
	private int function4;
	private int function5;
	private int function6;
	private int function7;
	private int function8;
	private int function9;
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
	
	public int getFunction1() {
		return function1;
	}
	public void setFunction1(int function1) {
		this.function1 = function1;
	}
	public int getFunction2() {
		return function2;
	}
	public void setFunction2(int function2) {
		this.function2 = function2;
	}
	public int getFunction3() {
		return function3;
	}
	public void setFunction3(int function3) {
		this.function3 = function3;
	}
	public int getFunction4() {
		return function4;
	}
	public void setFunction4(int function4) {
		this.function4 = function4;
	}
	public int getFunction5() {
		return function5;
	}
	public void setFunction5(int function5) {
		this.function5 = function5;
	}
	public int getFunction6() {
		return function6;
	}
	public void setFunction6(int function6) {
		this.function6 = function6;
	}
	public int getFunction7() {
		return function7;
	}
	public void setFunction7(int function7) {
		this.function7 = function7;
	}
	public int getFunction8() {
		return function8;
	}
	public void setFunction8(int function8) {
		this.function8 = function8;
	}
	public int getFunction9() {
		return function9;
	}
	public void setFunction9(int function9) {
		this.function9 = function9;
	}
	@Override
	public String toString() {
		return "AllowLicense [isAllow=" + isAllow + ", startTime=" + startTime + ", endTime=" + endTime + ", IMEI="
				+ IMEI + ", remark=" + remark + "]";
	}
	
}
