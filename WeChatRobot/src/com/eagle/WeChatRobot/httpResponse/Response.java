package com.eagle.WeChatRobot.httpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.eagle.WeChatRobot.service.Impl.WXMegsServiceImpl;
import com.eagle.WeChatRobot.utils.HttpServletUtil;
import com.eagle.WeChatRobot.utils.PubInfo;

/**
 * 判断是否为已登记微信fromUserName,根据发送的信息名字查找相关人员信息
 * 若存在则返回查询结果
 * @author lh
 * 
 */
public class Response {
	private Logger logger = Logger.getLogger(Response.class);
	public String searchUserResult(String fromUserName, String name, String url){
		String strResult = null;
		logger.info(fromUserName);
		Map<String, String> mapParam = new HashMap<String, String>();
		mapParam.put("wxFromUserName", fromUserName);
		mapParam.put("name", name);
		try {
			strResult = HttpServletUtil.sendHttpPost(url,
					mapParam);
		} catch (Exception e) {
			strResult = null;//连接通讯录系统异常
			e.printStackTrace();
		} 
		logger.info(strResult);
		return strResult;
	}

    /**
     * 判断此身份证号是否存在，存在则保存wxfromUserName,不存在则为外部人员
     * @param fromUserName
     * @param idNumber
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	public String hasidentity(String fromUserName,String idNumber){
		    String strResult = null;
		    logger.info(fromUserName);
			Map<String, String> mapParam = new HashMap<String, String>();
			mapParam.put("wxFromUserName", fromUserName);
			mapParam.put("idNumber", idNumber); 
			try {
				strResult = HttpServletUtil.sendHttpPost(PubInfo.ISINTERNALLHTTPURL,
						mapParam);
			} catch (Exception e) {
				strResult = null;//连接通讯录系统异常
				e.printStackTrace();
			} 
			logger.info(strResult);
			return strResult;			
		}
}
