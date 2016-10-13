var um;
$(function() {
	um = UM.getEditor('myEditor');
	var close = true;
	$(document).bind("keydown", function(e) {
		e = window.event || e;
		if (e.keyCode == 116) {
			close = false;
		}
	});
	$(window).unload(function() {
		if (close) {
			delCookie("name");
			delCookie("token");
		}
	});
	// 检查当前用户的令牌是否存在
	if (getCookie("token") == null) {
		window.location.href = "log_in.html";
	} else {

		// 当前用户的令牌存在则送回服务器检查，存在则放行
		// 不存在则强制踢出服务器，需要重新登陆
		$.ajax({
			url : getRootPath()+"/user/checkToken.do",
			type : "post",
			data : {
				cn_user_name : getCookie("name"),
				cn_user_token : getCookie("token")
			},
			dataType : "json",
			success : function(result) {
				if (result.data != true) {
					// 解析响应的数据，判断是否存在，不存在跳回登陆界面
					// 存在，则不做操作，留在当前页面
					window.location.href = "log_in.html";
				}
			},
			error : function(result) {
				window.location.href = "log_in.html";
			}
		});
	}

	var name = getCookie("name");// 从浏览器获得Cookie中的当前用户名
	var token = getCookie("token");// 从浏览器获得Cookie中的当前用户令牌
	/**
	 * 点击修改密码按钮时，将用户名和令牌存到Cookie中 以便以修改时使用
	 */
	$("#cpwd").click(function() {
		addCookie("username", name, 1);
		addCookie("usertoken", token, 1);
	});

	show_notebook(name);// 登陆时立即加载显示notebook

	// 提前绑定事件，当页面加载出现id=close_rename的节点时，就绑定点击事件
	$("#can").on("click", "#close_rename", function() {
		$("#can").empty();
	});

	// 点击添加按钮添加笔记本，加载添加页面
	$("#add_notebook").click(function() {
		$("#can").load("./alert/alert_notebook.html");
	});

	$("#can").on("click", "#cancel_rename", function() {// 将现有或将来的元素绑定事件（动态绑定）
		$("#input_notebook_rename").val("");
	});

	$("#can").on("click", "#cancel_add_notebook", function() {// 将现有或将来的元素绑定事件（动态绑定）
		$("#input_notebook").val("");
	});
	$("#can").on("focus", "#input_notebook", function() {// 将现有或将来的元素绑定事件（动态绑定）
		$("#iuput_msg").empty();

	});
	$("#can").on("click", "#close_add_notebook", function() {// 将现有或将来的元素绑定事件（动态绑定）
		$("#can").empty();
	});
	// 也可以写成全局
	// $(document).on("click","#close_add_notebook",function(){//将现有或将来的元素绑定事件（动态绑定）
	// $("#input_notebook").val("");
	// $("#can").empty();
	// });

	//enter按键监听确认按钮
	$(document).bind("keydown", function(e) {
		if (e.keyCode == 13) {
			$("#searchsharenotes").trigger("blur");
		}
	});
	
	/*
	 * 加载添加笔记本页面后，提交请求添加新的笔记本 ajax请求地址：/notebook/createnotebook.do
	 * 请求需要传递当前用户名name（从Cookie获得）和添加新的笔记名notebook_name
	 */
	$("#can")
			.on(
					"click",
					"#create_notebook",
					function() {// 将现有或将来的元素绑定事件（动态绑定）
						var regbookname = /^\s*\S+[^\x00-\xff]*$|^\s*\S*[^\x00-\xff]+$/i;
						var notebook_name = $("#input_notebook").val().trim();
						if (!regbookname.test(notebook_name)) {
							$("#iuput_msg").html("笔记本名称非法！");
							$("#iuput_msg").css("color", "#f00");
						} else {
							$
									.ajax({
										url : getRootPath()+"/notebook/createnotebook.do",
										type : "post",
										data : {
											"cn_user_name" : name,
											"cn_notebook_name" : notebook_name
										},
										dataType : "json",
										success : function(result) {
											if (result.status == 1) {
												$("#divnotebook").empty();
												$("#notebooks").empty();
												show_notebook(name);
												$("#iuput_msg")
														.html(result.msg);
												$("#iuput_msg").css("color",
														"#13A89E");
											} else {
												$("#iuput_msg")
														.html(result.msg);
												$("#iuput_msg").css("color",
														"#f00");
											}
										}
									});
						}
					});

	$("#rollback_button").click(function() {
		$("#pc_part_2").hide("slow");
		$("#pc_part_3").hide("slow");
		$("#pc_part_4").show("slow");
		$("#pc_part_5").show("slow");
		$("#pc_part_6").hide("slow");
		$("#pc_part_7").hide("slow");
		$("#pc_part_8").hide("slow");
		$("#recyclenotes").empty();
		recycle_notes(name);
		$("a").addClass("").removeClass("checked");
	});
	
	/**
	 * 收藏笔记列表
	 */
	$("#like_button").click(function(){
		$("#pc_part_5").show("slow");
		$("#pc_part_7").show("slow");
		$("#pc_part_2").hide("slow");
		$("#pc_part_3").hide("slow");
		$("#pc_part_4").hide("slow");
		$("#pc_part_6").hide("slow");
		$("#pc_part_8").hide("slow");
		$("#noput_note_title").text("");//笔记预览标题清空
		$("#notedetail").empty();//笔记预览内容清空
		like_notes();
	});
	
	/**
	 * 活动笔记列表
	 */
	$("#action_button").click(function(){
		$("#pc_part_5").show("slow");
		$("#pc_part_8").show("slow");
		$("#pc_part_2").hide("slow");
		$("#pc_part_3").hide("slow");
		$("#pc_part_4").hide("slow");
		$("#pc_part_6").hide("slow");
		$("#pc_part_7").hide("slow");
		$("#noput_note_title").text("");//笔记预览标题清空
		$("#notedetail").empty();//笔记预览内容清空
	});
	
	$("#searchbutton").click(function(){
		$("#pc_part_2").hide("slow");
		$("#pc_part_4").hide("slow");
		$("#pc_part_6").show("slow");
		$("#pc_part_3").hide("slow");
		$("#pc_part_5").show("slow");
		$("#pc_part_7").hide("slow");
		$("#pc_part_8").hide("slow");
	});
	/**
	 * 搜索分享的笔记
	 */
	$("#searchsharenotes").blur(function(){
		search_share_notes();
	});
	/**
	 * 监听保存笔记按钮，执行添加笔记的方法
	 */
	$("#save_note").click(function() {
		var notehtml = um.getContent();
		var input_note_title = $("#input_note_title").val().trim();
		save_note(notehtml, input_note_title, getCookie("noteId"));
	});
	$("#allnotebooks").click(function(){
		$("#pc_part_2").show("slow");
		$("#pc_part_3").show("slow");
		$("#pc_part_4").hide("slow");
		$("#pc_part_5").hide("slow");
		$("#pc_part_6").hide("slow");
		$("#pc_part_7").hide("slow");
		$("#pc_part_8").hide("slow");
		$("#input_note_title").val("");
		um.setContent("<p></p>", false);
		$("#shownotes").empty();
		delCookie("bookId");
		delCookie("noteId");
		$("a").addClass("").removeClass("checked");
	});
	
	/**
	 * 退出登录按钮监听，执行退出
	 */
	$("#logout").click(function(){
		window.location.reload();
	});
});

