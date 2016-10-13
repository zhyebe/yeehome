package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtils;

/**
 * BaseDAO，封装底层的查询和曾删改方法
 * @author Daneil
 * @see jdbc
 * @since JDK 1.6,J2EE5.0,Tomcat 7
 * @version 1.4.3
 */
public abstract class BaseDAO<Type> {

	private Connection connect=null;
	private PreparedStatement prestate=null;
	private ResultSet result=null;
	/*批量执行缓冲区大小*/
	private int bufferSize=500;
	
	/**
	 * 封装所有的查询方法
	 * @param sql 传入方法的sql语句
	 * @param params sql语句中的参数
	 * @return 集合，里面装载了查询结果集当中的数据行
	 * （每一行封装成一个数据实体类型的对象放在集合中）
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	protected List<Type> query(String sql,Object[]params)
				throws ClassNotFoundException,SQLException,Exception{
		List <Type> find=new ArrayList<Type>();
		try {
			connect=DBUtils.getConnection();
			prestate=connect.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					prestate.setObject(i+1, params[i]);
				}
			}
			result=prestate.executeQuery();
			while(result.next()){
				find.add(toEntity(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try {
				if(result!=null){
					result.close();
				}
				if(prestate!=null){
					prestate.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtils.closeConnection(connect);
		}
		return find;
	}
	
	/**
	 * 封装所有的增删改方法
	 * 因为涉及到对数据表的变更，
	 * 所以这里使用事务机制保证数据变更的并发安全性
	 * @param sql 传入方法的sql语句
	 * @param params sql语句的参数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	protected void modify(String sql,Object[]params)
				throws ClassNotFoundException,SQLException,Exception{
		try {
			connect=DBUtils.getConnection();
			connect.setAutoCommit(false);
			prestate=connect.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					prestate.setObject(i+1,params[i]);
				}
			}
			prestate.executeUpdate();
			connect.commit();
		} catch (SQLException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		}finally{
			try {
				connect.setAutoCommit(true);
				if(prestate!=null){
					prestate.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			DBUtils.closeConnection(connect);
			
		}
	}
	/**
	 * 抽象方法，由具体的DAO类实现，将查询结果集中的数据封装成实体类对象返回
	 * @param result
	 * @return 具体类型的实体
	 */
	protected abstract Type toEntity(ResultSet result);
	
	/**
	 * 重载modify方法，批量执行SQL
	 * @param sql 传入方法的sql语句
	 * @param params sql语句的参数
	 * @param n 批量执行的次数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	protected void modify(String sql,Object[][]params,int n)
			throws SQLException,ClassNotFoundException,Exception{
		try {
			connect=DBUtils.getConnection();
			connect.setAutoCommit(false);
			prestate=connect.prepareStatement(sql);
			for(int i=0;i<n;i++){
				if(params[i]!=null){
					for(int j=0;j<params[i].length;j++){
						prestate.setObject(j+1, params[i][j]);
					}
				}
				prestate.addBatch();
				if(i%bufferSize==0){
					prestate.executeBatch();
					prestate.clearBatch();
				}
			}
			prestate.executeBatch();
			prestate.clearBatch();
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connect.rollback();
			}catch(Exception e1){
				e1.printStackTrace();
			}
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try{
				connect.rollback();
			}catch(Exception e1){
				e1.printStackTrace();
			}
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			try{
				connect.rollback();
			}catch(Exception e1){
				e1.printStackTrace();
			}
			throw e;
		}finally{
			connect.setAutoCommit(true);
			if(prestate!=null){
				try{
					prestate.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			DBUtils.closeConnection(connect);
		}
	}
}
