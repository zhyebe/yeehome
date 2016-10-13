package idao;

import java.sql.SQLException;
import java.util.List;

import entity.Student;

/**
 * DAO接口，定义StudentDAO需要实现的方法
 * @author Array
 * @see idao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public interface IStudent {
	/**
	 * 根据关键字模糊查询账号
	 * @param keyWord
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Student> findByKeyWords(String keyWord)throws ClassNotFoundException, SQLException, Exception ;
	/**
	 *  查询所有的Student
	 * @return
	 */
	public List<Student> findAll()throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 根据用户名查询Student
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Student findByUsername(String username) throws ClassNotFoundException, SQLException, Exception;
	
	/**
	 * 通过学生ID查询Student
	 * @param id
	 * @return
	 */
	public Student findById(int id)throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 根据给定的ID删除Student
	 * @param id
	 */
	public void deleteStudent(int id)throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 通过id定位修改对应的Student的信息
	 * @param id
	 */
	public void updateStudent(int id)throws ClassNotFoundException, SQLException, Exception ;
	
	/**
	 * 添加新的Student的信息
	 * @param student
	 */
	public void addStudent(Student student)throws ClassNotFoundException, SQLException, Exception ;
}
