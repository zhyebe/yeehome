/*动态生成表格的行*/

function addRow(){
	var questionTable=document.getElementById("questionTable");
	var tr=questionTable.insertRow(questionTable.rows.length);
	console.log(tr.cells.length);
	var td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	td=tr.insertCell(tr.cells.length);
	td.innerHTML='<input type="button" value="修改"/><input type="button" value="删除"/>';
}
