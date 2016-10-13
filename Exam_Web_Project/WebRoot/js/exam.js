/*自动交卷方法：
在开考按钮按下之后激活，
周期性获取系统时间;
并考试时间完结结束之后自动提交表单
*/


$(function(){
	$('#end').hide();
	$('#start').click(function(){
		$(this).slideUp('slow');
		$('#timerSpace').slideDown('slow');
		$('#end').slideDown('slow');
	});
	$(document).ready(function(){
		$(document).bind("keydown",function(e){
			e=window.event||e;
			if(e.keyCode==116||e.keyCode==8){
				e.keyCode=0;
				layer.alert("<p style='color:red;'>对不起，作答期间不要离开此页面，否则成绩将被记为0分处理！</p>");
				return false;
			}
		});
	});
	$(document).ready(function(){
		$(document).bind("contextmenu",function(e){
			layer.alert("<p style='color:red;'>对不起，作答期间不能使用右键！</p>");
			return false;
		});
	});
});
var testTime;
$(function(){
	testTime=$("#examtime").val();
});
var timer;
function autoSubmit(){
	document.getElementById("questionZone").setAttribute("class","openExam");
	clearTimer();
	var startTime=new Date();	
	timer=setInterval(function(){
		var time=new Date();
		var current_time=time.getTime();
		var start_time=startTime.getTime();
		var time_remain=testTime-((current_time-start_time)/1000/60);
		document.getElementById("timerDisplay").innerHTML=time_remain.toFixed(0);
		console.log(time_remain);
		if(time_remain<0){
			document.getElementById("exam").submit();
			document.getElementById("questionZone").setAttribute("class","closeExam");
			clearTimer();
		}
	},1000);
}
/*销毁定时器*/
function clearTimer(){
	timer=clearInterval(timer);
	document.getElementById("timerDisplay").innerHTML="";
}
/*手动交卷*/
function sub(){
	document.getElementById("exam").submit();
	clearTimer();
	document.getElementById("questionZone").setAttribute("class","closeExam");
	
}

