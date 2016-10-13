<%@page pageEncoding="utf-8" 
contentType="text/html;charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		 <base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css" href="css/public_div.css"/>
		<link rel="stylesheet" type="text/css" href="css/management.css"/>
		<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="js/layer-2.1/layer.js"></script>
		<script type="text/javascript" src="js/management.js"></script>
		<script type="text/javascript" src="js/external.js"></script>
	</head>
	<body style="font-size:30px;">
		<div class="header">
			<p>Exam后台管理</p>
		</div>
		<div class="content">
			<div id="inputpwd">
				<form>
					<span>
						请输入管理员密码：<input type="password" name="password" id="password">
						<input type="button" value="确定" id="submit" class="butt">
					</span>
				</form>
			</div>
			<div id="updatepwd">
				<form>
					<span>
						修改管理员密码：<input type="password" name="newPwd" id="newPwd">
						<input type="button" value="修改" id="modify" class="butt">
					</span>
	
				</form>
			</div>
			<div id="control">
				<form name="options">
					<label for="stuoption">学生账号</label><input type="radio" name="option" id="stuoption" value="student" checked>
					<label for="teroption">教师账号</label><input type="radio" name="option" id="teroption" value="teacher">
				 	<input type="button" value="查询所有账号" id="selectall" class="butt">&nbsp;&nbsp;&nbsp;
					<input type="button" id="changepwd" class="butt" value="修改管理员密码">
				</form>
				<br/>
				<span>
				 	 <input type="text" id="keyWord" name="keyWord">
				 	 <input type="button" value="搜索" id="search" class="butt">
				</span>
			</div>
			<div id="table">
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>姓名</th>
							<th>账号</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					
				</table>
				<p id="delmsg"></p>
			</div>
		</div>
		<div class="footer"><p>&copy;2015 数组 | <a class="afooter" href="policy.html">使用前必读</a> | <a class="afooter" href="feedback.html">意见反馈</a> |&nbsp;ARRAY证150720号 </p></div>
	</body>
</html>