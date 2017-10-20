package com.isss.weixin.vo;

import org.liufeng.weixin.util.WeixinUtil;

import com.isss.weixin.pojo.AccessToken;

public class PubInfoWeixin {
	public static String APPID = "wx8e56f69c2328051c";
	
	public static String APPSECRET = "e4a06df0ca831e40ae63d691bc6fc8d2";
	// 获取access_token的接口地址（GET） 限200（次/天）   
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	
	private static  AccessToken accessToken = null;
	
	// 菜单创建（POST） 限100（次/天）   
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	
	public static String code_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	
	
	
	
	
	
	
	
	
	
	
	public static AccessToken getAccessTocken() {
		if(accessToken == null ) {
			accessToken = WeixinUtil.getAccessToken();
			return accessToken;
		}else if(accessToken.getIsOutTime()) {
			accessToken = WeixinUtil.getAccessToken();
			return accessToken;
		}else {
			return accessToken;
		}
	}
}
