import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 2018-11-17
 * ������Ҫ��װ��MySQL
 * ʹ��ǰҪ�������jar��
*/

public class MysqlConnection {

	public static void main(String[] args) {

		// һ��Ĭ�������url
		String url = "jdbc:mysql://127.0.0.1:3306/world?" + "serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&"
				+ "characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&"
				+ "autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true";
		String user = "root";
		String password = "root";

		Connection connection = null;
		
		// java.sql���µ���
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// step_1������MySQL����
			Class.forName("com.mysql.cj.jdbc.Driver");

			// step_2�����ӵ�ָ�����ݿ⣨MySQL�кܶ�DataBase����world ��MySQL�ṩ�����ݿ�
			// �û�����root�������ǰ�װMySQLʱ�Լ��趨��
			connection = DriverManager.getConnection(url, user, password);

			// step_3��ִ��SQL���
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from country");

			// step_4�������ؽ��
			while (resultSet.next()) {
				// ��ӡworld���ݿ�country����name�ֶ����е�ֵ
				System.out.println(resultSet.getString("name"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// step_5���ر���Դ����򿪵��ȹر�
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
