
function checkName(){
	var name=document.getElementById("name").value;
	var reg=/^\w{4,12}$/ig;
	if(reg.test(name)){
		return 1;
	}else{
		return 0;
	}
}

function checkPwd(){
	var pwd=document.getElementById("pwd").value;
	var role=/^\w{8,16}$/ig;
	if(role.test(pwd)){
		return 1;
	}else{
		return 0;
	}
}

function check(ischeckname,ischeckpwd){
	if((ischeckname*ischeckpwd)!=0){
		return true;
	}else{
		layer.alert("<p style='color:red;'>帐号或密码格式不符!</p>");	
		return false;

	}
}
function dis(){
	document.getElementById("error").className="pass";
}

function loginMessage(reaction){
	if(reaction=="pass"){
		layer.msg("登陆成功！");
	}else{
		layer.alert("<p style='color:red;'>账号或密码错误！</p>");
	}
}

