package com.isss.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isss.facerc.dao.FacePepleInfoDao;
import com.isss.facerc.service.Impl.FacePepleInfoServiceImpl;
import com.isss.facerc.util.HttpUtil;
import com.isss.liuh.vo.WeChatUser;
import com.isss.liuh.vo.WechatAndSurvey;
import com.isss.sampleandid.dao.QRcodeSampleAndIdDao;
import com.isss.sampleandid.util.UserInfoUtil;
import com.isss.weixin.message.resp.Article;
import com.isss.weixin.message.resp.MessageUtil;
import com.isss.weixin.message.resp.NewsMessage;
import com.isss.weixin.message.resp.TextMessage;
import com.isss.weixin.pojo.WeixinOauth2Token;
import com.isss.weixin.service.CoreService;
import com.isss.weixin.util.PubInfoWeixin;
import com.isss.weixin.util.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * 核心服务类
 * 
 * @author liu
 * @date 2013-07-25
 */
@Service
public class CoreServiceImpl implements CoreService{
	@Autowired
	private  QRcodeSampleAndIdDao qRcodeSampleAndIdDao;
	private Logger logger = Logger.getLogger(CoreServiceImpl.class);
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public  String processRequest(Map<String, String>  requestMap,String paramed) {
		String normaolrespMessage = null;
		String respMessage = null;
		String sampleID = null;
		String surveyID = null;
		try {
			// xml请求解析
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 文本消息
			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义			
			StringBuffer contentMsg = new StringBuffer();  
			String reminder = getReminder(toUserName,paramed);//提示语	
			String[] splitS = reminder.split("<br>");
			for(String str:splitS) {
				contentMsg.append(str).append("\n");
			}
			System.out.println("####"+splitS.length);

			textMessage.setContent(contentMsg.toString());
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");
				logger.info("content:"+content);
				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);
				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if (content.startsWith("sa")||content.startsWith("SA")||content.startsWith("sA")||content.startsWith("Sa")) {
					//存入样本编号	
					String[] strs = content.substring(2).split(" ");
					if(strs.length>1) {
						surveyID = strs[0];//问卷编号
						sampleID = strs[1];//样本编号
					}
					Article article = new Article();
					article.setTitle("样本编号"+sampleID+"绑定成功！");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
			}
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				// 接收用户发送的事件请求内容
				String Event = requestMap.get("Event");
				String EventKey = requestMap.get("EventKey");
				String[] parame = EventKey.split("qrscene_");//扫描带参二维码返回的参数
				if(parame.length>1) {
					String[] p =  parame[1].split("&");
					if(p.length>1) {
						sampleID = p[1];
						surveyID = p[0];
					}
				}
			}
			    if(sampleID!=null&&surveyID!=null) {//首次通过带参二维码关注时，插入用户信息
			    	firstSubscribe(requestMap,surveyID,sampleID,fromUserName);
			    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	/**
	 * 首次通过带参二维码关注时，插入用户信息
	 */
	private void firstSubscribe(Map<String, String> requestMap,String surveyID,String sampleID,String fromUserName) {
		  
		    /**
		     * 获取用户信息
		     */
		    WeChatUser user = UserInfoUtil.getUserInfo(getACCESS_TOKEN(requestMap,surveyID), fromUserName);
		    logger.info(sampleID+"sampleID：surveyID" + surveyID);
		    logger.info("OpenID：" + user.getOpenId());
		    logger.info("关注状态：" + user.getSubscribe());
		    logger.info("关注时间：" + user.getSubscribeTime());
		    logger.info("昵称：" + user.getNickname());
		    logger.info("性别：" + user.getSex());
		    logger.info("国家：" + user.getCountry());
		    logger.info("省份：" + user.getProvince());
		    logger.info("城市：" + user.getCity());
		    logger.info("语言：" + user.getLanguage());
		    logger.info("头像：" + user.getHeadImgUrl());
		    
		    String tableName = getSurveyAndWechatInfo(requestMap.get("ToUserName"), surveyID).getWechatUserTable();//在问卷公众号关联表中查找此公众号对应微信用户存储表
		    WeChatUser weChatUser = searchWeChatUser(tableName,fromUserName,sampleID);
		    if(weChatUser !=null ) {//如果数据库问卷对应微信用户表中已存在用户信息，则先删除
		    	qRcodeSampleAndIdDao.deleteWeChatUserBy(tableName, fromUserName, sampleID);
		    }
			    qRcodeSampleAndIdDao.addWeChatUserSampleID(tableName, user, fromUserName, sampleID);//插入微信信息
		    
	}
	/**
	 * 查询微信用户存储表中用户信息
	 * @param tableName
	 * @param fromUserName
	 * @param sampleID
	 * @return
	 */
	private WeChatUser searchWeChatUser(String tableName,String fromUserName,String sampleID) {
		List<WeChatUser> weChatUser = qRcodeSampleAndIdDao.searchWeChatUserBy(tableName, fromUserName, sampleID);
		if(weChatUser!=null && weChatUser.size()>0) {
			return weChatUser.get(0);
		}else {
			return null;
		}
		
	}
	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	private String getReminder(String toUserName,String surveyIdOrProjectName) {
		WechatAndSurvey wechatAndSurvey = getSurveyAndWechatInfo(toUserName,surveyIdOrProjectName);
		  String Reminder = null;
		  if(wechatAndSurvey!=null) {
			  return wechatAndSurvey.getReminder();
			 }
		  logger.info("查Reminder:"+Reminder);  
		return Reminder;
	}
	/**
	 * 更加SurveyID或者projectName获取公众号的APPID和APPSECRET,
	 * 然后获取accessToken
	 */
	@Override
	public String getACCESS_TOKEN(Map<String, String> requestMap,String surveyIdOrProjectName) {
		
		WechatAndSurvey wechatAndSurvey = getSurveyAndWechatInfo(requestMap==null?null:requestMap.get("ToUserName"),surveyIdOrProjectName);
		  String accessToken = null;
		  if(wechatAndSurvey!=null ) {
			   String APPID = wechatAndSurvey.getAPPID();
			   String APPSECRET = wechatAndSurvey.getAPPSECRET();
			   accessToken = PubInfoWeixin.getAccessTocken(APPID,APPSECRET).getToken();}
		  logger.info("查accessToken:"+accessToken);
		return accessToken;
	}
	/**
	 * 此Token是微信后台配置的
	 * 写死的，根据项目分别在数据库中查找对应的Token
	 * @param surveyIdOrProjectName
	 * @return
	 */
	@Override
	public String getTOKEN(Map<String, String> requestMap ,String surveyIdOrProjectName) {
		WechatAndSurvey wechatAndSurvey = getSurveyAndWechatInfo(requestMap==null?null:requestMap.get("ToUserName"),surveyIdOrProjectName);
		  String Token = null;
		  if(wechatAndSurvey!=null ) {
			  return wechatAndSurvey.getTOKEN();
			 }
		  logger.info("查Token:"+Token);
		return Token;
	}
	private WechatAndSurvey getSurveyAndWechatInfo(String ToUserName,String surveyIdOrProjectName){
		List<WechatAndSurvey> WechatAndSurveylist = null;
		try {
			if(surveyIdOrProjectName!=null) {
				WechatAndSurveylist =  qRcodeSampleAndIdDao.searchWechatAndSurveyInfo(surveyIdOrProjectName);
			}else {
				WechatAndSurveylist =  qRcodeSampleAndIdDao.searchWechatAndSurveyInfoByWechatId(ToUserName);//更加公众号获取相关信息
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//用户发给对应公众号的ID
		if(WechatAndSurveylist!=null && WechatAndSurveylist.size()>0) {
			logger.info(WechatAndSurveylist.get(0).toString());
			return WechatAndSurveylist.get(0);
		}else {
			return null;
		}

	}
}


	