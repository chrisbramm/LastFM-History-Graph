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
		inputGUIFrame.setBounds(50, 50, 700, 400);
		
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
	
	public void addScrobbleFilter(long trueDateOrigin){
		JPanel dateSorterPanel = new JPanel(new BorderLayout());
		Calendar calendar = Calendar.getInstance();
		Date dateOfFirstScrobble = new Date(trueDateOrigin * 1000);
		Date initDate = calendar.getTime();
		System.out.println("Calendar: " + calendar + " initDate: " + initDate + " trueDateOrigin: " + dateOfFirstScrobble);
		SpinnerDateModel s = new SpinnerDateModel(initDate, dateOfFirstScrobble, initDate, Calendar.YEAR);
		JSpinner spinner = new JSpinner(s);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "dd MMM yy");
		spinner.setEditor(de);
		dateSorterPanel. add(spinner, BorderLayout.LINE_START);
		
		
		JPanel beginDatePanel = new JPanel();
		beginDatePanel.setLayout(new BoxLayout(beginDatePanel, BoxLayout.PAGE_AXIS));
//		DateUtils dU = new DateUtils();
//		String[] monthStrings = dU.getMonthStrings();
//		
		
		
		
		
		btnGetHistory = new JButton("Get History");
		dateSorterPanel.add(btnGetHistory, BorderLayout.PAGE_END);
		dateSorterPanel.setVisible(true);
		
		
		contentPane.add(dateSorterPanel, BorderLayout.CENTER);
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