/**
 * 展示搜索分享笔记的结果，把所有分享状态的笔记列出
 */
var search_share_notes=function(){
	$("#sharenotes").empty();
	$("#noput_note_title").text("");
	$("#notedetail").empty();
	var inputKey=$("#searchsharenotes").val().trim();
	$.ajax({
		url:getRootPath()+"/note/searchsharenote.do",
		type:"post",
		data:{
			"cn_share_key":inputKey
		},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				var sharenotes=result.data;
				var num=0;
				for(var i=0;i<sharenotes.length;i++){
					num++;
					var shareTitle=sharenotes[i].cn_share_title;
					var shareBody=sharenotes[i].cn_share_body;
					var noteId=sharenotes[i].cn_note_id;
					var li_s="<li class='online'>"
						+ "<a  class=''>"
						+ "<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"
						+ shareTitle
						+ "<button type='button' style='display:none;float:right;margin:3px 15% 0 0;' title='收藏' class='btn btn-default btn-xs btn_plus'>"
						+ "<i class='fa fa-star'></i>"
						+ "</button>"
						+ "</a>"
						+"</li>";
					$li=$(li_s);
					$li.data("noteId",noteId);
					$li.data("shareTitle",shareTitle);
					$li.data("shareBody",shareBody);
					$("#sharenotes").append($li);
					$li.mouseover(function(){
						$("#noput_note_title").text($(this).data("shareTitle"));
						$("#noput_note_title").css("text-align","center");
						$("#notedetail").html($(this).data("shareBody"));
						$(this).find("button").show();
					});
					$li.mouseleave(function(){
						$(this).find("button").hide();
					});
					$li.find("button").click(function(){
						like_note($(this).parent().parent().data("noteId"),
								$(this).parent().parent().data("shareTitle"),
								$(this).parent().parent().data("shareBody"));
					});
				}
				if(num==0){
					$("#sharenotes").html("抱歉，没有搜索到任何相关的分享笔记！");
					$("#sharenotes").css("color","#13A89E");
					$("#noput_note_title").text("");
					$("#notedetail").empty();
				}
			}else{
				alert(result.msg);
			}
		}
	});
};
/**
 * 展示当前账户的收藏列表
 * 根据当前用户名展示
 */
