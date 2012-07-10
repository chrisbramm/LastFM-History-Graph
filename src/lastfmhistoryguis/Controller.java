package lastfmhistoryguis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import lastfmhistoryclasses.FileStuff;
import lastfmhistoryclasses.LastFMHistory;

public class Controller {
	
	private LastFMHistory lastFMHistoryModel;
	private InputGUI inputGUIView;
	private String username;

	public Controller(LastFMHistory model, InputGUI view){
		lastFMHistoryModel = model;
		inputGUIView = view;
		
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
			}catch(Exception ex){
				System.err.println(e);
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
