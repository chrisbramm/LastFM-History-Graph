package lastfmhistoryclasses;

import java.util.*;
import java.io.*;
import de.umass.lastfm.*;

public class FileStuff {

	private static String path;
	private static final String REGEX = "\\[(\\w*=(.*),)*\\w*=(.*)\\]";
;
	
	
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
	
		 Collection<Track> library = null;
		 int noOfLines = readLines(file_path);
		 
		 //String line = textReader.readLine();
		 
		 System.out.println(noOfLines);
		 
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
