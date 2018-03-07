package Data.datasource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class datasource_cloud
{
	private MysqlDataSource dataSource = new MysqlDataSource();

	public datasource_cloud() {
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("cupcakedb");
		dataSource.setUser("fluffms");
		dataSource.setPassword("Coding4u@snail");
	}

	public MysqlDataSource getDataSource()
	{
		return this.dataSource;
	}
}
