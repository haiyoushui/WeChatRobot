package com.eagle.WeChatRobot.manager;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.eagle.WeChatRobot.httpResponse.Response;
import com.eagle.WeChatRobot.service.Impl.WXMegsServiceImpl;
import com.eagle.WeChatRobot.utils.PubInfo;
import com.eagle.WeChatRobot.utils.SystemUtil;

public class WxMegContent {
	private Logger logger = Logger.getLogger(WXMegsServiceImpl.class);
	private Response response;
	private String respMessage = null;
	private String respMessageForT;// 特殊信息为 T的返回字符串,即通过名字查询职员信息
	private String respMessageForP;// 特殊信息为 I的返回字符串,即发送来的是身份证号
	private String sta = "NOWXFROMUSERNAME";// 透过json中meg的内容来判断失败的原因,没有微信的FromUserName,如果没有获得查询目标信息返回data:NORESULT
    private WXMegsServiceImpl wXMegsServiceImpl;
	/**
	 * 通过判断用户发送来的特殊信息的头部，来分别执行不同方法
	 * 
	 * @param content
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String identityMeg(String content, String fromUserName,
			String toUserName) throws ClientProtocolException, IOException {
		String sign = content.substring(0, 1);
		String meg = content.substring(1, content.length());
		wXMegsServiceImpl = new WXMegsServiceImpl();
		logger.info("获得的特殊标志字母为：" + sign);
		// 信息头部为 T或t,即通过名字查询职员信息
		if ("T".equals(sign) || "t".equals(sign)) {
			respMessage = isTstart(fromUserName, toUserName, meg);
		}
		// 信息头部为 P或p,即通过身份证号识别是否为内部人员
		else if ("P".equals(sign) || "p".equals(sign) || "P" == sign || "p" == sign) {
			respMessage = isPstart(fromUserName, toUserName, meg);
		}	
		else{
			respMessage = wXMegsServiceImpl.unifiedResponse(fromUserName,
					toUserName, PubInfo.OrdinaryRespons);// 非特殊字符的返回统一消息
		}
		return respMessage;

	}

	/**
	 * 如果用户发送来的特殊信息，开头为 T或t,即通过名字查询职员信息
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param meg
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String isTstart(String fromUserName, String toUserName, String name)
			throws ClientProtocolException, IOException {
		wXMegsServiceImpl = new WXMegsServiceImpl();
		response = new Response();
		String strResult = null;
		if (SystemUtil.isLetter(name)) {// 发送的名字为拼音
			strResult = response.searchUserResult(fromUserName, name,
					PubInfo.CONTACTHTTPURLBYPINYIN);
		} else {// 发送的名字为汉子
			strResult = response.searchUserResult(fromUserName, name,
					PubInfo.CONTACTHTTPURL);
		}
		try {
			JSONObject result = JSONObject.fromObject(strResult);
			String success = result.getString("success");
			if (success == "true" || "true".equals(success)) {
				JSONArray dataList = result.getJSONArray("data");
				String description = "";
				for (int i = 0; i < dataList.size(); i++) {
					JSONObject data = (JSONObject) dataList.get(i);
					description = description + "姓名："
							+ data.getString("fullName") + "\n" + "手机："
							+ data.getString("mobilePhone") + "\n" + "邮箱："
							+ JsonIsNull(data) + "\n";
				}
				respMessageForT = wXMegsServiceImpl.unifiedResponse(
						fromUserName, toUserName, description);// 返回查询的人员信息
				logger.info("获取人物信息！");
			} else {
				String flage = result.getString("msg");
				if (flage == sta || flage.equals(sta)) {
					respMessageForT = wXMegsServiceImpl.unifiedResponse(
							fromUserName, toUserName, PubInfo.NOTHISWXNAME);// 未登记的fromUserName让其发生身份证号
					logger.info("发送身份证号以验真身！");
				} else {
					logger.info("不存在此名字的人物信息！");
					respMessageForT = wXMegsServiceImpl.unifiedResponse(
							fromUserName, toUserName, PubInfo.AbnormalRespons);// 未找到要搜索人员的信息
				}

			}
		} catch (Exception e) {
			logger.info("连接通讯录系统异常！");
			respMessageForT = wXMegsServiceImpl.unifiedResponse(fromUserName,
					toUserName, PubInfo.AbnormalRespons);// 连接通讯录系统异常！未返回结果
		}
		return respMessageForT;

	}

	/**
	 * 用户发送以P开头的特殊信息，后面为身份证号
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param idNumber
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String isPstart(String fromUserName, String toUserName,
			String idNumber) throws ClientProtocolException, IOException {
		response = new Response();
		wXMegsServiceImpl = new WXMegsServiceImpl();

		try {
			if (idNumber.length() == 18) {
				// 身份证号符合规定的
				String strResult = response.hasidentity(fromUserName, idNumber);
				JSONObject result = JSONObject.fromObject(strResult);
				String success = result.getString("success");
				if (success == "true" || "true".equals(success)) {
					// 此身份证号存在，则根据保存在megStorge中的name来查询，此名字人员的信息
					JSONObject data = result.getJSONObject("data");
					logger.info(data);
					String description = "姓名：" + data.getString("fullName")
							+ "\n" + "手机：" + data.getString("mobilePhone")
							+ "\n" + "邮箱：" + JsonIsNull(data);
					logger.info("获取此身份证号的人物信息！");
					respMessageForP = wXMegsServiceImpl.unifiedResponse(
							fromUserName, toUserName, description);// 返回此身份证号的人员信息
				} else if (success == "false" || "false".equals(success)) {
					respMessageForP = wXMegsServiceImpl.unifiedResponse(
							fromUserName, toUserName, PubInfo.AbnormalRespons);// 发送的身份证号不是内部人员的
				} else {
					respMessageForP = wXMegsServiceImpl.unifiedResponse(
							fromUserName, toUserName, PubInfo.AbnormalRespons);// 通讯录系统服务器异常
				}

			} else {
				respMessageForP = wXMegsServiceImpl.unifiedResponse(
						fromUserName, toUserName, PubInfo.AbnormalRespons);// 发送的身份证号不符合规定
			}

		} catch (Exception e) {
			respMessageForP = wXMegsServiceImpl.unifiedResponse(fromUserName,
					toUserName, PubInfo.AbnormalRespons);//
		}
		return respMessageForP;
	}

	/**
	 * 判断Json中是否含有此Key值得数据， 接着判断数据是否为空
	 * 
	 * @param data
	 * @param string
	 */
	public String JsonIsNull(JSONObject data) {
		String resultdate = "";
		if (data.containsKey("email")) {
			if (data.getString("email") != null
					& !data.getString("email").equals("")) {
				resultdate = data.getString("email");
			}
		}
		if (data.containsKey("insideMailbox")) {
			if (data.getString("insideMailbox") != null
					& !data.getString("insideMailbox").equals("")) {
				resultdate = resultdate + " ; "
						+ data.getString("insideMailbox");
			}
		}
		return resultdate;

	}
}
