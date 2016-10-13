package idao;

import java.sql.SQLException;

/**
 * 学生答案表的DAO接口定义操作该表的DAO方法
 * @author Array
 * @see idao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public interface IStudentAnswer {
	/**
	 * 定义批量提交考试答题答案到学生答案表的方法
	 * @param answers 保存答案数据的二维数组
	 * @param n 题目的数量
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void insertBatch(Object[][]answers,int n) throws SQLException, ClassNotFoundException, Exception;
}
