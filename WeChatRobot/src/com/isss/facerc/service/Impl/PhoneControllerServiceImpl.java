package com.isss.facerc.service.Impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isss.facerc.dao.PhoneControllerDao;
import com.isss.facerc.service.PhoneControllerService;
import com.isss.liuh.vo.AllowLicense;
import com.isss.liuh.vo.PhoneMeg;
import com.isss.weixin.util.DateUtils;


@Service
public class PhoneControllerServiceImpl implements PhoneControllerService {

	@Autowired
	private PhoneControllerDao phoneControllerDao;
	private Logger logger = Logger.getLogger(PhoneControllerServiceImpl.class);

	@Override
	public AllowLicense phoneIsAllow(PhoneMeg phoneMeg) {
		System.out.println(phoneMeg.toString());
		AllowLicense allowLicenseR = null;
		if(!DateUtils.Is30Minute(phoneMeg.getTime())) {
			System.out.println("时间异常！");
			return null;
		}else {
			int status = 0;
			PhoneMeg phoneMegR = phoneControllerDao.searchPhoneMeg(phoneMeg.getIMEI());
			if(phoneMegR == null) {
				phoneControllerDao.addPhoneMeg(phoneMeg);
				AllowLicense allowLicense = new AllowLicense();
				allowLicense.setIMEI(phoneMeg.getIMEI());
				allowLicense.setStartTime(phoneMeg.getTime());
				allowLicense.setEndTime(DateUtils.getCurrentDateTimeStr2(DateUtils.parseDateTime(phoneMeg.getTime()).getTime()+300*24*60*60*1000));
				allowLicense.setRemark(phoneMeg.getTime());
				int stateLicense = phoneControllerDao.addAllowLicense(allowLicense);
				int statePhoneMeg = phoneControllerDao.addPhoneMeg(phoneMeg);
				System.out.println("数据库添加手机信息"+statePhoneMeg+"和手机使用权限"+stateLicense);
			}
			allowLicenseR = phoneControllerDao.searchAllowLicense(phoneMeg.getIMEI());
		return allowLicenseR;
	}
	}
}
