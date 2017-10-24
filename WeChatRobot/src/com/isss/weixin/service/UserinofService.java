package com.isss.weixin.service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.isss.weixin.pojo.PubInfoWeixin;
import com.isss.weixin.pojo.WeChatUser;
import com.isss.weixin.pojo.WeixinOauth2Token;
import com.isss.weixin.util.WeixinUtil;

import java.util.List;
  
/** 
 * 扫描就获取用户信息 
 *  
 * @param accessToken 
 *            接口访问凭证 
 * @param openId 
 *            用户标识 
 * @return WeixinUserInfo 
 */  
@Service  
public class UserinofService {  
      
      
	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId 公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public WeixinOauth2Token getOauth2AccessToken( String code) {
		/**
		 * 欢迎大家关注我的博客，如有疑问，请加qq群：454796847、135430763 共同进步！
    	也可以浏览我的博客，左侧有支付宝和微信的捐款二维码！
		 */
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", PubInfoWeixin.APPID);
		requestUrl = requestUrl.replace("SECRET", PubInfoWeixin.APPSECRET);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			}
		}
		return wat;
	}
	
	/**
	 * 通过网页授权获取用户信息
	 * @param accessToken 网页授权接口调用凭证
	 * @param openId 用户标识
	 * @return WeChatUser
	 */
	@SuppressWarnings( { "deprecation", "unchecked" })
	public WeChatUser getSNSUserInfo(String accessToken, String openId) {
		WeChatUser wcu = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				wcu = new WeChatUser();
				// 用户的标识
				wcu.setOpenId(jsonObject.getString("openid"));
				// 昵称
				wcu.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				wcu.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				wcu.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				wcu.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				wcu.setCity(jsonObject.getString("city"));
				// 用户头像
				wcu.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				wcu.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			}
		}
		return wcu;
	}
}  