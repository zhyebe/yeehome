
/*题号输入正则检查规则：*/
var question_regQuestionNo=/^\d+$/gi;
/*题目分值输入正则检查规则*/
var question_regScore=/^\d+$/gi;
/*对HTML中某元素的输入内容正则检查方法：
参数，该元素ID属性，该元素输入值适用的正则检查规则
通过检查返回true;否则返回false;
*/
function checkReg(objId,regex){
	if(regex.test(document.getElementById(objId).value)){	
		return true;
	}else{
		return false;	
	}
}
/*question.html提交表单onsubmit综合检查*/
function question_checkAll(){
	if((checkReg("questionNoInput",question_regQuestionNo))*(checkReg("score",question_regScore))){
		//alert("允许提交");		
		return true;
	}else{
		//alert("禁止提交");
		return false;
	}
	return true;
}

