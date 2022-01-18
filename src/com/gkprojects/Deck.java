package com.gkprojects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck.java represents an object of Deck of Playing Cards.
 * A deck of cards has 52 cards of four suits (Spades ,Hearts , Diamonds, Clubs) with 13 ranks
 * in each suit (Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King)
 * valued 1 to 13.
 */

class Deck{
	enum Suites{
		Spades ,Hearts , Diamonds, Clubs
	}

	enum Ranks {
		Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
	}

	final List<Card> deck; // List to store the cards in deck

	/**
	 * Constructor: Creates and initializes a Deck of Cards.
	 */
	public Deck(){
		deck = new ArrayList<>();
		// Create Card objects for all ranks and values in each suit
		// and add to deck
		for(Suites suit : Suites.values()){
			int value = 1;
			for(Ranks rank : Ranks.values()){
				deck.add(new Card(suit.name(), rank.name(), value));
				value++;
			}
		}
	}

	/**
	 * Shuffles deck
	 */
	public void shuffle(){
		if(deck.size() > 0)
			Collections.shuffle(deck);
	}

	/**
	 * Getter to return the size of deck
	 * @return cards remaining in the deck
	 */
	public int getDeckSize(){
		return this.deck.size();
	}

	/**
	 * Deal and return a Card from the Deck
	 * @return top Card from deck
	 */
	public Card dealCard(){
		return deck.remove(0);
	}

}
