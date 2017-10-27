package com.isss.facerc.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Utils {
	private final static Logger logger = LoggerFactory.getLogger(Utils.class);
	/**
	 * 获取IMEI<br>
	 * 获取不到则返回空<br>
	 * @param request
	 * @return IMEI
	 */
	public static String getIMEI(HttpServletRequest request) {
		String IMEI = null;
		try {
			IMEI = request.getHeader("IMEI");
		} catch (Exception e) {
			logger.error("从HttpServletRequest获取IMEI失败！" + e.getMessage());
		}
		if (IMEI == null||"".equals(IMEI)) {
			IMEI = "PC_UPLOAD"; // 电脑端
			logger.info("未获取到IMEI, 返回PC上传！");
		}
		return IMEI;
	}
}
