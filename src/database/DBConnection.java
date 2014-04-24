package database;

import java.sql.*;

public class DBConnection {

	private Connection _conn;
	
	public DBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		_conn = DriverManager.getConnection("jdbc:sqlite:data/testtweets.db");
	}
	
	public void createTable() throws SQLException {
		Statement statement = _conn.createStatement();
		String schema = "CREATE TABLE TWEETS" +
					 "(id INT PRIMARY KEY," +
					 "message TEXT(140)," +
					 "lat REAL," + 
					 "lon REAL," +
					 "date DATETIME," +
					 "retweets INT)";
		
		statement.executeUpdate(schema);
	}
	
	
	public void insertTweet() throws SQLException {
		Statement statement = _conn.createStatement();
		
		String insertion = "INSERT INTO TWEETS (id, message, lat, lon, date, retweets) " +
						   "VALUES (9, 'Obama is a president', 45.0244, 92.1922, 4/23/2014, 47);";
		//consider using the prepared statement class instead.
		
		statement.executeUpdate(insertion);
		
		
		insertion = "INSERT INTO TWEETS (id, message, lat, lon, date, retweets) " +
				   "VALUES (10, 'Obama is Obama', 45.0244, 92.1922, 4/22/2014, 15);";
		//consider using the prepared statement class instead.

		statement.executeUpdate(insertion);
		
		
		insertion = "INSERT INTO TWEETS (id, message, lat, lon, date, retweets) " +
				   "VALUES (11, 'This tweet does not contain you-know-what', 45.0244, 92.1922, 4/21/2014, 0);";
		//consider using the prepared statement class instead.

		statement.executeUpdate(insertion);
		
		statement.close();
		_conn.close();
		
		System.out.println("insertions done");
	}

}
