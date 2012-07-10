package lastfmhistoryguis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import lastfmhistoryclasses.LastFMHistory;

public class Controller {
	
	private LastFMHistory lastFMHistoryModel;
	private InputGUI inputGUIView;
	private String username;

	public Controller(LastFMHistory model, InputGUI view){
		lastFMHistoryModel = model;
		inputGUIView = view;
		
		inputGUIView.addGetLastFMHistoryListener(new GetLastFMHistory());
	}
	
	class GetLastFMHistory implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String username = "Not Set";
			try {
				username = inputGUIView.getUsername();
				System.out.println(username);
			}catch(Exception ex){
				System.err.println(e);
			}
			
		}
		
	}
	
}
