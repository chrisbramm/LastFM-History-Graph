package lastfmhistoryguis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import de.umass.lastfm.*;
import lastfmhistoryclasses.*;

public class GraphPanel extends JPanel {

	private LastFMHistory graphData;
	private int panelHeight;
	private int panelWidth;
	private final int PAD = 20;
	
	public GraphPanel(LastFMHistory model){
		this.graphData = model;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		 System.out.println("Drawing");
		Graphics2D graph = (Graphics2D) g;

		if (graphData == null) {
			System.err.println("No data found");
		} else {
			panelWidth = getWidth() - 4 * PAD;
			//panelHeight = getHeight() - 2 * PAD;
			panelHeight = 6000 - 2 * PAD;
			// graph.setClip(0, 0, getWidth(), 2000);
			System.out.println(panelWidth + ", " + panelHeight);

			int x0 = panelWidth + PAD;
			graph.draw(new Rectangle2D.Double(PAD, PAD, panelWidth, panelHeight));
			double xInc = (double) (panelWidth) / (graphData.dayMax);
			double secondHeight = (double) (panelHeight) / 86400;

			for (int i = 0; i <= 86400; i++) {
				if (i % 3600 == 0) {
					graph.draw(new Line2D.Double(x0, (i * secondHeight) + PAD,
							x0 + 10, (i * secondHeight) + PAD));
					String hour = Integer.toString(i / 3600);
					graph.drawString(hour, x0 + 15,
							(int) ((i * secondHeight) + PAD));
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
				graph.draw(new Line2D.Double(x0 - i * xInc, PAD, x0 - i * xInc, panelHeight + PAD));
				i = i + 7;
			}
		}
	}
	
	
}
