package personal.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

import personal.shopping.util.DataBase;

/**
 * 将数据库(shopping)中user表的一条记录映射为一个对象；简单的ORM思想
 * 
 * @author LIQINGZHEN
 */
public class User {
	private int id;
	private String username;
	private String password;
	private String phone;
	private String address;
	private Timestamp registerDate;

	public User(){}
	public User(String username, String password, String phone, String address){
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.registerDate = new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the registerDate
	 */
	public Timestamp getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate
	 *            the registerDate to set
	 */
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * 将注册得到的用户数据保存到数据库
	 */
	public void save() {
		Connection connection = DataBase.getConnection();
		String sql = "insert into user values(null, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = DataBase.getPreparedStatement(connection, sql);
		try {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, address);
			preparedStatement.setTimestamp(5, registerDate);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataBase.close(preparedStatement);
			DataBase.close(connection);
		}
	}
	
	/**
	 * 获取所有user的全部信息
	 * @return List<User>
	 */
	public static List<User> getUsers(){
		List<User> list = new ArrayList<User>();
		try {
			Connection connection = DataBase.getConnection();
			Statement statement = DataBase.getStatement(connection);
			String sql = "select * from user";
			ResultSet resultSet = DataBase.executeSQL(statement, sql);
			while(resultSet.next()){
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setPhone(resultSet.getString("phone"));
				user.setAddress(resultSet.getString("address"));
				user.setRegisterDate(resultSet.getTimestamp("register_date"));			
				list.add(user);
			}		
			DataBase.close(resultSet);
			DataBase.close(statement);
			DataBase.close(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
