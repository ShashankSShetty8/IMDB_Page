package objectRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLVariables
{
	
	public static Connection con;
	public static Statement stmt=null;
	public static ResultSet resultSet;
	public static String sSQLite = "org.sqlite.JDBC";
	public static String sDBPath = "jdbc:sqlite:C:\\Users\\Shashank S Shetty\\IMDB.db";
	public static String sQuery;

}
