package lastfmhistoryguis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import lastfmhistoryclasses.LastFMHistory;

public class Controller {
	
	private LastFMHistory lastFMHistoryModel;
	private JFrame inputGUIView;

	public Controller(LastFMHistory model, JFrame view){
		lastFMHistoryModel = model;
		inputGUIView = view;
	}
	
	class GetLastFMHistory implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String username = "";
			try {
				//username = inputGUIView.getUsername();
			}catch(Exception ex){
				System.err.println(e);
			}
			
		}
		
	}
	
}
