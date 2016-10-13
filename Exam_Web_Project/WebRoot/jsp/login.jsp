<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	String reaction="pass";
	if(request.getAttribute("fail_msg")==null){
		reaction="pass";
	}else if(request.getAttribute("fail_msg").toString().equals("error")){
		reaction="error";
	}else{
		reaction="pass";
	}
	
%>
<!DOCTYPE html>
<html>
	<head>	
		 <base href="<%=basePath%>">
		<title>登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="css/public_div.css">
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
		<script type="text/javascript" src="js/login.js">
		</script>
		<script type="text/javascript" src="js/external.js"></script>
	</head>
	<body>
	
		<div class="header" id="header">
			<p>Exam考试系统</p>
		</div>
		<div class="content" id="content">
			
			<div id="table">
				<div id="error" class="<%=reaction%>">
					<p>帐号或密码错误!!&nbsp;&nbsp;</p>
		
				</div>
				<form onsubmit="return check(checkName(),checkPwd());" action="login" method="post">
					<div id="login">
						<h3>登录</h3>
					</div>
					<div id="username">
						<p>
							<lable>
								帐号:
							</lable>
							<input class="" type="text" name="name" id="name"/>
						</p>
					</div>
					<div id="password">
						<p>
							<lable>
								密码:
							</lable>
							<input class="" type="password" name="password" id="pwd"/>
						</p>
					</div>
					<div id="radio">
						<input type="radio" id="student" name="accountselect" value="student" checked/><label class="status" for="student">学生</label>
						<input type="radio" id="admin" name="accountselect" value="teacher"/><label class="status" for="admin">教师</label>
					</div>
					<div id="button">
						<input type="submit" id="confirm" value="登录"/>&nbsp;
						<a id="linkreg" href="jsp/reg.jsp"><input type="button" id="reg" value="注册"/></a>&nbsp;
						<input type="reset" id="clear" value="重置" onclick="dis()"/>
					</div>
				</form>
			</div>
		</div>
		<div class="footer" id="footer">
			<p>
				&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号
			</p>
		</div>
	</body>
</html>