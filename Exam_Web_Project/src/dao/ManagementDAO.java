package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Management;
import idao.IManagement;
/**
 * ManagementDAO
 * @author Array
 * @see dao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class ManagementDAO extends BaseDAO<Management> implements IManagement {
	private static final String FINDALL="SELECT * FROM management";
	private static final String MODIFY="UPDATE management SET password=? WHERE id=?";
	
	/**
	 * 返回所有的管理对象
	 * @return 所有的管理对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Management> findAll() throws ClassNotFoundException, SQLException, Exception {
		List<Management> list=new ArrayList<Management>();
		list=query(FINDALL, null);
		if(list.size()>0){
			return list;
		}
		return null;
	}
	/**
	 * 返回单个管理对象@param sql 传入方法的sql语句
	 * @param id 管理对象的id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * @return 返回单个管理对象
	 */
	public Management findById(int id) throws ClassNotFoundException, SQLException, Exception {
		return null;
	}
	/**
	 * 修改管理对象的方法
	 * @param management 新的管理对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public void modify(Management management) throws ClassNotFoundException, SQLException, Exception {
		modify(MODIFY, new Object[]{management.getPassword(),management.getId()});

	}
	/**
	 * 从结果集中获得数据，封装成管理对象返回
	 * @param result 结果集
	 * @return 返回管理对象
	 */
	@Override
	protected Management toEntity(ResultSet result) {
		Management management=new Management();
		try {
			management.setId(result.getInt("id"));
			management.setPassword(result.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return management;
	}

}
