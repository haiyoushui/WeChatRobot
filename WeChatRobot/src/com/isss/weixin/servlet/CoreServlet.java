package com.isss.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isss.weixin.message.resp.MessageUtil;
import com.isss.weixin.service.CoreService;
import com.isss.weixin.service.impl.CoreServiceImpl;
import com.isss.weixin.util.CreatMenuUtil;
import com.isss.weixin.util.SignUtil;

/**
 * 核心请求处理类
 * 
 * @author liu
 * @date 2013-05-18
 */
@Controller
public class CoreServlet extends HttpServlet  {
	private final String wcFlag = "demo";//对应每个公众号的接口，比如对应此controller的公众号接口为http://iSurvey.pku.edu.cn/WeChatRobot/coreServlet/demo

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		System.out.println("signature"+signature+"||"+timestamp+"||"+nonce);
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
	
		if (SignUtil.checkSignature(coreService2.getTOKEN(null,wcFlag),signature, timestamp, nonce)) {
			out.print(echostr);
			System.out.println("微信服务验证成功！");
		}
		out.close();
		out = null;

	}

	/**
	 * 处理微信服务器发来的消息
	 */
	CoreServiceImpl coreService2 = new CoreServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String[] d = null;
			Map<String, String> requestMap = null;
			try {
				 requestMap = MessageUtil.parseXml(request);
				System.out.println("#######"+requestMap.get("FromUserName"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//CreatMenuUtil.creatMemus(coreService2.getACCESS_TOKEN(requestMap,wcFlag));// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息
		
		String respMessage = coreService2.processRequest(requestMap);
		
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		
		out.close();
	}

}