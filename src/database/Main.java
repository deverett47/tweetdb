package database;

import java.sql.SQLException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DBConnection dbc = new DBConnection();
			//dbc.insertTweet();
			dbc.searchTweets("obAMa");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
