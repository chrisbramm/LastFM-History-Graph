package lastfmhistoryguis;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import de.umass.lastfm.Track;

import lastfmhistoryclasses.*;

public class OutputGUI extends JFrame {
	
	private LastFMHistory lastFMHistoryModel;
	private JFrame outputGUIFrame;
	public GraphPanel graphPanel;
	public AutocompletePanel autocompletePanel;
	public JScrollPane graphScrollPanel;
	private int screenWidth;
	private int screenHeight;
	private int zoom;
	private Dimension standardScreenSize;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutputGUI(LastFMHistory model, int screenWidth, int screenHeight, int zoom){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.standardScreenSize = new Dimension(screenWidth, screenHeight);
		this.lastFMHistoryModel = model;
		this.zoom = zoom;
		createGUI();
	}
	
	
	public void createGUI(){
		outputGUIFrame = new JFrame();
		outputGUIFrame.setSize(standardScreenSize);
		outputGUIFrame.setLocation(10, 10);
		outputGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int frameWidth = outputGUIFrame.getWidth();
		int frameHeight = outputGUIFrame.getHeight();
		
		System.out.println("OutputGUI:: Frame Width: " + frameWidth + ", Frame Height:" + frameHeight);

		graphPanel = new GraphPanel(lastFMHistoryModel, zoom);
		


		graphScrollPanel = new JScrollPane(graphPanel);
		graphScrollPanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
		graphScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		autocompletePanel = new AutocompletePanel(lastFMHistoryModel.tracks, lastFMHistoryModel.artists);
		outputGUIFrame.add(autocompletePanel, BorderLayout.PAGE_END);
		
		//autocompletePanel.setPreferredSize(new Dimension(screenWidth, 100));



		//graphScrollPanel.setBounds(0, 0, this.screenWidth, this.screenHeight);
		//graphScrollPanel.add(graphPanel);
		outputGUIFrame.add(graphScrollPanel, BorderLayout.CENTER);
		


		outputGUIFrame.setVisible(true);
	}
	
	
	
}
