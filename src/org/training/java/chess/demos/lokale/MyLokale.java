package org.training.java.chess.demos.lokale;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class MyLokale {
	// Language Codes: en (Englisch), no (Norwegisch), fr (Französisch)
	// Country Codes: US (Unites States), GB (Great Britain), NO (Norway)
	// Language Constants: 
	//   Locale.ENGLISH (new Locale("en", ""))
	//   Locale.FRENCH (new Locale("fr", ""))
	//   Locale.GERMAN (new Locale("de", ""))
	// Country Constants
	//   Locale.US (new Locale("en", "US"))
	//   Locale.UK (new Locale("en", "GB"))
	//   Locale.CANADA_FRENCH (new Locale("fr", "CA"))

	public static void main(String[] args) {
		Locale locDF = Locale.getDefault();
		Locale locFR = new Locale("fr", "FR");
		Locale locUS = Locale.US;
		// Country Names
		System.out.println("In " + locDF.getDisplayCountry());
		System.out.println("In " + locFR.getDisplayCountry());
		System.out.println("In " + locFR.getDisplayCountry(locFR));
		System.out.println("In " + locUS.getDisplayCountry(locFR));
		System.out.println("In " + locFR.getDisplayCountry(locUS));
		// Language names
		System.out.println("In " + locFR.getDisplayLanguage());
		System.out.println("In " + locFR.getDisplayLanguage(locUS));
		System.out.println("In " + locDF.getDisplayLanguage(locUS));
		System.out.println("In " + locUS.getDisplayLanguage(locDF));
		
		Date today;
		String dateOut;
		DateFormat dateFormatter;

		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locDF);
		today = new Date();
		dateOut = dateFormatter.format(today);
		System.out.println(dateOut + " " + locDF.toString());

		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locUS);
		today = new Date();
		dateOut = dateFormatter.format(today);
		System.out.println(dateOut + " " + locUS.toString());
		
		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locFR);
		today = new Date();
		dateOut = dateFormatter.format(today);
		System.out.println(dateOut + " " + locFR.toString());
		
		// Zahl formatieren
		Double num = 12345.6789;
		// Deutsch (default)
		NumberFormat nf = NumberFormat.getNumberInstance(locDF);
		String formattedNumber = nf.format(num);
		System.out.println(formattedNumber);
		// Amerikanisch
		nf = NumberFormat.getNumberInstance(locUS);
		formattedNumber = nf.format(num);
		System.out.println(formattedNumber);
	}
}
