package com.eagle.WeChatRobot.service;

import com.isss.weixin.pojo.WXMeg;

public interface WXMegsService {
	/**
	 * 根据文本内容做相应处理并返回信息
	 * @param wxMeg
	 * @return
	 */
	public String processText(WXMeg wxMeg);
}
	