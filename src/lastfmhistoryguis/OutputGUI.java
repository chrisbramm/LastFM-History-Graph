package lastfmhistoryguis;

import javax.swing.*;

import lastfmhistoryclasses.*;

public class OutputGUI extends JFrame {
	
	private LastFMHistory lastFMHistoryModel;
	private JFrame outputGUIFrame;
	private int screenWidth;
	private int screenHeight;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutputGUI(LastFMHistory model, int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.lastFMHistoryModel = model;
		
		outputGUIFrame = new JFrame();
		outputGUIFrame.setBounds(10, 10, screenWidth, screenHeight);
		
		GraphPanel graphPanel = new GraphPanel(lastFMHistoryModel);
		
		outputGUIFrame.add(graphPanel);
		
		outputGUIFrame.setVisible(true);
		
		
	}
	
	
	
}
