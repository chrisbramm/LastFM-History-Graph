package lastfmhistoryguis;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import lastfmhistoryclasses.*;

public class OutputGUI extends JFrame {
	
	private LastFMHistory lastFMHistoryModel;
	private JFrame outputGUIFrame;
	public GraphPanel graphPanel;
	public AutocompletePanel autocompletePanel;
	private int screenWidth;
	private int screenHeight;
	private int zoom;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutputGUI(LastFMHistory model, int screenWidth, int screenHeight, int zoom){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.lastFMHistoryModel = model;
		this.zoom = zoom;
		createGUI();
		
		
		
		
	}
	public void createGUI(){
		outputGUIFrame = new JFrame();
		outputGUIFrame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		int frameWidth = outputGUIFrame.getWidth();
		int frameHeight = outputGUIFrame.getHeight();
		
		System.out.println("Frame Width: " + frameWidth + ", Frame Height:" + frameHeight);

		graphPanel = new GraphPanel(lastFMHistoryModel, zoom);
		graphPanel.setPreferredSize(new Dimension(outputGUIFrame.getWidth() - 80, 6000));


		JScrollPane graphScrollPanel = new JScrollPane(graphPanel);
		graphScrollPanel.setPreferredSize(new Dimension(outputGUIFrame.getWidth(), screenHeight));
		graphScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		autocompletePanel = new AutocompletePanel(lastFMHistoryModel.tracks, lastFMHistoryModel.artists);
		//autocompletePanel.setPreferredSize(new Dimension(screenWidth, 100));



		//graphScrollPanel.setBounds(0, 0, this.screenWidth, this.screenHeight);
		//graphScrollPanel.add(graphPanel);
		outputGUIFrame.add(graphScrollPanel, BorderLayout.CENTER);
		outputGUIFrame.add(autocompletePanel, BorderLayout.PAGE_END);


		outputGUIFrame.setVisible(true);
	}
	
	
}
