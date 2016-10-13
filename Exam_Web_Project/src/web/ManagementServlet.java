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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import dao.ManagementDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import entity.Management;
import entity.Student;
import entity.Teacher;
/**
 * 管理密码的Servlet
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class ManagementServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String uri=request.getRequestURI();
		String select=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		HttpSession session=request.getSession();
		if("/check".equals(select)){
			List<Management> pwds=new ArrayList<Management>();
			String inputPwd=request.getParameter("password");
			IManagement manageDao=new ManagementDAO();
			try {
				pwds=manageDao.findAll();
				boolean check=false;
				if(pwds!=null){
					for (Management pwd:pwds) {
						if(pwd.getPassword().equals(inputPwd)){
							check=true;
							session.setAttribute("pwdId", pwd.getId());
							break;
						}
					}
				}
				if(check){
//					request.setAttribute("checkMsg", "pass");
//					request.getRequestDispatcher("jsp/management.jsp")
//						.forward(request, response);
					out.println("pass");
				}else{
//					request.setAttribute("checkMsg", "fail");
//					request.getRequestDispatcher("jsp/management.jsp")
//						.forward(request, response);
					out.println("fail");
				}
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
		}else if("/modifyPwd".equals(select)){
			int pwdId=(Integer)session.getAttribute("pwdId");
			String newPwd=request.getParameter("newPwd");
			IManagement manageDAO=new ManagementDAO();
			Management management=new Management();
			management.setId(pwdId);
			management.setPassword(newPwd);
			try {
				manageDAO.modify(management);
				out.println("pass");
			} catch (ClassNotFoundException e) {
				out.println("fail");
				e.printStackTrace();
			} catch (SQLException e) {
				out.println("fail");
				e.printStackTrace();
			} catch (Exception e) {
				out.println("fail");
				e.printStackTrace();
			}
//			request.setAttribute("modifyMsg", "ok");
//			request.getRequestDispatcher("jsp/management.jsp")
//				.forward(request, response);
			
		}else if("/delAccount".equals(select)){
			String option=request.getParameter("option");
			if("student".equals(option)){
				int student_id=Integer.parseInt(request.getParameter("table_id"));
				IStudent studentDAO=new StudentDAO();
				try {
					studentDAO.deleteStudent(student_id);
					out.println("删除成功！");
				} catch (ClassNotFoundException e) {
					out.println("删除失败！");
					e.printStackTrace();
				} catch (SQLException e) {
					out.println("删除失败！");
					e.printStackTrace();
				} catch (Exception e) {
					out.println("删除失败！");
					e.printStackTrace();
				}
			}else if("teacher".equals(option)){
				int teacher_id=Integer.parseInt(request.getParameter("table_id"));
				ITeacher teacherDAO=new TeacherDAO();
				try {
					teacherDAO.deleteTeacher(teacher_id);
					out.println("删除成功！");
				} catch (ClassNotFoundException e) {
					out.println("删除失败！");
					e.printStackTrace();
				} catch (SQLException e) {
					out.println("删除失败！");
					e.printStackTrace();
				} catch (Exception e) {
					out.println("删除失败！");
					e.printStackTrace();
				}
			}
		}else if("/showAcount".equals(select)){
			String option=request.getParameter("option");
			
			try {
				if("student".equals(option)){
					List<Student> students=new ArrayList<Student>();
					IStudent studentDAO=new StudentDAO();
					students=studentDAO.findAll();
					JSONArray array=JSONArray.fromObject(students);
					out.println(array.toString());
				}else if("teacher".equals(option)){
					List<Teacher> teachers=new ArrayList<Teacher>();
					ITeacher teacherDAO=new TeacherDAO();
					teachers=teacherDAO.findAll();
					JSONArray array=JSONArray.fromObject(teachers);
					out.println(array.toString());
				}
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if("/findUser".equals(select)){
			String option=request.getParameter("option");
			String keyWord=request.getParameter("keyWord");
			try {
				if("student".equals(option)){
					List<Student> students=new ArrayList<Student>();
					IStudent studentDAO=new StudentDAO();
					students=studentDAO.findByKeyWords(keyWord);
					JSONArray array=JSONArray.fromObject(students);
					out.println(array.toString());
				}else if("teacher".equals(option)){
					List<Teacher> teachers=new ArrayList<Teacher>();
					ITeacher teacherDAO=new TeacherDAO();
					teachers=teacherDAO.findByKeyWords(keyWord);
					JSONArray array=JSONArray.fromObject(teachers);
					out.println(array.toString());
				}
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		out.close();

	}

}
