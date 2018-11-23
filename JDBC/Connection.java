import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 2018-11-17
 * 机器上要安装了MySQL
 * 使用前要导入相关jar包
*/

public class Connection {

	public static void main(String[] args) {

		// 一般默认用这个url
		String url = "jdbc:mysql://127.0.0.1:3306/world?" + "serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&"
				+ "characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&"
				+ "autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true";
		String user = "root";
		String password = "root";

		Connection connection = null;
		
		// java.sql包下的类
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// step_1：加载MySQL驱动
			Class.forName("com.mysql.cj.jdbc.Driver");

			// step_2：连接到指定数据库（MySQL有很多DataBase）；world 是MySQL提供的数据库
			// 用户名：root，密码是安装MySQL时自己设定的
			connection = DriverManager.getConnection(url, user, password);

			// step_3：执行SQL语句
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from country");

			// step_4：处理返回结果
			while (resultSet.next()) {
				// 打印world数据库country表中name字段所有的值
				System.out.println(resultSet.getString("name"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// step_5：关闭资源，后打开的先关闭
			try {
				if(resultSet != null){
					resultSet.close();
					resultSet = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if(connection != null){
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
