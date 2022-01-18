package com.gkprojects;

import java.util.Scanner;

/**
 * Simple version of Blackjack between Dealer(Computer) and Player(User)
 *
 * Rules:
 * 1. If both Player and Dealer have Blackjack (Hand value == 21), Dealer wins on a tie
 * 2. If Dealer has Blackjack (Hand value == 21), Dealer wins
 * 3. If Player has Blackjack (Hand value == 21), Player wins
 * 4. If neither has Blackjack, play continues as follows:
 *	  (1) Player chooses to Hit(H) or Stand(S):
 *     	  Hit : A new card is dealt to the player. If, at any point, player's hand value > 21, Player busted.
 * 		  Stand: Player halts at current hand value, game moves to Dealer.
 *	  (2) Dealer must draw until their hand value < 17. If, at any point, dealer's hand value > 21, Dealer busted.
 *    (3) If neither player was busted, compare Dealer and Player hands:
 *		  Tie: Dealer wins on a tie.
 *		  Dealer has higher hand: Dealer Wins.
 *		  Player has higher hand: Player Wins.
 */

class PlayBlackjack{

	static final Scanner sc = new Scanner(System.in);

	/**
	 * Main method to drive multiple Blackjack plays
	 * @param args
	 * Future work: Add logic to introduce and track Bets($$$)
	 */
	public static void main(String[] args){

		boolean playAgain = true;
		// Clear Console
		clear();

		// Welcome message
		printHeader();
		System.out.println("Welcome to BlackJack! Setting up the deck...\n");

		do{
//			System.out.println(new String(new char[50]).replace('\0', '*'));

			// Setup and Play Blackjack
			playBlackjack();

			// Ask player to play again
			System.out.print("Do you want to play again? (Y/N): ");
			String s = sc.next().trim();
			char ch = Character.toUpperCase(s.charAt(0));
			System.out.println();
			// Player chooses to play again
			if(ch == 'Y'){
				// Clear Console for New Game
				clear();
				printHeader();
				System.out.println("Great! Setting up the deck...\n");
			} else { // Invalid input OR Player chooses to quit
				System.out.println("\n\nQuitting...");
				playAgain = false;
			}

		} while(playAgain);
		sc.close();

		printHeader();
		System.out.println("Thank you for playing!");

	} // end main()

	/**
	 * Print a line formed by 75 asterisks(*)
	 */
	private static void printHeader(){
		System.out.println(new String(new char[75]).replace('\0', '*'));
	}
	/**
	 * Clear the console using ANSI escape sequence and reset cursor to top of the screen
	 * Reference: https://www.javatpoint.com/how-to-clear-screen-in-java
	 */
	private static void clear(){
		System.out.print("\033[H\033[2J"); // ANSI escape code to clear console
		System.out.flush(); // reset cursor position
	}

