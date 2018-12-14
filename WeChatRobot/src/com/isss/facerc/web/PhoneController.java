package com.isss.facerc.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.isss.facerc.util.Utils;
import com.isss.liuh.vo.AllowLicense;
import com.isss.liuh.vo.FacePepleInfo;
import com.isss.liuh.vo.PhoneMeg;
import com.isss.weixin.pojo.ControllerResult;
import com.isss.weixin.service.CoreService;
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
	public ControllerResult<AllowLicense> PhoneC( String PhoneModel, String Time,String Remark,String Version,String SysVersion,HttpServletRequest request ) {
		 logger.info("移动端请求使用权限"+PhoneModel+Time+Utils.getIMEI(request));
		PhoneMeg phoneMeg = new PhoneMeg();
		phoneMeg.setIMEI(Utils.getIMEI(request));
		phoneMeg.setTime(Time);
		phoneMeg.setRemark(Remark);
		phoneMeg.setVersion(Version);
		phoneMeg.setRemark(SysVersion);
		phoneMeg.setPhoneModel(PhoneModel);
	

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