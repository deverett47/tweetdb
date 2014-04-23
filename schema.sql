CREATE TABLE Tweets (
	id INT PRIMARY KEY,
	message TEXT(140),
	lat REAL,
	lon REAL,
	date DATETIME,
	retweets INT
);
