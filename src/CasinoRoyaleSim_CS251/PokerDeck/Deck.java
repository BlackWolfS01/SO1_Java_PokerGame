package CasinoRoyaleSim_CS251.PokerDeck;

import java.util.Stack;
import java.util.Collections;

public class Deck {
    private Stack<Card> DeckOfCards = new Stack<Card>();
    private final int deckSize = 52;

    public Deck() {
        for(int i = 0; i < deckSize; i++) {
            Card card = Card.convertCard (i);
            DeckOfCards.add(card);
        }
    }

    public void Shuffle() throws IllegalArgumentException{
        if(DeckOfCards.size() != deckSize)
            throw new IllegalArgumentException();
        int counter = 0;
        while(counter <= deckSize) {
            int x = (int)(Math.random() * deckSize),
                      y = (int)(Math.random() * deckSize);
            Collections.swap(DeckOfCards, x, y);
            counter++;
        }
    }
    //Draws a single card from the deck
    public Card Draw() {
        return DeckOfCards.pop();
    }
    //Returns a single card to the deck
    public void ReturnCard(Card card) {
        DeckOfCards.push(card);
    }
    //Returns an array of cards to the deck
    public void ReturnCards(Card[] cards) {
         for(Card card : cards)
              DeckOfCards.push(card);
    }

    public void displayDeck() {
        for(Card card : DeckOfCards)
            System.out.println(" " + card.toString());
        System.out.println();
    }

    public int DeckSize() {
        return DeckOfCards.size();
    }
}
