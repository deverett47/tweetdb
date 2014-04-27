package tweets;

import java.sql.SQLException;

import database.DBConnection;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TweetListener implements StatusListener {

	private DBConnection _dbConnection;
	private int _counter = 0;
	
	public TweetListener(DBConnection dbConnection) {
		_dbConnection = dbConnection;
	}

	@Override
	public void onException(Exception arg0) {
		System.out.println("EXCEPTION: " +arg0.getMessage());
		
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		System.out.println("STALL WARNING: " +arg0.getMessage());
		
	}

	@Override
	public void onStatus(Status arg0) {
			try {
				arg0.getGeoLocation();
				try {
					_dbConnection.insertTweet(arg0);
					System.out.println(arg0.getText() + " " + arg0.getGeoLocation().getLatitude() + ", "+ arg0.getGeoLocation().getLongitude());
					_counter++;
					System.out.println("COUNT: " + _counter);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
			} catch (NullPointerException e) {
				System.out.println("null pointer");
			}
		
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		System.out.println("TRACK LIMITATION NOTICE: " +arg0);
		
	}
	
}
