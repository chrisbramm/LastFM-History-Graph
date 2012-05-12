package lastfmhistoryclasses;


import java.util.*;
import java.awt.Color;
import java.io.*;

import de.umass.lastfm.*;
import de.umass.util.*;
import de.umass.xml.*;


public class LastFMHistory {
	private final static String API_KEY = "3c02b48004816856418a2c0b9a4e785b";;
	private String user;
	private Collection<Track> history;
	private Collection<Track> library;
	private HashMap<String, Color> colorMapping;
	private int page;
	private int total;
	private String tst = "tst";
	private String file_name;
	private long originDate;
	
	
	
	public LastFMHistory(){
		Caller.getInstance().setUserAgent(tst);
		Caller.getInstance().setDebugMode(false);
	}
	
	public void setUser(String user){
		this.user = user;
		this.file_name = user + ".txt";
		System.out.println(this.user);
	}
	
	public Collection<Track> getLibraryTracks(){
		try{
			library = FileStuff.openFile(file_name);
			if (library.isEmpty() == true){
				System.out.println("Library is not set");
			}
		}catch(FileNotFoundException e) {
			try{
				library = de.umass.lastfm.Library.getAllTracks(user, API_KEY);
				int red = 0;
				int green = 255;
				int blue;
				
				for(Track l : library){

					blue = (int)(Math.random() * 255);
					Color color = new Color(red, green, blue);
					
					l.setColour(color);
					if (red == 255){
						red = 0;
					}else{
					red++;
					}
					if (green == 0){
						green = 255;
					}else{
					green--;
					}
				}
				FileStuff.saveFile(file_name, library);
			}catch(IOException x){
				System.out.println(x);
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		System.out.println(library.size());
		return library;
	}
	public HashMap<String, Color> createHashmap(){
		colorMapping = new HashMap();
		for(Track l : library){
			String hashName = l.getName();
			String hashArtist = l.getArtist();
			String hashString = hashName + hashArtist;
			Color color = l.getColour();
			System.out.println(l + ", " + l.hashCode() + ", " + hashString.hashCode());
			colorMapping.put(hashString, color);
			
		}
		return colorMapping;
	}
	
	public Collection<Track> getRecentTracks() {
		history = new ArrayList<Track>();
		page = 1;
		int per_page = 50;
		final long STARTTIME = System.currentTimeMillis();
		int i = 0;
		long unixDate;
		do {
			PaginatedResult<Track> result = User.getRecentTracks(user, page, per_page, API_KEY);
			
			//PaginatedResult<Track> result = Library.getTracks(user, page, per_page, API_KEY);
			total = /*result.getTotalPages()*/ 1;
			Collection<Track> pageResults = result.getPageResults();
			for (Track t: pageResults){
				String hashName = t.getName();
				String hashArtist = t.getArtist();
				String hashString = hashName + hashArtist;
				Color colorOfOriginalMapping = colorMapping.get(hashString);
				t.setColour(colorOfOriginalMapping);
				
				if(t.getPlayedWhen() != null){
					Date fullDate = t.getPlayedWhen();
					unixDate = (fullDate.getTime() / 1000);
					if (i == 0){
							originDate = unixDate;
							i++;
					}	
					t.setCoordinates(unixDate, originDate);
				}
				System.out.println(t);
			}
			
			history.addAll(pageResults);
			
//			System.out.println("P: " + page + " " + result);
//			for(Track t: result.getPageResults()){
//				System.out.println(t.getArtist() + "\t" + t.getName() + "\t" + t.getPlayedWhen());
//			}
			page++;
			if(page % 10 == 0){
				try {
					Thread.sleep(5000);
				}catch (InterruptedException ex) {
					System.out.println("Error! :(");
				}
			}
		} while (page <= total);
		final long ENDTIME = System.currentTimeMillis();
		final long DURATION = ENDTIME - STARTTIME;
		System.out.println(DURATION);
		System.out.println(history.size());
		return history;
		
	}
	
//	public void getHistory(){
//		de.umass.lastfm.Library.getAllTracks(user, apiKey);
//		
//		System.out.println(history.size());
//	}
//	
//	public void iterateHistory(){
//		Iterator<Track> tracksItr = tracks.iterator();
//		while(tracksItr.hasNext()){
//			
//			String title = tracks.get(name)
//			
//			System.out.println(tracksItr.next());
//		}
	
	public void iterateHistory(){
		int i = 0;
		long unixDate;
		for (Track t: history){
			
			if(t.getPlayedWhen() != null){
				Date fullDate = t.getPlayedWhen();
				unixDate = (fullDate.getTime() / 1000);
				if (i == 0){
						originDate = unixDate;
						i++;
					}
				
				t.setCoordinates(unixDate, originDate);
				System.out.println(t);
			}
		}
		System.out.println(originDate);
		
	}
	

	
	
}

