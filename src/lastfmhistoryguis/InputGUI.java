package lastfmhistoryguis;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;


public class InputGUI extends JFrame {
	
	private int screenWidth;
	private int screenHeight;
	private JTextField usernameInput;
	private JButton btnGetHistory, btnDeleteHistory;
	
	
	public InputGUI(int screenWidth, int screenHeight){
		createGui();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	// Creates Small GUI to collect username
	
	public void createGui(){
		
		JFrame inputGUIFrame = new JFrame();
		JPanel inputGUIPanel = new JPanel();
		inputGUIFrame.setBounds(50, 50, 450, 100);
		
		usernameInput = new JTextField("wizard710");
		usernameInput.setColumns(15);
		inputGUIPanel.add(usernameInput);
		
		btnGetHistory = new JButton("Get History");
		inputGUIPanel.add(btnGetHistory);
		
		btnDeleteHistory = new JButton("Delete History");
		inputGUIPanel.add(btnDeleteHistory);
		
		
		
		inputGUIFrame.add(inputGUIPanel);
		inputGUIFrame.setVisible(true);
		
	}
	
	
	public String getUsername(){
		System.out.println("getUsername being called");
		return usernameInput.getText();
	}
	public void addGetLastFMHistoryListener(ActionListener gh){
		btnGetHistory.addActionListener(gh);
	}
	public void addDeleteLibraryFile(ActionListener dh){
		btnDeleteHistory.addActionListener(dh);
	}

}
