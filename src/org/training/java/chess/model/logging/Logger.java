/**
 * 
 */
package org.training.java.chess.model.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logging class with format dd.mm.yyyy hh.mm.ss
 * 
 * @author Peter Heide, pheide@t-online.de
 * @since 6.10.2012
 */
public class Logger {
	/** Switch logging on or off*/
	private static boolean on = true;
	/** Switch date and time logging on or off*/
	private static boolean time = true;
	/** Switch file logging on or off*/
	private static boolean file = true;

	/** Log file with actual time in file name*/
	private static Path path;
	
	/** 
	 * static constructor for path with actual date and time in filename 
	 * Format:  c:\temp\chess dd.mm.yyyy xxh yymin zzs.txt
	 * Example: c:\temp\chess 17.01.2013 07h 43min 21s.txt
	 */
	static{ 
		SimpleDateFormat dates = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat hours = new SimpleDateFormat("hh");
		SimpleDateFormat minutes = new SimpleDateFormat("mm");
		SimpleDateFormat seconds = new SimpleDateFormat("ss");
		StringBuffer buffer = new StringBuffer("chess "); 

		Date date = new Date();
		buffer.append(dates.format(date));
		buffer.append(" ");
		buffer.append(hours.format(date));
		buffer.append("h ");
		buffer.append(minutes.format(date));
		buffer.append("min ");
		buffer.append(seconds.format(date));
		buffer.append("s.txt");
		path = Paths.get(buffer.toString());
	}
	
	/**
	 * Logs a message to the console output stream
	 * @param message
	 */
	public static void log(Object message) {
		if (!on) return;
		StringBuffer logging = new StringBuffer();
		if (time) {
			Date date = new Date();
			SimpleDateFormat stringFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss ");
			logging.append(stringFormat.format(date));
			logging.append(message.toString());
			logging.append("\r\n"); // new line
			System.out.print(logging.toString());			
		} 
		if (file) {
			// SCHREIBEN
			try {
				if (!Files.exists(path))  {
					Files.createFile(path);
				}
				Files.write(path, logging.toString().getBytes(), 
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 	

	/**
	 * Getter for on
	 * @return on
	 */
	public static boolean isOn() {
		return on;
	}

	/**
	 * Setter for on
	 * @param on
	 */
	public static void setOn(boolean on) {
		Logger.on = on;
	}

	/**
	 * Getter for time switch
	 * @return time
	 */
	public static boolean isTime() {
		return time;
	}

	/**
	 * Setter for time switch
	 * @param time
	 */
	public static void setTime(boolean time) {
		Logger.time = time;
	}

	/**
	 * Getter for file
	 * @return time
	 */
	public static boolean isFile() {
		return file;
	}

	/**
	 * Setter for file switch
	 * @param time
	 */
	public static void setFile(boolean file) {
		Logger.file = file;
	}	
}


