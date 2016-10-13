<%@page import="efficient.entity.TotalScore"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

int start=0;
int rows=0;
int pageNo=1;
int blank_rows=0;
List<TotalScore> scores=new ArrayList<TotalScore>();
Object[] data=(Object[])request.getAttribute("data");
if(data[1]!=null&&data[2]!=null){
	scores=(List<TotalScore>)data[0];
	start=Integer.parseInt(data[1].toString());
	rows=Integer.parseInt(data[2].toString());
	pageNo=start/rows+1;
	if(scores.size()<rows){
		blank_rows=rows-scores.size();
	}
}else{
	blank_rows=4;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   	<title>成绩查询</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="css/liststudent.css"/>
	<link rel="stylesheet" type="text/css" href="css/public_div.css"/>

  </head>
  
  <body>
   		<div id="header" class="header">
		 	<p>Exam考试成绩统一查询</p>
		</div>

		<div id="content" class="content">
			<a href="listMsg.list" target="_blank" style="text-decoration: none;background:red;border-radius:3px;color:#fff;text-align:center;margin-left:161px;">列表展示（测试）</a>
			<div>
				<table class="t" id="list" name="student">
					<thead><!--表头-->
						<tr>
							<th>考试日期</th>
							<th>考试编号</th>
							<th>学生ID</th>
							<th>分数</th>	
						</tr>
					</thead>

					<tbody><!--表数据-->
					<% 
						for(TotalScore score:scores){
							
					%>
						<tr>
							<td><%=score.getExam_date()%></td>
							<td><%=score.getExamNo().replace("-", "")%></td>
							<td><%=score.getStudent_id()%></td>
							<td><%=score.getScore()%></td>
						</tr>
					<%
						}
						for(int i=0;i<blank_rows;i++){
					%>
						<tr>
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
			</div>

			<div id="pages">
				<p>	
					<form action="back.list" id="before" method="post">
						<input type="hidden" name="page" value="<%=pageNo%>">
						<input type="submit" id="bef" value="前一页">
					</form>
					
					&nbsp;第<%=pageNo%>页&nbsp;
					<form action="forward.list" id="after" method="post">
						<input type="hidden" name="page" value="<%=pageNo%>">
						<input type="submit" id="aft" value="后一页">
					</form>
				</p>
			</div>

		</div>
		
		<div id="footer" class="footer">
			<p>&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号 </p>
		</div>
		
  </body>
</html>
