<%@ page language="java" import="java.util.*,efficient.entity.TotalScore" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<TotalScore>scores=null;
if(request.getAttribute("scores")!=null){
	scores=(List<TotalScore>)request.getAttribute("scores");
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
int blank=0;
if(scores!=null&&scores.size()<rows){
	blank=rows-scores.size();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script src="regexp.js"></script>
	<link href="css/public_div.css" rel="stylesheet" type="text/css"/>
	<link href="css/student.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
	<script src="js/student.js"></script>
	<script type="text/javascript" src="js/external.js"></script>
  </head>
  
  <body>
    <div id="header" class="header">
     	<p>Exam考试系统——管理</p>
     </div>
     <div id="content" class="content">
     	<div id="result">
     		<p>学员考试详情</p>
     		<div id="findAll">
     			<form action="firstlist.manager" id="all" method="post">
     				<input type="hidden" name="queryoption" value="all">
     				<input type="submit" class="butt" id="viewall" value="查询所有"/>
     			</form>&nbsp;
     			<form action="firstlist.manager" id="order" method="post">
     				<input type="hidden" name="queryoption" value="order">
     				<input type="submit" class="butt" id="orderall" value="成绩排名"/>
     			</form>
     		</div>
     		<table id="resultTable">
     			<thead>
     				<tr>
     					<th>考试日期</th>
     					<th>考试编号</th>
     					<th>学生ID</th>
     					<th>分数</th>
     				</tr>
     			</thead>
     			
     			<tbody>
     				
     			<%
     				if(scores!=null&&scores.size()>0){
     					for(int i=0;i<scores.size();i++){
     						TotalScore score=scores.get(i);
     			%>
     				<tr>
     					<td><%=score.getExam_date()%></td>
     					<td><%=score.getExamNo().replace("-", "")%></td>
     					<td><%=score.getStudent_id()%></td>
     					<td><%=score.getScore()%></td>
     				</tr>
     					<%}%>
     			<%
     				}else{
     					for(int i=0;i<rows;i++){
     			%>
     				<tr>
     					<td></td>
     					<td></td>
     					<td></td>
     					<td></td>
     				</tr>
     			<%
     					}
     				}
     			%>
     			
     			<%for(int i=0;i<blank;i++){%>
     				<tr>
     					<td></td>
     					<td></td>
     					<td></td>
     					<td></td>
     				</tr>
     			<%}%>
     			</tbody>
     		</table>
     	</div>
     	<div id="pages">
     		<p id="page">	
					<form action="back.manager" method="post" id="back">
						<input type="hidden" name="page" value="<%=pageNo%>">
						<input type="submit" id="bef" value="前一页">
					</form>
					
					&nbsp;第<%=pageNo%>页&nbsp;
					<form action="forward.manager" method="post" id="forward">
						<input type="hidden" name="page" value="<%=pageNo%>">
						<input type="submit" id="aft" value="后一页">
					</form>
			</p>
     	</div>
     	<div id="control">
     		<div id="findByStudentId">
     			<p>按学员帐号查询</p>
     			<form action="firstlist.manager" method="post">
     				<input type="hidden" name="queryoption" value="username">
     				<p>
     					<input type="text" name="username" id="username" value="此处输入学员帐号名称"/>
     					<input type="submit" class="butt" value="查询"/>
     				</p>
     			</form>
     		</div>
     		<div id="findByDate">
     			<p>按考试日期查询</p>
     			<form action="firstlist.manager" method="post">
     				<input type="hidden" name="queryoption" value="date">
	     			<p>
	     				<select id="year" name="year">
	
	     				</select>年&nbsp;
	     				<select id="month" name="month">
	     					
	     				</select>月&nbsp;
	     				<select id="date" name="date">
	     					
	     				</select>日&nbsp;
	     				<input type="submit" class="butt" value="查询"/>
	     			</p>
     			</form>
     		</div>
     	</div>
     </div>
     <div id="footer" class="footer">
     	<p>&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号 </p>
     </div>
  </body>
</html>
