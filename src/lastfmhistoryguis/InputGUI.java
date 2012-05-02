package lastfmhistoryguis;

import lastfmhistoryclasses.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputGUI extends JFrame {

	private LastFMHistory test;
	private JPanel contentPane;
	private JTextField username;
	private JButton btnDeleteLibraryFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputGUI frame = new InputGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InputGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		username = new JTextField("wizard710");
		contentPane.add(username, BorderLayout.NORTH);
		username.setColumns(10);
		
		JButton btnGetHistory = new JButton("Get History");
		btnGetHistory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				doHistory();
			};
		});
		
		contentPane.add(btnGetHistory, BorderLayout.SOUTH);
		
		btnDeleteLibraryFile = new JButton("Delete Library File");
		btnDeleteLibraryFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				doDeleteLibrary();
			};
		});
		
		contentPane.add(btnDeleteLibraryFile, BorderLayout.CENTER);
		
		test = new LastFMHistory();
	}
	
	public void doHistory(){
		String user = username.getText();
		test.setUser(user);
//		test.getRecentTracks();
//		test.iterateHistory();
		test.getLibraryTracks();
		test.createHashmap();
		
		
	}
	
	public void doDeleteLibrary(){
		String user = username.getText();
		String file = user + ".txt";
		FileStuff.deleteFile(file);
		
	}
}
