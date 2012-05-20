package lastfmhistoryguis;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.umass.lastfm.Track;
import lastfmhistoryclasses.LastFMHistory;

public class OutputGUI extends JPanel{
	private final static int PAD = 20;

	
	public void paintComponent(Graphics g){
		Graphics2D graph = (Graphics2D)g;
		int w = 1200;
		int h = 800;
		int x0 = w - PAD;
		graph.draw(new Rectangle2D.Double(PAD, PAD, w-2*PAD, h-2*PAD));
		double xInc = (double)(w - 2*PAD)/(Math.abs(dayMax));
		double secondHeight = (double)(h - 2*PAD)/86400;
		for(Track t: history){
			Color color = t.getColour();
			int duration = t.getDuration();
			int day = t.getDay();
			
		
		}
	}
	
}
