package Data.datasource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class datasource_local
{
	private MysqlDataSource dataSource = new MysqlDataSource();

	public datasource_local() {
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("cupcakedb");
		dataSource.setUser("root");
		dataSource.setPassword("Hightech4u");
	}

	public MysqlDataSource getDataSource()
	{
		return this.dataSource;
	}
}
