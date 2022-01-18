package com.gkprojects;

import java.util.ArrayList;
import java.util.List;

/**
 * BlackJackHand.java represents a player's hand in Blackjack game.
 * In Blackjack, all face cards (Jack, Queen & King) have a value of 10
 * and an Ace can be valued at 1 OR 11 as per user choice.
 * When a user Hits, the program logic will return the best choice based
 * on addition, whichever among 1 or 11, yields the largest value <= 21.
 */


class BlackjackHand{

	final List<Card> cardsInHand;
	int handValue;
	boolean hasAnAce;
	final String name;

	/**
	 * Constructor
	 * @param name PlayerName (Player/Dealer)
	 */
	public BlackjackHand(String name){
		this.cardsInHand = new ArrayList<>();
		this.handValue = 0;
		this.hasAnAce = false;
		this.name = name;
	}

	/**
	 * Getter
	 * @return value of current hand
	 */
	public int getHandValue(){
		// If this player has at least one Ace card,
		// determine if it can be considered as value ONE or ELEVEN
		// based on whichever yields a result <= 21
		if(this.hasAnAce && this.handValue + 10 <= 21)
			return handValue + 10;
		return handValue;
	}

	/**
	 * Getter
	 * @return player name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Add card to list of cardsInHand for this player
	 * @param card input object of type Card
	 */
	public void addCardToHand(Card card){
		this.cardsInHand.add(card);
		// new card added, update hand value
		this.updateHandValue(card.getValue());
	}

	/**
	 * Update total hand value
	 * @param newCardValue new Card's value
	 */
	private void updateHandValue(int newCardValue){

		// All face cards (Jack, Queen, King) have a value of 10
		if(newCardValue > 10) newCardValue = 10;

		// If new card is an Ace, set hasAnAce flag to true
		if(newCardValue == 1) hasAnAce = true;

		// Add new card's value to total
		handValue += newCardValue;
	}

	/**
	 * Format and print cardsInHand for this player
	 */
	public void printCards(){
		System.out.format("<-- %s Cards -->\n", this.getName());
		for(Card c : cardsInHand){
			System.out.println(c);
		}
		System.out.println("--------------------");
		System.out.format("%s's value: " + this.getHandValue() + "\n\n", this.getName());
	}

}
