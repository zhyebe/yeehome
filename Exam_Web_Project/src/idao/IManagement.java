package idao;

import java.sql.SQLException;
import java.util.List;

import entity.Management;

/**
 * 管理密码DAO接口类
 * @author Array
 * @see idao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public interface IManagement {
	/**
	 * 查询管理密码
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Management> findAll() throws ClassNotFoundException, SQLException, Exception;
	
	
	/**
	 * 通过id查询管理密码
	 * @param id
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Management findById(int id) throws ClassNotFoundException, SQLException, Exception;
	
	/**
	 * 修改管理密码
	 * @param management
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void modify(Management management) throws ClassNotFoundException, SQLException, Exception;
}
