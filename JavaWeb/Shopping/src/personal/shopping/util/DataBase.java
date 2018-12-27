package personal.shopping.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 简单的封装JDBC，简化代码（用到JDBC时不用再try-catch）
 * @author LIQINGZHEN
 *
 */
public class DataBase {
	
	/**
	 * 静态代码块，加载mysql驱动
	 */
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/* 用于连接数据库的固定字符串+用户名+密码 */
	private static String url = "jdbc:mysql://127.0.0.1:3306/shopping?" + "serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&"
			+ "characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&"
			+ "autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true";
	private static String user = "root";
	private static String password = "root";
//	private DataBase(){}
	
	/**
	 * 获取Connection连接
	 * @return Connection实例对象
	 */
	public static Connection getConnection(){		
		Connection connection = null;		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return connection;
	}
	
	/**
	 * 传入Connection对象，得到Statement对象
	 * @param connection
	 * @return Statement实例对象
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
	
	/**
	 * 获取PrepareStatement对象
	 * @param connection
	 * @param sql
	 * @return PrepareStatement实例对象
	 */
	public static PreparedStatement getPreparedStatement(Connection connection, String sql){
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	/**
	 * 执行SQL查询语句，结果集存储到ResultSet中
	 * @param statement
	 * @param sql
	 * @return ResultSet实例对象
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
	
	/**
	 * 关闭 Connection资源
	 * @param connection
	 */
	public static void close(Connection connection){
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭 Statement/PrepareStatement资源
	 * @param statement
	 */
	public static void close(Statement statement){
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭 ResultSet资源
	 * @param resultSet
	 */
	public static void close(ResultSet resultSet){
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
