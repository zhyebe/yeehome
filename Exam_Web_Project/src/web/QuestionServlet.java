package web;

import idao.IQuestion;





import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDAO;
import entity.Question;

/**
 * 题库Servlet
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 * 
 */
public class QuestionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uri = request.getRequestURI();
		String select = uri.substring(uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		int start = 0;
		int rows = 4;
		HttpSession session = request.getSession();
		if ("/firstlist".equals(select)) {// 显示第一页
			List<Question> questions = new ArrayList<Question>();
			IQuestion questionDao = new QuestionDAO();
			// 获取隐藏域信息判断是哪个查询的请求
			String queryoption = request.getParameter("queryoption");
			try {
				if ("questionNo".equals(queryoption)) {
					// 根据题号查询
					session.setAttribute("queryoption", "questionNo");

					int questionNo = Integer.parseInt(request
							.getParameter("questionNo"));
					questions = questionDao.findByQuestionNo(questionNo, start,
							rows);
					session.setAttribute("questionNo", questionNo);
				} else if ("keyword".equals(queryoption)) {
					// 根据关键字查询
					session.setAttribute("queryoption", "keyword");
					String keyword = request.getParameter("keyword");
					questions = questionDao.findByKeyword(keyword, start, rows);
					session.setAttribute("keyword", keyword);
				}else{
					questions = questionDao.findAll();
				}

				request.setAttribute("questions", questions);
				request.setAttribute("start", start);
				request.setAttribute("rows", rows);
				request.getRequestDispatcher("jsp/question.jsp").forward(
						request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else if ("/back".equals(select)) {
			// 向前翻
			List<Question> questions = new ArrayList<Question>();
			int questionNo = -1;
			String keyword = "";
			IQuestion questionDao = new QuestionDAO();
			int page = Integer.parseInt(request.getParameter("page"));
			String queryoption="";
			if(session.getAttribute("queryoption")==null){
				queryoption="";
			}else{
				queryoption = session.getAttribute("queryoption").toString();
			}
			/* 调整翻页页码 */
			start = (page - 2) * rows;
			if (start < 0) {
				start = 0;
			}
			if ("questionNo".equals(queryoption)) {
				// 当查询模式为按题号查询时
				questionNo = (Integer) session.getAttribute("questionNo");
				try {
					questions = questionDao.findByQuestionNo(questionNo, start,
							rows);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if ("keyword".equals(queryoption)) {
				// 当查询模式为按关键字查询时
				keyword = session.getAttribute("keyword").toString();
				try {
					questions = questionDao.findByKeyword(keyword, start, rows);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					questions=questionDao.findAll();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("questions", questions);
			request.setAttribute("start", start);
			request.setAttribute("rows", rows);
			request.getRequestDispatcher("jsp/question.jsp").forward(request,
					response);

		} else if ("/forward".equals(select)) {
			// 向后翻
			List<Question> questions = new ArrayList<Question>();
			int questionNo = -1;
			String keyword = "";
			IQuestion questionDao = new QuestionDAO();
			String queryoption="";
			int page = Integer.parseInt(request.getParameter("page"));
			if(session.getAttribute("queryoption")==null){
				queryoption="";
			}else{
				queryoption = session.getAttribute("queryoption").toString();
			}
			/* 预查询，调整翻页 */
			boolean turn = true;
			if ("questionNo".equals(queryoption)) {
				questionNo = (Integer) session.getAttribute("questionNo");
				try {
					questions = questionDao.findByQuestionNo(questionNo,
							(page - 1) * rows, rows * 2);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if ("keyword".equals(queryoption)) {
				keyword = session.getAttribute("keyword").toString();
				try {
					questions = questionDao.findByKeyword(keyword, (page - 1)
							* rows, rows * 2);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}else{
				try {
					questions = questionDao.findAll();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (questions.size() <= 4) {
				turn = false;
			}
			if (turn) {
				start = page * rows;
			} else {
				start = (page - 1) * rows;
			}
			if ("questionNo".equals(queryoption)) {
				try {
					questions = questionDao.findByQuestionNo(questionNo, start,
							rows);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if ("keyword".equals(queryoption)) {
				try {
					questions = questionDao.findByKeyword(keyword, start, rows);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("start", start);
			request.setAttribute("rows", rows);
			request.setAttribute("questions", questions);
			request.getRequestDispatcher("jsp/question.jsp").forward(request,
					response);
		} else if ("/delete".equals(select)) {
			// 删除按钮按下后删除操作
			IQuestion questionDao = new QuestionDAO();
			int questionNo = Integer.parseInt(request
					.getParameter("questionNo"));
			try {
				questionDao.deleteByQuestionNo(questionNo);
				response.sendRedirect("jsp/question.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/load".equals(select)) {
			// 加载一道题

			int questionNo = Integer.parseInt(request
					.getParameter("questionNo"));
			IQuestion questionDao = new QuestionDAO();
			try {
				Question old_question = questionDao
						.loadByQuestionNo(questionNo);
				String quest=old_question.getQuestion();
				quest=quest.replaceAll("&nbsp", " ").replaceAll("<br>", "\r\n");
				old_question.setQuestion(quest);
				request.setAttribute("old_question", old_question);
				request.getRequestDispatcher("jsp/question.jsp").forward(
						request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/update".equals(select)) {
			// 修改一道已经加载的题
			// 获得加载的题目的各个属性数据
			Question question = new Question();
			if("0".equals(request.getParameter("score"))){
				response.sendRedirect("jsp/question.jsp");
				return;
			}
			try {
				String quest=request.getParameter("question");
				quest=quest.replaceAll(" ", "&nbsp").replaceAll("\r\n", "<br>");
				question.setQuestion(quest);
				question.setScore(Integer.parseInt(request
						.getParameter("score")));
				/* 设定复选框 */
				String[] answer = request.getParameterValues("answer");
				String ans = "";
				for (int i = 0; i < answer.length; i++) {
					ans += answer[i];
				}
				question.setAnswer(ans);
				IQuestion questionDao = new QuestionDAO();

				if (request.getParameter("questionNo") != null
						&& !("".equals(request.getParameter("questionNo")))) {
					// 如果有题号提交过来不为空就判别为修改

					question.setQuestionNo(Integer.parseInt(request
							.getParameter("questionNo")));
					questionDao.updateByQuestionNo(question);
				} else {
					// 如果题号为空，就执行添加
					questionDao.addQuestion(question);
				}
				response.sendRedirect("jsp/question.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NumberFormatException e){
				response.sendRedirect("jsp/question.jsp");
			}catch (Exception e) {
				e.printStackTrace();
			}

		}else if("/all".equals(select)){
			List<Question> questions = new ArrayList<Question>();
			IQuestion questionDao = new QuestionDAO();
			try {
				questions=questionDao.findAll();
				request.setAttribute("questions", questions);
				request.setAttribute("start", start);
				request.setAttribute("rows", rows);
				request.getRequestDispatcher("jsp/question.jsp").forward(request,
						response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
