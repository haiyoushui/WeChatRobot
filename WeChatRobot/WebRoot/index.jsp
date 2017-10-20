<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <style type="text/css">
	  body{
	  background-color: #FCFCFC}
   div{
   margin-left: 100px;
   margin-top: 20px;
   }
    #head{
    margin: auto;}
    .form{
    height: 40px;
    width: 180px;}
    
    #familysubmit{
    display: none;
    }
    .error{
    	display: none;
    }
    #serchname{
    		display: none;
    }
   </style>
  </head>
  
  <body>
  <h1 id="head">欢迎进入订餐系统</h1>
 
   		<div>
      		手机：<input id="mobilephone" name="mobilephone" type="text" placeholder="输入手机号"></input>
      		<input type="button" id="submit"  onclick="serchname()" value="提交"/>
  		</div>
      <p id="phoneError" class="error">*未找到相应信息！</p>
   		<div id="serchname">
   				姓名：<input></input>
   		</div>
   <div>
   <form action="">
			<input name="radio1" type="radio" value="self"  onclick="chosefamily()">单人
			<input name="radio1" type="radio" value="family"  onclick="chosefamily()">家属
    	<div id="familysubmit">
    		订餐数量：<input id="num"  name="num" type="text"></input>
    		<input id="submitnum" type="button" onclick="familsub()" value="提交订单">
    	</div>  
	</form>
   </div>
 
   <script type="text/javascript">
      var mobilephone = document.getElementById("mobilephone").value;
      var submit = document.getElementById("submit");
      var familysubmit =  document.getElementById("familysubmit");
      var order = document.getElementById("num");
      var submitnum  = document.getElementById("submitnum");
      var num = 1;
      
       function serchname(){    
   		var myreg = /^1[3458]\d{9}$/;
   		if (mobilephone.length!=11){
				alert('请输入正确的手机号')
			}else{
     		getName();
     } 
  }   
     
     //提交订单
     function ordering(num){
     var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
  	xmlhttp=new XMLHttpRequest();
 	 }
	else{// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
	xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  }
	xmlhttp.open("POST","<%=path%>/OrderServelet",true);
	xmlhttp.send("num=3");
     }
     
     //通过手机号查人名
     function getName()
	{
		alert("name")
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
  	xmlhttp=new XMLHttpRequest();
 	 }
	else{// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
	xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("name").innerHTML=xmlhttp.responseText;
    }
  }
	xmlhttp.open("GET","<%=path%>/OrderServelet?mobilephone=mobilephone",true);
	xmlhttp.send();
    }
     //家属的chekbox是否选中
      function chosefamily(){
        var obj  = document.getElementsByName('radio1');
        for(var i=0;i<obj.length;i++){
            if(obj[i].checked==true){
                if(obj[i].value=='self'){
                   ordering(1);
                }else if(obj[i].value=='family'){
                	familysubmit.style.display="block";
                }
            }
        }
    }
     function familsub(){
     ordering(order.value);
     }
   </script>
   
   
  </body>
</html>
