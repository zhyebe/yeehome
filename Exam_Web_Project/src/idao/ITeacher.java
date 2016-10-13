package idao;

import java.sql.SQLException;
import java.util.List;

import entity.Teacher;

/**
 * DAO接口，定义TeacherDAO需要实现的方法
 * @author Array
 * @see idao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 * 
 */
public interface ITeacher {
	/**
	 * 根据关键字模糊查询账号
	 * @param keyWord
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Teacher> findByKeyWords(String keyWord)throws ClassNotFoundException, SQLException, Exception ;
	/**
	 *  查询所有的Teacher
	 * @return
	 */
	public List<Teacher> findAll()throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 根据username从teacher表中查询单个数据的方法
	 * @param username
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public Teacher findByUsername(String username) 
			throws ClassNotFoundException, SQLException, Exception;

	/**
	 * 通过学生ID查询Teacher
	 * @param id
	 * @return
	 */
	public Teacher findById(int id)throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 根据给定的ID删除Teacher
	 * @param id
	 */
	public void deleteTeacher(int id)throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 通过id定位修改对应的Teacher的信息
	 * @param teacher
	 */
	public void updateTeacher(int id)throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 添加新的Teacher的信息
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher)throws ClassNotFoundException, SQLException, Exception ;
}

