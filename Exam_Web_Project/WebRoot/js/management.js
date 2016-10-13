$(function() {
	var obj1 = $('#updatepwd').get(0);
	var obj2 = $('#table').get(0);
	var obj3 = $('#control').get(0);
	obj1.className = 'fail';
	obj2.className = 'fail';
	obj3.className = 'fail';
	$('#changepwd').click(function() {
		$('#updatepwd').get(0).className = 'pass';
		$('#table').get(0).className='fail';
		$('#control').get(0).className='fail';
		$('#inputpwd').get(0).className='fail';
	});
	$('#search').click(
			function() {
				$('#table').get(0).className='pass';
				var option=null;
				for (var i = 0; i < document.options.option.length; i++) {
					if (document.options.option[i].checked) {
						option = document.options.option[i].value;
					}
				}
				$.post('findUser.manpwd', {
					"option" : option,
					"keyWord" : $('#keyWord').val()
				}, function(data) {
					var table = $('tbody').get(0);
					$('tbody').empty();
					for (var i = 0; i < data.length; i++) {
						var tr = table.insertRow(table.rows.length);
						var td = tr.insertCell(0);
						td.innerHTML = data[i].id;
						td = tr.insertCell(1);
						td.innerHTML = data[i].realname;
						td = tr.insertCell(2);
						td.innerHTML = data[i].username;
						td = tr.insertCell(3);
						td.innerHTML = "<input type='button' id='del" + i
								+ "' value='删除' class='butt'>";
					}
					for (var i = 0; i < $('tbody tr').length; i++) {
						$('#del' + i).click(
								function() {
									$.post('delAccount.manpwd', {
										"table_id" : $(this).parent()
												.siblings().eq(0).text(),
										"option" : option
									}, function(delMsg) {
										layer.msg(delMsg);
										$("#search").trigger("click");
									}, 'text');
								});
					}
				}, 'json');
			});

	$('#selectall').click(
			function() {
				$('#table').get(0).className='pass';
				var option=null;
				for (var i = 0; i < document.options.option.length; i++) {
					if (document.options.option[i].checked) {
						option = document.options.option[i].value;
					}
				}
				$.post('showAcount.manpwd', 'option=' + option, function(data) {
					var table = $('tbody').get(0);
					$('tbody').empty();
					for (var i = 0; i < data.length; i++) {
						var tr = table.insertRow(table.rows.length);
						var td = tr.insertCell(0);
						td.innerHTML = data[i].id;
						td = tr.insertCell(1);
						td.innerHTML = data[i].realname;
						td = tr.insertCell(2);
						td.innerHTML = data[i].username;
						td = tr.insertCell(3);
						td.innerHTML = "<input type='button' id='del" + i
								+ "' value='删除' class='butt'>";
					}
					for (var i = 0; i < $('tbody tr').length; i++) {
						$('#del' + i).click(
								function() {
									$.post('delAccount.manpwd', {
										"table_id" : $(this).parent()
												.siblings().eq(0).text(),
										"option" : option
									}, function(delMsg) {
										layer.msg(delMsg);
										$("#selectall").trigger("click");
									}, 'text');
								});
					}

				}, 'json');
			});
	$('#modify').click(function() {
		var newpwd = $(this).siblings().eq(0).val();

		if ('' == $.trim(newpwd)) {
			var update = $('#updatepwd').get(0);
			update.innerHTML = '密码修改失败！请刷新页面重试！';
			$('#updatepwd').css({
				"color" : "#f00",
				"font-size" : "25px",
				"padding" : "200px 0 0 0"
			});
		} else {
			$.post('modifyPwd.manpwd', 'newPwd=' + newpwd, function(data) {
				var update = $('#updatepwd').get(0);
				if ('pass' == $.trim(data)) {
					update.innerHTML = '密码修改成功，请刷新页面！';
					$('#updatepwd').css({
						"color" : "#fff",
						"font-size" : "25px",
						"padding" : "200px 0 0 0"
					});
				} else {
					update.innerHTML = '密码修改失败！请刷新页面重试！';
					$('#updatepwd').css({
						"color" : "#f00",
						"font-size" : "25px",
						"padding" : "200px 0 0 0"
					});
				}
			}, 'text');
		}
	});
	$('#submit').click(function() {
		var inputpwd = $(this).siblings().eq(0).val();
		$.post('check.manpwd', 'password=' + inputpwd, function(data) {
			var input = $('#inputpwd').get(0);
			var obj1 = $('#updatepwd').get(0);
			var obj2 = $('#table').get(0);
			var obj3 = $('#control').get(0);
			obj1.className = 'fail';
			obj2.className = 'fail';
			obj3.className = data;
			if ('pass' == $.trim(data)) {
				input.className = 'fail';
			} else {
				input.innerHTML = '抱歉，管理员密码错误！<br>请刷新后重试！';
				$('#inputpwd').css({
					"color" : "#f00",
					"font-size" : "80px",
					"padding" : "200px 0 0 0"
				});
			}
		}, 'text');

	});
});
