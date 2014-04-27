package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;

import database.DBConnection;


import tweets.TweetStreamer;
import twitter4j.TwitterException;

public class MainPanel extends JPanel {

	private SearchButton _searchButton;
	private DBConnection _dbConnection;
	private TweetStreamer _streamer;
	
	public MainPanel() {
		try {
			_dbConnection = new DBConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		_streamer = new TweetStreamer(_dbConnection);
		_streamer.streamTweets();
		
//		try {
//			//_dbConnection.createTable();
//			//_dbConnection.searchTweets("rihanna");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//this.setSize(800,600); 
		

		/*
		 * Add the search button.
		 */
		_searchButton = new SearchButton(this);
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,1,10,10);
		c.gridx = 1;
		c.gridy = 1;
		this.add(_searchButton, c);
		
		
		
	}
	
	
	
	public void close() {
		try {
			_streamer.close();
			_dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static final long serialVersionUID = 647647647;
	
}
