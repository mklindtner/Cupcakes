package Data.unused;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector_local
{
	public Connection getConnection() throws SQLException
	{
		Connection conn = null;
		try {
			//Properties connectionProps = new Properties();
			//connectionProps.put("user", "fluffms");
			//connectionProps.put("password", "Coding4u@snail");
			//change localhost to: 159.89.111.245 when development
			//servername for droplet server: http://159.89.99.45
			String serverName   = "localhost";
			int    port         = 3306;
			String databaseName = "cupcakedb";
			String url          = "jdbc:mysql://" + serverName + ":" + port + "/" + databaseName + "?useSSL=false";
			String user         = "root";
			String password     = "Hightech4u";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("Connected to database");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
