package lastfmhistoryguis;

import lastfmhistoryclasses.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

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
	private static final int SCREENPAD = 15;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		
		int screenHeight = (int)d.getHeight() - 2*SCREENPAD - 50;
		int screenWidth = (int)d.getWidth() - 2*SCREENPAD;
		
		
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
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new OutputGUI());
		f.setSize(screenWidth, screenHeight);
		//f.setExtendedState(MAXIMISED_BOTH);
		f.setLocation(20, 20);
		f.setVisible(true);
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
		test.getLibraryTracks();
		test.createColorHashmap();
		test.createDurationHashmap();
		test.getRecentTracks();
//		test.iterateHistory();
		
		
		
	}
	
	public void doDeleteLibrary(){
		String user = username.getText();
		String file = user + ".txt";
		FileStuff.deleteFile(file);
		
	}
}
