package lastfmhistoryclasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import lastfmhistoryguis.*;

public class Controller {
	
	private LastFMHistory lastFMHistoryModel;
	private InputGUI inputGUIView;
	private OutputGUI outputGUIView;
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
				
				outputGUIView = new OutputGUI(lastFMHistoryModel, screenWidth, screenHeight);
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
	
}