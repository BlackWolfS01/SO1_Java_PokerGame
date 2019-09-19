package CasinoRoyaleSim_CS251.ConsoleTests;

import CasinoRoyaleSim_CS251.Player;
import CasinoRoyaleSim_CS251.PokerChip.Chip;
import CasinoRoyaleSim_CS251.PokerChip.Converter;
import CasinoRoyaleSim_CS251.PokerDeck.Card;
import CasinoRoyaleSim_CS251.PokerDeck.Deck;

import java.util.*;

public class PlayerActionConsoleTest {
    public static void main(String[] args) {
        Deck _NewDeck = new Deck();
        _NewDeck.Shuffle();
        /* Design Notes - Thoughts
        First round of a hand. All the players are dealt two cards. Odds can be either good or bad. A hand
        is never usually decided that early on. Yet, some players decides its not worth it, and fold. Usually, if their hands
        were bad, or they were low on chips. That's where confidence comes in.
        How would I look at it?
        The cards. If there's no obvious advantage, like a pair, then you look at the ranks, high ranks are more desirable,
        though lower ones can have some use. There's also the suit to consider. Rank and Suit both play a factor in the gameplay.

        This needs to go beyond a simple equation. A simple check.
         */

        LinkedList<Card> CardsInHand = new LinkedList<>();
        List<Card> CommunityCards = new LinkedList<>();
        _NewDeck.Shuffle();
        _NewDeck.Shuffle();

        Stack<Card> _BurnCards = new Stack<>();
        for(int i = 0; i < 5; i++){
            _BurnCards.push(_NewDeck.Draw());
            CommunityCards.add(_NewDeck.Draw());
        }

        //Card - Fave, Straight, Rank Value, Suit Value
        Player Player_JohnKnight = new Player("John Knight", 15000000);
        //Player_JohnKnight.EvaluateCards(CommunityCards);
        List<Card> TestCards = new LinkedList<>();
        TestCards.add(new Card("Nine","Diamond", 9, 2));
        TestCards.add(new Card("Ten","Diamond", 10, 2));
        TestCards.add(new Card("Jack","Diamond", 11, 2));
        TestCards.add(new Card("Queen","Diamonds", 12, 2));
        TestCards.add(new Card("King","Diamond", 13, 2));
        Player_JohnKnight.EvaluateCards(TestCards);
    }

    public static Stack<Card> AGoodHand = new Stack<>();
    public static Stack<Card> ABadHand = new Stack<>();

    private static void FistEvaluation(LinkedList<Card> holeCards) {
        String[] priorities = {"Ace", "Ten", "Jack", "Queen", "King"};

        for(Card card : holeCards) {
            

        }
    }
}
