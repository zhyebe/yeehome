<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String json=request.getAttribute("json").toString();
JSONObject object=JSONObject.fromObject(json);
JSONArray array=object.getJSONArray("rows");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>easyui列表展示测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<style type="text/css">
		table th,td{text-align:center;}
	</style>
  </head>
  
  <body>
    <h2>Accordion Tools</h2>
	<p>Click the tools on top right of panel to perform actions.</p>
	<div style="margin:20px 0 10px 0;"></div>
	<div class="easyui-accordion" style="width:700px;height:300px;">
		<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
			<h3 style="color:#0099FF;">Accordion for jQuery</h3>
			<p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
		</div>
		<div title="Help" data-options="iconCls:'icon-help'" style="padding:10px;">
			<p>The accordion allows you to provide multiple panels and display one ore more at a time. Each panel has built-in support for expanding and collapsing. Clicking on a panel header to expand or collapse that panel body. The panel content can be loaded via ajax by specifying a 'href' property. Users can define a panel to be selected. If it is not specified, then the first panel is taken by default.</p> 		
		</div>
		<div title="DataGrid" style="padding:10px" data-options="
				selected:true,
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#dg').datagrid('reload');
					}
				}]">
			<table id="dg" class="easyui-datagrid" data-options="url:'listMsg.list',method:'get',fit:true,fitColumns:true,singleSelect:true">
				<thead>
					<tr>
						<th data-options="field:'itemid',width:220">Item ID</th>
						<th data-options="field:'productid',width:220">Product ID</th>
						<th data-options="field:'productname',width:220">Product Name</th>
						<th data-options="field:'listprice',width:220">List Price</th>
						<th data-options="field:'unitcost',width:220">Unit Cost</th>
						<th data-options="field:'attr1',width:220">Attribute</th>
						<th data-options="field:'status',width:220">Status</th>
					</tr>
				</thead>
				<tbody>
				<%for(int i=0;i<array.size();i++){
					JSONObject object2=array.getJSONObject(i);
				%>
					<tr>
						<td><%=object2.get("itemid")%></td>
						<td><%=object2.get("productid")%></td>
						<td><%=object2.get("productname")%></td>
						<td><%=object2.get("listprice")%></td>
						<td><%=object2.get("unitcost")%></td>
						<td><%=object2.get("attr1")%></td>
						<td><%=object2.get("status")%></td>
					</tr>
				<%}%>
				</tbody>
			</table>
		</div>
	</div>
  </body>
</html>
