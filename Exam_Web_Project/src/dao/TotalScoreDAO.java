package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;











import entity.TotalScore;
import idao.ITotalScore;
/**
 * TotalScoreDAO，用来操作TotalScore表的数据
 * @author Array
 * @see dao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */

public class TotalScoreDAO extends BaseDAO<TotalScore> implements ITotalScore {
	private static final String FINDBYID="SELECT * FROM totalScore WHERE student_id=? limit ?,?";
	private static final String ADDSCORE="insert into totalScore(student_id,exam_date,score,examNo)values(?,?,?,?)";
	private static final String FINDALL="select * from totalScore limit ?,?";
	private static final String FINDBYDATE="select * from totalScore where exam_date=? limit ?,?";
	private static final String FINDALLORDER="select * from totalScore order by score desc limit ?,?";
	/**
	 * DAO类实现方法，分页查询根据学生的id查询考试总分
	 * @param student_id 
	 * @param start 开始行
	 * @param rows 行数
	 * @return 学生考试分数
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TotalScore> findScoresById(int student_id,int start,int rows) throws ClassNotFoundException, SQLException, Exception {
		List<TotalScore> scores=new ArrayList<TotalScore>();
		scores=query(FINDBYID, new Object[]{student_id,start,rows});
		if(scores.size()>0){
			return scores;
		}
		return null;
	}
	/**
	 * DAO类实现抽象方法，将查询结果集中的数据封装成TotalScore实体类对象返回
	 * @param result	
	 * @return TotalScore类型的实体
	 */
	@Override
	protected TotalScore toEntity(ResultSet result) {
		TotalScore score=new TotalScore();
		try {
			score.setExam_date(result.getString("exam_date"));
			score.setExamNo(result.getString("examNo"));
			score.setScore(result.getInt("score"));
			score.setStudent_id(result.getInt("student_id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return score;
	}
	/**
	 * DAO类实现抽象方法，添加新的TotalScore的信息
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param score
	 */
	public void addScore(TotalScore score) throws ClassNotFoundException, SQLException, Exception {
		modify(ADDSCORE,new Object[]{score.getStudent_id(),score.getExam_date(),score.getScore(),score.getExamNo()});
		
	}
	/**
	 * DAO类实现抽象方法，查询所有的TotalScore
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @return TotalScore所有查询结果集
	 */
	public List<TotalScore> findAll(int start, int rows) throws ClassNotFoundException, SQLException, Exception {
		List<TotalScore>scores=new ArrayList<TotalScore>();
		scores=query(FINDALL,new Object[]{start,rows});
		if(scores.size()>0){
			return scores;
		}
		return null;
	}
	/**
	 * DAO类实现抽象方法，分页查询按考试日期查询总分信息
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @param exam_date 考试时间
	 * @param start 开始行
	 * @param rows 行数
	 * @return scores 考试分数
	 */
	public List<TotalScore> findByDate(String exam_date, int start, int rows) throws ClassNotFoundException, SQLException, Exception {
		List<TotalScore>scores=new ArrayList<TotalScore>();
		scores=query(FINDBYDATE,new Object[]{exam_date,start,rows});
		if(scores.size()>0){
			return scores;
		}
		return null;
	}
	
	/**
	 * DAO类实现抽象方法，分页查询所有总分排序
	 * @param start 开始行
	 * @param rows 行数
	 * @return scores 分数
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TotalScore> findAllOrder(int start, int rows) throws ClassNotFoundException, SQLException, Exception {
		List<TotalScore>scores=new ArrayList<TotalScore>();
		scores=query(FINDALLORDER,new Object[]{start,rows});
		if(scores.size()>0){
			return scores;
		}
		return null;
	}

}
