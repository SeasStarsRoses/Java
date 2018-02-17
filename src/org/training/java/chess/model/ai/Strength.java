package org.training.java.chess.model.ai;
/**
 * AI Strength SIMPLE, INTERMEDIATE, or STRONG
 * @author Peter Heide, pheide@t-online.de
 *
 */
public enum Strength { 
	/** Makes random moves **/
    SIMPLE,
	/** Hits the enemy figure with the highest value **/
    INTERMEDIATE, 
	/** Calculates several moves **/
    STRONG
}
