package com.isss.facerc.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eagle.WeChatRobot.service.WXMegsService;
import com.isss.facerc.service.PhoneControllerService;
import com.isss.facerc.util.HttpUtil;
import com.isss.liuh.vo.AllowLicense;
import com.isss.liuh.vo.FacePepleInfo;
import com.isss.liuh.vo.PhoneMeg;
import com.isss.weixin.pojo.ControllerResult;

import net.sf.json.JSONObject;
/**
 * 返回给手机端使用权限
 * @author LiuH
 *
 */
@Controller
@RequestMapping("/IDCard")
public class IDCardSearchController {
	@Autowired PhoneControllerService phoneControService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/search/{userId}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String PhoneC( @PathVariable("userId") String userId  ) {
		 Map<String, String> parameters = new HashMap<String, String>();
		 parameters.put("in_id", userId);
		 parameters.put("submit", "查 询");
		 String soap = HttpUtil.sendPost("https://sp0.baidu.com/5aU_bSa9KgQFm2e88IuM_a/sfzcxgg.duapp.com/index.php", parameters);
		 List<String> list = new ArrayList<String>();  
		 String rgex = "<div class=\"data\">(.*?)</div>";
	        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
	        Matcher m = pattern.matcher(soap);  
	        JSONObject jsonR = new JSONObject();
	        while (m.find()) {  
	            int i = 1;  
	            Pattern p = Pattern.compile("<(.*?)>");
	            Matcher ms = p.matcher(m.group(i));
	            if(ms.find()) {
	            	 list.add("");
	            }else {
	            	list.add(m.group(i));  
	            }
	         
	            
	            i++;  
	       
	        }  
	        jsonR.put("address", list.get(0));
	         jsonR.put("birthday", list.get(1));
	         jsonR.put("gender", list.get(2));
	         jsonR.put("IDCardId", list.get(3));
	         logger.info("移动端查询身份证号信息"+jsonR.toString());
		return jsonR.toString();
	}
	
}