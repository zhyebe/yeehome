package com.yeehome.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.array.rsa.util.RSAUtil;
import org.springframework.stereotype.Service;

import com.yeehome.entity.Note;
import com.yeehome.entity.NoteResult;
import com.yeehome.entity.Notebook;
import com.yeehome.entity.Notebook_Type;
import com.yeehome.entity.Share;
import com.yeehome.entity.User;
import com.yeehome.idao.IDao;

@Service
public class NoteBookService {
	@Resource
	IDao<Notebook> noteBookDao;
	@Resource
	IDao<User> userDao;
	@Resource
	IDao<Notebook_Type> noteBookTypeDao;
	@Resource
	IDao<Note> noteDao;
	@Resource 
	IDao<Share> shareDao;
	public NoteResult<List<Notebook>> findAllNoteBook(User user) {
		NoteResult<List<Notebook>> result=new NoteResult<List<Notebook>>();
		List<Notebook> noteBooks=new ArrayList<Notebook>();
		try{
			noteBooks=noteBookDao.findByObject(user);
			if(noteBooks!=null&&noteBooks.size()>0){
				result.setData(noteBooks);
				result.setStatus(NoteResult.SUCCESS_STATUS);
				result.setMsg("success");
			}else{
				result.setData(null);
				result.setStatus(NoteResult.FAIL_STATUS);
				result.setMsg("您目前还没有笔记本！");
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg("笔记本列表加载失败！");
			return result;
		}
		
	}
	/**
	 * 添加新的笔记本到数据库
	 * @param user
	 * @return
	 */
	public NoteResult<Notebook> addNoteBook(String userName,String noteBookName) {
		NoteResult<Notebook> result=new NoteResult<Notebook>();
		Notebook notebook=new Notebook();
		User baseUser=
				userDao.findByString(userName);
		Notebook_Type baseNoteBookType=
				noteBookTypeDao.findByString(Notebook_Type.code5);
		String createTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		notebook.setCn_notebook_name(noteBookName);
		notebook.setCn_notebook_createtime(createTime);
		notebook.setCn_notebook_id(RSAUtil.createId());
		notebook.setCn_notebook_type_id(baseNoteBookType.getCn_notebook_type_id());
		notebook.setCn_user_id(baseUser.getCn_user_id());
		try{
			noteBookDao.add(notebook);
			result.setData(null);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setMsg(noteBookName+"笔记本添加成功！");
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setData(null);
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setMsg(noteBookName+"笔记本添加失败！");
			return result;
		}
	}
	/**
	 * 修改笔记本名称
	 * @param notebook
	 * @return
	 */
	public NoteResult<Notebook> modifyNoteBookName(Notebook notebook) {
		NoteResult<Notebook> result=new NoteResult<Notebook>();
		try{
			noteBookDao.modify(notebook);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setData(null);
			result.setMsg("笔记本重命名成功！");
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setData(null);
			result.setMsg("笔记本重命名失败！");
			return result;
		}
	}
	/**
	 * 删除笔记本和笔记
	 * @param notebook
	 * @return
	 */
	public NoteResult<Notebook> deleteNoteBookAndNote(Notebook notebook){
		NoteResult<Notebook> result=new NoteResult<Notebook>();
		List<Note> notes=new ArrayList<Note>();
		notes=noteDao.findById(notebook.getCn_notebook_id());
		Share share=new Share();
		try{
			for(Note note:notes){
				share.setCn_note_id(note.getCn_note_id());
				shareDao.deleteById(share);
			}
			noteDao.deleteBy(notebook);
			noteBookDao.deleteBy(notebook);
			result.setStatus(NoteResult.SUCCESS_STATUS);
			result.setData(null);
			result.setMsg("success");
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setStatus(NoteResult.FAIL_STATUS);
			result.setData(null);
			result.setMsg("笔记本删除失败！");
			return result;
		}
	}
	
}