var like_notes=function(){
	$("#likenotes").empty();
	$.ajax({
		url:getRootPath()+"/note/likenotes.do",
		type:"post",
		data:{
			"cn_user_name":getCookie("name")
		},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				var likenotes=result.data;
				var num=0;
				for(var i=0;i<likenotes.length;i++){
					var noteId=likenotes[i].cn_note_id;
					var noteTitle=likenotes[i].cn_note_title;
					var noteBody=likenotes[i].cn_note_body;
					var noteType=likenotes[i].cn_note_type_id;
					if(noteType==1){
						num++;
						var li_s="<li class='idle'>" +
								"<a>" +
								"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>" +noteTitle+
								"<button type='button' class='btn btn-default btn-xs btn_position btn_delete'>" +
								"<i class='fa fa-times'></i>" +
								"</button>" +
								"</a>" +
								"</li>";
						$li=$(li_s);
						$li.data("noteId",noteId);
						$li.data("noteTitle",noteTitle);
						$li.data("noteBody",noteBody);
						$("#likenotes").append($li);
						$li.mouseover(function(){
							$("#noput_note_title").text($(this).data("noteTitle"));
							$("#noput_note_title").css("text-align","center");
							$("#notedetail").html($(this).data("noteBody"));
						
						});
						$li.find("button").click(function(){
							delete_likenote($(this).parent().parent().data("noteId"));//删除收藏夹的笔记
						});
					}
				}
				if(num==0){
					$("#likenotes").html("收藏夹为空！");
					$("#likenotes").css("color","#13A89E");
					$("#noput_note_title").text("");
					$("#notedetail").empty();
				}
			}else{
				alert(result.msg);
			}
		}
	});
};

/**
 * 删除收藏的笔记，即取消收藏
 */
var delete_likenote=function(noteId){

	$("#can").load("./alert/alert_delete_like.html",function(){
		$("#close_delete_like").click(function(){
			$("#can").empty();
		});
		$("#cancel_delete_like").click(function(){
			$("#can").empty();
		});
		$("#confirm_delete_like").click(function(){
			$.ajax({
				url:getRootPath()+"/note/dellikenote.do",
				type:"post",
				data:{
					"cn_note_id":noteId
				},
				dataType:"json",
				success:function(result){
					if(result.status==1){
						like_notes();
						$("#can").empty();
						alert(result.msg);
					}else{
						$("#can").empty();
						alert(result.msg);
					}
				}
			});
		});
	});
};
/**
 * 收藏笔记
 */
