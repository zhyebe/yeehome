<%@page import="efficient.entity.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Question>questions=null;
if(request.getAttribute("questions")!=null){
	questions=(List<Question>)request.getAttribute("questions");
}

int pageNo=1;
int rows=4;
int start=0;
if(request.getAttribute("start")!=null&&request.getAttribute("rows")!=null){
	start=Integer.parseInt(request.getAttribute("start").toString());
	rows=Integer.parseInt(request.getAttribute("rows").toString());
	pageNo=start/rows+1;
}else{
	pageNo=start/rows+1;
}
Question old_question=null;
if(request.getAttribute("old_question")!=null){
	old_question=(Question)request.getAttribute("old_question");
}
int blank=0;
if(questions!=null&&questions.size()<rows){
	blank=rows-questions.size();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="content-type"
		content="text/html;charset=utf-8"
	/>
		<title>question.jsp</title>
				<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
		<script src="js/regexp.js"></script>
		<script src="js/question.js"></script>
		<script type="text/javascript" src="js/external.js"></script>
		<link rel="stylesheet" type="text/css" href="css/public_div.css"/>
		<link rel="stylesheet" type="text/css" href="css/question.css"/>
  </head>
  
  <body>
    <div id="header" class="header">
			<p>Exam考试系统——题库</p>
		</div>
		<div id="content" class="content">
			<div id="tableZone">
				<form action="all.question" method="post" id="allq">
					<input type="hidden" name="page" value="<%=pageNo%>">
					<input type="submit" class="butt" style="width:160px;" id="all" value="查看所有题目">
				</form>
				<div id="table">
					<table id="questionTable">
						<thead>
							<tr>
								<th>
									题号
								</th>
								<th>
									题目
								</th>
								<th>
									答案
								</th>
								<th>
									分数
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<%
								if(questions!=null&&questions.size()>0){
								for(int i=0;i<questions.size();i++){
									Question question=questions.get(i); 
							%>
								
							<tr>
								<td><%=question.getQuestionNo()%></td>
								<td class="ques"><%=question.getQuestion()%></td>
								<td><%=question.getAnswer()%></td>
								<td><%=question.getScore()%></td>
								<td>
									<a href="delete.question?questionNo=<%=question.getQuestionNo()%>" 
										onclick="return confirm('确认删除吗？')">
										<input type="button" class="butt" value="删除"/>
									</a>
									<a href="load.question?questionNo=<%=question.getQuestionNo()%>">
										<input type="button" class="butt" value="修改">
									</a>
								</td>
									
							
							</tr>	
							<% 
								}
							}else{
								for(int i=0;i<rows;i++){
							%>
								<tr>
     								<td></td>
     								<td class="ques"></td>
     								<td></td>
     								<td></td>
     								<td></td>
     							</tr>
							<%
								}
							}
							%>
							
							<%
							for(int i=0;i<blank;i++){
							%>
								<tr>
     								<td></td>
     								<td></td>
     								<td></td>
     								<td></td>
     								<td></td>
     							</tr>
							<%
							}
							%>
						</tbody>
					
					</table>
					<p id="control">
						<form action="back.question" method="post" id="back">
							<input type="hidden" name="page" value="<%=pageNo%>">
							<input type="submit" id="bef" value="前一页">
						</form>
						<span id="page">&nbsp;第<%=pageNo%>页&nbsp;</span>
						<form action="forward.question" method="post" id="forward">
							<input type="hidden" name="page" value="<%=pageNo%>">
							<input type="submit" id="aft" value="后一页">
						</form>
					</p>				
				</div>
				<div id="query">
					<div id="queryBox">
						<form onsubmit=
							"return checkReg('questionNoQuery',question_regQuestionNo)"
							action="firstlist.question" method="post"
						>
							<input type="hidden" name="queryoption" value="questionNo">
							<p id="inputno">按题号查询:
								<input id="questionNoQuery" 
									type="text" value="此处输入题号" 
									onblur="checkReg(questionNoQuery,question_regQuestionNo)"
									name="questionNo"
								/>
								<input type="submit" class="butt"
								value="查询"/>
							</p>
						
						</form>
						<form action="firstlist.question" method="post">
							<input type="hidden" name="queryoption" value="keyword"/>
							<p id="inputkey">按关键字查询:
						
								<input type="text" id="key" name="keyword"
									value="此处输入关键字" />
								<input type="submit" class="butt"
									value="查询"/>					
							</p>
						</form>
					</div>
				</div>
			</div>
			<div id="textZone">
				<form 
					action="update.question" method="post">
					<p>
						<%if(old_question!=null){ %>
						<textarea name="question"><%=old_question.getQuestion()%>答案:<%=old_question.getAnswer()%>
						</textarea>
						<%}else {%>
						<textarea name="question">请在这里输入题目
						</textarea>
						<%} %>
					</p>
					<p id="qno">题号：<input id="questionNoInput"
						name="questionNo"
						type="text" 
						onblur=
							"checkReg(this.id,question_regQuestionNo)"
						readonly value="<%=old_question!=null?old_question.getQuestionNo():""%>"/>
					</p>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<p id="scor">分值：<input id="score" 
							name="score"
							type="text" 
							onblur=
								"checkReg(this.id,question_regScore)"
								value="<%=old_question!=null?old_question.getScore():""%>"
							/>
					</p>
					<p id="answer">答案:
						<input type="checkbox" id="a" name="answer" class="checkb" value="A"/><label for="a">A</label>
						&nbsp;
						<input type="checkbox" id="b" name="answer" class="checkb" value="B"/><label for="b">B</label>
						&nbsp;
						<input type="checkbox" id="c" name="answer" class="checkb" value="C"/><label for="c">C</label>
						&nbsp;
						<input type="checkbox" id="d" name="answer" class="checkb" value="D"/><label for="d">D</label>
						&nbsp;
						<input type="checkbox" id="e" name="answer" class="checkb" value="E" checked/><label for="e">E</label>
					</p>			
			
					<p id="contr">
						<input type="submit" id="butt"
							value="修改/添加"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" class="butt" value="重置">
					</p>
				</form>
			</div>
		</div>
		<div id="footer" class="footer">
			<p>&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号 </p>
		</div>
  </body>
</html>
