package CasinoRoyaleSim_CS251.Utilities;

import CasinoRoyaleSim_CS251.Exceptions.CardException;
import CasinoRoyaleSim_CS251.PokerDeck.Card;

public class CardAnalysis {

    public CardAnalysis(){}
    public boolean CheckPair (Card[] cards) throws CardException {
        Card firstCard = cards[0];
        int freq = 1;
        for(int i = 1; i < cards.length; i++){
            if(firstCard.getRankValue() == cards[i].getRankValue()){
                freq+=1;
            }
        }
        if(freq == 2){
            return true;
        } else if(freq > 2){
            throw new CardException("More than one pair");
        } else return false;
    }
}
