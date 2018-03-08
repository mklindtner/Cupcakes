package Data.Users;

import Data.DBConnector;
import Data.cupcakes.Bottom;
import Data.cupcakes.Topping;
import Data.datasource.datasource_cloud;
import Data.datasource.datasource_local;
import Data.unused.DBConnector_local;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//using local now
public class UserDAO implements IUser
{
	//DBConnector_local conn = new DBConnector_local(); old on
	DBConnector dbcon = new DBConnector(new datasource_local().getDataSource());
	//DBConnector dbcon = new DBConnector(new datasource_cloud().getDataSource());


	public void insertUser(String username, String password, String email)
	{
		try {
			dbcon.open();
			String insertUser = "INSERT INTO users(username, upassword, ubalance, email, admin) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = dbcon.preparedStatement(insertUser);
			//PreparedStatement st = conn.getConnection().prepareStatement(insertUser);
			st.setString(1, username);
			st.setString(2, password);
			st.setInt(3, 0);
			st.setString(4, email);
			st.setBoolean(5, false);
			st.executeUpdate();
			dbcon.close();
		} catch(SQLException ex) {
			throw new IllegalStateException(ex);
		}

	}

	public User findUser(String username)
	{
		User u = null;
		try {
			dbcon.open();
			String sqlQuery = "Select * FROM users WHERE username='" + username + "'";
			PreparedStatement st = dbcon.preparedStatement(sqlQuery);
			ResultSet rs = st.executeQuery();
			//PreparedStatement st = conn.getConnection().prepareStatement(sqlQuery);
			if(rs.next()) {
				String tmpUsername = rs.getString("username");
				String password = rs.getString("upassword");
				String email = rs.getString("email");
				boolean admin = rs.getBoolean("admin");
				u = new User(tmpUsername, password, 0, email, admin);
			}
			dbcon.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {
			return u;
		}
	}

	public User updateUserBalance(String username, int ubalance) {
		User u = null;
		try {
			dbcon.open();
			String sql = "Update users SET ubalance=? WHERE username=?";
			PreparedStatement st = dbcon.preparedStatement(sql);
			st.setInt(1, ubalance);
			st.setString(2, username);
			st.executeUpdate();
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String password = rs.getString("password");
				String email = rs.getString("email");
				boolean admin = rs.getBoolean("admin");
				int balance = rs.getInt("balance");
				u = new User(username, password, balance, email, admin);
			}
			return u;
		} catch(SQLException ex) {
			throw new IllegalStateException("Unable to updateBalance");
		}
	}

	public User validateUser(String username, String password)
	{
		User user = null;
		try
		{
			dbcon.open();
			String sql = "select * from users where username = ? and upassword = ?";
			PreparedStatement preparedStatement = dbcon.preparedStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				String email = resultSet.getString("email");
				int value = resultSet.getInt("ubalance");
				boolean admin = resultSet.getBoolean("admin");
				user = new User(username, password, value, email, admin);
			}
			dbcon.close();
		}
		catch (SQLException ex)
		{
			throw new IllegalStateException("User unable to validate, query probably bad");
		}
		return user;
	}

	public void deleteUser(String username) {
		try {
			dbcon.open();
			String sql = "Delete FROM users where username=?";
			PreparedStatement st = dbcon.preparedStatement(sql);
			st.setString(1, username);
			st.executeUpdate();
		} catch(SQLException ex) {
			throw new IllegalStateException("Unable to delete user");
		}
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			dbcon.open();
			String sql = "Select * FROM users";
			PreparedStatement st = dbcon.preparedStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("upassword");
				String email = rs.getString("email");
				boolean admin = rs.getBoolean("admin");
				User u = new User(username, password, 0, email, admin);
				users.add(u);
			}
		}catch(SQLException ex) {
			throw new IllegalStateException("unable to get list of users");
		}
		return users;
	}



/*
	//move this into controller
	public User validateUser(String username, String password)
	{
		User u = null;
		try {
			dbcon.open();
			String sqlQuery = "Select * FROM users WHERE username = ? AND password = ? ";
			PreparedStatement st = dbcon.preparedStatement(sqlQuery);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String email = rs.getString("email");
				u = new User(username, password, 0, email);
			}
			dbcon.close();
		} catch(SQLException ex) {
			throw new IllegalStateException();
		}
		return u;
	} */

	/*
	public void testInsert() {
		try {
			Statement st = conn.getConnection().createStatement();
			String query = "INSERT INTO users (username, upassword, ubalance, email) VALUES ('usercakeTest', '123', '0', " +
						   "'anEmail')";
			st.executeUpdate(query);
		} catch (SQLException ex) {
			throw new IllegalStateException(ex);
		}
	}*/
}
