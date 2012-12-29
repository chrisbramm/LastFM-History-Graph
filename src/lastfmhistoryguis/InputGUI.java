package lastfmhistoryguis;

import java.awt.*;
import java.awt.event.ActionListener;
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
	
	public void addDateSorter(long trueDateOrigin){
		JPanel dateSorterPanel = new JPanel(new GridLayout(4,1));
		
		//dateSorterPanel.setPreferredSize(new Dimension(200,200));
		dateSorterPanel.setBackground(Color.RED);
		
		System.out.println(dateSorterPanel.getPreferredSize());
		btnGetHistory = new JButton("Get History");
		//btnGetHistory.setSize(50, 20);
		dateSorterPanel.add(btnGetHistory);
		dateSorterPanel.setVisible(true);
		
		
		contentPane.add(dateSorterPanel, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
		pack();
		
		System.out.println(dateSorterPanel.isShowing());
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
