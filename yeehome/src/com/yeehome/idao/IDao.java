package com.yeehome.idao;

import java.util.List;
import java.util.Map;

import com.yeehome.entity.Notebook;


public interface IDao<Type> {
	/**
	 * 添加实体类数据
	 * @param type
	 * @throws Exception 
	 */
	public void add(Type type);
	
	/**
	 * 删除实体类数据
	 * @param obj
	 * @throws Exception 
	 */
	public void deleteBy(Object param);
	
	/**
	 * 查询所以的数据
	 * @return
	 * @throws Exception 
	 */
	public List<Type> findAll();
	public void deleteById(Type type);
	public List<Type> findByObject(Object param);
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public Type findByString(String param);
	public Type findByInt(int param);
	public List<Type> findBykey(String key);
	public List<Type> findById(String id);
	public Type findByType(Type type);
	public List<Type> findByUserId(String id);
	/**
	 * 修改数据
	 * @param type
	 * @throws Exception 
	 */
	public void modify(Type type);
	
	public void update(Type type);
	/**
	 * 修改密码
	 * @param type
	 */
	public void updatePwd(Type type);

	public void modifyType(Type type);
}
