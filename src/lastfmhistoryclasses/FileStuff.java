package lastfmhistoryclasses;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import de.umass.lastfm.*;

public class FileStuff {

	private static String path;
	private static final String REGEX = "name=(.*),artist=(.*),duration=(.*),red=(.*),green=(.*),blue=(.*)\\]";
	public static Collection<Track> library;
	
	
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

	 public static Collection<Track> openFile(String file_path) throws IOException{
		 path = file_path;
	
		 FileReader fr = new FileReader(path);
		 BufferedReader textReader = new BufferedReader(fr);
	

		 int noOfLines = readLines(path);
		 
		 Pattern p = Pattern.compile(REGEX);
		 
		 for (int i = 0; i<5; i++){
			 String line = textReader.readLine();
			 Matcher m = p.matcher(line);
			 boolean b = m.find();
			 System.out.println(b);
			 
			 if (b) {
				    String name = m.group(1);
				    String artist = m.group(2);
				    int duration = Integer.parseInt(m.group(3));
				    int red = Integer.parseInt(m.group(4));
				    int green = Integer.parseInt(m.group(5));
				    int blue = Integer.parseInt(m.group(6));
				    
				    System.out.println(name + ", " + artist + ", " + duration + ", " + red + ", " + green + ", " + blue);
				    
				    Track track = new Track(name, artist, duration, red, green, blue);
				    
				    System.out.println(track);    
			}
		 }
		 return library;
	 }

	public static void saveFile(String file_path, Collection<Track> library) throws IOException {
		path = file_path;
		int red = 0;
		int green = 255;
		
		File file = new File(path);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		// FileWriter fr = new FileWriter(path);
		// BufferedWriter out = new BufferedWriter(fr);
		for (Track l : library) {
			
			int blue = (int)(Math.random() * 255);
			//System.out.println(l);
			
			l.setColour(red, green, blue);
			
			String line = l.toFileString();
			out.write(line);
			out.newLine();
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

		// out.write(line);

	}
	public static void deleteFile(String file_path){
		path = file_path;
		
		File file = new File(path);
		file.delete();
		
		
	}
		
	
}
