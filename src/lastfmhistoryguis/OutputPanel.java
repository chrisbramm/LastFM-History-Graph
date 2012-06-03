package lastfmhistoryguis;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.umass.lastfm.Track;
import lastfmhistoryclasses.LastFMHistory;

public class OutputPanel extends JPanel {
	private static int PAD = 20;
	int panelWidth;
	int panelHeight;
	private LastFMHistory data;

	public void setData(LastFMHistory data) {
		this.data = data;
		System.out.println("Data SET");
		paint(getGraphics());
		repaint();

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//System.out.println("Drawing");
		Graphics2D graph = (Graphics2D) g;

		if (data == null) {
			System.err.println("No data found");
		} else {

			panelWidth = getWidth() - 4* PAD;
			//int panelHeight = getHeight() - 2 * PAD;
			panelHeight = 2000 - 2 * PAD;
			

			System.out.println(panelWidth + ", " + panelHeight);

			
			int x0 = panelWidth + PAD;
			graph.draw(new Rectangle2D.Double(PAD, PAD, panelWidth, panelHeight));
			double xInc = (double) (panelWidth) / (data.dayMax);
			double secondHeight = (double) (panelHeight) / 86400;
			
			for(int i = 0; i <= 86400; i++){
				if(i % 3600 == 0){
					graph.draw(new Line2D.Double(x0, (i*secondHeight) + PAD, x0 + 10, (i*secondHeight) + PAD));
					String hour = Integer.toString(i / 3600);
					graph.drawString(hour, x0 + 15, (int)((i*secondHeight) + PAD));
				}
			}

			for (Track t : data.history) {
				if (t.getPlayedWhen() != null) {
					
					Color color = t.getColour();

					int duration = t.getDuration();
					int day = Math.abs(t.getDay());
					double dayOrigin = x0 - ((day + 1) * xInc);
					double timeOrigin = t.getGraphHeight()*secondHeight + PAD;
					double trackHeight = duration * secondHeight;
					graph.setColor(color);
					//System.out.println("PLOTTING TRACK, " + day + ", " +  dayOrigin + ", " + t.getGraphHeight()  + ", " + timeOrigin  + ", " + trackHeight);
					//graph.draw(new Rectangle2D.Double(dayOrigin, timeOrigin, xInc, trackHeight));
					graph.fillRect((int)dayOrigin, (int)timeOrigin, (int)xInc, (int)trackHeight);
				}

			}
		}
	}
	public Dimension getPreferredSize(){
		return new Dimension((int)panelWidth, (int)panelHeight);
	}

}
