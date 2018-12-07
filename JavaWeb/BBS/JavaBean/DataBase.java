package com.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	// 连接mysql，使用bbs数据库
	private static String url = "jdbc:mysql://127.0.0.1:3306/bbs?" + "serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&"
			+ "characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&"
			+ "autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true";
	private static String user = "root";
	private static String password = "root";
	
	/*
	 * 获得Connection对象
	 */
	public static Connection getConnection(){		
		Connection connection = null;
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/*
	 * 传递一个Connection对象
	 * 返回Statement对象
	 */
	public static Statement getStatement(Connection connection){
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return statement;		
	}
	
	/*
	 * 执行SQL语句，结果返回到resultSet
	 */
	public static ResultSet executeSQL(Statement statement, String sql){
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;	
	}
	
	/*
	 *  方法重载，关闭资源
	 */
	public static void close(Connection connection){
		if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Statement statement){
		if(statement != null)
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(ResultSet resultSet){
		if(resultSet != null)
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
