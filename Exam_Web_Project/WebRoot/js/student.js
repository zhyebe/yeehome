/*查询结果表添加新行*/
function addRow(){
	var resultTable=document.getElementById("resultTable");
	var tr=resultTable.insertRow(resultTable.rows.length);
	console.log(tr.cells.length);
	var td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	
}
/*JS和网页本身不判断向数据库插入的日期的合法性。
SQL在插入的时候会做检查，所以只需要接受SQLException即可*/
/*在页面加载的时候加载2000-9999年的年份*/
function loadYear(){
	var yearSelect=document.getElementById("year");
	for(var i=2000;i<3000;i++){
		var year=document.createElement("option");
		year.innerHTML=i.toString();
		year.value=i;
		yearSelect.appendChild(year);		
	}
}
/*在页面加载的时候加载1-12月的月份*/
function loadMonth(){
	var monthSelect=document.getElementById("month");
	for(var i=1;i<=12;i++){
		var month=document.createElement("option");
		month.innerHTML=i.toString();
		month.value=i;
		monthSelect.appendChild(month);	
	}
}
/*在页面加载的时候加载1-31日的月份*/
function loadDate(){
	var dateSelect=document.getElementById("date");
	for(var i=1;i<=31;i++){
		var date=document.createElement("option");
		date.innerHTML=i.toString();
		date.value=i;
		dateSelect.appendChild(date);		
	}
}
/*初始化方法，在页面加载的时候调用加载年月日选项的方法*/
function init(){
	loadYear();
	loadMonth();
	loadDate();
}
window.onload=init;


