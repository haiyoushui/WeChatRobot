package com.isss.weixin.service;

import org.liufeng.weixin.util.WeixinUtil;

import com.eagle.WeChatRobot.utils.PubInfo;
import com.isss.weixin.pojo.WeixinMenuClickButton;
import com.isss.weixin.pojo.WeixinMenuViewButton;
import com.isss.weixin.vo.PubInfoWeixin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CreatMenu {

	public static void main(String[] args) {
	     
		WeixinMenuClickButton cbt=new WeixinMenuClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");
         
         
        WeixinMenuViewButton vbt=new WeixinMenuViewButton();
		String isssurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		isssurl = isssurl.replace("APPID", PubInfoWeixin.APPID).replace(  
                "REDIRECT_URI", WeixinUtil.urlEncodeUTF8("http://weixinliuhao.tunnel.qydev.com/WeChatRobot/WeXinUserInfo/userinfo/user"));
		
        vbt.setUrl(isssurl);
        vbt.setName("博客");
        vbt.setType("view");
         
        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);
         
         
        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);
         
        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
        button.add(cbt);
         
        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String url = PubInfoWeixin.menu_create_url.replace("ACCESS_TOKEN", PubInfoWeixin.getAccessTocken().getToken());
        try{
            String rs=WeixinUtil.httpRequest(url,"POST", menujson.toString()).toString();
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
     
    }
 
}