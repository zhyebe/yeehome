$(function(){
	$(document).ready(function(){
		$(document).bind("keydown",function(e){
			e=window.event||e;
			if(e.keyCode==116){
				e.keyCode=0;
				layer.alert("<p style='color:red;'>注册期间不能刷新此页面！请使用重置按钮！</p>");
				return false;
			}
		});
	});
	$(document).ready(function(){
		$(document).bind("contextmenu",function(e){
			layer.alert("<p style='color:red;'>注册期间不能使用右键！</p>");
			return false;
		});
	});
});
function checkName(){
	var name = document.getElementById("input1").value;
	var role = /^\w{4,12}$/i;
	var span = document.getElementById("result1");
	if(role.test(name)){
		span.innerHTML = "可以使用";
		span.className="pass";
		return true;
	}else{
		name = document.getElementById("input1");			
		span.innerHTML = "请输入4~12个单词字符";
		span.className="error";
		return false;
	}
}
function checkPwd(){
	var name = document.getElementById("input2").value;
	var role = /^\w{8,16}$/i;
	var span = document.getElementById("result2");
	if(role.test(name)){
		span.innerHTML = "可以使用";
		span.className="pass";
		return true;
	}else{
		name = document.getElementById("input2");			
		span.innerHTML = "请输入8~16个单词字符";
		span.className="error";
		return false;
	}
}
function checkleibie(va){
	var span = document.getElementById("result3");
	if(va==1){
		document.getElementById("input3").className="show";
		span.innerHTML = "请填写管理员密码";
		span.className="error";
	}else if(va==0){

		document.getElementById("input3").className="hidden"; 
		span.innerHTML = "";
	}
}
function checkGpwd(){
					
	var val;
	var rg = document.getElementsByName("leibie");
	var obj = rg.length;
	for(var i=0;i<obj;i++){
		if(rg[i].checked){
			val = rg[i].value;
		}
	}
	var name = document.getElementById("input3").value;
	var role = /^\w{1,10}$/i;
	var span = document.getElementById("result3");
	if(val==1){
		if(role.test(name)){
					
			return true;
		}else{
				
			return false;
		}
	}else if(val==0){
		document.getElementById("input3").value="";
		return true;
	}else{
					
		return false;
	}
}
function clearAlert(){
	document.getElementById("notice").className="none";
	document.getElementById("notice").innerHTML="";
	document.getElementById("input3").className="hidden";
	var span1 = document.getElementById("result1");
	var span2 = document.getElementById("result2");
	var span3 = document.getElementById("result3");
	span1.innerHTML="";
	span2.innerHTML="";
	span3.innerHTML="";
}
