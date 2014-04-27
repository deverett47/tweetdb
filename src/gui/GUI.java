package gui;

import javax.swing.JFrame;

public class GUI extends JFrame {

	public GUI() {
		super("Twitter Trends");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new MainPanel());
		this.setVisible(true);
		this.pack();	
	}
	private static final long serialVersionUID = 647647647;
}
//1000 820;