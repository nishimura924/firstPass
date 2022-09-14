package dao;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DAO
{
	static javax.sql.DataSource ds;
	
	public Connection getConnection() throws Exception
	{
		if(ds == null)
		{
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:/comp/env/jdbc/FIRSTPASS");
		}
		
		return ds.getConnection();
	}
}