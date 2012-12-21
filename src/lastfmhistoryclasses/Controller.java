package lastfmhistoryclasses;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public Controller(LastFMHistory model, InputGUI view, int screenWidth, int screenHeight){
		lastFMHistoryModel = model;
		inputGUIView = view;
		
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		inputGUIView.addGetLastFMHistoryListener(new GetLastFMHistory());
		inputGUIView.addDeleteLibraryFile(new DeleteLibraryFile());
	}
	
	
	
	class GetLastFMHistory implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			username = "Not Set";
			try {
				username = inputGUIView.getUsername();
				System.out.println(username);
				lastFMHistoryModel.setUser(username);
				lastFMHistoryModel.getLibraryTracks();
				lastFMHistoryModel.createColorHashmap();
				lastFMHistoryModel.createDurationHashmap();
				lastFMHistoryModel.getRecentTracks();
				lastFMHistoryModel.graphMax();
				lastFMHistoryModel.getTrackList();
				lastFMHistoryModel.getArtistList();
				
				outputGUIView = new OutputGUI(lastFMHistoryModel, screenWidth, screenHeight, 1);
				outputGUIView.autocompletePanel.addZoom2000(new Zoom2000());
				outputGUIView.autocompletePanel.addZoomDefault(new ZoomDefault());
				outputGUIView.autocompletePanel.addZoom6000(new Zoom6000());
				outputGUIView.autocompletePanel.addTrackListener(new trackListener());
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
}
