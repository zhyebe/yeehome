package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import idao.ITeacher;
import entity.Teacher;
/**
 * TeacherDAO，用来操作Teacher表的数据
 * @author Array
 * @see dao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class TeacherDAO extends BaseDAO<Teacher> 
	implements ITeacher{
	private final static String FINDALL="SELECT * FROM teacher";
	private final static String FINDBYUSERNAME="SELECT realname,username,password,teacher_id FROM teacher WHERE username=?";
	private final static String ADDTEACHER="INSERT INTO teacher(realname,username,password)VALUES(?,?,?)";
	private final static String DELETEBYID="DELETE FROM teacher WHERE teacher_id=?";
	private final static String FINDBYKEYWORD="SELECT * FROM teacher WHERE username like ? or realname like ?";
	/**
	 * DAO类实现toEntity抽象方法，将查询结果集中的数据封装成Teacher实体类对象返回
	 * @param result	
	 * @return Teacher类型的实体
	 */
	protected Teacher toEntity(ResultSet result){
		Teacher teacher=new Teacher();
		try {
			teacher.setId(result.getInt("teacher_id"));
			teacher.setRealname(result.getString("realname"));
			teacher.setUsername(result.getString("username"));
			teacher.setPwd(result.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}
	/**
	 * DAO类实现抽象方法,根据用户名查询Teacher
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException
	 * @param username 用户名
	 * @return 查询结果集
	 */
	public Teacher findByUsername(String username) throws 
		ClassNotFoundException, SQLException, Exception {
		List<Teacher>teachers=new ArrayList<Teacher>();
		teachers=query(FINDBYUSERNAME,new Object[]{username});
		if(teachers.size()>0){
			return teachers.get(0);
		}else{
			return null;
		}
	}
	/**
	 * DAO类实现抽象方法，查询所有的Teacher
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @return Student所有查询结果集
	 */
	public List<Teacher> findAll() throws ClassNotFoundException, SQLException,
			Exception {
		
		return query(FINDALL, null);
	}
	/**
	 * DAO类实现抽象方法，根据给定di查询Teacher
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param id
	 */
	public Teacher findById(int id) throws ClassNotFoundException,
			SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * DAO类实现抽象方法，根据给定的ID删除Teacher
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param id
	 */
	public void deleteTeacher(int id) throws ClassNotFoundException,
			SQLException, Exception {
		modify(DELETEBYID, new Object[]{id});
		
	}
	/**
	 * DAO类实现抽象方法，通过id定位修改对应的Teacher的信息
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param id
	 */
	public void updateTeacher(int id) throws ClassNotFoundException,
			SQLException, Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * DAO类实现抽象方法，添加新的Teacher的信息
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher) throws ClassNotFoundException,
			SQLException, Exception {
		modify(ADDTEACHER, new Object[]{teacher.getRealname(),teacher.getUsername(),teacher.getPwd()});
		
		
	}
	/**
	 * DAO类实现抽象方法，根据关键字模糊查询账号
	 * @param keyWord 关键字
	 * @return 根据关键字模糊查询账号返回Teacher结果集
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Teacher> findByKeyWords(String keyWord)
			throws ClassNotFoundException, SQLException, Exception {
		List<Teacher> list=new ArrayList<Teacher>();
		String key="%"+keyWord+"%";
		list=query(FINDBYKEYWORD, new Object[]{key,key});
		if(list.size()>0){
			return list;
		}
		return null;
	}
	

}
