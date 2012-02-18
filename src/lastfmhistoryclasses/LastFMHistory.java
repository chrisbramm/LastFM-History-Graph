package lastfmhistoryclasses;


import java.util.*;
import java.io.*;

import de.umass.lastfm.*;
import de.umass.util.*;
import de.umass.xml.*;


public class LastFMHistory {
	private final static String API_KEY = "3c02b48004816856418a2c0b9a4e785b";;
	private final static String SECRET = "5f763212ed4f82c1d2afe01e74556bd8";
	private String user;
	private Collection<Track> tracks;
	private Collection<Track> library;
	private int page;
	private int total;
	private String tst = "tst";
	private String file_name;
	
	
	
	public LastFMHistory(){
		Caller.getInstance().setUserAgent(tst);
		Caller.getInstance().setDebugMode(true);
	}
	
	public void setUser(String user){
		this.user = user;
		this.file_name = user + ".txt";
		System.out.println(this.user);
	}
	
	public Collection<Track> getLibraryTracks(){
		try{
			FileStuff.openFile(file_name);
			
			
		}catch(FileNotFoundException e) {
			try{
				library = de.umass.lastfm.Library.getAllTracks(user, API_KEY);
				FileStuff.saveFile(file_name, library);
			}catch(IOException x){
				System.out.println(x);
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		System.out.println(library.size());
		return library;
		//}
	}
	
	public Collection<Track> getRecentTracks() {
		tracks = null;
		page = 1;
		int per_page = 50;
		final long STARTTIME = System.currentTimeMillis();
		do {
			PaginatedResult<Track> result = User.getRecentTracks(user, page, per_page, API_KEY);
			//PaginatedResult<Track> result = Library.getTracks(user, page, per_page, API_KEY);
			total = /*result.getTotalPages()*/ 5;
			Collection<Track> pageResults = result.getPageResults();
			if (tracks == null) {
				// tracks is initialized here to initialize it with the right size and avoid array copying later on
				tracks = new ArrayList<Track>(total * pageResults.size());
			}
			tracks.addAll(pageResults);
			
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
		System.out.println(tracks.size());
		return tracks;
		
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
		long originDate;
		for (Track t: tracks){
			Date fullDate = t.getPlayedWhen();
			try {
				unixDate = fullDate.getTime();
				if (i == 0){
					originDate = unixDate;
					i++;
				}
			} catch (Exception e){
				e.printStackTrace();
				break;
			}
			
			System.out.println(fullDate  + " " + unixDate /*fullDate.getTime()*/);
		}
		
		//System.out.println(originDate);
		
	}
	
	public void iterateLibrary(){
		System.out.println("All the " + library.size() + " tracks in a users Library:");
		for(Track t: library){
			System.out.println(t.getName());
		}
	}
	
	

	
	
}

