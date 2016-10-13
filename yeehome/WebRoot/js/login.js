$(function() {
	$("#count").focus(function() {
		$("#count_msg").empty();
		$("#login_msg").empty();
	});
	$("#password").focus(function() {
		$("#password_msg").empty();
		$("#login_msg").empty();
	});
	
	$(document).bind("keydown", function(e) {
		e = window.event || e;
		if (e.keyCode == 13) {
			$('#count').trigger("blur");
			$('#password').trigger("blur");
			$("#login").trigger("click");
		}
	});
	
	$("#login").click(function() {
		// 获得数据
		var name = $("#count").val().trim();
		var pwd = $("#password").val().trim();
		var ok = true;
		// 检测数据格式
		if (name == "") {
			$("#count_msg").html("用户不能为空");
			$("#count_msg").css("color", "red");
			ok = false;
		}
		if (pwd == "") {
			$("#password_msg").html("密码不能为空");
			$("#password_msg").css("color", "red");
			ok = false;
		}
		// 发送请求
		if (ok) {
			$.ajax({
				url : getRootPath()+"/user/login.do",
				type : "post",
				data : {
					"cn_user_name" : name,
					"cn_user_password" : pwd
					
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						//将传回的token加入到cookie
						addCookie("token",result.data,1);
						addCookie("name",name,1);
						window.location.href = "edit.html";
					} else if (result.status == 0) {
						console.log("status"+result.status);
						$("#login_msg").html(result.msg);
						$("#login_msg").css("color", "red");
					}
				},
			});
		}
		// 返回数据

	});
	var confirm = false;
	$("#zc").css("visibility", "visible");
	$('#sig_in').click(function() {
		$('#dl').attr('class', 'log log_out');
		$('#zc').attr('class', 'sig sig_in');
	});
	$('#back').click(function() {
		$('#zc').attr('class', 'sig sig_out');
		$('#dl').attr('class', 'log log_in');
	});
	$("#final_password").blur(function() {
		var npassword = $('#regist_password').val().trim();
		var fpassword = $('#final_password').val().trim();
		if (npassword != fpassword) {
			$("#fpwd").empty();
			$('#warning_3').css('display', 'block');
			confirm = false;
		}else if(fpassword==""){
			$('#warning_3').css('display', 'none');
			$("#fpwd").html("*不能为空");
			$("#fpwd").css("color", "red");
			confirm = false;
		}else{
			confirm = true;
		}
	});
	$('#regist_password').blur(function() {
		var reg=/^\w{6,16}$/i;
		var npwd = $('#regist_password').val().trim();
		var npassword = npwd.length;
		if (npassword < 6 && npassword > 0) {
			$("#rpwd").empty();
			$('#warning_2').css('display', 'block');
			confirm = false;
		}else if(!reg.test(npwd)){
			$('#warning_2').css('display', 'none');
			$("#rpwd").html("*必须6~16字符");
			$("#rpwd").css("color", "red");
			confirm = false;
		}else{
			confirm = true;
		}
	});
	$("#regist_username").focus(function() {
		$("#regist_msg").empty();
		$("#username_msg").empty();
		$('#warning_1').css('display', 'none');
	});
	$("#nickname").focus(function() {
		$("#regist_msg").empty();
	});
	$('#regist_password').focus(function() {
		$('#warning_2').css('display', 'none');
		$("#regist_msg").empty();
		$("#rpwd").empty();
	});
	$('#final_password').focus(function() {
		$('#warning_3').css('display', 'none');
		$("#regist_msg").empty();
		$("#fpwd").empty();
	});

	$("#regist_username").blur(function() {
		var reg=/^\w{4,12}$/i;
		var name = $("#regist_username").val();
		if (!reg.test(name)) {
			$("#username_msg").html("*必须4~12字符");
			$("#username_msg").css("color", "red");
		} else {
			$.ajax({
				url : getRootPath()+"/user/load.do",
				type : "post",
				data : {
					"cn_user_name" : name
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						$("#username_msg").html("用户名可用");
						$("#username_msg").css("color","#13A89E");
						confirm = true;
					} else if (result.status == 0) {
						$('#warning_1').css('display', 'block');
						$("#warning_1").css("color", "red");
						confirm = false;
					}
				},
			});
		}
	});

	$(document).bind("keydown", function(e) {
		e = window.event || e;
		if (e.keyCode == 13) {
			$('#regist_username').trigger("blur");
			$('#nickname').trigger("blur");
			$('#regist_password').trigger("blur");
			$('#final_password').trigger("blur");
			$("#regist_button").trigger("click");
		}
	});
	
	$("#regist_button").click(function() {
		$("#regist_msg").empty();
		$("#username_msg").empty();
		var name = $("#regist_username").val().trim();
		var nickname = $("#nickname").val().trim();
		var pwd = $("#final_password").val().trim();
		if (confirm) {
			$.ajax({
				url : getRootPath()+"/user/regist.do",
				type : "post",
				data : {
					"cn_user_name" : name,
					"cn_user_nickname" : nickname,
					"cn_user_password" : pwd
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						$("#regist_username").empty();
						$("#nickname").empty();
						$("#final_password").empty();
						$("#regist_password").empty();
						window.location.href = "log_in.html";
						alert(result.msg);
					} else if (result.status == 0) {
						$("#regist_msg").html(result.msg);
						$("#regist_msg").css("color", "red");
					}
				},
			});
		}
	});

});

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}