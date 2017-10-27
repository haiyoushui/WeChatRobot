package com.isss.liuh.vo;

public class FacePepleInfo {
	private String uid;
	private String groupid;
	private String uname;
	private String gender;
	private String birthday;
	private String uinfo;
	private String address;
	private String idcardaddress;
	private String ethnic;
	private String idcardid;
	private String time;
	private String IMEI;
	private String faceliveness;
	private String picPath;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUinfo() {
		return uinfo;
	}
	public void setUinfo(String uinfo) {
		this.uinfo = uinfo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdcardaddress() {
		return idcardaddress;
	}
	public void setIdcardaddress(String idcardaddress) {
		this.idcardaddress = idcardaddress;
	}
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	public String getIdcardid() {
		return idcardid;
	}
	public void setIdcardid(String idcardid) {
		this.idcardid = idcardid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getFaceliveness() {
		return faceliveness;
	}
	public void setFaceliveness(String faceliveness) {
		this.faceliveness = faceliveness;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Override
	public String toString() {
		return "FaceInfo [uid=" + uid + ", groupid=" + groupid + ", uname=" + uname + ", gender=" + gender
				+ ", birthday=" + birthday + ", uinfo=" + uinfo + ", address=" + address + ", idcardaddress="
				+ idcardaddress + ", ethnic=" + ethnic + ", idcardid=" + idcardid + ", time=" + time + ", IMEI=" + IMEI
				+ ", faceliveness=" + faceliveness + ", picPath=" + picPath + "]";
	}
	
}
