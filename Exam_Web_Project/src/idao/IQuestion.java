package idao;

import java.sql.SQLException;
import java.util.List;

import entity.Question;

/**
 * QuestionDAO对应接口，定义QuestionDAO中要实现的方法
 * @author Array
 * @see idao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public interface IQuestion {
	/**
	 * 抽题方法：随机抽取规定数量的题目并通过集合返回
	 * @param n 抽取题目的数量
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Question> findBatch(int n) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 查出所有的题目
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Question> findAll() throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 添加一道题目
	 * @param question被添加的题目对象
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void addQuestion(Question question) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 根据题库题号查询特定题的答案
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Question findQAnswer(int questionNo)
			throws ClassNotFoundException,
			SQLException, Exception;
	/**
	 * 分页根据题库题号来查询题目
	 * @param questionNo
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Question>findByQuestionNo(int questionNo,int start,int rows) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 分页根据题目当中的关键字查询题目
	 * @param keyword
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Question> findByKeyword(String keyword,int start,int rows) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 根据题号删除题库中的某题
	 * @param questionNo
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void deleteByQuestionNo(int questionNo) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 根据题号加载题库中的某题的方法
	 * @param questionNo
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Question loadByQuestionNo(int questionNo) throws ClassNotFoundException, SQLException, Exception;
	/**
	 * 修改存在的某个题目的方法
	 * @param question
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updateByQuestionNo(Question question) throws ClassNotFoundException, SQLException, Exception;
}
