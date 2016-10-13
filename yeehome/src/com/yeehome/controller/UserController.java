package com.yeehome.controller;

import javax.annotation.Resource;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeehome.entity.AccessToken;
import com.yeehome.entity.NoteResult;
import com.yeehome.entity.User;
import com.yeehome.iservice.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	IUserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult<String> login(
			String cn_user_name,
			String cn_user_password){
		return userService.checkLogin(
				cn_user_name,
				cn_user_password);
	}
	
	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult<User> load(String cn_user_name){
		return userService.checkLoad(cn_user_name);
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult<User> regist(User user) throws Exception{
		return userService.add(user);
	}
	
	@RequestMapping("/checkToken.do")
	@ResponseBody
	public NoteResult<Boolean> checkToken(String cn_user_name,String cn_user_token){
		return userService.chekToken(cn_user_name,cn_user_token);
	}
	@RequestMapping("/changepwd.do")
	@ResponseBody
	public NoteResult<User> changePwd(User user){
		return userService.updatePwd(user);
	}
	@RequestMapping("/checkpwd.do")
	@ResponseBody
	public NoteResult<User> checkPwd(User user){
		return userService.loadPwd(user);
	}
}
