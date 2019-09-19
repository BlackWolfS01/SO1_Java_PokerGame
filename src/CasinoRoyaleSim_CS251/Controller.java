package CasinoRoyaleSim_CS251;

import CasinoRoyaleSim_CS251.NeuralNetworkSource.NeuralNetwork;

import java.util.*;

/*
 */
public class Controller {

    public static void main(String[] args) {
        /*Deck deck = new Deck();
        int[] HiddenLayers = {100};
        NeuralNetwork network = new NeuralNetwork(3*(int)(Math.pow(5,2)),HiddenLayers,5);
        int square = (int)Math.pow(5,2);
        double[] input = new double[square * 3];
        for(int i = 0; i < input.length; i++) {
            input[i] =  Math.random();
        }
        double[][] output = network.FullEvaluate(input);
        double[] expected = new double[input.length];
        for(int i = 0; i < expected.length; i++){
            expected[i] = Math.random();
        }
        int c = 0;
        while(c < 10){
            network.BackProp(output, expected);
            c++;;
        }

        for(int i = 0; i < output.length; i++){
            for(int j = 0; j < output[i].length; j++){
                System.out.println(output[i][j]);
            }
        } */
        /*deck.Shuffle();
        deck.Shuffle();
        Stack<Card> PriorityCards = new Stack<>();
        LinkedList<Card> CardsInHand = new LinkedList<>();
        String[] Priority_Values = {"Ace","Jack","Queen","King"};
        for(int i=0;i<2;i++)
            CardsInHand.add(deck.Draw());

        int square = (int)Math.pow(5,2);
        double[] inputs = new double[square * 3];


        System.out.println();
        for(Card c : PriorityCards)
            System.out.println(c.cardName());
        System.out.println();
        for(Card c2 : CardsInHand)
            System.out.println(c2.cardName());*/
        /*LinkedList<Card> Player_Hand = new LinkedList<>();
        Stack<Card> Priority_Cards = new Stack<>();
        for(int i=0;i<2;i++) {
            Player_Hand.add(deck.Draw());
        System.out.println("Player Hand");
        for(Card card : Player_Hand)
            System.out.print(card.cardName() + " ");
        System.out.println();

        if(Player_Hand.size() != 2)
            return;
        Card CardOne = Player_Hand.get(0);
        Card CardTwo = Player_Hand.get(1);

        for(Card card : Player_Hand) {
            if(card.getRankValue() >= 5)
                Priority_Cards.add(card);
        }/*


        /*
        for(int i = 0; i < Player_Hand.size();i++) {
            for(int j = Player_Hand.size(); j >= 0;j--) {
                if(Player_Hand.get(i).getFace().equals(Player_Hand.get(j).getFace())){
                    Priority_Cards.addAll(Player_Hand);
                } else {
                    for(String value : Priority_Values) {
                        if(Player_Hand.get(i).getFace().equals(value)) {
                            if(!Priority_Cards.contains(Player_Hand.get(i)))
                                Priority_Cards.add()
                        }
                    }
                }
            }
        }*/
        /*
        Card[] Cards_One = {new Card("Diamonds","Six",6,2),
                            new Card("Diamonds","Seven",7,2),
                            new Card("Diamonds","Eight",8,2),
                            new Card("Diamonds","Five",5,2),
                            new Card("Diamonds","Four",4,2)};

        int lowestValue = 0;
        int highestValue = 0;
        for(int i = 0;i<Cards_One.length;i++) {
            lowestValue = Cards_One[i].getRankValue();
            for(int j = Cards_One.length-1;j>=0;j--) {
                if(Cards_One[j].getRankValue() < Cards_One[i].getRankValue()) {
                    if (Cards_One[j].getRankValue()<lowestValue) {
                        lowestValue = Cards_One[j].getRankValue();
                    }
                } else {
                    if(Cards_One[i].getRankValue() < lowestValue) {
                        lowestValue = Cards_One[i].getRankValue();
                    }
                }
            }
        }

        for(int i = 0; i<Cards_One.length;i++) {
            for(int j = Cards_One.length - 1; j >=0;j--) {
                if(Cards_One[j].getRankValue() > Cards_One[i].getRankValue()) {
                    if(Cards_One[j].getRankValue() > highestValue) {
                        highestValue = Cards_One[j].getRankValue();
                    }
                } else {
                    if(Cards_One[i].getRankValue() > highestValue) {
                        highestValue = Cards_One[i].getRankValue();
                    }
                }
            }
        }

        System.out.println(lowestValue + " " + highestValue);

        int frequency = 0;
        for (Card value : Cards_One) {
            if(lowestValue <= value.getRankValue() &&
                    value.getRankValue() <= highestValue)
                frequency+=1;
        }
        System.out.println(frequency);

        if(frequency >= 4) {
            String suit = Cards_One[0].getSuit();
            int freq =1;
            for(int i=1;i<Cards_One.length;i++){
                if(Cards_One[i].getSuit().equals(suit))
                    freq+=1;
            }

            if(freq >= 4)
                System.out.println("Straight Flush");
            else
                System.out.println("Straight");
        }*/


        /*
        Card[] Cards = {new Card("Spades","Ace",1,2),
                        new Card("Spades","Ten",10,2),
                        new Card("Spades","Jack",11,2),
                        new Card("Spades","Queen",12,2),
                        new Card("Spades","King",13,2)};
        String[] Faces = {"Ace","Ten","Jack","Queen","King"};

        Stack<Card> Hand = new Stack<>();
        int OneCount = 0;
        for(int i = 0; i < Cards.length; i++) {
            for(int j = Faces.length - 1; j >= 0; j--) {
                if(Cards[i].getFace().equals(Faces[j]))
                    OneCount++;
            }
        }
        if(OneCount == 5) {
            int suit = Cards[0].getSuitValue();
            int freq=0;
            for(int i=1; i<Cards.length;i++) {
                if(suit == Cards[i].getSuitValue())
                    freq++;
            }

            if(freq >= 4) {
                System.out.println("Royal Flush");
            }
        }*/
        /*
        * */
    }



}
