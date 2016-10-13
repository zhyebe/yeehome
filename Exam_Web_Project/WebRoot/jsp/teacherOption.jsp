<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>studentOption.html</title>
		<meta http-equiv="content-type"
			content="text/html;charset=utf-8"		
		/>
		<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
		<script type="text/javascript" src="js/teacherOption.js"></script>
		<script type="text/javascript" src="js/external.js"></script>
		<link type="text/css" rel="stylesheet" href="css/public_div.css"/>
		<link type="text/css" rel="stylesheet" href="css/teacherOption.css"/>
    
  </head>
  
  <body>
   	<div id="header" class="header">
			<p>Exam考试系统</p>
		</div>
		<div id="content" class="content">
			<div id="selection">
				<a href="<%=path%>/jsp/question.jsp"><input type="button" id="viewquestion" value="【进入题库】>>>"/></a><br>
				<a href="<%=path%>/jsp/student.jsp"><input type="button" id="viewstudents" value="【学生信息】>>>"/></a>			
			</div>
			<div id="bbs">
				
			</div>
		</div>
		<div id="footer" class="footer">
			<p>&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号 </p>
		</div>
  </body>
</html>
