
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Batch{

	public static void main(String[] args) {

		// 一般默认用这个url
		String url = "jdbc:mysql://127.0.0.1:3306/my_practise?" + "serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&"
				+ "characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&"
				+ "autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true";
		String user = "root";
		String password = "root";

		Connection connection = null;
		Statement statement = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			
			// 批处理：创建 dept 表并插入三个元组
			statement.addBatch("create table dept ("
					+ "deptno int primary key,"
					+ "dname varchar(14),"
					+ "location varchar(13)"
					+ ")");
			statement.addBatch("insert into dept values(10, 'A', 'Shandong')");	
			statement.addBatch("insert into dept values(20, 'B', 'Beijing')");	
			statement.addBatch("insert into dept values(30, 'A', 'Shanghai')");	
			statement.executeBatch();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
