package lastfmhistoryguis;

import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import lastfmhistoryclasses.LastFMHistory;

public class OutputGUI extends Canvas{

	public OutputGUI(){
		OutputGUI canvas = new OutputGUI();
		JFrame frame = new JFrame();
		frame.setSize(1200, 800);
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		
	}
	public void paint(Graphics graphics){
	}
	
	
	
	
}
