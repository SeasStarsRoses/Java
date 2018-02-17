package org.training.java.chess.chess.view.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

import org.training.java.chess.model.logging.Logger;


/**
 * Class Languages for internationalization 
 * @author Peter Heide, pheide@t-online.de
 * @version 1
 * @since 17.02.2018
 */

/* The following copyright information is for internationalization, 
 * the source code was taken out of the oracle java tutorials 
 * http://docs.oracle.com/javase/tutorial/i18n/index.html 
 */

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


public class Languages {

	/** messages for internationalization*/
    private static ResourceBundle messages;
    
    
    /** Internationalization for About Dialog title */
    public static final String ABOUT = "ABOUT";
    /** Internationalization for Artificial Intelligence */
    public static final String ARTIFICIAL_INTELLIGENCE = "ARTIFICIAL_INTELLIGENCE";
    /** Internationalization for Author */
    public static final String AUTHOR = "AUTHOR";
    /** Internationalization for Back button*/
    public static final String BACK = "BACK";
    /** Internationalization for Black */
    public static final String BLACK = "BLACK";
    /** Internationalization for OK Button*/
    public static final String CANCEL = "CANCEL";
    /** Internationalization for Change Button*/
    public static final String CHANGE = "CHANGE";
    /** Internationalization for chess peaces in about dialog */
    public static final String CHESS_PIECES = "CHESS_PIECES";
    /** Internationalization for pawn exchange dialog title */
    public static final String CHOOSE_ONE = "CHOOSE_ONE";   
    /** Internationalization for Player type in Welcome Dialog */
    public static final String CHOOSE_PLAYER_TYPE = "CHOOSE_PLAYER_TYPE";
    /** Internationalization for Computer in Welcome Dialog */  
    public static final String COMPUTER = "COMPUTER";
    /** Internationalization for HUMAN in Welcome Dialog */
    public static final String HUMAN = "HUMAN";    
    /** Internationalization for computer strength SIMPLE, INTERMEDIATE, or STRONG */
    public static final String INTERMEDIATE = "INTERMEDIATE";
    /** Internationalization */
    public static final String TRAINING = "TRAINING";
    /** Internationalization for internet link chess pieces */
    public static final String LINK_CHESS_PIECES = "LINK_CHESS_PIECES";
    /** Internationalization for New Game button */
    public static final String NEW_GAME = "NEW_GAME";
    /** Internationalization for OK Button */
    public static final String OK = "OK";
    /** Internationalization for Reproduction in About Dialog */
    public static final String REPRODUCTION = "REPRODUCTION";
    /** Internationalization for computer strength SIMPLE, INTERMEDIATE, or STRONG */
    public static final String SIMPLE = "SIMPLE";
    /** Internationalization for computer strength SIMPLE, INTERMEDIATE, or STRONG */
    public static final String STRONG = "STRONG";
    /** Internationalization for welcome text in Welcome Dialog */
    public static final String WELCOME = "WELCOME";
    /** Internationalization for White */
    public static final String WHITE = "WHITE";
    
	/**
	 * Constructor
	 * @param args used for the languages english, german, or french: Game en US, Game de DE, or Game fr FR
	 */
	public static void initialize(String[] args) {
		
		// Internationalization
	    String language;
	    String country;

	    if (args.length != 2) {
	        language = new String("en");
	        country = new String("US");
	        Logger.log("Usage for the languages english, german, or french:\nGame en US\nGame de DE\nGame fr FR");
	    } else {
	        language = new String(args[0]);
	        country = new String(args[1]);
	    }

	    Locale currentLocale;
	    currentLocale = new Locale(language, country);

	    messages =
	      ResourceBundle.getBundle("MessagesBundle", currentLocale);		
	}
	
	/**
	 * Get internalization message string
	 * @param key
	 * @return message string
	 */
	public static String getValue(String key) {
		return messages.getString(key);
	}
}
