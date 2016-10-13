package web;

import idao.IStudent;
import idao.ITeacher;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDAO;
import dao.TeacherDAO;
import entity.Student;
import entity.Teacher;
/**
 * 登陆Servlet
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public class LoginServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*从页面获取用户输入的帐号和密码*/
		String username=request.getParameter("name");
		String password=request.getParameter("password");
		/*从页面获取用户在单选框的选项*/
		String option=request.getParameter("accountselect");
		String table_password=null;
		int table_student_id=0;
		int table_teacher_id=0;
		Student student=null;
		Teacher teacher=null;
		IStudent studentDao=new StudentDAO();
		ITeacher teacherDao=new TeacherDAO();
		
		HttpSession session=request.getSession();
		
		
		//判断登录属性并跳转相应的查询数据库取得对应的密码
		if("student".equals(option)){
			//查询学生表
			try {
				student=studentDao.findByUsername(username);
				if(student==null){
					table_password=null;
				}else{
					table_password=student.getPwd();
					table_student_id=student.getId();
					session.setAttribute("table_id",table_student_id);
					
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("teacher".equals(option)){
			//查询老师表
			try {
				teacher=teacherDao.findByUsername(username);
				if(teacher==null){
					table_password=null;
				}else{
					table_password=teacher.getPwd();
					table_teacher_id=teacher.getId();
					session.setAttribute("table_id", table_teacher_id);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*比较密码核对结果和单选框选项来控制跳转（重定向）*/
		if(table_password!=null&&password.equals(table_password)&&"teacher".equals(option)){
			/*如果查到的表中密码不为空，
			 * 且查到的密码与输入的密码相等
			 * 且option选项选择的是管理帐号的时候
				那么就跳转管理页面
			*/
	
			request.setAttribute("teacher_id", table_teacher_id);
			request.getRequestDispatcher("jsp/teacherOption.jsp")
					.forward(request, response);
			
		}else if(table_password!=null&&password.equals(table_password)&&"student".equals(option)){
			/*如果查到的表中密码不为空，
			 * 且查到的密码与输入的密码相等
			 * 且option选项选择的是学生帐号的时候
				那么就跳转学生页面
			*/
			request.setAttribute("student_id", table_student_id);
			request.getRequestDispatcher("jsp/studentOption.jsp")
					.forward(request, response);
		}else{
			/*如果验证失败，直接转发回JSP然后JSP弹框一个失败提示*/
			request.setAttribute("fail_msg", "error");
			//response.sendRedirect("jsp/login.jsp");
			request.getRequestDispatcher("jsp/login.jsp").
				forward(request,response);
			//
		}	
		
		
	}

}
