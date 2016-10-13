package com.yeehome.service;

import javax.annotation.Resource;

import org.array.rsa.util.RSAUtil;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeehome.entity.NoteResult;
import com.yeehome.entity.User;
import com.yeehome.idao.IDao;
import com.yeehome.iservice.IUserService;

@Service
public class UserService implements IUserService {
	
	@Resource
	private IDao<User> userDao;

	public NoteResult<String> checkLogin(String username, String password) {
		NoteResult<String> noteResult = new NoteResult<String>();
		User user = userDao.findByString(username);
		String pwd = null;
		if (username == null || user == null) {
			noteResult.setStatus(NoteResult.FAIL_STATUS);
			noteResult.setData(null);
			noteResult.setMsg("用户名或密码错误!");
			return noteResult;
		} else {
			pwd = RSAUtil.decrypt(user.getCn_user_password());
		}
		if (pwd != null && user.getCn_user_name().equals(username)
				&& pwd.equals(password)) {

			String token = RSAUtil.createId().replaceAll("-", "");
			noteResult.setData(token);
			user.setCn_user_token(token);
			userDao.modify(user);
			noteResult.setStatus(NoteResult.SUCCESS_STATUS);
			noteResult.setMsg("登陆成功！");
			return noteResult;
		}
		noteResult.setStatus(NoteResult.FAIL_STATUS);
		noteResult.setData(null);
		noteResult.setMsg("用户名或密码错误!");
		return noteResult;
	}

	public NoteResult<User> checkLoad(String username) {
		NoteResult<User> noteResult = new NoteResult<User>();
		User user = userDao.findByString(username);
		if (user == null) {
			noteResult.setStatus(NoteResult.SUCCESS_STATUS);
			noteResult.setData(null);
			noteResult.setMsg("success");
			return noteResult;
		} else {
			noteResult.setStatus(NoteResult.FAIL_STATUS);
			noteResult.setData(null);
			noteResult.setMsg("fail");
			return noteResult;
		}
	}

	@Transactional(rollbackFor={Exception.class})
	@Pointcut
	@Before("")
	public NoteResult<User> add(User user) throws Exception {
		NoteResult<User> noteResult = new NoteResult<User>();
		try {
			String pwd = RSAUtil.encrypt(user.getCn_user_password());
			user.setCn_user_password(pwd);
			user.setCn_user_id(RSAUtil.createId());
			userDao.add(user);
			noteResult.setStatus(NoteResult.SUCCESS_STATUS);
			noteResult.setData(null);
			noteResult.setMsg("恭喜你，注册成功！");
		} catch (Exception e) {
			e.printStackTrace();
			noteResult.setStatus(NoteResult.FAIL_STATUS);
			noteResult.setData(null);
			noteResult.setMsg("对不起，注册失败！");
			throw e;
		}
		return noteResult;
	}

	/**
	 * 检查token
	 */
	public NoteResult<Boolean> chekToken(String username, String usertoken) {
		NoteResult<Boolean> result = new NoteResult<Boolean>();
		User baseUser = userDao.findByString(username);
		if (baseUser != null && baseUser.getCn_user_token().equals(usertoken)) {
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setData(true);
			result.setMsg("登陆成功！");
		} else {
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setData(false);
			result.setMsg("登陆失败！");
		}
		return result;
	}

	public NoteResult<User> updatePwd(User user) {
		NoteResult<User> result = new NoteResult<User>();
		try {
			String pwd=RSAUtil.encrypt(user.getCn_user_password());
			user.setCn_user_password(pwd);
			userDao.updatePwd(user);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("密码修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("密码修改失败！");
		}
		return result;
	}

	public NoteResult<User> loadPwd(User user) {
		NoteResult<User> result = new NoteResult<User>();
		User baseUser = userDao.findByString(user.getCn_user_name());
		String basePwd = RSAUtil.decrypt(baseUser.getCn_user_password());
		if (user.getCn_user_password().equals(basePwd)) {
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("允许修改！");
		} else {
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("密码错误！");
		}
		return result;
	}

}
