import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public void createFullDeck() {
        // generate cards
            for(Suits currentSuit: Suits.values()){
                for(Values value: Values.values()){
                    Card newCard = new Card(currentSuit, value);
                    addCard(newCard);
                }
            }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public Card getCard(int i){
        return this.deck.get(i);
    }

    public void removeCard(int i){
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        addCard(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {
        for(Card cards: deck){
            moveTo.addCard(cards);
        }
        deck.clear();
    }
    public String toString(){
        String deckList = "";
        for (Card cards: deck){
            deckList+= cards + "\n";
        }
        return deckList;
    }
    public int calculateHand(){
        int handValue = 0;
        for (int i = 0; i < deckSize(); i++){
            switch (getCard(i).getValue()) {
                case TWO : handValue += 2;
                    break;
                case THREE : handValue += 3;
                    break;
                case FOUR : handValue += 4;
                    break;
                case FIVE : handValue += 5;
                    break;
                case SIX : handValue += 6;
                    break;
                case SEVEN : handValue += 7;
                    break;
                case EIGHT : handValue += 8;
                    break;
                case NINE :  handValue += 9;
                    break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING: handValue += 10;
                    break;
                case ACE: if(handValue + 11 > 21){
                    handValue += 1;
                } else {
                    handValue += 11;
                }
                    break;
                default: handValue += 0;
                    break;

            }
        }
        return handValue;
    }

}

//            Can create a deck randomly using this
//        while(deckSize() < 52){
//            Card newCard = new Card();
//            addCard(newCard);
//            int uniqueCard = 0;
//            for (Card cards: deck){
//                if(cards.getSuit() == newCard.getSuit() && cards.getValue() == newCard.getValue()){
//                    uniqueCard++;
//                }
//            }
//            if (uniqueCard > 1){
//                removeCard(deckSize()-1);
//            }
//        }
