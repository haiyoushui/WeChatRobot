package com.isss.facerc.dao;


import org.apache.ibatis.annotations.Param;

import com.isss.liuh.vo.AllowLicense;
import com.isss.liuh.vo.PhoneMeg;

public interface PhoneControllerDao {

	public AllowLicense searchAllowLicense(@Param("IMEI") String IMEI);
	public  int addAllowLicense( AllowLicense allowLicense);
	public  int updataAllowLicense( AllowLicense allowLicense);


	public PhoneMeg searchPhoneMeg(@Param("IMEI") String IMEI);
	public  int addPhoneMeg( PhoneMeg phoneMeg);
	public  int updataPhoneMeg( PhoneMeg phoneMeg);
}
