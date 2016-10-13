package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 欢迎页面跳转到登录页面的转发
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public class EnterServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str="welcome";
		response.setContentType("text/html;charset=utf-8");//设置响应的内容类型及编码方式
		request.setCharacterEncoding("utf-8");//设置请求编码方式
		request.setAttribute("welcome",str);//在request中加入名为"welcome"的属性，并赋值为str
		request.getRequestDispatcher("WEB-INF/html/login.html").
			forward(request,response);//将请求转发到指定URL地址
		
	}

}
