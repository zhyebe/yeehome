package web;

import idao.IQuestion;
import idao.IStudentAnswer;
import idao.ITotalScore;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDAO;
import dao.StudentAnswerDAO;
import dao.TotalScoreDAO;
import entity.Question;
import entity.TotalScore;
/**
 * web测试类用来测试一次性提交表单中的所有数据到另一个表
 * 用于测试考试交卷的时候的交卷功能。
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class TestSubmitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int scorePattern;
	public void init(){
		ServletConfig config=this.getServletConfig();
		scorePattern=Integer.parseInt(config.getInitParameter("scorePattern"));
	}
	public void service(HttpServletRequest request, HttpServletResponse response)
		
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		/*初始参数*/
		//题目数量
		int qnum=(Integer)session.getAttribute("qnum");
		//学生ID
		int student_id=Integer.parseInt(request.getParameter("student_id"));
		//考试日期，选择当前系统时间
		String exam_date=new SimpleDateFormat("yyyyMMdd").format(new Date());
		//考试场次号(根据系统时间生成)
		String examNo=new SimpleDateFormat("yyyyMMdd").format(new Date());
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//PrintWriter out =response.getWriter();
		
		/*从题目页面上抓取填写的表单，并把它放进答案表中
		 * 二维数组说明：1行参数：随机抽取到的题目的数量 2:列参数：答案表中的字段数
		 * */
		
		Object[][] obj=new Object[qnum][5];
		for(int i=0;i<qnum;i++){
			String answer="";
			String[] answers=request.getParameterValues(""+(i+1));
			if(answers==null){
				answers=new String[]{"blank"};
			}
			for(int j=0;j<answers.length;j++){
				answer+=answers[j];
				
			}
			obj[i][0]=request.getParameter("q"+(i+1));
			obj[i][1]=answer;
			obj[i][2]=student_id;
			obj[i][3]=examNo;
			obj[i][4]=exam_date;
		}
		/*调用DAO将读得的答案数据批量插入数据表*/
		IStudentAnswer saDao=new StudentAnswerDAO();
		try {
			saDao.insertBatch(obj, qnum);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*批改，遍历二维数组并调用QuestionDAO与正确答案进行对比然后得出分数值*/
		IQuestion questionDao=new QuestionDAO();
		Question question=null;
		int score=0;
		for(int i=0;i<qnum;i++){
			int questionNo=Integer.parseInt(obj[i][0].toString());
			String answer=obj[i][1].toString();
			
			try {
				question=questionDao.findQAnswer(questionNo);
				if(question!=null){
					if(question.getAnswer().equals(answer)){
						score+=question.getScore();
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*将考试的成绩以及相关信息存入总分表*/
		int questTotalScore=(Integer)session.getAttribute("questTotalScore");
		score=(int)(((double)score/(double)questTotalScore)*(double)scorePattern);
		ITotalScore scoreDao=new TotalScoreDAO();
		TotalScore totalscore=new TotalScore();
		totalscore.setScore(score);
		totalscore.setExam_date(exam_date);
		totalscore.setExamNo(examNo);
		totalscore.setStudent_id(student_id);
		try {
			scoreDao.addScore(totalscore);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//跳转回登陆页面
		response.sendRedirect("jsp/login.jsp");
	}

}
