$(function() {
	var close = true;
	$(document).bind("keydown", function(e) {
		e = window.event || e;
		if (e.keyCode == 116) {
			close = false;
		}
	});
	$(window).unload(function() {
		if (close) {
			delCookie("username");
			delCookie("usertoken");
		}
	});
	if (getCookie("usertoken") == null || getCookie("username") == null) {
		window.location.href = "log_in.html";
	}
	var post = false;
	var lastp = false;
	var newp = false;
	var finalp = false;
	var username = getCookie("username");
	var usertoken = getCookie("usertoken");
	$('#back').click(function() {
		$('#zc').attr('class', 'sig sig_out');
		addCookie("name", username, 1);
		addCookie("token", usertoken, 1);
		window.location.href = "edit.html";
	});

	$('#last_password').blur(function() {
		var lpassword = $('#last_password').val();
		if (lpassword.trim() == "") {
			$("#npassword_msg1").html("*不能为空");
			$("#npassword_msg1").css("color", "red");
			lastp = false;
		} else {
			$.ajax({
				url : getRootPath()+"/user/checkpwd.do",
				type : "post",
				data : {
					"cn_user_name" : username,
					"cn_user_password" : lpassword
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						lastp = true;
						$("#npassword_msg1").html(result.msg);
						$("#npassword_msg1").css("color", "#13A89E");
					} else {
						lastp = false;
						$("#npassword_msg1").html(result.msg);
						$("#npassword_msg1").css("color", "red");
					}
				}
			});
		}
	});

	$('#final_password').blur(function() {
		if (newp) {
			var npassword = $('#new_password').val();
			var fpassword = $('#final_password').val();
			if (fpassword.trim() == "") {
				$("#npassword_msg3").html("*不能为空");
				$("#npassword_msg3").css("color", "red");
				$('#warning_3').css('display', 'none');
				finalp = false;
			} else if (npassword != fpassword) {
				$('#warning_3').css('display', 'block');
				$("#npassword_msg3").empty();
				finalp = false;
			} else {
				finalp = true;
			}
		}
	});

	$('#new_password').blur(function() {
		if (lastp) {
			var reg=/^\w{6,16}$/i;
			var lpassword = $("#last_password").val().trim();
			var npassword = $('#new_password').val().trim();
			if (npassword == "") {
				$('#warning_2').css('display', 'none');
				$("#npassword_msg2").html("*不能为空");
				$("#npassword_msg2").css("color", "red");
			} else if (npassword.length < 6 && npassword.length > 0) {
				$("#npassword_msg2").empty();
				$('#warning_2').css('display', 'block');
			} else if(!reg.test(npassword)){
				$("#npassword_msg2").html("*必须6~16字符");
				$("#npassword_msg2").css("color", "red");
			}else if (lpassword == npassword) {
				$("#npassword_msg2").html("*不能是原密码");
				$("#npassword_msg2").css("color", "red");
			} else {
				newp = true;
			}
		}
	});

	$("#last_password").focus(function() {
		$("#npassword_msg1").empty();
	});
	$('#new_password').focus(function() {
		$('#warning_2').css('display', 'none');
		$("#npassword_msg2").empty();
	});
	$('#final_password').focus(function() {
		$('#warning_3').css('display', 'none');
		$("#npassword_msg3").empty();
	});

	$(document).bind("keydown", function(e) {
		e = window.event || e;
		if (e.keyCode == 13) {
			$('#last_password').trigger("blur");
			$('#new_password').trigger("blur");
			$('#final_password').trigger("blur");
			$("#changePassword").trigger("click");
		}
	});
	
	$("#changePassword").click(function() {
		var final_password = $("#final_password").val().trim();
		if (finalp) {
			$.ajax({
				url : getRootPath()+"/user/changepwd.do",
				type : "post",
				data : {
					"cn_user_name" : username,
					"cn_user_password" : final_password
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						alert(result.msg);
						delCookie("username");
						delCookie("usertoken");
						window.location.href = "log_in.html";
					} else {
						$("#cpwdmsg").css("color", "#f00");
						$("#cpwdmsg").html(result.msg);
					}
				}
			});
		}

	});

});