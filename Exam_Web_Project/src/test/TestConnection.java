package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import utils.DBUtils;

/**
 * 测试类
 * 测试数据库连接
 * 主要测试工具类是否正常工作
 * @author Array
 * @see text
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 * 
 *
 */
public class TestConnection {
	private static Connection connect=null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			connect=DBUtils.getConnection();
			System.out.println(connect);
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(connect);
		}

	}

}
