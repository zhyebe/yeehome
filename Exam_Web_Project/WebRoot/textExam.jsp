<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,efficient.entity.Question"%>
<% 
	List<Question>questions=
		(List<Question>)request.getAttribute("questions");
	int qnum=questions.size();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>testExam</title>
	</head>
	<body>
		<form action="testSubmit" method="post">
			<%for(int i=0;i<qnum;i++){ 
				Question question=questions.get(i);
			%>
			<p>
				<span>第<%=i+1%>题</span>
				<span><%=question.getQuestion()%></span>
				<span>分值：<%=question.getScore()%></span>
				<input type="hidden" name="q<%=i+1%>" value="<%=question.getQuestionNo()%>">
				<input type="checkbox" name="<%=i+1%>" value="A">A
				<input type="checkbox" name="<%=i+1%>" value="B">B
			</p>
			<%}%>
			
			<input type="submit" value="交卷"/>
		</form>

	</body>
</html>