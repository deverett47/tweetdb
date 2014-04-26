package database;

import java.util.Calendar;
import java.util.Date;

public class Tweet implements Comparable<Tweet>{

	private final String _message;
	private double _sentiment;
	private final double _lat, _long;
	private final Calendar _date;
	private double _multiplier; //classifier statistic that depends on how different probpos and negative was
	private int _classifierint; //0 is negative, 1 is positive for the classifier
	private int _rt = 0;
	private long _id = 0;
	
	public Tweet(String message, double latitude, double longitude, Calendar date) {
		_message = message;
		_lat = latitude;
		_long = longitude;
		_date = date;
	}
	
	public double getLatitude() {
		return _lat;
	}
	
	public double getLongitude() {
		return _long;
	}
	
	public String getMessage() {
		return _message;
	}
	
	public Calendar getDate() {
		return _date;
	}
	
	public void setSentiment(double sentimentValue) {
		_sentiment = sentimentValue;
	}
	
	public void setID(long id) {
		_id = id;
	}
	
	public void setRetweetCount(int rt) {
		_rt = rt;
	}
	
	public long getID(){
		return _id;
	}
	
	public int getRetweetCount() {
		return _rt;
	}
	
	public double getSentiment() {
		return _sentiment;
	}
	@Override
	public int compareTo(Tweet o) {
		double oSentiment = o.getSentiment();
		if (oSentiment == _sentiment){
			String oMessage = o.getMessage();
			return _message.compareTo(oMessage);
		}
		else if (_sentiment > oSentiment)
			return 1;
		else
			return -1;
	}

	public double get_multiplier() {
		return _multiplier;
	}

	public void set_multiplier(double _multiplier) {
		this._multiplier = _multiplier;
	}

	public int get_classifierint() {
		return _classifierint;
	}

	public void set_classifierint(int _classifierint) {
		this._classifierint = _classifierint;
	}
}

