package lastfmhistoryguis;

import lastfmhistoryclasses.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.Document;
import javax.swing.JFrame;

import sas.samples.AutoCompleteDocument;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InputPanel extends JFrame {

	public LastFMHistory test;
	private JPanel contentPane;
	private JTextField username;
	private JButton btnDeleteLibraryFile;
	private OutputPanel outputFrame;
	private static final int SCREENPAD = 15;
	private static int screenHeight;
	private static int screenWidth;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = (int) d.getHeight() - 2 * SCREENPAD - 50;
		screenWidth = (int) d.getWidth() - 2 * SCREENPAD;
		
		
		JFrame outputFrame = new JFrame();
		outputFrame.setSize(screenWidth, screenHeight);
		OutputPanel outputCanvas = new OutputPanel(outputFrame);
		InputPanel inputFrame = new InputPanel(outputCanvas);
		
		outputFrame.add(outputCanvas, BorderLayout.CENTER);

		
		outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//outputFrame.setSize(200, 200);
		
		// f.setExtendedState(MAXIMISED_BOTH);
		outputFrame.setLocation(20, 20); 
		outputFrame.setVisible(true);
	
		//By leaving frame.setVisible down here, it creates the input window on top of the output window
		inputFrame.setVisible(true);
		
		
		outputCanvas.repaint();
		

	}

	/**
	 * Create the frame.
	 */
	public InputPanel(final OutputPanel outputFrame) {
		this.outputFrame = outputFrame;
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
		btnGetHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputFrame.setData(doHistory());
				outputFrame.invalidate();
				outputFrame.setSize(screenWidth, screenHeight);
			};
		});

		contentPane.add(btnGetHistory, BorderLayout.LINE_START);

		btnDeleteLibraryFile = new JButton("Delete Library File");
		btnDeleteLibraryFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDeleteLibrary();
			};
		});

		contentPane.add(btnDeleteLibraryFile, BorderLayout.LINE_END);

		test = new LastFMHistory();
	}

	public LastFMHistory doHistory() {
		String user = username.getText();
		test.setUser(user);
		test.getLibraryTracks();
		
		test.createColorHashmap();
		test.createDurationHashmap();
		test.getRecentTracks();
		test.graphMax();
		test.getTrackList();
		test.getArtistList();
		return test;

	}

	public LastFMHistory getHistory() {
		return test;
	}
	
	public void doDeleteLibrary() {
		String user = username.getText();
		String file = user + "_library.txt";
		FileStuff.deleteFile(file);

	}
}
