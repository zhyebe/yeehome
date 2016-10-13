package web;

import idao.IQuestion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDAO;
import entity.Question;
/**
 * 抽题Servlet
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class TestQuestionSelectServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sum;//每套卷子抽取的题目数量，通过配置文件读取
	private int examtime;
	public void init(){//初始化试卷题目数量
		ServletConfig config=this.getServletConfig();
		sum=Integer.parseInt(config.getInitParameter("sum"));
		examtime=Integer.parseInt(config.getInitParameter("examtime"));
	}
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		IQuestion questionDao=new QuestionDAO();
		List<Question>questions=new ArrayList<Question>();
		HttpSession session=request.getSession();
		int student_id=Integer.parseInt(request.getParameter("student_id"));
		int questTotalScore=0;
		try {
			questions=questionDao.findBatch(sum);
			for(Question question:questions){
				questTotalScore+=question.getScore();
			}
			session.setAttribute("questTotalScore", questTotalScore);
			session.setAttribute("qnum", questions.size());

			/*将抽题的Question集合转发到考试页面*/
			Object[] data=new Object[]{student_id,questions};
			request.setAttribute("data",data);
			request.setAttribute("examtime", examtime);
			request.getRequestDispatcher("jsp/exam.jsp").forward(request,response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
