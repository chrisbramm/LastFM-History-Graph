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
	public JSpinner spinnerFrom;
	public JSpinner spinnerTo;
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
		//System.out.println("addScrobbleFilter 1");
		JPanel scrobbleFilterPanel = new JPanel(new BorderLayout());
		Date dateOfFirstScrobble = new Date(trueDateOrigin * 1000);
		
		Calendar calendarTo = Calendar.getInstance();
		Date initDateTo = calendarTo.getTime();
		Calendar calendarFrom = Calendar.getInstance();
		calendarFrom.add(Calendar.DATE, -14);
		Date initDateFrom = calendarFrom.getTime();
		//System.out.println("Calendar: " + calendar + " initDate: " + initDate + " trueDateOrigin: " + dateOfFirstScrobble);
		SpinnerDateModel modelFrom = new SpinnerDateModel(initDateFrom, dateOfFirstScrobble, initDateTo, Calendar.DAY_OF_WEEK);
		SpinnerDateModel modelTo = new SpinnerDateModel(initDateTo, dateOfFirstScrobble, initDateTo, Calendar.DAY_OF_WEEK);
		//System.out.println("addScrobbleFilter 2");
		
		spinnerFrom = new JSpinner(modelFrom);
		JSpinner.DateEditor deFrom = new JSpinner.DateEditor(spinnerFrom, "dd MMM yy");
		spinnerFrom.setEditor(deFrom);
		scrobbleFilterPanel.add(spinnerFrom, BorderLayout.LINE_START);
		//System.out.println("addScrobbleFilter 3");
		
		spinnerTo = new JSpinner(modelTo);
		JSpinner.DateEditor deTo = new JSpinner.DateEditor(spinnerTo, "dd MMM yy");
		spinnerTo.setEditor(deTo);
		scrobbleFilterPanel.add(spinnerTo, BorderLayout.LINE_END);
		//System.out.println("addScrobbleFilter 4");
		
		btnGetHistory = new JButton("Get History");
		scrobbleFilterPanel.add(btnGetHistory, BorderLayout.PAGE_END);
		//System.out.println("addScrobbleFilter 5");
		scrobbleFilterPanel.setVisible(true);
		//System.out.println("addScrobbleFilter 6");
		
		contentPane.add(scrobbleFilterPanel, BorderLayout.CENTER);
		//System.out.println("addScrobbleFilter 7");
		contentPane.revalidate();
		//System.out.println("addScrobbleFilter 8");
		contentPane.repaint();
		//System.out.println("addScrobbleFilter 9");
		pack();
		//System.out.println("addScrobbleFilter 10");
		
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