var like_note=function(noteId,shareTitle,shareBody){
	$("#can").load("./alert/alert_like.html",function(){
		$("#close_like").click(function(){
			$("#can").empty();
		});
		$("#cancel_like").click(function(){
			$("#can").empty();
		});
		$("#confirm_like").click(function(){
			$.ajax({
				url:getRootPath()+"/note/likenote.do",
				type:"post",
				data:{
					"cn_note_id":noteId,
					"cn_note_title":shareTitle,
					"cn_note_body":shareBody,
					"cn_user_name":getCookie("name")
				},
				dataType:"json",
				success:function(result){
					if(result.status==1){
						$("#can").empty();
						alert(result.msg);
						$("#likenotes").empty();
						like_notes();
					}else{
						alert(result.msg);
					}
				}
			});
		});
	});
};

/**
 * 保存note笔记的方法，执行ajax请求 服务端处理，返回处理结果result
 * 
 * @param notehtml
 *            笔记内容（是一个html文本）
 * @param input_note_title
 *            笔记标题
 * @param note_id
 *            当前笔记的id
 */
function save_note(note_body, note_title, note_id) {
	var regnotetitle = /^\s*\S+[^\x00-\xff]*$|^\s*\S*[^\x00-\xff]+$/i;
	if (note_body == "<p>请输入笔记内容......</p>") {
		note_body = null;
	}
	if (!regnotetitle.test(note_title)) {
		if (note_title == "") {
			note_title = "空字符串";
		}
		alert("你不能使用" + note_title + "作为标题名！");
	} else {
		if (note_id != null) {
			$.ajax({
				url : getRootPath()+"/note/savenote.do",
				type : "post",
				data : {
					"cn_note_body" : note_body,
					"cn_note_id" : note_id,
					"cn_note_title" : note_title
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						$("#shownotes").empty();
						click_option(getCookie("bookId"));
						$("#input_note_title").val("");
						um.setContent("<p></p>", false);
						delCookie("noteId");
					} else {
						alert(result.msg);
						delCookie("noteId");
					}
				}
			});
		} else {
			alert("不能保存未选中的笔记，请选择要编辑的笔记！");
		}
	}
}
/**
 * 该方法是用于提交请求添加当前笔记本中的笔记 发送ajax请求
 * 
 * @param bookId
 *            当前笔记本的id
 */
function add_note(bookId) {
	console.log(bookId);
	var regtitle = /^\s*\S+[^\x00-\xff]*$|^\\s*\S*[^\x00-\xff]+$/i;
	var note_title = $("#input_note").val().trim();
	if (!regtitle.test(note_title)) {
		$("#input_note_msg").html("您不能使用该名称作为标题！");
		$("#input_note_msg").css("color", "#f00");
	} else {
		$.ajax({
			url : getRootPath()+"/note/createnote.do",
			type : "post",
			data : {
				"cn_notebook_id" : bookId,
				"cn_user_name" : getCookie("name"),
				"cn_note_title" : note_title
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 1) {
					click_option(bookId);
					$("#input_note_msg").html(result.msg);
					$("#input_note_msg").css("color", "#13A89E");
				} else {
					$("#input_note_msg").html(result.msg);
					$("#input_note_msg").css("color", "#f00");
				}
			}
		});
	}
}

/**
 * 此方法用于展示当前用户回收站的全部笔记需要传入参数
 * 
 * @pram name 当前用户名
 */
function recycle_notes(name) {
	$("#recyclenotes").empty();//清空回收站
	$
			.ajax({
				url : getRootPath()+"/note/recyclenote.do",
				type : "post",
				data : {
					"cn_user_name" : name
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						var notes = result.data;
						for (var i = 0; i < notes.length; i++) {
							var note = notes[i].cn_note_title;
							var noteId = notes[i].cn_note_id;
							var notebook_type_id = notes[i].cn_note_type_id;
							var noteBody=notes[i].cn_note_body;
							if (notebook_type_id == 2) {
								var li_s = "<li class='disable'>"
										+ "<a >"
										+ "<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"
										+ note
										+ "<button type='button' title='永久删除笔记' class='btn btn-default btn-xs btn_position btn_delete'>"
										+ "<i class='fa fa-times'></i>"
										+ "</button>"
										+ "<button type='button' title='还原笔记' class='btn btn-default btn-xs btn_position_2 btn_replay'>"
										+ "<i class='fa fa-reply'></i>"
										+ "</button>" + "</a>" + "</li>";
								$li = $(li_s);
								$li.data("noteId", noteId);
								$li.data("noteTitle",note);
								$li.data("noteBody",noteBody);
								$("#recyclenotes").append($li);
								$li.mouseover(function() {
									$("#noput_note_title").text($(this).data("noteTitle"));
									$("#noput_note_title").css("text-align","center");
									$("#notedetail").html($(this).data("noteBody"));
									addCookie("noteId", $(this).data("noteId"), 1);
								});
								$li.find("button[title='还原笔记']").click(function(e){
									e.stopPropagation();
									rollback_note();
								});
								$li.find("button[title='永久删除笔记']").click(function(e){
									e.stopPropagation();
									remove_note();
								});
							}
						}
					} else {
						alert(result.msg);
					}
				}
			});
}

