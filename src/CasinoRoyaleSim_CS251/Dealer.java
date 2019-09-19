package CasinoRoyaleSim_CS251;
import CasinoRoyaleSim_CS251.PokerDeck.Card;
import CasinoRoyaleSim_CS251.PokerDeck.Deck;

import java.util.Stack;

public class Dealer {
     private String Dealer_FirstName;
     private String Dealer_LastName;

     private Deck _Deck;
     public Dealer(String firstName, String lastName) {
          this.Dealer_FirstName = firstName;
          this.Dealer_LastName = lastName;
     }

     public void ReceiveDeck(Deck deck){
          this._Deck = deck;
     }

     public void ShuffleDeck() {
          int c = 0;
          do{this._Deck.Shuffle();
               c++;
          } while ( c < 5);
     }

     public void ReceiveBurnCards(Stack<Card> Burn_Cards){
          if(Burn_Cards.size() == 0)
               throw new IllegalArgumentException("Stack size is zero and out of bounds");

          for(int i = 0; i < Burn_Cards.size(); i++) {
               this.receiveCard(Burn_Cards.pop());
          }
     }

     public Card drawOneCard(){
          Card card  = _Deck.Draw();
          card.FlipFaceUp();;
          return card;
     }

     public Card drawCard() {
          return _Deck.Draw();
     }
     public void receiveCard(Card card) {
          System.out.println(card.cardName());
          this._Deck.ReturnCard (card);
     }
     public Deck getDeck() { return this._Deck; }
     public String getDealer_FirstName() { return this.Dealer_FirstName; }
     public String getDealer_LastName() { return this.Dealer_LastName; }
     public String getDealer_FullName() { return this.Dealer_FirstName  + " " + this.Dealer_LastName; }
}