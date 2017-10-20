package com.eagle.WeChatRobot.utils;

public class PubInfo {

    public static String HEADIMAGEURL = "http://bbsimg.ali213.net/data/attachment/forum/201501/17/1449227zqzq7qqm9tnzvjv.png";//用户头像为空时的默认图片地址
	public static String ISINTERNALLHTTPURL ="http://localhost:8080/Contact/weChat/IDNumber";//调取通讯录管理系统的判断是否内部人员接口地址，查身份证号的存在
    public static String CONTACTHTTPURL = "http://localhost:8080/Contact/weChat/wxGetuser";//调取通讯录管理系统的查询人员信息接口地址,根据T名字的汉字查信息
    public static String CONTACTHTTPURLBYPINYIN = "http://localhost:8080/Contact/weChat/wxGetuserByPinYin";//调取通讯录管理系统的查询人员信息接口地址,根据T名字的拼音查信息
	public static String OrdinaryRespons = "您的留言已经收到，我们将尽快给您回复，您也可拨打电话：0531-55721001，与我们取得联系。感谢您对易构软件的关注！";//微信用户普通留言的反馈信息
	public static String AbnormalRespons =  "您的留言已收到，我们将尽快给您回复，您也可拨打电话：0531-55721001，与我们取得联系。感谢您对易构软件的关注！";//微信用户发送异常留言的反馈
	public static String NOTHISWXNAME=  "您非已记录特殊微信名称的人员，请发送“P”加身份证号以确认是否为内部人员！";//未登记的fromUserName让其发送身份证号
}
