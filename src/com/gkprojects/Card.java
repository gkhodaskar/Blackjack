package com.gkprojects;

/**
 * Card.java represent an object for a Card in a deck of playing cards.
 * Stores suit, rank and numeric value for that rank.
 */
class Card{

	final String suit;
	final String rank;
	final int value;

	/**
	 * Constructor
	 * @param suit Spades/Hearts/Diamonds/Clubs
	 * @param rank Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
	 * @param value 1-13
	 */
	public Card(String suit, String rank, int value){
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

	/**
	 * Getter to return the suit
	 * @return Suit of this Card
	 */
	public String getSuit(){
		return this.suit;
	}

	/**
	 * Getter to return the rank
	 * @return Rank of this Card
	 */
	public String getRank(){
		return this.rank;
	}

	// Return value of this Card

	/**
	 * Getter to return the Value corresponding to the Rank of this Card
	 * @return value
	 */
	public int getValue(){
		return this.value;
	}

	/**
	 * Convert this card object to formatted string value
	 * @return card information formatted as <Rank> of <Suit>
	 */
	@Override
	public String toString() {
		return getRank() + " of " + getSuit();
	}
}
