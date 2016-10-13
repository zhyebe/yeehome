package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import idao.IStudent;
/**
 * StudentDAO用来操作Student表的数据
 * @author Array
 * @see dao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public class StudentDAO extends BaseDAO<Student> implements IStudent {
	private final static String FINDALL="SELECT * FROM student";
	private final static String FINDBYUSERNAME="SELECT student_id,realname,username,password FROM student WHERE username=?";
	private static final String ADDSTUDENT="INSERT INTO student(realname,username,password)VALUES(?,?,?)";
	private static final String DELETEBYID="DELETE FROM student WHERE student_id=?";
	private static final String FINDBYKEYWORD="SELECT * FROM student WHERE username like ? or realname like ?";
	/**
	 * DAO类实现toEntity抽象方法，将查询结果集中的数据封装成Student实体类对象返回
	 * @param result 
	 * @return Student类型的实体
	 */
	protected Student toEntity(ResultSet result) {
		Student student=new Student();
		try {
			student.setId(result.getInt("student_id"));
			student.setRealname(result.getString("realname"));
			student.setPwd(result.getString("password"));
			student.setUsername(result.getString("username"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * DAO类实现抽象方法,根据用户名查询Student
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException
	 * @param username 用户名
	 * @return 查询结果集
	 */
	public Student findByUsername(String username) throws ClassNotFoundException, SQLException, Exception {
		List<Student>students=new ArrayList<Student>();
		students=query(FINDBYUSERNAME,new Object[]{username});
		if(students.size()>0){
			return students.get(0);
		}else{
			return null;
		}
	}
	/**
	 * DAO类实现抽象方法，查询所有的Student
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @return Student所有查询结果集
	 */
	public List<Student> findAll() throws ClassNotFoundException, SQLException, Exception {
		return query(FINDALL, null);
	}
	/**
	 * DAO类实现抽象方法，根据给定di查询Student
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param id
	 */
	public Student findById(int id)throws ClassNotFoundException, SQLException, Exception  {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * DAO类实现抽象方法，根据给定的ID删除Student
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param id
	 */
	public void deleteStudent(int id) throws ClassNotFoundException, SQLException, Exception {
		modify(DELETEBYID, new Object[]{id});
		
	}
	/**
	 * DAO类实现抽象方法，通过id定位修改对应的Student的信息
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param id
	 */
	public void updateStudent(int id) throws ClassNotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * DAO类实现抽象方法，添加新的Student的信息
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param student
	 */
	public void addStudent(Student student)throws ClassNotFoundException, SQLException, Exception  {
		modify(ADDSTUDENT, new Object[]{student.getRealname(),student.getUsername(),student.getPwd()});
	}
	/**
	 * DAO类实现抽象方法，根据关键字模糊查询账号
	 * @param keyWord 关键字
	 * @return 根据关键字模糊查询账号返回Student结果集
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Student> findByKeyWords(String keyWord)
			throws ClassNotFoundException, SQLException, Exception {
		List<Student> list=new ArrayList<Student>();
		String key="%"+keyWord+"%";
		list=query(FINDBYKEYWORD, new Object[]{key,key});
		if(list.size()>0){
			return list;
		}
		return null;
	}
	
}
