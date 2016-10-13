<%@ page language="java" import="java.util.*,efficient.entity.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	Object[] data=(Object[])request.getAttribute("data");
	int student_id=Integer.parseInt(data[0].toString());
	Object obj=(Object)data[1];
	List<Question> questions=(List<Question>)obj;
	int qnum=questions.size();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>exam.jsp</title>

<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
<link rel="stylesheet" type="text/css" href="css/public_div.css" />
<link rel="stylesheet" type="text/css" href="css/exam.css" />
<script type="text/javascript" src="js/exam.js" /></script>
<script type="text/javascript" src="js/external.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<div id="header" class="header">
		<p>Exam考试系统-考试</p>
	</div>
	<div id="content" class="content">
		<form action="testSubmit" method="get" id="exam" name="exam">
			<div id="timerSpace" class="hide">
				<input type="hidden" id="examtime" value="${examtime}">
				考试时间还有&nbsp;<span id="timerDisplay"></span>&nbsp;分钟
			</div>
			<div id="questionZone" class="closeExam">
				<%
					for(int i=0;i<qnum;i++){ 
						Question question=questions.get(i);
				%>
				<p>
					<span id="qno"><%=i+1%>、<span>(分值：<%=question.getScore()%>分)</span>
					</span><br><span><%=question.getQuestion()%></span>
					
				</p>
				<input type="hidden" name="q<%=i+1%>"
					value="<%=question.getQuestionNo()%>">
				<p id="ckbox">
					<input id="a" type="checkbox" class="cbox" name="<%=i+1%>" value="A">
					<label for="a">A</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="b" type="checkbox" class="cbox" name="<%=i+1%>" value="B">
					<label for="b">B</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="c" type="checkbox" class="cbox" name="<%=i+1%>" value="C">
					<label for="c">C</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="d" type="checkbox" class="cbox" name="<%=i+1%>" value="D">
					<label for="d">D</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="e" type="checkbox" class="cbox" name="<%=i+1%>" value="E">
					<label for="e">E</label>
				</p>
				<input type="hidden" name="student_id" value="<%=student_id%>">
				<%
					}
				%>
			</div>
			<div id="controlZone">
				<div id="startbutton">
					<a href="javascript:;" id="start" onclick="autoSubmit()">&gt;&gt;&gt;开始考试&lt;&lt;&lt;</a>
				</div>
				<span></span>
				<div id="endbutton">
					<a href="jsp/login.jsp" rel="external" id="end" onclick="sub()">交卷&gt;&gt;&gt;</a>
				</div>
			</div>
		</form>
	</div>
	<div id="footer" class="footer">
		<p>
			&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a
				class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号
		</p>
	</div>
</body>
</html>
