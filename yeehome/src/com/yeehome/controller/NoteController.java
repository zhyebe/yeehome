package com.yeehome.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeehome.entity.Note;
import com.yeehome.entity.NoteResult;
import com.yeehome.entity.Share;
import com.yeehome.entity.User;
import com.yeehome.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	NoteService noteService;
	
	/**
	 * 处理单击笔记本发送来的请求，返回笔记本中的所有笔记列表，
	 * 请求传到服务端处理
	 * @param cn_notebook_id 笔记本id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/shownotes.do")
	@ResponseBody
	public NoteResult<List<Note>> showNotes(String cn_notebook_id) throws Exception{
		return noteService.findAllNotes(cn_notebook_id);
	}
	
	/**
	 * 处理创建笔记名称的请求
	 * @param cn_notebook_id 当前笔记本id
	 * @param cn_user_name 当前用户名
	 * @param cn_note_title 新笔记标题
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/createnote.do")
	@ResponseBody
	public NoteResult<Note> createNote(String cn_notebook_id,
			String cn_user_name,String cn_note_title) throws Exception{
		return noteService.addNote(cn_notebook_id,cn_user_name,cn_note_title);
	}
	
	/**
	 * 处理保存编辑笔记的请求
	 * @param note 当前编辑好的笔记Note
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/savenote.do")
	@ResponseBody
	public NoteResult<Note> saveNote(Note note) throws Exception{
		return noteService.modifyNote(note);
	}
	
	/**
	 * 处理删除笔记的请求，目的是把cn_note_type_id设置为2
	 * @param note
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/deletenote.do")
	@ResponseBody
	public NoteResult<Note> deleteNote(Note note) throws Exception{
		return noteService.delNote(note);
	}
	
	/**
	 * 获取回收站所有笔记
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/recyclenote.do")
	@ResponseBody
	public NoteResult<List<Note>> recycleNotes(User user) throws Exception{
		return noteService.allNotes(user);
	}
	
	/**
	 * 还原回收站的笔记
	 * @param note
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/rollbacknote.do")
	@ResponseBody
	public NoteResult<Note> rollbackNote(Note note) throws Exception{
		return noteService.modifyNotetype(note);
	}
	
	/**
	 * 永久删除回收站的笔记
	 * @param note
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/removenote.do")
	@ResponseBody
	public NoteResult<Note> removeNote(Note note) throws Exception{
		return noteService.deleteNote(note);
	}
	
	/**
	 * 分享笔记：把分享的数据放入数据库cn_share表中
	 * @param note
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/sharenote.do")
	@ResponseBody
	public NoteResult<Share> shareNote(Share share) throws Exception{
		return noteService.addShare(share);
	}
	
	/**
	 * 搜索分享的笔记：把搜索的分享笔记放入列表中
	 * @param note
	 * @return
	 */
	@RequestMapping("/searchsharenote.do")
	@ResponseBody
	public NoteResult<List<Share>> SearchShareNote(String cn_share_key){
		return noteService.SearchShare(cn_share_key);
	}
	
	/**
	 * 收藏笔记请求
	 * @param note
	 * @param user
	 * @return
	 */
	@RequestMapping("/likenote.do")
	@ResponseBody
	public NoteResult<Note> likeNote(Note note,User user){
		return noteService.addLikeNote(note,user);
	}
	
	/**
	 * 查询当前用户所有的收藏笔记
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/likenotes.do")
	@ResponseBody
	public NoteResult<List<Note>> likeNote(User user) throws Exception{
		return noteService.findLikeNotes(user);
	}
	
	@RequestMapping("/dellikenote.do")
	@ResponseBody
	public NoteResult<Note> delLikeNote(Note note) throws Exception{
		return noteService.deleteLikeNote(note);
	}
}
