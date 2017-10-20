package com.eagle.WeChatRobot.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpServletUtil {
/**
 * 统一的http数据交互类
 * @param url
 * @param mapParam
 * @return
 * @throws ClientProtocolException
 * @throws IOException
 */
	public static String sendHttpPost(String url, Map<String, String> mapParam)
			throws ClientProtocolException, IOException {
		String strResult = null;
		// 使用NameValuePair来保存要传递的Post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 添加要传递的参数
		for (Iterator<String> i = mapParam.keySet().iterator(); i.hasNext();) {
			String paramKey = i.next();
			String paramValue = mapParam.get(paramKey);
			params.add(new BasicNameValuePair(paramKey, paramValue));
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// HttpPost连接对象
		HttpPost httpRequest = new HttpPost(url);
		// 设置字符集
		HttpEntity httpentity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
		// 请求httpRequest
		httpRequest.setEntity(httpentity);
		// 发送请求
		CloseableHttpResponse httpResponse = httpclient.execute(httpRequest);
		// 设置请求的参数 超时时间
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			strResult = EntityUtils.toString(httpResponse.getEntity(),
					HTTP.UTF_8);
		} else {
			
			strResult = "连接通讯录系统异常！";
		}
		return strResult;
	}

}
