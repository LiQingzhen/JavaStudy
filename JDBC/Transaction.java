import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {

	public static void main(String[] args) {

		String url = "jdbc:mysql://127.0.0.1:3306/my_practise?"
				+ "serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&"
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

			// 默认情况下一条SQL语句就是一个事务，为了定义符合要求的事务，关闭自动提交
			connection.setAutoCommit(false);

			// 批处理：创建 dept表并插入三个元组
			statement.addBatch("create table dept (" + "deptno int primary key," + "dname varchar(14),"
					+ "location varchar(13)" + ")");
			statement.addBatch("insert into dept values(10, 'A', 'Shandong')");
			statement.addBatch("insert into dept values(20, 'B', 'Beijing')");
			statement.addBatch("insert into dept values(30, 'A', 'Shanghai')");
			statement.executeBatch();

			// 提交当前事务；恢复现场
			connection.commit();
			connection.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					// 事务内部异常，回滚当前事务
					connection.rollback();
					// 恢复现场
					connection.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
