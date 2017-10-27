package com.isss.facerc.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eagle.WeChatRobot.service.WXMegsService;
import com.isss.facerc.service.PhoneControllerService;
import com.isss.liuh.vo.AllowLicense;
import com.isss.liuh.vo.FacePepleInfo;
import com.isss.liuh.vo.PhoneMeg;
import com.isss.weixin.pojo.ControllerResult;
/**
 * 返回给手机端使用权限
 * @author LiuH
 *
 */
@Controller
@RequestMapping("/PhoneC")
public class PhoneController {
	@Autowired PhoneControllerService phoneControService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/IsAllow", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ControllerResult<AllowLicense> PhoneC( @RequestBody Map<String,String> params   ) {
		 logger.info("移动端请求使用权限"+params.toString());
		PhoneMeg phoneMeg = new PhoneMeg();
		phoneMeg.setIMEI(params.get("IMEI"));
		phoneMeg.setTime(params.get("Time"));
		phoneMeg.setRemark(params.get("Remark"));
		phoneMeg.setVersion(params.get("Version"));
		logger.info("接收数据"+phoneMeg.toString());
		AllowLicense allowLicense = phoneControService.phoneIsAllow(phoneMeg);
		ControllerResult<AllowLicense> result = new ControllerResult<AllowLicense>(true,"成功");
		if(allowLicense == null) {
			result.setSuccess(false);
			result.setMsg("时间异常");
			
		}else {
			result.setData(allowLicense);
			result.setSuccess(true);
			result.setMsg("成功");
		}
		 logger.info("返回给移动端使用权限"+result.toString());
		return result;
	}
	
}