/**
 * 还原回收站的笔记到相应的笔记本
 * @param noteId 当前要还原的笔记id
 */
var rollback_note=function(){
	$("#can").load("./alert/alert_delete_rollback.html",function(){
		$("#close_rollback").click(function(){
			$("#can").empty();
		});
		$("#cancel_rollback").click(function(){
			$("#can").empty();
		});
		$("#confirm_rollback").click(function(){
			$.ajax({
				url:getRootPath()+"/note/rollbacknote.do",
				type:"post",
				data:{
					"cn_note_id":getCookie("noteId")
				},
				dataType:"json",
				success:function(result){
					if(result.status==1){
						$("#can").empty();
						$("#shownotes").empty();//清空笔记列表
						click_option(getCookie("bookId"));//刷新笔记列表
						$("#recyclenotes").empty();//清空回收站
						recycle_notes(getCookie("name"));//刷新回收站
						$("#noput_note_title").text("");//笔记预览标题清空
						$("#notedetail").empty();//笔记预览内容清空
						delCookie("noteId");
					}else{
						$("#can").empty();
						alert(result.msg);
						delCookie("noteId");
					}
				}
			});
		});
		
	});
	
};

/**
 * 永久删除回收站笔记笔记
 * @param noteId 当前要删除的笔记id
 */
var remove_note=function(){
	$("#can").load("./alert/alert_delete_note.html",function(){
		$("#close_remove_note").click(function(){
			$("#can").empty();
		});
		$("#cancel_remove_note").click(function(){
			$("#can").empty();
		});
		$("#confirm_remove_note").click(function(){
			$.ajax({
				url:getRootPath()+"/note/removenote.do",
				type:"post",
				data:{
					"cn_note_id":getCookie("noteId")
				},
				dataType:"json",
				success:function(result){
					if(result.status==1){
						$("#can").empty();
						$("#sharenotes").empty();
						$("#shownotes").empty();//清空笔记列表
						click_option(getCookie("bookId"));//刷新笔记列表
						$("#recyclenotes").empty();//清空回收站
						recycle_notes(getCookie("name"));//刷新回收站
						$("#noput_note_title").text("");//笔记预览标题清空
						$("#notedetail").empty();//笔记预览内容清空
						delCookie("noteId");
					}else{
						alert(result.msg);
						delCookie("noteId");
					}
				}
			});
		});
	});
};
/**
 * 该方法是用于显示笔记本列表 通过提交请求获响应数据 提交地址：/notebook/shownotebook.do 该方法需要传入一个参数：
 * 
 * @param name
 *            用户名（可以从浏览器Cookie获得）
 */
