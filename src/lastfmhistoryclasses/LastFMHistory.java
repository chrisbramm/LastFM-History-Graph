package lastfmhistoryclasses;


import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.geom.*;

import java.io.*;

import javax.swing.*;

import org.xml.sax.SAXParseException;

import de.umass.lastfm.*;
import de.umass.util.*;
import de.umass.xml.*;


public class LastFMHistory {
	private final static String API_KEY = "3c02b48004816856418a2c0b9a4e785b";;
	private String user;
	public Collection<Track> history;
	private Collection<Track> library;
	private HashMap<String, Color> colorMapping;
	private HashMap<String, Integer> durationMapping;
	private int page;
	private int per_page = 50;
	private int total;
	private String tst = "tst";
	//private String file_name;
	public long originDate;
	public int dayMax;
	
	public List<String> artists = new ArrayList<String>();
	public List<String> tracks = new ArrayList<String>();
	
	
	public LastFMHistory(){
		Caller.getInstance().setUserAgent(tst);
		Caller.getInstance().setDebugMode(true);
	}
	
	/**
	 * Sets the username of the lastFMHistory model
	 * 
	 * @param user The username of the Last.fm user
	 */
	public void setUser(String user){
		this.user = user;
		
		System.out.println(this.user);
	}
	
	
	public long getLastDate(){
		PaginatedResult<Track> result = User.getRecentTracks(user, page, per_page, API_KEY);
		total = result.getTotalPages();
				
		System.out.println(total);
		
		result = User.getRecentTracks(user, total, per_page, API_KEY);
		Collection<Track> pageResults = result.getPageResults();
		int i = 1;
		long trueDateOrigin = 0;
		for (Track t: pageResults){
			Date datePlayed = t.getPlayedWhen();
			long timePlayed = datePlayed.getTime() / 1000;
			
			if (i == 1){
				trueDateOrigin = timePlayed;
			}else{
				if (timePlayed < trueDateOrigin){
					trueDateOrigin = timePlayed;
				}
			}
			System.out.println(i + " = " + datePlayed + ", " + timePlayed);
			System.out.println(trueDateOrigin);
			i++;
		}
		int pageResultsSize = pageResults.size();
		System.out.println(pageResultsSize);
		
		return trueDateOrigin;
	}
	
	public Collection<Track> getLibraryTracks(){
		String file_name = user + "_library.txt";
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
					System.out.println(l);
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
		//System.out.println("Library size: " + library.size());
		return library;
	}
	
	public HashMap<String, Color> createColorHashmap(){
		colorMapping = new HashMap();
		for(Track l : library){
			String hashName = l.getName();
			String hashArtist = l.getArtist();
			String hashString = hashName + hashArtist;
			Color color = l.getColour();
			//System.out.println(l + ", " + l.hashCode() + ", " + hashString.hashCode());
			colorMapping.put(hashString, color);
			
		}
		return colorMapping;
	}
	public HashMap<String, Integer> createDurationHashmap(){
		durationMapping = new HashMap();
		for(Track l : library){
			String hashName = l.getName();
			String hashArtist = l.getArtist();
			String hashString = hashName + hashArtist;
			int duration = l.getDuration();
			//System.out.println(l + ", " + l.hashCode() + ", " + hashString.hashCode());
			durationMapping.put(hashString, duration);
		}
		return durationMapping;
	}
	
	public Collection<Track> getRecentTracks() {
		String file_name = user + "_history1.txt";
		history = new ArrayList<Track>();
		page = 1;
		
		int i = 0;
		long unixDate;
		/*try{
			history = FileStuff.openFile(file_name);
			for (Track h : history){
				System.out.println("History Track Name: " + h.getName());
			}
			System.out.println("LOLOLOL I'M READING THE HISTORY");
			if (history.isEmpty() == true){
				System.out.println("Library is not set");
			}
		}catch(FileNotFoundException x){*/
			do {
				try {
					PaginatedResult<Track> result = User.getRecentTracks(user, page, per_page, API_KEY);
					total = /*result.getTotalPages();*/ 5;
					System.out.println(total + ", " + page);
					Collection<Track> pageResults = result.getPageResults();
					for (Track t: pageResults){
						String hashName = t.getName();
						String hashArtist = t.getArtist();
						String hashString = hashName + hashArtist;
						try{
							Color colorOfOriginalMapping = colorMapping.get(hashString);
							t.setColour(colorOfOriginalMapping);
						}catch(Exception e){
							System.err.println(e + "Could not find track in colorMapping");
						}

						try{
							int duration = durationMapping.get(hashString);
							t.setDuration(duration);
						}catch (Exception e){
							t.setDuration(60);
							System.err.println(e + ", " + t + " Could not find track in durationMapping");
						}
						//Pull out date of when played and then use it to set the coordinates for the graph		
						if(t.getPlayedWhen() != null){
							Date fullDate = t.getPlayedWhen();
							Calendar fullDateCalendar = Calendar.getInstance();
							fullDateCalendar.setTime(fullDate);
							TimeZone timeZone = fullDateCalendar.getTimeZone();
							//System.out.println(timeZone + ", " + timeZone.getDSTSavings() + ", " + timeZone.inDaylightTime(fullDate));
							int timeZoneOffset = timeZone.getRawOffset();
							unixDate = (fullDate.getTime() + timeZoneOffset) / 1000;

							//This Section corrects for being in DST
							if(timeZone.inDaylightTime(fullDate) == true){
								int dSTOffset = timeZone.getDSTSavings() / 1000;
								unixDate = unixDate + dSTOffset;
							}

							if (i == 0){
								originDate = unixDate;
								i++;
							}
							t.setCoordinates(unixDate, originDate);
							//System.out.println(t);
						}
					}
					history.addAll(pageResults);


					if(page % 15 == 0){
						try {
							Thread.sleep(1500);
						}catch (InterruptedException ex) {
							System.out.println("Error! :(");
						}
					}
				}catch(Exception e){
					System.out.println(e);
				}
				page++;
				try {
					FileStuff.saveFile(file_name, history);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (page <= total);
		/*}catch(IOException x){
			System.err.println(x);
		}*/
			
		System.out.println("History Size:" + history.size());
		
		return history;
		
	}
	
	public void graphMax(){
		for(Track t: history){
			if(t.getDay() < dayMax){
				dayMax = t.getDay();
				
			}
		}
		dayMax = Math.abs(dayMax) + 1;
		
	}
	
	public List<String> getTrackList(){
		for(Track t: history){
			String trackName = t.getName();
			if(!tracks.contains(trackName)){
				tracks.add(trackName);
			}
		}
		return tracks;
	}
	
	public List<String> getArtistList(){
		for(Track t: history){
			String artist = t.getArtist();
			if (!artists.contains(artist)){
				artists.add(artist);
			}
		}
		return artists;
	}
}

