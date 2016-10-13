package com.yeehome.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.array.rsa.util.RSAUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeehome.entity.Note;
import com.yeehome.entity.NoteResult;
import com.yeehome.entity.Note_Type;
import com.yeehome.entity.Notebook;
import com.yeehome.entity.Share;
import com.yeehome.entity.User;
import com.yeehome.idao.IDao;

@Service
public class NoteService {
	@Resource
	IDao<Note> noteDao;
	@Resource
	IDao<User> userDao;
	@Resource
	IDao<Note_Type> noteTypeDao;
	@Resource
	IDao<Share> shareDao;
	/**
	 * 查询当前笔记本中的所有笔记，用List<Note>返回
	 * 
	 * @param cn_notebook_id
	 *            当前笔记本的id
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<List<Note>> findAllNotes(String cn_notebook_id) throws Exception {
		List<Note> notes = new ArrayList<Note>();
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		try {
			notes = noteDao.findById(cn_notebook_id);
			result.setData(notes);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记列表获取异常！");
			throw e;
		}
		return result;
	}

	/**
	 * 在当前笔记本添加note笔记
	 * 
	 * @param cn_notebook_id
	 *            当前笔记本id
	 * @param cn_user_name
	 *            当前用户名称
	 * @param cn_note_title
	 *            添加新笔记的标题
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor={Exception.class})
	public NoteResult<Note> addNote(String cn_notebook_id, String cn_user_name,
			String cn_note_title) throws Exception {
		NoteResult<Note> result = new NoteResult<Note>();
		Note note = new Note();
		Note_Type noteType = noteTypeDao.findByString(Note_Type.code5);
		User baseUser = userDao.findByString(cn_user_name);
		long lastModifyTime = System.currentTimeMillis();
		long createTime = lastModifyTime;
		note.setCn_note_create_time(createTime);
		note.setCn_note_last_modify_time(lastModifyTime);
		note.setCn_note_title(cn_note_title);
		note.setCn_notebook_id(cn_notebook_id);
		note.setCn_user_id(baseUser.getCn_user_id());
		note.setCn_note_id(RSAUtil.createId());
		note.setCn_note_type_id(noteType.getCn_note_type_id());
		try {
			noteDao.add(note);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg(cn_note_title + "笔记添加成功！");
		} catch (Exception e) {
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg(cn_note_title + "笔记添加失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 修改笔记内容，保存入数据库
	 * 
	 * @param note
	 *            笔记
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<Note> modifyNote(Note note) throws Exception {
		NoteResult<Note> result = new NoteResult<Note>();
		long lastModifyTime = System.currentTimeMillis();
		note.setCn_note_last_modify_time(lastModifyTime);
		try {
			noteDao.modify(note);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记" + note.getCn_note_title() + "保存失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 服务端处理：把笔记的状态改为回收站状态2
	 * 
	 * @param note
	 *            要放入回收站的笔记
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<Note> delNote(Note note) throws Exception {
		NoteResult<Note> result = new NoteResult<Note>();
		Note_Type baseNoteType = noteTypeDao.findByString(Note_Type.code2);
		String noteTypeId = baseNoteType.getCn_note_type_id();
		note.setCn_note_type_id(noteTypeId);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		try {
			noteDao.update(note);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
		
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记删除失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 根据用户Id查询所有的笔记
	 * 
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<List<Note>> allNotes(User user) throws Exception {
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		List<Note> notes = new ArrayList<Note>();
		try {
			notes = noteDao.findByObject(user);
			result.setData(notes);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("回收站内容加载失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 还原回收站的笔记实质上是把当前笔记的cn_note_type_id设置为正常即5
	 * 
	 * @param note
	 * @return
	 * @throws Exception 
	 */
	
