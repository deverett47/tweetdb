package tweets;

import database.DBConnection;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetStreamer {

	private TweetListener _listener;
	private TwitterStream _twitterStream;
	private DBConnection _dbConnection;
	
	public TweetStreamer(DBConnection dbConnection) {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("69iGtJfotY8Swz3jKOGXDagwR");
		builder.setOAuthConsumerSecret("RY9awNGpNWoUG3SxLJrgVDhTIVJjw2qBDxIqffhoSySn0OHQbx");
		builder.setOAuthAccessToken("2445489793-MP1rScKQgoUPXXZRmtGgvqJQoDPPUYlVxsTjeXd");
		builder.setOAuthAccessTokenSecret("tGQQQkrAV1JBrmvJGlAX6bKGfurreoMFuOD7orMJWY1nC");
		//builder.setJSONStoreEnabled(true);
		builder.setDebugEnabled(false);
		
		_dbConnection = dbConnection;
		_listener = new TweetListener(_dbConnection);
		
		_twitterStream = new TwitterStreamFactory(builder.build()).getInstance();
		_twitterStream.addListener(_listener);
	}
	
	public void streamTweets() {
		String[] language = {"en"};
		String[] trackArray = {""};
		double[][] trackLocations = {{-128.671875, 1.406109}, {-37.265625, 69.162558}};
		long[] follow = {};
		FilterQuery fq = new FilterQuery();
		//FilterQuery fq = new FilterQuery(0, follow, trackArray, trackLocations, language);
		fq.language(language);
		fq.locations(trackLocations);
		_twitterStream.filter(fq);
	}

	
	public void close() {
		_twitterStream.cleanUp();
		_twitterStream.shutdown();
	}
}
