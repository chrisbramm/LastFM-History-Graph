package lastfmhistoryclasses;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.umass.lastfm.Track;

public class FileStuff {

	private static String path;
	private static final String REGEX = "name=(.*),artist=(.*),duration=(.*),red=(.*),green=(.*),blue=(.*)\\]";
	private static final String REGEXColor = "name=(.*),artist=(.*),duration=(.*),color=java\\.awt\\.Color\\[r=(.*),g=(.*),b=(.*)\\]\\,day=(.*),graphHeight=(.*)]";
	public static Collection<Track> collection = new HashSet<Track>(); 
	
	
	private static int readLines(String file_path) throws IOException {
		path = file_path;
		
		FileReader fr = new FileReader(path);
		BufferedReader lineReader = new BufferedReader(fr);
		
		String aLine;
		int noOfLines = 0;
		
		while(( aLine  = lineReader.readLine()) != null){
			noOfLines++;
		}
		lineReader.close();
		
		return noOfLines;
		
	}

	 public static Collection<Track> openFile(String file_path) throws FileNotFoundException, IOException{
		 path = file_path;
	
		 FileReader fr = new FileReader(path);
		 BufferedReader textReader = new BufferedReader(fr);

		 int noOfLines = readLines(path);
		 
		 Pattern p = Pattern.compile(REGEXColor);
		 
		 //System.out.println("# of lines: " + noOfLines);
		 
		 for (int i = 0; i < noOfLines; i++){
			 String line = textReader.readLine();
			 Matcher m = p.matcher(line);
			 boolean b = m.find();
			 
			 if (b == true) {
				 	
				    String name = m.group(1);
				    String artist = m.group(2);
				   				    
				    int duration = Integer.parseInt(m.group(3));
				    int red = Integer.parseInt(m.group(4));
				    int green = Integer.parseInt(m.group(5));
				    int blue = Integer.parseInt(m.group(6));
				    Color color = new Color(red,green,blue);
				    int day = Integer.parseInt(m.group(7));
				    int graphHeight = Integer.parseInt(m.group(8));
				    
				    /*if(!dateString.equals("null")){
				    	System.out.println(dateString);
				        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
				        try {
				        	Date playedWhen = format.parse(dateString);
				        	System.out.println(playedWhen);
				        	Track track = new Track (name, null, artist, color, duration, playedWhen);
				        	System.out.println(track);
						    library.add(track);
				        } catch (ParseException e) {
				        	// TODO Auto-generated catch block
				        	System.err.println(e);
				        }
				    }*/
				    
				    if(graphHeight != 0){
				    	Track track = new Track(name, null, artist, color, duration, day, graphHeight);
				    	System.out.println("History Track " + track);
				    	collection.add(track);
				    }else{
				    	Track track = new Track(name, null, artist, color, duration);
				    	System.out.println("Library Track " + track);
					    collection.add(track);
				    }
				    
				    
				    //SimpleDateFormat date = new SimpleDateFormat(m.group(3));
				    //Date playedWhen = parse(date);
				    
				    
				    
				   
				    
			}
		 }
		 textReader.close();
		 return collection;
	 }

	public static void saveFile(String file_path, Collection<Track> library) throws IOException {
		path = file_path;
		
		
		File file = new File(path);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		// FileWriter fr = new FileWriter(path);
		// BufferedWriter out = new BufferedWriter(fr);
		for (Track l : library) {
			String hashName = l.getName();
			String hashArtist = l.getArtist();
			String hashString = hashName + hashArtist;
			//System.out.println("Save File: Played When: " + l.getPlayedWhen());
			//Date playedWhen = l.getPlayedWhen();
			//long playedTime = playedWhen.getTime();
			String line = l.toFileString() + ", "+ l.hashCode() + ", "+ hashString.hashCode();
			out.write(line);
			out.newLine();
		}
	out.close();
		// out.write(line);

	}
	public static void deleteFile(String file_path){
		path = file_path;
		
		File file = new File(path);
		file.delete();
		
		
	}
		
	
}
