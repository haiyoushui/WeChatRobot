package com.isss.weixin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eagle.WeChatRobot.service.WXMegsService;
import com.isss.weixin.pojo.WXMeg;

@Controller
@RequestMapping("/weChatManager")
public class WeChatController {
	@Autowired WXMegsService wxMegsService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/wxText", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String weChat(WXMeg wxMeg) {
		logger.info(wxMeg.getSysflag());
//		wxMeg.setContent("Tli");
//		wxMeg.setFromUserName("lr");
//		wxMeg.setToUserName("lr");
		String wxXML = wxMegsService.processText(wxMeg);
		logger.info(wxXML);
		return wxXML;
	}
	
}
