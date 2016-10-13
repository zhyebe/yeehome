package web;

import idao.ITotalScore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.TotalScoreDAO;
import entity.TotalScore;
/**
 * 学生自己查询自己的考试成绩的Servlet
 * @author Array
 * @see web
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 *
 */
public class ListStudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取session ,从中间获取绑定的student_id
		HttpSession session=request.getSession();
		int student_id=Integer.parseInt(session.getAttribute("table_id").toString());
		//获取到uri对请求进行分类判断
		String uri=request.getRequestURI();
		String select=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		//定义分页查询的起始点和查询的条目数
		int start=0;
		int rows=4;
		Object[] data=new Object[3];
		if("/firstlist".equals(select)){//显示第一页
			List<TotalScore> scores=new ArrayList<TotalScore>();
			ITotalScore scoreDAO=new TotalScoreDAO();
			try {
				scores=scoreDAO.findScoresById(student_id, start, rows);
				if(scores!=null&&scores.size()>0){
					data[0]=scores;
					data[1]=start;
					data[2]=rows;
					request.setAttribute("data", data);
					request.getRequestDispatcher("jsp/liststudent.jsp")
							.forward(request, response);
				}else{
					request.setAttribute("data", data);
					request.getRequestDispatcher("jsp/liststudent.jsp")
							.forward(request, response);
				}
			} catch (ClassNotFoundException e) {
			
				e.printStackTrace();
			} catch (SQLException e) {
			
				e.printStackTrace();
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
		}else if("/forward".equals(select)){//翻到下一页
			List<TotalScore> scores=new ArrayList<TotalScore>();
			ITotalScore scoreDAO=new TotalScoreDAO();
			int page=Integer.parseInt(request.getParameter("page"));
			boolean turn=true;
			try {
				scores=scoreDAO.findScoresById(student_id, (page-1)*rows, rows*2);
				if(scores!=null&&scores.size()<=4){
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
				scores=scoreDAO.findScoresById(student_id, start, rows);
				if(scores!=null&&scores.size()>0){
					data[0]=scores;
					data[1]=start;
					data[2]=rows;
					request.setAttribute("data", data);
					request.getRequestDispatcher("jsp/liststudent.jsp")
							.forward(request, response);
				}else{
					request.setAttribute("data", data);
					request.getRequestDispatcher("jsp/liststudent.jsp")
							.forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/back".equals(select)){//翻到上一
			List<TotalScore> scores=new ArrayList<TotalScore>();
			ITotalScore scoreDAO=new TotalScoreDAO();
			int page=Integer.parseInt(request.getParameter("page"));
			start=(page-2)*rows;
			if(start<0){
				start=0;
			}
			try {
				scores=scoreDAO.findScoresById(student_id, start, rows);
				if(scores!=null&&scores.size()>0){
					data[0]=scores;
					data[1]=start;
					data[2]=rows;
					request.setAttribute("data", data);
					request.getRequestDispatcher("jsp/liststudent.jsp")
							.forward(request, response);
				}else{
					request.setAttribute("data", data);
					request.getRequestDispatcher("jsp/liststudent.jsp")
							.forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/listMsg".equals(select)){
			String json="{'total':28,'rows':["+
				"{'productid':'FI-SW-01','productname':'Koi','unitcost':10.00,'status':'P','listprice':36.50,'attr1':'Large','itemid':'EST-1'},"+
				"{'productid':'K9-DL-01','productname':'Dalmation','unitcost':12.00,'status':'P','listprice':18.50,'attr1':'Spotted Adult Female','itemid':'EST-10'},"+
				"{'productid':'RP-SN-01','productname':'Rattlesnake','unitcost':12.00,'status':'P','listprice':38.50,'attr1':'Venomless','itemid':'EST-11'},"+
				"{'productid':'RP-SN-01','productname':'Rattlesnake','unitcost':12.00,'status':'P','listprice':26.50,'attr1':'Rattleless','itemid':'EST-12'},"+
				"{'productid':'RP-LI-02','productname':'Iguana','unitcost':12.00,'status':'P','listprice':35.50,'attr1':'Green Adult','itemid':'EST-13'},"+
				"{'productid':'FL-DSH-01','productname':'Manx','unitcost':12.00,'status':'P','listprice':158.50,'attr1':'Tailless','itemid':'EST-14'},"+
				"{'productid':'FL-DSH-01','productname':'Manx','unitcost':12.00,'status':'P','listprice':83.50,'attr1':'With tail','itemid':'EST-15'},"+
				"{'productid':'FL-DLH-02','productname':'Persian','unitcost':12.00,'status':'P','listprice':23.50,'attr1':'Adult Female','itemid':'EST-16'},"+
				"{'productid':'FL-DLH-02','productname':'Persian','unitcost':12.00,'status':'P','listprice':89.50,'attr1':'Adult Male','itemid':'EST-17'},"+
				"{'productid':'AV-CB-01','productname':'Amazon Parrot','unitcost':92.00,'status':'P','listprice':63.50,'attr1':'Adult Male','itemid':'EST-18'}"+
				"]}";
			request.setAttribute("json",json);
			request.getRequestDispatcher("jsp/listdata.jsp")
			.forward(request, response);
		}else if("/php".equals(select)){
			request.getRequestDispatcher("caculate.php")
			.forward(request, response);
		}
	}

}
