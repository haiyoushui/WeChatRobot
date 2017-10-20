package com.isss.weixin.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.isss.weixin.pojo.WeChatUser;
import com.isss.weixin.pojo.WeixinOauth2Token;
import com.isss.weixin.service.UserinofService;



@Controller
@RequestMapping("/WeXinUserInfo")
public class WeXinUserInfoController {
	 UserinofService userinfoService = new UserinofService();
	/** 
     * 技术人员任务判断 
     *  
     * @param session 
     * @param model 
     * @param code 
     */  
	@RequestMapping(value = "/userinfo/user", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public void check(HttpSession session, Map<String, Object> model,HttpServletRequest request,HttpServletResponse response) {  
        String code = request.getParameter("code");//我们要的code  
        try {  
     		if (!"authdeny".equals(code)) {
    			// 获取网页授权access_token
    			WeixinOauth2Token weixinOauth2Token = userinfoService.getOauth2AccessToken(code);
    			if(null == weixinOauth2Token)
    				return;
    			// 网页授权接口访问凭证
    			String accessToken = weixinOauth2Token.getAccessToken();
    			// 用户标识
    			String openId = weixinOauth2Token.getOpenId();
    			// 获取用户信息
    			WeChatUser wcu = userinfoService.getSNSUserInfo(accessToken, openId);// 设置要传递的参数
    		    request.setAttribute("wcu", wcu);
    			  System.out.println("@@###调通"+wcu.toString());
     		}
     		// 跳转到index.jsp
     		request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
      

    }  


	}  