package lastfmhistoryguis;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;


import javax.swing.*;


public class InputGUI extends JFrame {
	
	private int screenWidth;
	private int screenHeight;
	private JTextField usernameInput;
	private JButton btnGetData, btnGetHistory, btnDeleteHistory;
	private JFrame inputGUIFrame;
	private Container contentPane;
	
	public InputGUI(int screenWidth, int screenHeight){
		createGui();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	// Creates Small GUI to collect username
	
	public void createGui(){
		
		JFrame inputGUIFrame = new JFrame();
		contentPane = inputGUIFrame.getContentPane();
		inputGUIFrame.setContentPane(contentPane);
		
		JPanel inputGUIPanel = new JPanel();
		inputGUIFrame.setBounds(50, 50, 700, 250);
		
		usernameInput = new JTextField("wizard710");
		usernameInput.setColumns(15);
		inputGUIPanel.add(usernameInput);
		
		btnGetData = new JButton("Get Data");
		inputGUIPanel.add(btnGetData);
		
		btnDeleteHistory = new JButton("Delete History");
		inputGUIPanel.add(btnDeleteHistory);
		
		
		
		contentPane.add(inputGUIPanel, BorderLayout.PAGE_START);
		inputGUIFrame.setVisible(true);
	}
	
	public void progresBarPanel(int libraryTotalPages){
		JPanel progressBarPanel = new JPanel(new BorderLayout());
		JProgressBar libraryTracksProgressBar = new JProgressBar(0, libraryTotalPages);
		libraryTracksProgressBar.setValue(0);
		libraryTracksProgressBar.setStringPainted(true);
		inputGUIFrame.add(progressBarPanel, BorderLayout.CENTER);
	}
	public void addScrobbleFilter(long trueDateOrigin){
		JPanel scrobbleFilterPanel = new JPanel(new BorderLayout());
		
				
		
		Calendar calendar = Calendar.getInstance();
		Date dateOfFirstScrobble = new Date(trueDateOrigin * 1000);
		Date initDate = calendar.getTime();
		System.out.println("Calendar: " + calendar + " initDate: " + initDate + " trueDateOrigin: " + dateOfFirstScrobble);
		SpinnerDateModel modelFrom = new SpinnerDateModel(initDate, dateOfFirstScrobble, initDate, Calendar.DAY_OF_WEEK);
		SpinnerDateModel modelTo = new SpinnerDateModel(initDate, dateOfFirstScrobble, initDate, Calendar.DAY_OF_WEEK);
		
		
		JSpinner spinnerFrom = new JSpinner(modelFrom);
		JSpinner.DateEditor deFrom = new JSpinner.DateEditor(spinnerFrom, "dd MMM yy");
		spinnerFrom.setEditor(deFrom);
		scrobbleFilterPanel.add(spinnerFrom, BorderLayout.LINE_START);
		
		JSpinner spinnerTo = new JSpinner(modelTo);
		JSpinner.DateEditor deTo = new JSpinner.DateEditor(spinnerTo, "dd MMM yy");
		spinnerTo.setEditor(deTo);
		
		scrobbleFilterPanel.add(spinnerTo, BorderLayout.LINE_END);
		
		btnGetHistory = new JButton("Get History");
		scrobbleFilterPanel.add(btnGetHistory, BorderLayout.PAGE_END);
		scrobbleFilterPanel.setVisible(true);
		
		
		contentPane.add(scrobbleFilterPanel, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
		pack();
		
	}
	
	
	public String getUsername(){
		System.out.println("getUsername being called");
		return usernameInput.getText();
	}
	public void addGetDataListener(ActionListener gd){
		btnGetData.addActionListener(gd);
	}
	public void addGetLastFMHistoryListener(ActionListener gh){
		btnGetHistory.addActionListener(gh);
	}
	public void addDeleteLibraryFile(ActionListener dh){
		btnDeleteHistory.addActionListener(dh);
	}

}
