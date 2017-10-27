package com.isss.facerc.service;

import com.isss.liuh.vo.AllowLicense;
import com.isss.liuh.vo.PhoneMeg;

public interface PhoneControllerService {
	/**
	 * 根据表情况反馈是否允许此手机使用
	 * @param wxMeg
	 * @return
	 */
	public AllowLicense phoneIsAllow(PhoneMeg phoneMeg);
}
	