package lastfmhistoryclasses;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import lastfmhistoryguis.*;

	

public class MainClass {
	
	
	public static int screenHeight;
	public static int screenWidth;
	public static final int SCREENPAD = 10;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = (int) d.getHeight() - 2 * SCREENPAD - 50;
		screenWidth = (int) d.getWidth() - 2 * SCREENPAD;
		
		LastFMHistory lastFMHistoryModel = new LastFMHistory();
		JFrame InputGUI = new InputGUI(screenWidth, screenHeight);
		
		
	}
	
	

}