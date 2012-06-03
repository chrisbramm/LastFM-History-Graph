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

public class InputPanel extends JFrame {

	private LastFMHistory test;
	private JPanel contentPane;
	private JTextField username;
	private JButton btnDeleteLibraryFile;
	private OutputPanel outputFrame;
	private static final int SCREENPAD = 15;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		int screenHeight = (int) d.getHeight() - 2 * SCREENPAD - 50;
		int screenWidth = (int) d.getWidth() - 2 * SCREENPAD;
		
		OutputPanel gui = new OutputPanel();
		gui.setVisible(true);
		InputPanel frame = new InputPanel(gui);
//		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LastFMHistory data = frame.getHistory();
		f.setSize(screenWidth, screenHeight);
		
		
		JScrollPane scrollOutput = new JScrollPane(gui);
		//scrollOutput.add(gui);
		scrollOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		f.add(scrollOutput);
		// f.setExtendedState(MAXIMISED_BOTH);
		f.setLocation(20, 20);
		f.setVisible(true);
	
		//By leaving frame.setVisible down here, it creates the input window on top of the output window
		frame.setVisible(true);
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
			};
		});

		contentPane.add(btnGetHistory, BorderLayout.SOUTH);

		btnDeleteLibraryFile = new JButton("Delete Library File");
		btnDeleteLibraryFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDeleteLibrary();
			};
		});

		contentPane.add(btnDeleteLibraryFile, BorderLayout.CENTER);

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
