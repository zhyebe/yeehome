package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import utils.MathUtils;
import entity.Question;
import idao.IQuestion;
/**
 * QuestionDao用来操作question 表
 * @author Array
 * @see dao
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public class QuestionDAO extends BaseDAO<Question> implements IQuestion {
	private static final String FINDALL="select * from question";
	private static final String ADDQUESTION="insert into question(question,answer,score)values(?,?,?)";
	private  static final String SELECT_QUESTION = "select * from question where questionNo in";
	private static final String FINDONEANSWER="select questionNo,score,answer,question from question where questionNo=?";
	private static final String FINDBYQUESTIONNO="select questionNo,score,answer,question from question where questionNo=? limit ?,?";
	private static final String FINDBYKEYWORD="select questionNo,score,answer,question from question where question like ? limit ?,?";
	private static final String DELETEBYQUESTIONNO="delete from question where questionNo=?";
	private static final String LOADBYQUESTIONNO="select questionNo,score,answer,question from question where questionNo=?";
	private static final String UPDATEBYQUESTIONNO="update question set question=?,answer=?,score=? where questionNo=?";
	/**
	 * 创建Question表DAO
	 * @param result 结果集
	 * @return question 返回Question表
	 * 
	 */
	protected Question toEntity(ResultSet result) {
		Question question=new Question();
		try {
			question.setAnswer(result.getString("answer"));
			question.setScore(result.getInt("score"));
			question.setQuestion(result.getString("question"));
			question.setQuestionNo(result.getInt("questionNo"));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return question;
	}
	
	/**
	 * 抽题方法
	 * @param sum
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * @return all 返回抽取题目的集合
	 */
	public List<Question> findBatch(int sum) throws ClassNotFoundException, SQLException, Exception {
		/*获得题库中所有题目的NO.*/
		List<Integer> ints=new ArrayList<Integer>();
		List<Question> all=findAll();
		int size=all.size();
		for(Question question:all){
			ints.add(question.getQuestionNo());
		}
		/*如果需要抽取的题目数量超过了题库数量直接中止方法并返回空*/
		if(sum>size&&size>0){
			return all;
		}
		/*生成随机数集合*/
		Set<Integer> randomsId = MathUtils.getSet(sum,ints);
		/*生成动态ＳＱＬ语句*/
		String sql=MathUtils.getSql(SELECT_QUESTION, "?", sum);
		/*设定调用BaseDAOquery方法的两个参数(sql已经准备好了)*/
		Object[]params=new Object[sum];
		int index=0;
		for(Integer num:randomsId){
			params[index]=num;
			index++;
		}
		return query(sql,params);
	}
	/**
	 * 返回所有题目
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * @return 所有题目
	 */
	public List<Question> findAll() throws ClassNotFoundException, SQLException, Exception {
		return query(FINDALL,null);
	}
	
	/**
	 * 插入一道题目的方法
	 * @param question 题目对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public void addQuestion(Question question) 
			throws ClassNotFoundException, 
			SQLException, Exception{
		modify(ADDQUESTION,new Object[]
				{question.getQuestion(),
				question.getAnswer(),question.getScore()});
		
	}
	/**
	 * 根据题库题号查询一道题的方法
	 * @param questionNo 题号
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Question findQAnswer(int questionNo) throws ClassNotFoundException, SQLException, Exception{
		return query(FINDONEANSWER,new Object[]{questionNo}).get(0);
	}
	/**
	 * 查询题目集
	 * @param questionNo 题号
	 * @param start 开始
	 * @param rows 行数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * @return 返还查询题目集
	 */
	public List<Question> findByQuestionNo(int questionNo,int start,int rows) throws ClassNotFoundException, SQLException, Exception {

		List<Question> questions=new ArrayList<Question>();
		questions=query(FINDBYQUESTIONNO,new Object[]{questionNo,start,rows});
		if(questions.size()>0){
			
			return questions;
		}else{
			return null;
		}
		
	}
	/**
	 * @param keyword 关键字 
	 * @param start 开始
	 * @param rows 行数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * @return 返回单个管理对象
	 */
	public List<Question> findByKeyword(String keyword,int start, int rows) throws ClassNotFoundException, SQLException, Exception {
		List<Question>questions=new ArrayList<Question>();
		String key='%'+keyword+'%';
		questions=query(FINDBYKEYWORD,new Object[]{key,start,rows});
		if(questions.size()>0){
			return questions;
		}
		return null;
	}
	/**
	 * 通过题号删除题目
	 * @param questionNo 题号
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteByQuestionNo(int questionNo) throws ClassNotFoundException, SQLException, Exception {
		modify(DELETEBYQUESTIONNO,new Object[]{questionNo});
		
	}
	/**
	 * 根据题号增加题目
	 * @param questionNo 题号
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 * @return 根据题号增加题目
	 */
	public Question loadByQuestionNo(int questionNo) throws ClassNotFoundException, SQLException, Exception {
		List<Question>questions=new ArrayList<Question>();
		questions=query(LOADBYQUESTIONNO,new Object[]{questionNo});
		if(questions.size()>0){
			return questions.get(0);
		}
		return null;
	}
	/**
	 * 根据题号修改题目
	 * @param questionNo 题号
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updateByQuestionNo(Question question) throws ClassNotFoundException, SQLException, Exception{
		modify(UPDATEBYQUESTIONNO,new Object[]{question.getQuestion(),question.getAnswer(),question.getScore(),question.getQuestionNo()});
		
	}


}
