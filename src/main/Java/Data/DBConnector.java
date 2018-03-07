package Data;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector
{
	private DataSource datasource;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public DBConnector(DataSource datasource)
	{
		this.datasource = datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public void open() throws SQLException
	{
		if(connection == null || connection.isClosed()) {
			connection = datasource.getConnection();
		}
	}
	public void close() throws SQLException
	{
		if(resultSet != null)
		{
			resultSet.close();
		}

		if(statement != null)
		{
			statement.close();
		}

		if(connection != null && !connection.isClosed())
		{
			connection.close();
			connection = null;
		}
	}

	public ResultSet executeQuery(String sql) throws SQLException
	{
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		return resultSet;
	}

	public void executeUpdate(String sql) throws SQLException
	{
		statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public PreparedStatement preparedStatement(String sql) throws SQLException
	{
		return connection.prepareStatement(sql);
	}

	//made these
	public void setAutoCommit(boolean bol) throws SQLException {
		connection.setAutoCommit(bol);
	}

	public boolean haveConnection() {
		return connection != null ? true : false;
	}

	public void rollback() throws SQLException {
		connection.rollback();
	}


}