function show_notebook(name) {
	if (name != null) {
		$
				.ajax({
					url : getRootPath()+"/notebook/shownotebook.do",
					type : "post",
					data : {
						"cn_user_name" : name
					},
					dataType : "json",
					success : function(result) {
						if (result.status == 1) {
							var notebooks = result.data;
							var num = 0;
							for (var i = 0; i < notebooks.length; i++) {
								num++;
								var notebook = notebooks[i].cn_notebook_name;
								var bookId = notebooks[i].cn_notebook_id;
								var s_li = "<li class='online'>"
										+ "<a>"
										+ "<i class='fa fa-book' title='online' rel='tooltip-bottom'></i> "
										+ notebook
										+ "<button title='删除' type='button' style='float:right;margin:3px 4px 0 0;' class='btn btn-default btn-xs btn_plus' id='delete_notebook"
										+ i
										+ "'><i class='fa fa-close'></i></button>"
										+ "</a></li>";
								$li = $(s_li);// 将li字符串转为对象
								$li.data("bookId", bookId);// 循环绑定bookId到每个$li对象上
								$li.data("bookName", notebook);// 循环绑定bookName到每个$li对象上
								$("#notebooks").append($li);// 循环添加html节点，即全部当前用户的笔记本列表
								$li.click(function() {// 循环绑定单击事件到每一个$li上
									$("#pc_part_2").show("slow");
									$("#pc_part_3").show("slow");
									$("#pc_part_4").hide("slow");
									$("#pc_part_5").hide("slow");
									$("#pc_part_6").hide("slow");
									$("#pc_part_7").hide("slow");
									$("#pc_part_8").hide("slow");
									$("#input_note_title").val("");
									um.setContent("<p></p>", false);
									delCookie("noteId");
									// 当单击当前笔记的时候，调用相应的方法处理其他逻辑
									addCookie("bookId", $(this).data("bookId"), 1);
									$("a").click(function() {
										$("a").addClass("").removeClass("checked");
										$(this).addClass("checked").removeClass("");
									});
									click_option($(this).data("bookId"));
									var bookId_this = $(this).data("bookId");
									/**
									* 通过按钮加载add_note笔记页面
									*/
									$("#add_note").click(function() {
										$("#can").load("./alert/alert_note.html",function() {
											$("#close_note").click(function() {
												$("#can").empty();
											});
											$("#input_note").focus(function() {
												$("#input_note_msg").empty();
											});
											$("#cancel_note").click(function() {
												$("#input_note").val("");
											});
											$("#create_note").click(function() {
													add_note(bookId_this);
											});
										});

									});
								});
								$li.dblclick(function() {// 循环绑定双击事件到每一个$li上
									// 当双击当前笔记的时候，调用相应的方法处理其他逻辑
									dblclick_option($(this).data("bookName"),
											$(this).data("bookId"), name);
								});
								$li.find("button").click(
										function(e) {
											e.stopPropagation();
											delete_notebook($(this).parent()
													.parent().data("bookId"));
										});
							}
							if (num == 0) {
								$("#divnotebook").html("<p>您目前还没有笔记本！</p>");
								$("#divnotebook").css("color", "#13A89E");
								$("#divnotebook").css("text-align", "center");
							}
						} else {
							$("#divnotebook").html("<p>" + result.msg + "</p>");
							$("#divnotebook").css("color", "#13A89E");
							$("#divnotebook").css("text-align", "center");
						}
					}
				});
	}
}

/**
 * 删除笔记的方法：目的是把当前需要删除的笔记
 * 放回到回收站，即把笔记cn_note_type_id改为2表示处在回收站
 */
var delete_note=function(){
	$.ajax({
		url:getRootPath()+"/note/deletenote.do",
		type:"post",
		data:{
			"cn_note_id":getCookie("noteId")
		},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				$("#shownotes").empty();
				click_option(getCookie("bookId"));
				$("#input_note_title").val("");
				um.setContent("<p></p>", false);
				delCookie("noteId");
			}else{
				$("#input_note_title").val("");
				um.setContent("<p></p>", false);
				delCookie("noteId");
				alert(result.msg);
			}
		}
	});
};

/**
 * 删除笔记本的方法---永久删除笔记本及笔记本中的笔记
 * 
 * @param bookId
 *            当前点击中的笔记本id
 */
