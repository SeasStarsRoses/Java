package org.training.java.chess.model.highscore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * High Score List manages the High Scores in a Vector List and stores them in a file
 * @author Peter Heide, pheide@t-online.de
 * @version 1 
 * @since 17.02.2018
 */
public class HighScoreList {
	/** Only one object because of Singleton Design Pattern */
	private static HighScoreList instance = null;
	/** HighScoreList is responsable for list of HighScore Objecte */ 
	private List<HighScore> highScoreList = new ArrayList<HighScore>();
	/** Sort according to RoundsComparator, WinnerComparator, LooserComparator, or DateComparator */ 
	private Comparator<HighScore> comparator = new HighScoreComparatorRounds();
	/** Relative file path */ 
    private Path path = Paths.get("c:\\temp\\HighScore.txt");
    /** Convert date to string and backwards*/
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy h:m:s");
	/** For writing HighScort into File: "01.01.2013,Winner,Looser,10" */
	private String separator = ",";
	/** Switch HighScore off during KI when Computer calculates move */
	private static boolean switchOff = false; 	
	/**
	 * Private Constructor because ofSingleton Design Pattern
	 */
	private HighScoreList() {}
	
	/**
	 * Gets access to only one instance for everyone because of singleton
	 * @return only one instance
	 */
	public static HighScoreList getInstance() {
		if (instance == null) {
			instance = new HighScoreList();			
		}
		return instance;
	}

	/**
	 * Switch HighScoreList On
	 */
	public static void switchOn() {
		switchOff = false;
	}

	/**
	 * Switch HighScoreList Off
	 */
	public static void switchOff() {
		switchOff = true;
	}

	/**
	 * Set Comparator
	 * @param comparator
	 */		
	public void setComparator(Comparator<HighScore> comparator)
	{
		if (comparator != null)
		{
			this.comparator = comparator;
		}
	}
	
	/**
	 * Add a new High Score to the existing list
	 * @param highScore to add 
	 */	
	public void addHighScore(HighScore highScore)
	{
		if (switchOff) { return; }
		readFromDisk();
		highScoreList.add(highScore);
		Collections.sort(highScoreList, comparator);
		while (highScoreList.size() > 10) // If list bigger that 10 remove an element
		{
			highScoreList.remove(10);
		}
		writeToDisk();
	}
	
	/**
	 * Get a String representation of the High Scores List 
	 * @return String representation of all High Scores in the List
	 */	
	@Override
	public String toString()
	{
		StringBuffer res = new StringBuffer("HighScoreList\n");
		for (HighScore highScore : highScoreList)
		{
			String sDate = simpleDateFormat.format(highScore.getDate());
			String winner = highScore.getWinnersName();
			String looser = highScore.getLoosersName();
			String rounds = "" +highScore.getRounds();
			res.append("On " + sDate + ", " + winner + " won against " + looser + " in " + rounds + " rounds\n");
		}
		return res.toString();
	}
	
	/**
	 * Read the high scores from a File 
	 */	
	private void readFromDisk()
	{
		highScoreList.clear();
		if (!Files.exists(path)) { return; } 
		try {
 
			// LESEN aller Zeile auf einmal in ein String Array
			for (String input : Files.readAllLines(path, StandardCharsets.UTF_8)) {
				String [] splitter = input.split(separator);
				Date date = null;
				try {
					date = simpleDateFormat.parse(splitter[0]);
				} catch (ParseException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				String winner = splitter[1];
				String looser = splitter[2];
				int rounds = Integer.parseInt(splitter[3]);
				HighScore highScore = new HighScore(date, winner, looser, rounds);
				highScoreList.add(highScore);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write the high scores to a File 
	 */	
	private void writeToDisk()
	{
		try {
			StringBuffer buffer = new StringBuffer();
			boolean firstLine = true;
			for (HighScore highScore : highScoreList)
			{
				String date = simpleDateFormat.format(highScore.getDate());
				String winner = highScore.getWinnersName();
				String looser = highScore.getLoosersName();
				int rounds = highScore.getRounds();
				String separator = ",";
				if (!firstLine) {
					buffer.append("\n");
				}
				buffer.append(date + separator + winner + separator + looser + separator + rounds);
				firstLine = false;
			}
			Files.write(path, buffer.toString().getBytes());						
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}	
}