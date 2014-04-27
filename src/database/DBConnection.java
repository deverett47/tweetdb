package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import tweets.Tweet;
import twitter4j.Status;

public class DBConnection {

	private Connection _conn;
	
	public DBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		_conn = DriverManager.getConnection("jdbc:sqlite:data/tweets1.db");
	}
	
	public void createTable() throws SQLException {
		Statement statement = _conn.createStatement();
		String schema = "CREATE TABLE TWEETS" +
					 "(id INT PRIMARY KEY," +
					 "message TEXT," +
					 "lat REAL," + 
					 "lon REAL," +
					 "date DATETIME," +
					 "retweets INT)";
		
		statement.executeUpdate(schema);
	}
	
	public void test() {
		System.out.println("test");
	}
	
	
	public void insertTweet(Status s) throws SQLException {
		PreparedStatement statement = _conn.prepareStatement("INSERT INTO TWEETS (id, message, lat, lon, date, retweets) " +
				"VALUES (?, ?, ?, ?, ?, ?);");

		//String date = new SimpleDateFormat("yyyy-MM-dd").format(s.getCreatedAt());
		//date += " ";
		//date += new SimpleDateFormat("HH-MM-SS.SSS").format(s.getCreatedAt());
		
		statement.setString(1, Long.toString(s.getId()));
		statement.setString(2, s.getText());
		statement.setString(3, Double.toString(s.getGeoLocation().getLatitude()));
		statement.setString(4, Double.toString(s.getGeoLocation().getLongitude()));
		statement.setDate(5, new java.sql.Date(s.getCreatedAt().getTime()));
		statement.setString(6, Integer.toString(s.getRetweetCount()));
		

		
		statement.execute();
		statement.close();		
	}
	
	
	public ArrayList<Tweet> searchTweets(String s) throws SQLException {
		ArrayList<Tweet> result = new ArrayList<Tweet>();
		
		Statement statement = _conn.createStatement();
		//ResultSet results = statement.executeQuery("SELECT * FROM TWEETS;");
		ResultSet results = statement.executeQuery("SELECT * FROM TWEETS WHERE message LIKE '%" +s+"%';");
		
		while (results.next()) {
			int id = results.getInt("id");
			String message = results.getString("message");
			double lat = results.getDouble("lat");
			double lon = results.getDouble("lon");
			Date date = results.getDate("date");
			int rt = results.getInt("retweets");
			
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			
			Tweet newTweet = new Tweet(message, lat, lon, c);
			newTweet.setRetweetCount(rt);
			newTweet.setID(id);
			
			System.out.println(id + " " + message + " " + lat + ", " + lon + " " + date + " " + rt);
		}
		
		System.out.println("done");
		results.close();
		statement.close();
		
		return result;
	}

	
	
	public void close() throws SQLException {
		_conn.close();
	}
	
}
