package lastfmhistoryclasses;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MainClass {
	
	public static int screenHeight;
	public static int screenWidth;
	public static final int SCREENPAD = 10;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = (int) d.getHeight() - 2 * SCREENPAD - 50;
		screenWidth = (int) d.getWidth() - 2 * SCREENPAD;
		
		System.out.println("Main Class:: Width = " + screenWidth + ", Height = " + screenHeight);
		@SuppressWarnings("unused")
		Controller controller = new Controller(screenWidth, screenHeight);
				
	}
	
}