var delete_notebook = function(bookId) {
	$("#can")
			.load(
					"./alert/alert_delete_notebook.html",
					function() {
						$("#close_delete_notebook").click(function() {
							$("#can").empty();
						});
						$("#cancel_delete_notebook").click(function() {
							$("#can").empty();
						});
						$("#confirm_delete_notebook")
								.click(
										function() {
											$
													.ajax({
														url : getRootPath()+"/notebook/deletenotebook.do",
														type : "post",
														data : {
															"cn_notebook_id" : bookId
														},
														dataType : "json",
														success : function(
																result) {
															if (result.status == 1) {
																$("#notebooks")
																		.empty();
																$("#shownotes")
																		.empty();
																$("#recyclenotes").empty();
																recycle_notes(getCookie("name"));
																$("#noput_note_title").text("");
																$("#notedetail").empty();
																$("#sharenotes").empty();
																show_notebook(getCookie("name"));
																$("#input_note_title").val("");
																um.setContent("<p></p>", false);
																delCookie("bookId");
																delCookie("noteId");
																$("#can").empty();
															} else {
																delCookie("bookId");
																delCookie("noteId");
																alert(result.msg);
															}
														}
													});
										});
					});

};

/**
 * 该方法用于加载笔记本重命名页面，同时执行相关的修改操作 用ajax提交修改请求并返回响应数据
 * ajax提交地址：/notebook/renamenotebook.do 该方法需要传入三个参数：
 * 
 * @param notebook
 *            旧版笔记本名称
 * @param bookId
 *            笔记本id
 * @param name
 *            用户名称（可从浏览器Cookie里获得）
 */
var dblclick_option = function(notebook, bookId, name) {
	$("#can")
			.load(
					"./alert/alert_rename.html",
					function() {
						$("#input_notebook_rename").val(notebook);
						$("#input_notebook_rename").focus(function() {
							$("#iuput_msg").empty();
						});
						$("#confirm_rename")
								.click(
										function() {
											var notebook_rename = $(
													"#input_notebook_rename")
													.val().trim();
											var regbookname = /^\s*\S+[^\x00-\xff]*$|^\\s*\S*[^\x00-\xff]+$/i;
											if (!regbookname
													.test(notebook_rename)) {
												$("#iuput_msg")
														.html("笔记本名称非法！");
												$("#iuput_msg").css("color",
														"#f00");
											} else {
												$
														.ajax({
															url : getRootPath()+"/notebook/renamenotebook.do",
															type : "post",
															data : {
																"cn_notebook_name" : notebook_rename,
																"cn_notebook_id" : bookId
															},
															dataType : "json",
															success : function(
																	result) {
																if (result.status == 1) {
																	$(
																			"#notebooks")
																			.empty();
																	show_notebook(name);
																	$(
																			"#iuput_msg")
																			.html(
																					result.msg);
																	$(
																			"#iuput_msg")
																			.css(
																					"color",
																					"#13A89E");
																} else {
																	$(
																			"#iuput_msg")
																			.html(
																					result.msg);
																	$(
																			"#iuput_msg")
																			.css(
																					"color",
																					"#f00");
																}
															}
														});
											}
										});
					});
};

/**
 * 该方法用于展示某一个笔记本中的所有笔记，提交ajax请求获得笔记内容等 需要传入一个参数：
 * 
 * @param bookId
 *            与当前笔记对应的笔记本的id
 */
