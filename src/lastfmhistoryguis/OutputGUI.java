package lastfmhistoryguis;

import java.awt.Dimension;

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
		createGUI();
		
		
		
		
	}
	public void createGUI(){
		outputGUIFrame = new JFrame();
		outputGUIFrame.setSize(screenWidth, screenHeight);

		GraphPanel graphPanel = new GraphPanel(lastFMHistoryModel);
		graphPanel.setPreferredSize(new Dimension(screenWidth, 6000));


		JScrollPane graphScrollPanel = new JScrollPane(graphPanel);
		graphScrollPanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
		graphScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);




		//graphScrollPanel.setBounds(0, 0, this.screenWidth, this.screenHeight);
		//graphScrollPanel.add(graphPanel);
		outputGUIFrame.add(graphScrollPanel);




		outputGUIFrame.setVisible(true);
	}
	
	
}
