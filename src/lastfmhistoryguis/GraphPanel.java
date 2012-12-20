package lastfmhistoryguis;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import de.umass.lastfm.*;
import lastfmhistoryclasses.*;

@SuppressWarnings("serial")
public class GraphPanel extends Canvas{
	
	

	private LastFMHistory graphData;
	private int graphHeight;
	private int graphWidth;
	private int zoom;
	private final int PAD = 20;
	private double secondHeight;
	private Graphics2D graph;
	private AffineTransform newScale = new AffineTransform();
	
	public GraphPanel(LastFMHistory model, int zoom){
		this.graphData = model;
		this.zoom = zoom;
	}
	
	public void paint(Graphics g) {
		//System.out.println("Drawing");
		graph = (Graphics2D) g;
		if (graphData == null) {
			System.err.println("No data found");
		} else {
			graphWidth = getWidth() - 4 * PAD;
			secondHeight = ((double) (getHeight()) / 90000) * (double) zoom;
			//graphHeight = getHeight() - 2 * PAD;
			
			int x0 = graphWidth + PAD;
			graph.draw(new Rectangle2D.Double(PAD, PAD, graphWidth, (86400*secondHeight)));
			double xInc = (double) (graphWidth) / (graphData.dayMax);
			

			
			//Draws righthand axis on output graph
			for (int i = 0; i <= 86400; i++) {
				if (i % 3600 == 0) {
					graph.draw(new Line2D.Double(x0, (i * secondHeight) + PAD,
							x0 + 10, (i * secondHeight) + PAD));
					String hour = Integer.toString(i / 3600);
					graph.drawString(hour, x0 + 15,
							(int) ((i * secondHeight) + PAD));
				}
			}
			if (zoom == 2){
				for (int i = 0; i <= 86400; i++) {
					if (i % 1800 == 0) {
						graph.draw(new Line2D.Double(x0, (i * secondHeight) + PAD,
								x0 + 10, (i * secondHeight) + PAD));
						String hour = Double.toString(i / 3600 );
						graph.drawString(hour, x0 + 15,
								(int) ((i * secondHeight) + PAD));
					}
				}
			}
			if (zoom == 6){
				for (int i = 0; i <= 86400; i++) {
					if (i % 600 == 0) {
						graph.draw(new Line2D.Double(x0, (i * secondHeight) + PAD,
								x0 + 10, (i * secondHeight) + PAD));
						
					}
				}
				for (int i = 0; i <= 86400; i++) {
					if (i % 3600 == 0) {
						graph.draw(new Line2D.Double(PAD, (i * secondHeight) + PAD,
								x0, (i * secondHeight) + PAD));
						
					}
				}
			}

			for (Track t : graphData.history) {
				if (t.getPlayedWhen() != null) {

					Color color = t.getColour();

					int duration = t.getDuration();
					int day = Math.abs(t.getDay());
					double dayOrigin = x0 - ((day + 1) * xInc);
					double timeOrigin = t.getGraphHeight() * secondHeight + PAD;
					double trackHeight = duration * secondHeight;
					graph.setColor(color);
					// System.out.println("PLOTTING TRACK, " + day + ", " +
					// dayOrigin + ", " + t.getGraphHeight() + ", " + timeOrigin
					// + ", " + trackHeight);
					// graph.draw(new Rectangle2D.Double(dayOrigin, timeOrigin,
					// xInc, trackHeight));
					graph.fillRect((int) dayOrigin, (int) timeOrigin,
							(int) xInc, (int) trackHeight);
				}

			}
			
			for (int i = 0; i < graphData.dayMax;){
				graph.draw(new Line2D.Double(x0 - i * xInc, PAD, x0 - i * xInc, graphHeight + PAD));
				i = i + 7;
			}
		}
		
		
	}
	public void zoom(int zoom){
		this.zoom = zoom;
		System.out.println("Prefered Size: " + this.getPreferredSize());
		System.out.println("Minumum Size: " + this.getMinimumSize());
		repaint();
	}
		
	
	
}
