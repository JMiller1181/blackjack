import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();
        // Game loops
        int playerFunds = 50;
        int bet = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to BlackJack. Let's start you off with $50.");
        // start of game
        while(true){
            playerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);
            playerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);
            System.out.println("""
                    Place your bets:
                    Must be a multiple of 5""");
            // sets bet amount
            bet = scanner.nextInt();
            if ((bet % 5) != 0){
                System.out.println("Must bet a multiple of 5.");
                playerHand.moveAllToDeck(playingDeck);
                dealerHand.moveAllToDeck(playingDeck);
                playingDeck.shuffleDeck();
                continue;
            }
            //Check to see if player can afford bet and sets to all in if no
            if (bet > playerFunds){
                System.out.println("All in.");
                bet = playerFunds;
            }
            if (playerHand.calculateHand() == 21) {
                playerFunds += (bet * 1.5);
                System.out.println(
                        "Your hand is:\n" + playerHand + "for a total hand value of " + playerHand.calculateHand() +
                        "You win. Would you like to play another round?\n1) Yes\n2) No");
                int blackjackRestart = scanner.nextInt();
                if(blackjackRestart == 2){
                    System.out.println("OK, goodbye.");
                    break;
                } else {
                    System.out.println("OK, next round.");
                    playerHand.moveAllToDeck(playingDeck);
                    dealerHand.moveAllToDeck(playingDeck);
                    playingDeck.shuffleDeck();
                    continue;
                }

            }
            // while loop for drawing a hand
            while(true){
                System.out.println("Your hand is:\n" + playerHand
                        + "for a total hand value of " + playerHand.calculateHand() +
                        "\nDealer is showing " + dealerHand.getCard(0) +
                        "\n1) Stay\n2) Hit\n3) Double down");
                // option to stay or hit
                int moveChoice = scanner.nextInt();
                if(moveChoice == 1) {
                    break;
                } else if (moveChoice == 2) {
                    playerHand.draw(playingDeck);
                } else if (moveChoice == 3){
                    bet *= 2;
                    if (bet > playerFunds) {
                        bet = playerFunds;
                    }
                    playerHand.draw(playingDeck);
                } else {
                    continue;
                }
                // check hand value to see if bust
                if (playerHand.calculateHand() > 21) {
                    System.out.println("The current value of your hand is " + playerHand.calculateHand()
                    + ". Bust.");
                    break;
                }
            }
            // start over for bust
            if (playerHand.calculateHand() > 21) {
                playerFunds -= bet;
                System.out.println("Your total funds are: " + playerFunds + "." +
                        "\nWould you like to play again?\n" +
                        "1) Yes\n2) No");
                int bustRestart = scanner.nextInt();
                if(bustRestart == 2){
                    System.out.println("OK, goodbye.");
                    break;
                } else {
                    System.out.println("OK, next round.");
                    playerHand.moveAllToDeck(playingDeck);
                    dealerHand.moveAllToDeck(playingDeck);
                    playingDeck.shuffleDeck();
                    continue;
                }
            }
            // flip the cards
            System.out.println("Time to show the hands.\n" +
                    "Your hand is:\n" + playerHand
                    + "for a total hand value of " + playerHand.calculateHand()
                    + "\nThe dealer's hand is:\n" + dealerHand
                    + "for a total hand value of " + dealerHand.calculateHand());
            // make dealer draw
                while (dealerHand.calculateHand() <= 16){
                    System.out.println("Dealer under 17, must hit");
                    dealerHand.draw(playingDeck);
                    System.out.println("The dealer's hand is:\n" + dealerHand
                            + "for a total hand value of " + dealerHand.calculateHand());
                }
                // calculate winner and award funds
                if(playerHand.calculateHand() > dealerHand.calculateHand()) {
                    playerFunds += (bet * 2);
                } else if (dealerHand.calculateHand() > 21) {
                    System.out.println("Dealer busts.");
                    playerFunds += (bet * 2);
                } else {
                    System.out.println("Dealer wins.");
                    playerFunds -= bet;
                }
                if(playerFunds == 0){
                    System.out.println("You are out of funds, better luck next time.");
                    break;
                } else {
                    // replay
                    System.out.println("Your total funds are: " + playerFunds + "." +
                            "\nWould you like to play again?\n" +
                            "1) Yes\n2) No");
                    int replay = scanner.nextInt();
                    if(replay == 2){
                        System.out.println("OK, goodbye.");
                        break;
                    } else {
                        System.out.println("OK, next round.");
                        bet = 0;
                        playerHand.moveAllToDeck(playingDeck);
                        dealerHand.moveAllToDeck(playingDeck);
                        playingDeck.shuffleDeck();
                    }
                }
        }
    }
}