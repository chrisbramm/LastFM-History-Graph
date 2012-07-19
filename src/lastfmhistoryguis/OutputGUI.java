package lastfmhistoryguis;

import java.awt.BorderLayout;
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
		
		int frameWidth = outputGUIFrame.getWidth();
		int frameHeight = outputGUIFrame.getHeight();
		
		System.out.println("Frame Width: " + frameWidth + ", Frame Height:" + frameHeight);

		GraphPanel graphPanel = new GraphPanel(lastFMHistoryModel);
		graphPanel.setPreferredSize(new Dimension(screenWidth - 80, 6000));


		JScrollPane graphScrollPanel = new JScrollPane(graphPanel);
		graphScrollPanel.setPreferredSize(new Dimension(screenWidth, screenHeight - 100));
		graphScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		AutocompletePanel autocompletePanel = new AutocompletePanel(lastFMHistoryModel.tracks, lastFMHistoryModel.artists);
		autocompletePanel.setPreferredSize(new Dimension(screenWidth, 100));



		//graphScrollPanel.setBounds(0, 0, this.screenWidth, this.screenHeight);
		//graphScrollPanel.add(graphPanel);
		//outputGUIFrame.add(graphScrollPanel, BorderLayout.CENTER);
		outputGUIFrame.add(autocompletePanel);



		outputGUIFrame.setVisible(true);
	}
	
	
}
