package utils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 连接工具类
 * 	建立JDBC基本连接
 * 	使用连接池
 * @author Daniel
 * @see utils
 * @since JDK1.6,J2EE5.0,Tomcat7
 * @version 1.4.3
 */
public class DBUtils {
	private static BasicDataSource datasource=null;
	static{
		datasource=new BasicDataSource();
		Properties prop=new Properties();
		try {
			prop.load(DBUtils.class.getClassLoader().
					getResourceAsStream("utils/db.properties"));
			datasource.setDriverClassName(prop.getProperty("driver"));
			datasource.setUrl(prop.getProperty("url"));
			datasource.setPassword(prop.getProperty("password"));
			datasource.setUsername(prop.getProperty("username"));
			datasource.setInitialSize(Integer.
					parseInt(prop.getProperty("initialSize")));
			datasource.setMaxActive(Integer.
					parseInt(prop.getProperty("maxActive")));
			datasource.setMaxWait(Integer.
					parseInt(prop.getProperty("maxWait")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 连接数据库
	 * @return 连接数据库
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public static Connection getConnection()
			throws SQLException,ClassNotFoundException,Exception{
		return datasource.getConnection();
	}
	/**
	 * 关闭数据库连接
	 * @param connect
	 */
	public static void closeConnection (Connection connect){
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
