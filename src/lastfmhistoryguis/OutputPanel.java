package lastfmhistoryguis;

import java.awt.Canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.Document;

import sas.samples.AutoCompleteDocument;

import de.umass.lastfm.Track;
import lastfmhistoryclasses.LastFMHistory;
import lastfmhistoryclasses.NameService;

public class OutputPanel extends JPanel {
	private static int PAD = 20;
	int panelWidth;
	int panelHeight;
	private LastFMHistory data;
	private static NameService nameService = new NameService(new ArrayList<String>());

	public OutputPanel(JFrame parent) {
		JPanel bottomPanel = new JPanel();

		/**
		 * Create the autocomplete track box
		 **/

		// TODO - Pass Data into nameService
		// List<String> testComplete = new ArrayList<String>();
		// testComplete.add();
		//NameService nameService = new NameService(data.tracks);
		JTextField trackInput = new JTextField();
		Document autoCompleteDocument = new AutoCompleteDocument(nameService,
				trackInput);
		trackInput.setDocument(autoCompleteDocument);

		trackInput.setColumns(25);
		bottomPanel.add(trackInput);

		JButton trackGo = new JButton("Track");
		trackGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		bottomPanel.add(trackGo);
		JScrollPane scrollOutput = new JScrollPane();

		scrollOutput.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		parent.add(bottomPanel, BorderLayout.SOUTH);
		parent.add(scrollOutput, BorderLayout.CENTER);
	}

	public void setData(LastFMHistory data) {
		this.data = data;
//		nameService = new NameService(data.tracks);
		System.out.println("Data SET");
		paint(getGraphics());
		repaint();

	}

	public void setTrack(String name) {
		for (Track track : data.history) {
			if (!track.getName().equals(name)) {
				data.history.remove(track);
			}
		}
		repaint();

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		 System.out.println("Drawing");
		Graphics2D graph = (Graphics2D) g;

		if (data == null) {
			System.err.println("No data found");
		} else {
			panelWidth = getWidth() - 4 * PAD;
			int panelHeight = getHeight() - 2 * PAD;
			// panelHeight = 6000 - 2 * PAD;
			// graph.setClip(0, 0, getWidth(), 2000);
			System.out.println(panelWidth + ", " + panelHeight);

			int x0 = panelWidth + PAD;
			graph.draw(new Rectangle2D.Double(PAD, PAD, panelWidth, panelHeight));
			double xInc = (double) (panelWidth) / (data.dayMax);
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

			for (Track t : data.history) {
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
		}
	}

}