var click_option = function(bookId) {
	$("#shownotes").empty();
	$
			.ajax({
				url : getRootPath()+"/note/shownotes.do",
				type : "post",
				data : {
					"cn_notebook_id" : bookId
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						var notes = result.data;
						var num = 0;
						for (var i = 0; i < notes.length; i++) {
							var note_title = notes[i].cn_note_title;
							var note_body = notes[i].cn_note_body;
							var note_id = notes[i].cn_note_id;
							var note_type_id=notes[i].cn_note_type_id;
							if(note_type_id!=2){
								num++;
								var li_s = "<li class='online'>"
									+ "<a  class=''>"
									+ "<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"
									+ note_title
									+ "<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'>"
									+ "<i class='fa fa-chevron-down'></i>"
									+ "</button>"
									+ "</a>"
									+ "<div class='note_menu' tabindex='-1'>"
									+ "<dl>"
									+ "<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>"
									+ "<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>"
									+ "<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>"
									+ "</dl>" + "</div>" + "</li>";
								$li = $(li_s);
								$li.data("noteId", note_id);
								$li.data("noteTitle", note_title);
								$li.data("noteBody", note_body);
								$("#shownotes").append($li);
								//单条笔记的点击事件
								$li.click(function() {
									$("a").click(function() {
										$("a").addClass("").removeClass("checked");
										$(this).addClass("checked").removeClass("");
									});
									note_detail($(this).data("noteId"), $(this)
											.data("noteTitle"), $(this).data(
											"noteBody"));
									addCookie("noteId", $(this).data("noteId"), 1);

								});
								//下拉按钮列表的按钮事件
								$li.find("button:first").click(function(e) {
									e.stopPropagation();
									addCookie("noteId", $(this).parent().parent().data("noteId"), 1);
									$(this).parent().next("div").slideToggle();
								});
								
								//删除笔记按钮的点击事件
								$li.find("button[title='删除']").click(function(e){
									e.stopPropagation();
									delete_note();//删除笔记的方法
								});
							
								//分享笔记的按钮
								$li.find("button[title='分享']").click(function(e){
									e.stopPropagation();
									share_note($(this).parent().parent().parent().parent().data("noteId"),
											$(this).parent().parent().parent().parent().data("noteTitle"),
											$(this).parent().parent().parent().parent().data("noteBody"));//分享笔记的方法
								});
								
								$li.mouseleave(function() {
									$(this).find("div").slideUp();
								});
							}
						}
						if (num == 0) {
							$("#shownotes").html("该笔记本目前没有任何笔记！");
							$("#shownotes").css({"color":"#13A89E"});
							$("#shownotes").css("padding", "4px 0 4px 5px");
						}
					} else {
						alert(result.msg);
					}
				}
			});
};

/**
 * 分享笔记的方法，把笔记内容分享到其他地方
 * 也就是把笔记插入到分享的表里
 * @param noteId
 * @param noteTitle
 * @param noteBody
 */
var share_note=function(noteId,noteTitle,noteBody){
	$.ajax({
		url:getRootPath()+"/note/sharenote.do",
		type:"post",
		data:{
			"cn_note_id":noteId,
			"cn_share_title":noteTitle,
			"cn_share_body":noteBody
		},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		}
	});
};

/**
 * 该方法是展示编辑页面的笔记标题和笔记内容
 * 
 * @param noteId
 */
function note_detail(noteId, noteTitle, noteBody) {
	$("#input_note_title").val(noteTitle);// 把笔记标题刷出到编辑页面的输入框
	if (noteBody != null && noteBody != "") {
		um.setContent(noteBody, false);// 把笔记内容刷出到编辑文本框
	} else {
		um.setContent("<p>请输入笔记内容......</p>", false);
	}

}
// 加载DOM之后处理页面高度
function get_dom(e) {
	return document.getElementById(e);
}
function set_height() {
	var pc_height = window.innerHeight;
	pc_height = pc_height - 132;
	get_dom('first_side_right').style.height = (pc_height - 31) + 'px';
	get_dom('second_side_right').style.height = pc_height + 'px';
	get_dom('four_side_right').style.height = pc_height + 'px';
	get_dom('sixth_side_right').style.height = pc_height + 'px';
	get_dom('seventh_side_right').style.height = pc_height + 'px';
	get_dom('eighth_side_right').style.height = pc_height + 'px';
	get_dom('third_side_right').style.height = (pc_height - 15) + 'px';
	get_dom('fifth_side_right').style.height = (pc_height - 15) + 'px';
}

function myEditorWidth() {
	var dom = get_dom('third_side_right');
	var style = dom.currentStyle || window.getComputedStyle(dom, null);
	get_dom('myEditor').style.width = style.width;
}
set_height();
// 改变窗口大小时调整页面尺寸
window.onresize = function() {
	set_height();
	var width = $('#third_side_right').width() - 35;
	$('.edui-container,.edui-editor-body').width(width);
	$('#myEditor').width(width - 20);
};

// 重写JS原生alert函数
window.alert = function(e) {
	$('#can').load('./alert/alert_error.html', function() {
		$('#error_info').text(' ' + e);
		$('.opacity_bg').show();
		$("#close_error").click(function() {
			$("#can").empty();
			$('.opacity_bg').hide();

		});
		$("#confirm_error").click(function() {
			$("#can").empty();
			$('.opacity_bg').hide();

		});
	});
};
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