	public NoteResult<Note> modifyNotetype(Note note) throws Exception {
		NoteResult<Note> result = new NoteResult<Note>();
		Note_Type noteType = noteTypeDao.findByString(Note_Type.code5);
		note.setCn_note_type_id(noteType.getCn_note_type_id());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		try {
			noteDao.modifyType(note);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记还原失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 永久删除笔记
	 * @param note
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<Note> deleteNote(Note note) throws Exception {
		NoteResult<Note> result = new NoteResult<Note>();
		Share share=new Share();
		share.setCn_note_id(note.getCn_note_id());
		try {
			shareDao.deleteById(share);
			noteDao.deleteById(note);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记删除失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 把分享的笔记插入到cn_share表中
	 * @param share
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<Share> addShare(Share share) throws Exception {
		NoteResult<Share> result = new NoteResult<Share>();
		share.setCn_share_id(RSAUtil.createId());
		Share baseShare=shareDao.findByString(share.getCn_note_id());
		try {
			if(baseShare==null){
				shareDao.add(share);
				result.setData(null);
				result.setStatus(NoteResult.SUCCESS_STATUS);
				result.setMsg("笔记"+share.getCn_share_title()+"分享成功！");
			}else{
				result.setData(null);
				result.setStatus(NoteResult.FAIL_STATUS);
				result.setMsg("该笔记已经分享过了！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记"+share.getCn_share_title()+"分享失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 按照关键字查询分享的笔记
	 * @param cn_share_key 关键字
	 * @return
	 */
	public NoteResult<List<Share>> SearchShare(String cn_share_key) {
		cn_share_key="%"+cn_share_key+"%";
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		List<Share> shareNotes=new ArrayList<Share>();
		try {
			shareNotes=shareDao.findBykey(cn_share_key);
			result.setData(shareNotes);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记分享列表加载失败！");
			return result;
		}
	}

	/**
	 * 添加当前用户收藏的笔记
	 * @param note 收藏的笔记
	 * @param user 当前用户
	 * @return
	 */
	public NoteResult<Note> addLikeNote(Note note, User user) {
		NoteResult<Note> result = new NoteResult<Note>();
		User baseUser=userDao.findByString(user.getCn_user_name());
		note.setCn_user_id(baseUser.getCn_user_id());
		Note baseNote=noteDao.findByType(note);
		Note_Type noteType=noteTypeDao.findByString(Note_Type.code1);
		try {
			if(baseNote==null){
				note.setCn_note_id(RSAUtil.createId());
				note.setCn_note_create_time(System.currentTimeMillis());
				note.setCn_note_last_modify_time(System.currentTimeMillis());
				note.setCn_note_type_id(noteType.getCn_note_type_id());
				note.setCn_notebook_id(Notebook.LIKE_ID);
				noteDao.add(note);
				result.setData(null);
				result.setStatus(NoteResult.SUCCESS_STATUS);
				result.setMsg("笔记"+note.getCn_note_title()+"收藏成功！");
				return result;
			}else{
				result.setData(null);
				result.setStatus(NoteResult.FAIL_STATUS);
				result.setMsg("你不需要再收藏自己的笔记！");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记"+note.getCn_note_title()+"收藏失败！");
			return result;
		}
	}

	/**
	 * 查询所有当前用户的收藏笔记
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<List<Note>> findLikeNotes(User user) throws Exception {
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		List<Note> likeNotes = new ArrayList<Note>();
		User baseUser=userDao.findByString(user.getCn_user_name());
		try {
			likeNotes = noteDao.findByUserId(baseUser.getCn_user_id());
			result.setData(likeNotes);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("收藏笔记列表加载失败！");
			throw e;
		}
		return result;
	}

	/**
	 * 删除收藏的笔记
	 * @param note
	 * @return
	 * @throws Exception 
	 */
	public NoteResult<Note> deleteLikeNote(Note note) throws Exception {
		NoteResult<Note> result = new NoteResult<Note>();
		try {
			noteDao.deleteById(note);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg("成功删除收藏笔记！");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("删除收藏失败！");
			throw e;
		}
		return result;
	}
}
