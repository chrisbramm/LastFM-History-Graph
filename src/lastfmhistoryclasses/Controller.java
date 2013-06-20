package lastfmhistoryclasses;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import lastfmhistoryguis.*;

public class Controller {
	
	private LastFMHistory lastFMHistoryModel;
	private InputGUI inputGUIView;
	private OutputGUI outputGUIView;
	private GraphPanel graphPanel;
	private String username;
	private int screenWidth;
	private int screenHeight;

	public Controller(int screenWidth, int screenHeight){
		lastFMHistoryModel = new LastFMHistory();
		inputGUIView = new InputGUI(screenWidth, screenHeight);
		
		
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		inputGUIView.addGetDataListener(new GetData());
		inputGUIView.addDeleteLibraryFile(new DeleteLibraryFile());
	}
	
	
	class GetData implements ActionListener {
		public void actionPerformed(ActionEvent e){
			username = "Not Set";
			try {
				username = inputGUIView.getUsername();
				lastFMHistoryModel.setUser(username);
				long tDO = lastFMHistoryModel.getLastDate();
				lastFMHistoryModel.getLibraryTracks();
				lastFMHistoryModel.createColorHashmap();
				lastFMHistoryModel.createDurationHashmap();
				inputGUIView.addScrobbleFilter(tDO);
			}catch(Exception ex){
				System.err.println(ex);
				System.out.println(username);
			}
			inputGUIView.addGetLastFMHistoryListener(new GetLastFMHistory());			
		}
	}
	class GetLastFMHistory implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
//				
				lastFMHistoryModel.getRecentTracks();
				lastFMHistoryModel.graphMax();
				lastFMHistoryModel.getTrackList();
				lastFMHistoryModel.getArtistList();
				
				outputGUIView = new OutputGUI(lastFMHistoryModel, screenWidth, screenHeight, 1);
				outputGUIView.autocompletePanel.addZoom2000(new Zoom2000());
				outputGUIView.autocompletePanel.addZoomDefault(new ZoomDefault());
				outputGUIView.autocompletePanel.addZoom6000(new Zoom6000());
				outputGUIView.autocompletePanel.addTrackListener(new trackListener());
				outputGUIView.autocompletePanel.addArtistListener(new artistListener());
				
				outputGUIView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				inputGUIView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}catch(Exception ex){
				System.err.println(ex);
				System.out.println(username);
			}
			
		}
		
	}
	class DeleteLibraryFile implements ActionListener{
		public void actionPerformed(ActionEvent e){
			username = "Not Set";
			try{
				username = inputGUIView.getUsername();
				FileStuff.deleteFile(username + "_library.txt");
			}catch(Exception ex){
				System.err.println(ex);
				System.out.println(username);
			}
		}
	}
	
	class ZoomDefault implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Action Received: DefaultZoom");
			outputGUIView.graphPanel.zoom(1);
			outputGUIView.graphScrollPanel.revalidate();
			//outputGUIView.graphScrollPanel.updateUI();
			
		}
	}
	class Zoom2000 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Action Received: 2000Zoom");
			outputGUIView.graphPanel.zoom(2);
			outputGUIView.graphScrollPanel.revalidate();
			
		}
	}
	class Zoom6000 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Action Received: 6000Zoom");
			outputGUIView.graphPanel.zoom(6);
			outputGUIView.graphScrollPanel.revalidate();
			
		}
	}
	class trackListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Action Performed: Track Button Pressed");
			String trackName = outputGUIView.autocompletePanel.trackInput.getText();
			System.out.println(trackName);
			outputGUIView.graphPanel.graphTrack(trackName);
		}
		
	}
	class artistListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String artistName = outputGUIView.autocompletePanel.artistInput.getText();
			System.out.println(artistName);
			outputGUIView.graphPanel.graphArtist(artistName);
		}
		
	}
	
}
