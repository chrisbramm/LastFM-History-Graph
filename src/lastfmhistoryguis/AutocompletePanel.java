package lastfmhistoryguis;

import java.awt.*;

import java.awt.event.*;

import java.util.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;

import sas.samples.AutoCompleteDocument;
import lastfmhistoryclasses.*;

@SuppressWarnings("serial")
public class AutocompletePanel extends JPanel{
	
	public List<String> trackList = new ArrayList<String>();
	private List<String> artistList = new ArrayList<String>();
	
	public AutocompletePanel(List<String> trackList, List<String> artistList){
		
		this.trackList = trackList;
		this.artistList = artistList;
		createAutocompletePanel();
		
	}
	
	public void createAutocompletePanel(){
		
		
		
		for(String s : trackList){
			System.out.println(s);
		}
		/**
		 * Create the autocomplete track box
		 **/

		// List<String> testComplete = new ArrayList<String>();
		// testComplete.add();
		//NameService nameService = new NameService(trackList);
		JTextField trackInput = new JTextField();
		//Document autoCompleteDocument = new AutoCompleteDocument(nameService,	trackInput);
		//trackInput.setDocument(autoCompleteDocument);

		trackInput.setColumns(25);
		this.add(trackInput);

		JButton trackGo = new JButton("Track");
		
		this.add(trackGo);
		System.out.println("Lol");
	}

	
	
}
