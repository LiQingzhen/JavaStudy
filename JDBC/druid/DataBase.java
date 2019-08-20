import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;



/**
 * 
 * @author Qingzhen Li
 *
 */
public class DataBase {

	private static DataSource dataSource = null;
	
	// 初始化连接池
	static {
		try {
			// 加载Druid连接池配置文件
			InputStream inputStream = DataBase.class.getResourceAsStream("/druid-config.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			// 创建数据源
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 创建PreparedStatement
	 * @param connection, sql
	 * @return
	 */
	public static PreparedStatement createStatement(Connection connection, String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	/**
	 * 资源回收，方法重载
	 */
	public static void close(Connection connection) {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void close(PreparedStatement statement) {
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void close(ResultSet resultSet) {
		if (resultSet != null)
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
