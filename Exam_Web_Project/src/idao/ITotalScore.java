package idao;

import java.sql.SQLException;
import java.util.List;

import entity.TotalScore;
/**
 * DAO接口，定义TotalScore需要实现方法
 * @author Array
 * @see idao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public interface ITotalScore {
	/**
	 * 将一条考试总分信息记录插入总分表的方法
	 * @param score
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void addScore(TotalScore score) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 分页查询根据学生的id查询考试总分
	 * @param student_id
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TotalScore> findScoresById(int student_id,int start,int rows) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 分页查询所有的总分信息
	 * @param start
	 * @param rows
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TotalScore>findAll(int start,int rows) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 分页查询按考试日期查询总分信息
	 * @param exam_date
	 * @param start
	 * @param rows
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TotalScore>findByDate(String exam_date,int start,int rows) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 分页查询所有总分排序
	 * @param start
	 * @param rows
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TotalScore>findAllOrder(int start,int rows) throws ClassNotFoundException, SQLException, Exception;
}
