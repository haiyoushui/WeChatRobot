package com.isss.weixin.util;

import com.isss.weixin.pojo.AccessToken;

public class PubInfoWeixin {
/*	public static String APPID = "wxd1b59b467c53b51e";
	
	public static String APPSECRET = "15b0bc9c663a292f66f198e0d0549344";*/
	// 获取access_token的接口地址（GET） 限200（次/天）   
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	
	private static  AccessToken accessToken = null;
	
	// 菜单创建（POST） 限100（次/天）   
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	
	public static String code_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	//获取网页授权凭证
	public static String getOauth2AccessToken =  "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	//通过网页授权获取用户信息
	public static String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	
	public static String getUserInfo2 = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	
	
	
	
	
	
	public static AccessToken getAccessTocken(String APPID,String APPSECRET) {
		if(accessToken == null ) {
			accessToken = WeixinUtil.getAccessToken( APPID, APPSECRET);
			return accessToken;
		}else if(accessToken.getIsOutTime()) {
			accessToken = WeixinUtil.getAccessToken( APPID, APPSECRET);
			return accessToken;
		}else {
			return accessToken;
		}
	}
}
