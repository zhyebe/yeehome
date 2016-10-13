<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String alert="";
if(request.getAttribute("alert")==null){
	alert="";
}else{
	alert=request.getAttribute("alert").toString();
}
String regmsg="";
if(request.getAttribute("regmsg")!=null){
	regmsg=request.getAttribute("regmsg").toString();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="css/reg.css"/>
	<link rel="stylesheet" type="text/css" href="css/public_div.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
	<script type="text/javascript" src="js/reg.js" ></script>
	<script type="text/javascript" src="js/external.js"></script>
  </head>
  
  <body>
  	<div class="header">
		<p>Exam考试系统</p>
	</div>
	
	<div class="content">
		<div class="loginform">
			<div id="reg"><h2>注册用户</h2></div>
			<form action="TestReg" method="post"
				onsubmit="return checkName() && checkPwd()&&checkGpwd()">
				<p><label>姓名:</label>
					<input id="input0" type="text" name="realname" onblur="checkName()">
					<span id="result0"></span>
				</p>
				<p><label>帐号:</label>
					<input id="input1" type="text" name="name" onblur="checkName()">
					<span id="result1"></span>
				</p>
				<p><label>密码:</label>
					<input id="input2" type="password" name="pwd" onblur="checkPwd()">
					<span id="result2"></span>
				</p>
				<p><label>帐号类别：</label>
					<input id="student" type="radio" name="leibie" value=0
					onclick="checkleibie(0)" checked="checked"><label for="student">学生</label>
					<input id="teacher" type="radio" name="leibie" value=1
					onclick="checkleibie(1);"><label for="teacher">教师</label>
				</p>
				<p id="notice" class="<%=regmsg%>">
					<%=alert%>
				</p>
				<div>
					<p id="mpwd">
						<label>管理密码:</label>
						<input class="hidden" 
						id="input3" type="password" name="mpwd" onblur="checkGpwd()">
						<span id="result3"></span>
					</P>
				</div>
				<p id="control">
					<input type="submit" value="提交" id="ok">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置" id="none" onclick="clearAlert()">
				</p>
			</form>
		 </div>
		</div>
		<div class="footer">
			<p>&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号 </p>
		</div>
  </body>
</html>
