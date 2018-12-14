<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>login test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="ajax方式">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        function login() {
            $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/WeChatRobot/WeChatConfig/addWechatSurvey" ,//url
                data: $('#form1').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        alert("SUCCESS");
                    }
                    ;
                },
                error : function() {
                    alert("异常！");
                }
            });
        }
		        function update() {
            $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/WeChatRobot/WeChatConfig/updateWechatSurvey" ,//url
                data: $('#form1').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        alert("SUCCESS");
                    }
                    ;
                },
                error : function() {
                    alert("异常！");
                }
            });
        }
    </script>
</head>
<body>
<div id="form-div">
    <form id="form1" onsubmit="return false" action="##" method="post">
        <p>surveyId：<input name="surveyId" type="text" id="surveyId" tabindex="1" size="15" value=""/></p>
        <p>wechatID：<input name="wechatID" type="text" id="wechatID" tabindex="2" size="16" value=""/></p>
		<p>公众号名称：<input name="wechatName" type="text" id="wechatName" tabindex="3" size="15" value=""/></p>
        <p>APPID：<input name="APPID" type="text" id="APPID" tabindex="4" size="16" value=""/></p>
		<p>APPSECRET：<input name="APPSECRET" type="text" id="APPSECRET" tabindex="5" size="15" value=""/></p>
        <p>TOKEN：<input name="TOKEN" type="text" id="TOKEN" tabindex="6" size="16" value=""/></p>
		<p>微信后台用户名：<input name="wechatAccount" type="text" id="wechatAccount" tabindex="7" size="15" value=""/></p>
        <p>微信后台密码：<input name="wechatPassword" type="text" id="wechatPassword" tabindex="8" size="16" value=""/></p>
		<p>用户问卷表名：<input name="wechatUserTable" type="text" id="wechatUserTable" tabindex="9" size="15" value=""/></p>  
        <p>提示语：<input name="reminder" type="text" id="reminder" tabindex="10" size="16" value=""/></p>
        <p>备注：<input name="remark" type="text" id="remark" tabindex="10" size="16" value=""/></p>
        <p>创建人：<input name="creatuser" type="text" id="creatuser" tabindex="10" size="16" value=""/></p>
        <p><input type="button" value="添加" onclick="login()">&nbsp;<input type="reset" value="更新"onclick="update()"></p>
    </form>
</div>
</body>
</html>