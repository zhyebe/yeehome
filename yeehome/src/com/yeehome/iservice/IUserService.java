package com.yeehome.iservice;

import com.yeehome.entity.NoteResult;
import com.yeehome.entity.User;

public interface IUserService {
	/**
	 * 登陆检查方法
	 * @param username
	 * @param password
	 * @return
	 */
	public NoteResult<String> checkLogin(String username,String password);

	/**
	 * 检查用户名是否重复
	 * @param username
	 * @return
	 */
	public NoteResult<User> checkLoad(String username);
	
	/**
	 * 添加用户注册信息
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<User> add(User user) throws Exception;

	public NoteResult<Boolean> chekToken(String cn_user_name,
			String cn_user_token);

	public NoteResult<User> updatePwd(User user);

	public NoteResult<User> loadPwd(User user);
}