	/**
	 *  Core Blackjack setup and play
	 */
	private static void playBlackjack(){

		// Create a new Deck and shuffle
		Deck deck = new Deck();
		deck.shuffle();

		// Create a BlackjackHand each for Player and Dealer
		BlackjackHand player = new BlackjackHand("Player");
		BlackjackHand dealer = new BlackjackHand("Dealer");

		// Deal two cards each to Player and Dealer
		player.addCardToHand(deck.dealCard());
		player.addCardToHand(deck.dealCard());
		dealer.addCardToHand(deck.dealCard());
		dealer.addCardToHand(deck.dealCard());

		// Check if either player has Blackjack

		// 1. Dealer has blackjack, dealer wins.
		// Dealer wins in a tie, hence check dealer cards first
		if(dealer.getHandValue() == 21){
			printHeader();
			// Show all players' cards
			showPlayerCards(dealer, player);
			System.out.println("Dealer has Blackjack!\n~~DEALER WINS!~~");
			return;
		}

		// 2. Player has blackjack, player wins.
		if (player.getHandValue() == 21){
			printHeader();
			// Show all players' cards
			showPlayerCards(dealer, player);

			System.out.println("Player has Blackjack!\n~~PLAYER WINS!~~");
			return;
		}

		// 3. No player has Blackjack, continue game
		while(true){
			printHeader();

			// Show Dealer and Player Cards
			// ** Dealer only shows one card at the beginning **

			System.out.println("<-- Dealer Cards -->");
			System.out.println(dealer.cardsInHand.get(0));
			System.out.println("--------------------");
			System.out.println("Dealer's value: " + dealer.cardsInHand.get(0).value + "\n");

			// ** Player shows all cards **
			player.printCards();

			// (1) Ask player to Hit or Stand
			System.out.print("What would you like to do, Hit (H) or Stand (S)? : ");
			char choice;
			do {
				choice = sc.next().trim().charAt(0);
				choice = Character.toUpperCase(choice);
				if (choice != 'H' && choice != 'S')
					System.out.print("Please respond H or S: ");
			} while (choice != 'H' && choice != 'S');

			System.out.println();

			if(choice == 'S'){
				// Player chooses to STAND, break while loop and pass game to the Dealer
				break;
			} else { // Player chooses to HIT

				// Clear Console
				clear();
				printHeader();

				// Deal a new card to the Player
				Card newCard = deck.dealCard();
				System.out.println("Player Hits: " + newCard + "\n");
				player.addCardToHand(newCard);

				// if new hand value is higher than 21, Player busted
				if(player.getHandValue() > 21){
					// Show all players' cards
					printHeader();
					showPlayerCards(dealer, player);

					System.out.format("Player busted with total value %d.\n~~DEALER WINS!~~\n\n", player.getHandValue());
					return;
				}

			}
		}

		// Player chooses to STAND, Dealers turn.
		// Clear Console
		clear();
		printHeader();

		// Dealer shows all cards
		System.out.println("Player chose to Stand. Dealer shows all cards...");
		printHeader();

		if(dealer.getHandValue() < 17){
			dealer.printCards();
			// (2) Dealer hits until their hand value < 17
			System.out.println("Dealer's hand value is 17 or lower; dealer must hit...\n");
			while (dealer.getHandValue() < 17) {
				// Deal a new card to the Dealer
				Card newCard = deck.dealCard();
				printHeader();
				System.out.println("Dealer Hits: " + newCard + "\n");
				dealer.addCardToHand(newCard);

				// if new hand value is higher than 21, Dealer busted
				if (dealer.getHandValue() > 21) {
					// Show all players' cards
					printHeader();
					showPlayerCards(dealer, player);

					System.out.format("Dealer busted with total value %d.\n~~PLAYER WINS!~~\n\n", dealer.getHandValue());
					return;
				}
			}
		}

		// (3) If neither player was busted, the winner is decided by comparing their final hand value
		int dealerTotal = dealer.getHandValue();
		int playerTotal = player.getHandValue();

		// Clear Console
		clear();
		printHeader();
		System.out.println("Dealer's hand value is 17 or higher, no more hits needed...\n");
		printHeader();

		// Show all players' cards
		showPlayerCards(dealer, player);

		if(dealerTotal == playerTotal){ // TIE
			System.out.println("It's a tie...\n~~DEALER WINS!~~\n\n");
		} else if( playerTotal > dealerTotal){ // Player has higher hand
			System.out.println("~~PLAYER WINS!~~\n\n");
		} else { // Dealer has higher hand
			System.out.println("~~DEALER WINS!~~\n\n");
		}

	} // end playBlackJack()

	/**
	 * Print all cards for all players in arguments
	 */
	private static void showPlayerCards(BlackjackHand dealer, BlackjackHand player) {
		// ** Dealer shows all cards **
		dealer.printCards();

		// ** Player shows all cards **
		player.printCards();
	} // end showPlayerCards()



}
