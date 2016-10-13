package web;


import idao.IStudent;
import idao.ITotalScore;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.StudentDAO;
import dao.TotalScoreDAO;

import entity.Student;
import entity.TotalScore;
/**
 * 处理管理账号对学生考试信息的查询请求的Servlet
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class StudentManageServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*使用DAO根据学院账号或者考试日期或者totalScore表上的条目(分页)*/
		String uri=request.getRequestURI();
		String select=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		//定义分页查询的起始点和查询的条目数
		int start=0;
		int rows=4;
	
		HttpSession session=request.getSession();
		/*DAO的相关变量*/
		Student student=new Student();
		String exam_date=null;
		if("/firstlist".equals(select)){//显示第一页
			List<TotalScore> scores=new ArrayList<TotalScore>();
			ITotalScore scoreDAO=new TotalScoreDAO();
			//获取隐藏域信息判断是哪个查询的请求
			String queryoption=request.getParameter("queryoption");
			try {
				if("username".equals(queryoption)){
					//根据学生账号查询请求
					session.setAttribute("queryoption","username");
					IStudent studentDao=new StudentDAO();
					student=studentDao.findByUsername(request.getParameter("username"));
					if(student!=null){
						scores=scoreDAO.findScoresById(student.getId(), start, rows);
						session.setAttribute("studentId",student.getId());
					}else{
						scores=null;
					}
				}else if("date".equals(queryoption)){
					//根据日期查询的请求
					session.setAttribute("queryoption","date");
					/*从页面上获取日期*/
					String year=request.getParameter("year");
					String month=request.getParameter("month");
					String date=request.getParameter("date");
					/*月份,日期小于10前面补0*/
					if(Integer.parseInt(month)<10){
						month="0"+month;
					};
					if(Integer.parseInt(date)<10){
						date="0"+date;
					}
					String examDate=year+""+month+""+date;
					/*使用SimpleDateFormat 进行日期格式检查*/
					exam_date=new SimpleDateFormat("yyyyMMdd").
							format(new SimpleDateFormat("yyyyMMdd").
									parse(examDate));
					session.setAttribute("examdate",exam_date);
					scores=scoreDAO.findByDate(exam_date, start, rows);
				}else if("all".equals(queryoption)){
					//查询所有的请求
					session.setAttribute("queryoption","all");
					scores=scoreDAO.findAll(start, rows);
				}else if("order".equals(queryoption)){
					//排名查询的请求
					session.setAttribute("queryoption","order");
					scores=scoreDAO.findAllOrder(start, rows);
				}
				request.setAttribute("scores", scores);
				request.setAttribute("start",start);
				request.setAttribute("rows",rows);
				request.getRequestDispatcher("jsp/student.jsp")
					.forward(request, response);
			}catch(ParseException e){
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
			
				e.printStackTrace();
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}else if("/forward".equals(select)){//翻到下一页
			int studentId=0;
			String examdate="";
			List<TotalScore> scores=new ArrayList<TotalScore>();
			ITotalScore scoreDAO=new TotalScoreDAO();
			int page=Integer.parseInt(request.getParameter("page"));
			boolean turn=true;
			String queryoption=session.getAttribute("queryoption").toString();
			try {
				if("username".equals(queryoption)){
					//当查询状态为按学生账号查询时
					Object obj=session.getAttribute("studentId");
					if(obj!=null){
						studentId=Integer.parseInt(obj.toString());
						scores=scoreDAO.findScoresById(studentId, (page-1)*rows, rows*2);
					}
				}else if("date".equals(queryoption)){
					//当状态为按日期状态查询时
					examdate=session.getAttribute("examdate").toString();
					scores=scoreDAO.findByDate(examdate,(page-1)*rows, rows*2);
				}else if("all".equals(queryoption)){
					//当查询状态为查询所有时
					scores=scoreDAO.findAll((page-1)*rows, rows*2);
				}else if("order".equals(queryoption)){
					//当查询状态为排名查询时
					scores=scoreDAO.findAllOrder((page-1)*rows, rows*2);
				}
				
				if(scores.size()<=4){
					turn=false;
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(turn){
				start=page*rows;
			}else{
				start=(page-1)*rows;
			}
			try {
				if("username".equals(queryoption)){
					scores=scoreDAO.findScoresById(studentId, start, rows);
				}else if("date".equals(queryoption)){
					scores=scoreDAO.findByDate(examdate, start, rows);
				}else if("all".equals(queryoption)){
					scores=scoreDAO.findAll(start, rows);
				}else if("order".equals(queryoption)){
					scores=scoreDAO.findAllOrder(start, rows);
				}
				request.setAttribute("start",start);
				request.setAttribute("rows",rows);
				request.setAttribute("scores", scores);
				request.getRequestDispatcher("jsp/student.jsp")
						.forward(request, response);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/back".equals(select)){//翻到上一
			int studentId=0;
			String examdate="";
			List<TotalScore> scores=new ArrayList<TotalScore>();
			ITotalScore scoreDAO=new TotalScoreDAO();
			int page=Integer.parseInt(request.getParameter("page"));
			String queryoption=session.getAttribute("queryoption").toString();
			start=(page-2)*rows;
			if(start<0){
				start=0;
			}
			try {
				if("username".equals(queryoption)){
					Object obj=session.getAttribute("studentId");
					if(obj!=null){
						studentId=Integer.parseInt(obj.toString());
						scores=scoreDAO.findScoresById(studentId, start, rows);
					}
				}else if("date".equals(queryoption)){
					examdate=session.getAttribute("examdate").toString();
					scores=scoreDAO.findByDate(examdate, start, rows);
				}else if("all".equals(queryoption)){
					scores=scoreDAO.findAll(start, rows);
				}else if("order".equals(queryoption)){
					scores=scoreDAO.findAllOrder(start, rows);
				}
				request.setAttribute("rows",rows);
				request.setAttribute("start",start);
				request.setAttribute("scores", scores);
				request.getRequestDispatcher("jsp/student.jsp")
						.forward(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
