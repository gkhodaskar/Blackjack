# Blackjack
A simple command-line blackjack game played between Dealer and one Player. At the beginning, a Dealer shuffles the deck of cards and deals two cards to the player (both face up) and two cards to themself (one face up, one hidden). The total hand value for each player must stay below or equal to 21 at all times, otherwise they lose. A player has a Blackjack if their hand value is equal to 21.

Dealer can see both their cards, if they have a Blackjack(score >= 21) they win by default even before the game begins.

The player wins when they get a score (hand value) higher than the dealer but less than or equal to 21. 

The dealer wins when there is a tie (for simplicity within the scope of this project) or their hand value is greater than player, but less than equal to 21. The player/dealer is busted if their hand value, at any point, is greater than 21. 

# Rules:
1.  If both Player and Dealer have Blackjack (Hand value == 21), Dealer wins on a tie
2.  If Dealer has Blackjack (Hand value == 21), Dealer wins
3.  If Player has Blackjack (Hand value == 21), Player wins
4.  If neither has Blackjack, play continues as follows:
    -   Player chooses to Hit(H) or Stand(S):
        -  Hit : A new card is dealt to the player. If, at any point, player's hand value > 21, Player busted.
        -  Stand: Player halts at current hand value, game moves to Dealer.
    -   Dealer must draw until their hand value < 17. If, at any point, dealer's hand value > 21, Dealer busted.
    -   If neither player was busted, compare Dealer and Player hands:
        -  Tie: Dealer wins on a tie.
        -  Dealer has higher hand: Dealer Wins.
        -  Player has higher hand: Player Wins.


# Compile and Run instructions on Terminal:
Note that running this code requires JVM installed in your machine
1. Download src/ folder and open a terminal at this location
2. Compile .java files:     javac com/gkprojects/*.java
3. Run PlayBlackjack:       java com.gkprojects.PlayBlackjack
