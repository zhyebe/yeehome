package web;

import idao.IManagement;
import idao.IStudent;
import idao.ITeacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagementDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import entity.Management;
import entity.Student;
import entity.Teacher;
/**
 * 用户注册Servlet
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class TestRegServlet extends HttpServlet {

	/**
	 * 测试注册页面过程——> 1）读取表单数据：通过reg.jsp提交表单到此TestRegServlet进行数据处理
	 * 2）查询数据库已有数据，对比是否已经存在，不存在才能存入数据库（通过调用Dao层写入数据库） 3）判断：写入成功则表示注册成功，否则表示失败
	 */
	private static final long serialVersionUID = 1L;
	

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String realname = request.getParameter("realname");
		String username = request.getParameter("name");
		String password = request.getParameter("pwd");
		String mpassword = request.getParameter("mpwd");
		String option=request.getParameter("leibie");
		IStudent studentDao = new StudentDAO();
		ITeacher teacherDao = new TeacherDAO();
		Student student = new Student();
		Teacher teacher = new Teacher();
		List<Student> students = new ArrayList<Student>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		boolean flag = true;
		try {
			teachers = teacherDao.findAll();
			students = studentDao.findAll();
			if ("1".equals(option)&&!("".equals(username))) {
				// 注册验证老师用户
				List<Management> pwds = new ArrayList<Management>();
				IManagement manageDAO = new ManagementDAO();
				pwds = manageDAO.findAll();
				for (Teacher teach : teachers) {
					if (teach.getUsername().equals(username)) {
					
						request.setAttribute("regmsg", "fail");
						request.setAttribute("alert", "此教师用户已被注册！<a style='color:#f00;' href='jsp/login.jsp'>登陆</a>？");
						request.getRequestDispatcher("jsp/reg.jsp").forward(
								request, response);
						flag = false;
						break;
					}
				}
				boolean checkMpwd = false;
				for (Management pwd : pwds) {
					if(pwd.getPassword().equals(mpassword)){
						checkMpwd=true;
						break;
					}
				}
				if (flag && checkMpwd) {
					teacher.setRealname(realname);
					teacher.setUsername(username);
					teacher.setPwd(password);
					teacherDao.addTeacher(teacher);
					request.setAttribute("regmsg", "ok");
					request.setAttribute("alert", "恭喜你，注册成功！<a style='color:#f00;' href='jsp/login.jsp'>登陆</a>？");
					request.getRequestDispatcher("jsp/reg.jsp").forward(
							request, response);
				}else{
					request.setAttribute("regmsg", "fail");
					request.setAttribute("alert", "<a style='color:#f00;'>注册失败!</a>");
					request.getRequestDispatcher("jsp/reg.jsp").forward(
							request, response);
				}

			} else if("0".equals(option)&&!("".equals(username))){
				// 注册验证学生用户
				for (Student stud : students) {
					if (stud.getUsername().equals(username)) {
						request.setAttribute("regmsg", "fail");
						request.setAttribute("alert", "此学生用户已被注册！<a style='color:#f00;' href='jsp/login.jsp'>登陆</a>？");
						request.getRequestDispatcher("jsp/reg.jsp").forward(
								request, response);
						flag = false;
						break;
					}
				}
				if (flag) {
					student.setRealname(realname);
					student.setUsername(username);
					student.setPwd(password);
					studentDao.addStudent(student);
					request.setAttribute("regmsg", "ok");
					request.setAttribute("alert", "恭喜你，注册成功！<a style='color:#f00;' href='jsp/login.jsp'>登陆</a>？");
					request.getRequestDispatcher("jsp/reg.jsp").forward(
							request, response);
				}
			}
		} catch (ClassNotFoundException e) {
			out.println("注册失败！");
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("注册失败！");
			e.printStackTrace();
		} catch (Exception e) {
			out.println("系统繁忙。。");
			e.printStackTrace();
		}
		out.close();
	}

}
