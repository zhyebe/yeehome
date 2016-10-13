<%@page import="oracle.net.aso.r"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int student_id=Integer.parseInt(request.getAttribute("student_id").toString());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>studentOption.jsp</title>
		  <base href="<%=basePath%>">
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
		<script type="text/javascript" src="jsp/studentOption.js"></script>
		<script type="text/javascript" src="js/external.js"></script>
		<link type="text/css" rel="stylesheet" href="css/public_div.css" />
		<link type="text/css" rel="stylesheet" href="css/studentOption.css" />
	</head>

	<body>
		<div id="header" class="header">
			<p>Exam考试系统</p>
		</div>
		<div id="content" class="content">
			<div id="selection" style="margin:0 0 10px 0;">
				
					
					<a href="questionSelect?student_id=<%=student_id%>" rel="external"><input type="submit" id="biginexam" value=">>考试&lt;&lt;" /></a>
				
			</div>
			<div>
				 	<a href="firstlist.list" rel="external"><input type="submit" id="viewscore" value="【查询成绩】>>>" /></a>
			</div>		
				
			
		<div id="bbs"></div>
		</div>
		<div id="footer" class="footer">
			<p>&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号 </p>
		</div>
	</body>
</html>
