package com.yeehome.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeehome.entity.NoteResult;
import com.yeehome.entity.Notebook;
import com.yeehome.entity.User;
import com.yeehome.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class NoteBookController {
	@Resource
	NoteBookService noteBookService;
	
	/**
	 * 接受ajax请求，从cookie获得username传回，使用user对象获取username
	 * 传入service层处理，返回所有该用户的笔记本，发送到view端显示在页面
	 * @param user
	 * @return
	 */
	@RequestMapping("/shownotebook.do")
	@ResponseBody
	public NoteResult<List<Notebook>> showNoteBook(User user){
		return noteBookService.findAllNoteBook(user);
	}
	
	/**
	 * 用户添加笔记本请求处理，送回服务端处理
	 * @param user
	 * @return
	 */
	@RequestMapping("/createnotebook.do")
	@ResponseBody
	public NoteResult<Notebook> createNoteBook(String cn_user_name,String cn_notebook_name){
		return noteBookService.addNoteBook(cn_user_name,cn_notebook_name);
	}
	
	/**
	 * 重命名笔记本名称
	 * @param notebook
	 * @return
	 */
	@RequestMapping("/renamenotebook.do")
	@ResponseBody
	public NoteResult<Notebook> renameNoteBook(Notebook notebook){
		return noteBookService.modifyNoteBookName(notebook);
	}
	
	/**
	 * 处理删除笔记本的请求，彻底删除，包括其中的笔记
	 * @param notebook
	 * @return
	 */
	@RequestMapping("/deletenotebook.do")
	@ResponseBody
	public NoteResult<Notebook> deleteNoteBook(Notebook notebook){
		return noteBookService.deleteNoteBookAndNote(notebook);
	}
}
