package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.StudentAnswer;
import idao.IStudentAnswer;
/**
 * 学生答案DAO
 * @author Array
 * @see dao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public class StudentAnswerDAO extends BaseDAO<StudentAnswer> implements IStudentAnswer {
	private static final String INSERTBATCH=
			"insert into studentAnswers (questionNo,answer," +
			"student_id,examNo,exam_date) values(?,?,?,?,?)";
	/**
	 * 
	 * 重载modify方法，批量执行SQL
	 * @param answers sql语句的参数
	 * @param n 批量执n行的次数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public void insertBatch(Object[][]answers,int n) 
				throws SQLException, ClassNotFoundException, Exception {
		modify(INSERTBATCH,answers,n);
		
	}
	/**
	 * 抽象方法，由DAO类实现，得到结果集封装返回
	 * @param result 结果集
	 */
	@Override
	protected StudentAnswer toEntity(ResultSet result) {
		return null;
	}

}
