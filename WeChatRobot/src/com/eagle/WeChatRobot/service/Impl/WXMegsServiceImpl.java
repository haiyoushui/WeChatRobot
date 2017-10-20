package com.eagle.WeChatRobot.service.Impl;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.eagle.WeChatRobot.manager.WxMegContent;
import com.eagle.WeChatRobot.service.WXMegsService;

import com.eagle.WeChatRobot.utils.PubInfo;
import com.eagle.WeChatRobot.utils.QqFaceUtil;
import com.isss.weixin.message.resp.MessageUtil;
import com.isss.weixin.message.resp.TextMessage;
import com.isss.weixin.pojo.WXMeg;

@Service
public class WXMegsServiceImpl implements WXMegsService {
	private Logger logger = Logger.getLogger(WXMegsServiceImpl.class);

	/**
	 * 处理微信发来的文本请求
	 */
	@Override
	public String processText(WXMeg wxMeg) {
		logger.info("处理文本消息……");
		String respMessage = null;
		try {
			// // 默认返回的文本消息内容
			String respContent = PubInfo.AbnormalRespons;
			String fromUserName = wxMeg.getFromUserName();
			String toUserName = wxMeg.getToUserName();
			String content = wxMeg.getContent();
			logger.info("收到数据：" + wxMeg.toString());
			if (content != null) {
				if (content.equals("?") || content.equals("？")) {
					respContent = PubInfo.AbnormalRespons;
					respMessage = unifiedResponse(fromUserName, toUserName,
							respContent);
				} else if (QqFaceUtil.isQqFace(content)) {// 微信用户发送的qq表情，回复同样的表情
					respContent = content;
					respMessage = unifiedResponse(fromUserName, toUserName,
							respContent);
				} else {
					if (content.length() >= 2) {// 微信用户发送文本消息时，做出的回复
						WxMegContent wxMegContent = new WxMegContent();
						/** 用户发送正确格式的特殊信息，鉴别信息头来确定操作 */
						respMessage = wxMegContent.identityMeg(content,
								fromUserName, toUserName);

					} else {
						// 回复文本消息
						respContent = PubInfo.OrdinaryRespons;// 一般留言的反馈信息
						respMessage = unifiedResponse(fromUserName, toUserName,
								respContent);
					}
				}
			} else {
				// 回复文本消息
				respContent = PubInfo.AbnormalRespons;// 异常情况的反馈信息
				respMessage = unifiedResponse(fromUserName, toUserName,
						respContent);
			}

		} catch (Exception e) {
			logger.error("Class: TextService, Method: process", e);
			e.printStackTrace();
		}
		return respMessage;
	}

	/**
	 * 信息头部为T或者t 回复详细信息,即通过名字查询职员信息
	 * 
	 * @param meg
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
//	public String megSearchUser(String fromUserName, String toUserName,
//			String meg) throws ClientProtocolException, IOException {
//		Response response = new Response();
//		String respMessage = null;
//		String strResult = response.searchUserResult(fromUserName, meg,PubInfo.CONTACTHTTPURL);
//		JSONObject result = JSONObject.fromObject(strResult);
//		String success = result.getString("success");
//		if (success == "true" || "true".equals(success)) {
//			// 回复图文消息
//			NewsMsg newsMsg = new NewsMsg();
//			newsMsg.setToUserName(fromUserName);
//			newsMsg.setFromUserName(toUserName);
//			newsMsg.setCreateTime(new Date().getTime() + "");
//			newsMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//			List<Article> articles = new ArrayList<Article>();
//			JSONArray dataList = result.getJSONArray("data");// 可能为多条数据
//			newsMsg.setArticleCount(dataList.size());
//			for (int i = 0; i < dataList.size(); i++) {
//				Article article = new Article();
//				JSONObject data = (JSONObject) dataList.get(i);
//				String description = "姓名：" + data.getString("fullName") + "\n"
//						+ "手机：" + data.getString("mobilePhone") + "\n" + "邮箱："
//						+ data.getString("email") + "\n" + "微信："
//						+ data.getString("weChat");
//				article.setTitle(data.getString("fullName") + "的相关信息");
//				article.setDescription(description);
//				logger.info(data.getString("headImageUrl"));
//				if (null != data.getString("headImageUrl")
//						&& !"".equals(data.getString("headImageUrl"))) {
//					article.setPicUrl(data.getString("headImageUrl"));
//				} else {
//					article.setPicUrl(PubInfo.HEADIMAGEURL);
//				}
//
//				articles.add(article);
//			}
//			newsMsg.setArticles(articles);
//			respMessage = MessageUtil.newsMessageToXml(newsMsg);
//			logger.info("获取人物信息！");
//		} else {
//			// 回复异常查询结果
//			respMessage = unifiedResponse(fromUserName, toUserName,
//					result.getString("msg"));
//		}
//
//		logger.info(respMessage);
//		return respMessage;
//	}

	/**
	 * 统一回复的文本格式信息（XML）
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param news
	 * @return
	 */
	public String unifiedResponse(String fromUserName, String toUserName,
			String renews) {
		logger.info("返回文本信息_：" + renews+fromUserName+toUserName);
		String respMessage = null;
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setFromUserName(toUserName);
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(renews);
		respMessage = MessageUtil.textMessageToXml(textMessage);
		return respMessage;
	}
}
