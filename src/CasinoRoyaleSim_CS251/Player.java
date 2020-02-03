package CasinoRoyaleSim_CS251;

import java.util.*;

import CasinoRoyaleSim_CS251.Action.Actions;
import CasinoRoyaleSim_CS251.PokerDeck.Card;
import CasinoRoyaleSim_CS251.PokerChip.Chip;
import CasinoRoyaleSim_CS251.PokerChip.ChipColor;
import CasinoRoyaleSim_CS251.PokerChip.ChipType;
import CasinoRoyaleSim_CS251.Disks.DealerDisk;
import CasinoRoyaleSim_CS251.Disks.SmallBlindDisk;
import CasinoRoyaleSim_CS251.Disks.BigBlindDisk;
import CasinoRoyaleSim_CS251.Disks.Disk;
import CasinoRoyaleSim_CS251.PokerDeck.FaceStatus;
import CasinoRoyaleSim_CS251.Utilities.EmotionalCalculator;
/*
Dev Notes: Needs to be rewritten with code cleanup.
Expand AI variables and implement the dfiferent actions.
Actoin Interface holds all Action types
 */
/*
Dev Notes - Details Pertaining to the Neural Netowrk
There are four main attributes that reflect a player's current emotional state. Emotions aren't on a simple
scale of 0-100. Its more like a 360 degree spectrum of posibility. While a neural network simmulates the human
mind in decision making, via bia and wieght interpretation.. Using a modification algorithm, you can simulate it
a human's emoitional state to a degree. Whether or not this will work is still a mystery..

This is a working theory:
Hypotheically, in a poker game. You can only see your cards, the cards on the table, and the other players.
Thing is, with Texas Hold'em, it isn't about playing your own cards, but about playing the guy sitting across from you.
Or, at least, that's what Holloywood wants you to think. To a computer, there are affectively three layers of information, it can see.
Visible Layer - It's own cards and the cards on the table
Low Visibile Layer - It's opponents and their actions
Hidden Layyer - What it percipeces on an instinctuale level, and what kind of outcome it can perdict

Include a basic strategy system that dictates the kinds of actions the AI will take. While its own Neural Network
is being influenced by constanctly changing emotional state and high stakes gameplay. Well, the outcome would be
unpredictable, to say the least.

This is going to take a lot of advanced situational scripting. Or, a modular design with actions and strategies
are carried out. Latter option proved to be the most feasible.

Strategy Notes: Still a work in progress
Main Objective: Flexibity

Strategies are meant to actually influence the Neural Network's decision making process. Neural networks make choices via 
Weight and Bias values. By giving prefered weight to certainn actions. It causes the network to prefer certain actions
over others. Strategies can change based on a player's overall position within the game. If they are lossing chips, rapidly.
They may be more desperate, and take greater risks. While others may play more defensively. Trying to buy time so they could
make a comeback.

Those emotional values would what strategy is used. And those values would, in turn, be influenced by cards, chips, and the other
players. That Hidden Layer of information, that one that's meant to simulate a player's intuition about whether, or not, their
opponent is bluffing. It's basically like a tell.

Walking through it step by step. Everything starts with the two hole cards
 */

public class Player implements Actions, UtilInterface, Analysis{
    private String playerName;
    private String playerFirstName;
    private String playerLastName;
    private int totalFunds;
    private List<Card> CardsInHand = new ArrayList<Card>();
    private List<Card> BestHand = new LinkedList<>();
    private List<Chip> PlayerChips = new LinkedList<>();
    private Random _Random = new Random();
    private EmotionalCalculator _EmotionCalc = new EmotionalCalculator();

    private static final int[] Default_Hidden_Layers = {100};
    private double[] Emotion_Doubt;
    private double[] Emotion_Despair;
    private double[] Emotion_Aggression;
    private double[] Emotion_Confidence;
    private float[][] weight;
    private Actions ACTIONS;
    private int handSize;
    public double handValue;
    private final int MovesAhead = 5;
    private Card rankingCard = null;
    //private Action aciton;
    //private NeuralNetwork network;
    private DealerDisk Disk_Dealer;
    private SmallBlindDisk Disk_SmallBlind;
    private BigBlindDisk Disk_BigBlindDisk;
    //private LinkedList<Disk> disks = new LinkedList<>();
    private Stack<Disk> gameDisk = new Stack<>();
    private double[] _NeuralInput;
    public Player() { }

    public Player(String name, int funds) {
        this.playerName = name;
        this.totalFunds = funds;
        String[] splitName = name.split(" ");
        this.playerFirstName = splitName[0];
        this.playerLastName = splitName[1];
        this._NeuralInput = new double[0];
    }

    public void SetupAIEmotionalArrays(){
        this.Emotion_Aggression = new double[0];
        this.Emotion_Confidence = new double[0];
        this.Emotion_Doubt = new double[0];
        this.Emotion_Despair = new double[0];
        int count = 0;

        do{
            this.updateAggression(Math.random() * 15.0);
            this.updateDoubt(Math.random() * 25.0);
            this.updateDespair(Math.random() * 15.0);
            this.updateConfidence(Math.random() * 25.0);

            if(count >= 3){
                this.UpdateNeuralInput(
                        this.calcEmotions(
                                _EmotionCalc._CalcAggression(this.Emotion_Aggression),
                                _EmotionCalc._CalcDoubt(this.Emotion_Doubt),
                                _EmotionCalc._CalcDespair(this.Emotion_Despair),
                                _EmotionCalc._CalcConfidence(this.Emotion_Confidence)
                        )
                );
            }
            count++;
        }while(count < 10);

    }

    public void UpdateNeuralInput(double value){
        double[] newArray = new double[this._NeuralInput.length + 1];
        for(int i = 0; i < newArray.length; i++){
            if(i < (newArray.length - 1)){
                newArray[i] = this._NeuralInput[i];
            } else{
                newArray[i] = value;
            }
        }
        this._NeuralInput = newArray;
    }

   /* public void UpdateNeuralnput(double[] a, double[] b){
        double[] result = new double[a.length + b.length];
        for(int i = 0; i < a.length; i++)
            result[i] = a[i];
        for(int j = 0; j < b.length; j++)
            result[j] = b[j];
        this._NeuralInput = result;
    }*/

    public void ExamineCommunityCards(List<Card> commCards) {
        System.out.println(playerName);
        for(Card card : commCards) {
            System.out.print(" " + card.cardName() + " ");
        }
    }

    public void TakeTurn() {
        System.out.println("Player Name: " + this.playerName);

        for(Card card : this.BestHand){
            System.out.println(card.cardName());
        }
    }

   /* public int CardValueAtIndex(int index){
        return this.CardsInHand.get(i).getRankValue();
    }*/

    public int buyIn(int amount) {
        this.totalFunds -= amount;
        return amount;
    }

    public void ReceiveChips(Chip[] chips) {
        System.out.println(this.getPlayerFirstName() + " has chips");
        for(Chip chip : chips){
            this.PlayerChips.add(chip);
        }
    }

    public List<Chip> getPlayerChips() {
        return this.PlayerChips;
    }

    public String getPlayerName() {return this.playerName;}
    public String getPlayerFirstName() { return this.playerFirstName; }
    public String getPlayerLastName() { return this.playerLastName; }


    public void PrintChips(){
        System.out.println(playerName + " Chips: ");
        for(Chip chip : PlayerChips)
            System.out.print(chip.toString() + ", \n");
    }

    /*
    public void TakeTurn() {
        if(playerChips <= 0)
            this.isActivePlayer = false;
        else
            PlayerTurn();
    }
    public void PlayerTurn() {
        System.out.println ( playerName + "'s Turn" );
        EvulateCards ( );

        double RandomElement = ( double ) ( Math.random ( ) * 0.300 - 0.005 );
        double fuzzySet = Fuzzy_Logic.FuzzyInference ( getOdds ( ) , AI_Confidence , AI_Doubt , handValue , RandomElement );
        System.out.println ( playerName + "Fuzzy Set " + fuzzySet );
        if ( fuzzySet <= 0 && playerChips <= 100 ) Fold ( );
        else if ( fuzzySet >= 0 && fuzzySet < 0.4 ) action = Actions.Raise;
        else if ( ( fuzzySet > 0.4 ) && playerChips > 100 ) action = Actions.Call;

    }*/

    private int cardCounter;
    public void ReceiveCard(Card card) {
        if(cardCounter >= 2)
            return;
        if(card.status == FaceStatus.Face_Up)
            System.out.println(this.playerName + " has " + card.cardName());
        this.CardsInHand.add(card); cardCounter++;
    }

    public Card[] ReturnCards() {
        Card[] cards;
        if(CardsInHand.size() == 2){
            cards = new Card[2];
        } else {
            cards = new Card[1];
        }

        for(int i = 0; i < CardsInHand.size(); i++) {
            cards[i] = CardsInHand.get(i);
            CardsInHand.remove(i);
        }
        return cards;
    }

    public Card showOneCaard() {
        Card card = CardsInHand.get(0);
        return card;
    }
    /*
    public void CheckCards(){
        System.out.println(this.playerName);
        for(Card card : this.CardsInHand)
            System.out.print(card.cardName() + ", ");
        Card[] h = new Card[this.CardsInHand.size()];
        for(int i = 0; i < h.length; i++){
            h[i] = this.CardsInHand.get(i);
        }
        String[] priorityFV = {"Ace","Ten","Jack","Queen","King"};
        if(h[0].getSuitValue() == h[1].getSuitValue()){
            for(int i = 0; i < h.length; i++){
                for(int j = 0; j < priorityFV.length; j++){
                    if(h[i].getFace().equals(priorityFV[j])) {
                        this.BestHand.add(h[i]);
                    } else{
                        if(h[i].getRankValue() > 4 && h[i].getRankValue() < 10){
                            this.BestHand.add(h[i]);
                        }
                    }
                }
            }
        } else if(h[0].getRankValue() == h[1].getRankValue()){
            this.BestHand.addAll(Arrays.asList(h));
        } else {
            int highestValue = 0;
            for(int i  = 0; i < h.length; i++){
                highestValue = h[i].getRankValue();
                if(i > 0){
                    if(h[i].getRankValue() > highestValue)
                        highestValue = h[i].getRankValue();
                }
            }
            for(Card card : h){
                if(card.getRankValue() == highestValue) {
                    this.BestHand.add(card);
                }
            }
        }
    }*/

    public void EvaluateCards(List<Card> CommunityCards){
        Card[] C_Cards = new Card[CommunityCards.size()];
        for(int c = 0; c < CommunityCards.size(); c++){
            C_Cards[c] = CommunityCards.get(c);
        }
        int n = C_Cards.length;
        for(int i = 1; i < n; i++){
            Card key = C_Cards[i];
            int j = i - 1;
            while((j >= 0) && (C_Cards[j].getRankValue() > key.getRankValue())){
                C_Cards[j + 1] = C_Cards[j];
                j = j - 1;
            }
            C_Cards[j + 1] = key;
        }
        int lowestValue = C_Cards[0].getRankValue();
        int highestValue = C_Cards[C_Cards.length - 1].getRankValue();
        int straightFrequency = 0;
        for(int i = 1; i < C_Cards.length - 1; i++){
            if (lowestValue < C_Cards[i].getRankValue()
                                    && C_Cards[i].getRankValue() < highestValue) {
                straightFrequency += 1;
            }
        }
        System.out.println("Straight Frequency: " + straightFrequency);
        if(straightFrequency >= 3) {
            int suitFrequency = 0;
            int suitValue = C_Cards[0].getSuitValue();
            for(int i = 1; i < C_Cards.length; i++){
                if(C_Cards[i].getSuitValue() == suitValue){
                    suitFrequency += 1;
                }
            }
            if(suitFrequency >= 4) {
                System.out.println("Flush");
            } else {
                System.out.println("Straight");
            }
        }
    }
    ///Original EvaluateCards method
       /* for(Card card : this.CardsInHand){
            for(Card card2 : this.CardsInHand) {
                if(card.getCardNumber() != card2.getCardNumber()) {
                    if(card.getSuitValue() == card.getSuitValue()){
                        this.BestHand.addAll(this.CardsInHand);
                    } else {
                        if(card.getRankValue() == card2.getRankValue()) {
                            this.BestHand.addAll(this.CardsInHand);
                        }
                    }
                }
            }
        }
        String[] priorityFaceValues = {"Ace","Ten","Jack","Queen","King"};
        for(Card card : this.CardsInHand) {
            if(!this.BestHand.contains(card)){
                for(String value : priorityFaceValues) {
                    if(card.getFace().equals(value))
                        this.Besthand.add(card);
                }

                if(card.getRankValue() >= 5)
                    this.BestHand.add(card);
            }
        }*/

    private double[] updateArray(double[] a, double[] b) {
        double[] result = new double[a.length + b.length];
        for(int i = 0; i < a.length; i++){
            result[i] = a[i];
        }
        for(int j = 0; j < b.length; j++){
            result[j] = b[j];
        }
        return result;
    }



    /*

    public void EvulateCards(List<Card> cCards) {
        Card[] h=CardsInHand.toArray(new Card[0]);
        Card[] c = cCards.toArray(new Card[0]);
        String[] suits = new Card().getCardSuits();
        String[] faces = new Card().getCardFaces();
        for(int i = 0; i < h.length; i++) {
            for(int j = h.length - 1; j >= 0; j--) {
                if(h[i].getFace() == h[j].getFace()) {
                    System.out.println(playerName + " has a pair");
                    BestHand.addAll(Arrays.asList(h));
                }

                if(h[i].getSuitValue() == h[j].getSuitValue()) {
                    String[] priorityCards = {"Ace", "5","6","7","8","9","10",
                                "Jack","Queen","King"};
                    for(int a = 0; a < priorityCards.length; a++) {
                        if(h[i].getFace().equals(priorityCards[a])) {
                            BestHand.add(h[i]);
                        }
                        if(h[j].getFace() )
                    }
                }

                if((h[i].getFace() != h[j].getFace())
                        && (h[i].getSuit() != h[j].getSuit())) {

                }
            }
        }


        for(int i = 0; i < c.length; i++) {
            for(int j = c.length - 1; j >= 0; j--) {

            }
        }
    } */

    /*
    private final double CheckAI_Confidence() {
        if(AI_Confidence >= 0.5)
            return AI_Confidence = 0.5;
        else if(AI_Confidence <= 0.010)
            return AI_Confidence = 0.010;
        else return AI_Confidence;
    }

    private final double CheckAI_Doubt() {
        if(AI_Doubt >= 0.5)
            return AI_Doubt = 0.5;
        else if(AI_Doubt <= 0.010)
            return AI_Doubt = 0.010;
        else return AI_Doubt;
    }*/

    public double getHandValue() {
        return handValue;
    }

    public void returnFunds(int amount){
        this.totalFunds += amount;
    }
    public int getTotalFunds() { return this.totalFunds; }

    public void ShowHand() {
        if(CardsInHand.size() != 2)
            System.out.println("Hand empty");

        System.out.println(playerName);
        for(Card card : CardsInHand) {
            System.out.print(" " + card.cardName()+" ");
        }System.out.println();
    }

    private String pokerSeat;
    public void setPokerSeat(String value){
        this.pokerSeat = value;
    }

    public String getPokerSeat(){
        return this.pokerSeat;
    }

    public void clearSeat(){
        this.pokerSeat = "";
    }

    public void ResetHand() {
        CardsInHand.isEmpty();
    }

    public void ReceiveDisk(Disk disk){
        this.gameDisk.push(disk);
    }

    public boolean CheckDealerDisk(Disk disk){
        if(this.gameDisk.contains(disk))
            return true;
        else return false;
    }

    public boolean CheckSmallBlindDisk(Disk disk){
        if(this.gameDisk.contains(disk))
            return true;
        else return true;
    }

    public boolean CheckBigBlind(Disk disk){
        if(this.gameDisk.contains(disk))
            return true;
        else return false;
    }

    public Disk ReturnDisk(){
        Disk disk = this.gameDisk.pop();
        this.gameDisk.clear();
        return disk;
    }


    public double calcEmotions(double a, double dt, double ds, double c) {
        return Math.exp((a + dt + ds + c) % Math.random() * 0.85);
    }


    public void updateAggression(double value) {
        double[] newArray = new double[this.Emotion_Aggression.length + 1];
        for(int i = 0; i < newArray.length; i++){
            if(i < newArray.length - 1) {
                newArray[i] = this.Emotion_Aggression[i];
            } else {
                newArray[i] = value;
            }
        }
        this.Emotion_Aggression = newArray;
    }

    public void updateDoubt(double value){
        double[] newArray = new double[this.Emotion_Doubt.length + 1];
        for(int i = 0; i < newArray.length; i++){
            if(i <(newArray.length - 1)) {
                newArray[i] = this.Emotion_Doubt[i];
            } else {
                newArray[i] = value;
            }
        }
        this.Emotion_Doubt = newArray;
    }

    public void updateDespair(double value){
        double[] newArray = new double[this.Emotion_Despair.length + 1];
        for(int i = 0; i < newArray.length; i++){
            if(i < (newArray.length - 1)) {
                newArray[i] = this.Emotion_Despair[i];
            } else {
                newArray[i] = value;
            }
        }
        this.Emotion_Despair= newArray;
    }

    public void updateConfidence(double value){
        double[] newArray = new double[this.Emotion_Confidence.length + 1];
        for(int i = 0; i < newArray.length; i++){
            if(i < (newArray.length - 1)) {
                newArray[i] = this.Emotion_Confidence[i];
            } else {
                newArray[i] = value;
            }
        }
        this.Emotion_Confidence = newArray;
    }



    public double[] getEmotion_Aggression() { return this.Emotion_Aggression; }
    public double[] getEmotion_Doubt() { return this.Emotion_Doubt; }
    public double[] getEmotion_Despair() { return this.Emotion_Despair; }
    public double[] getEmotion_Confidence() { return this.Emotion_Confidence; }
    public Chip[] postBlind(int amount){
        Chip[] chips = new Chip[3];
        int chipValue = amount / 3;
        if(chipValue == 5000){
            for(Chip chip : this.PlayerChips) {
                if(chip.getType() == ChipType.Chip){
                    if(chip.getColor() == ChipColor.Green) {
                        for(int i = 0; i < chips.length; i++) {
                            chips[i] = chip;
                        }
                    }
                }
            }
        } else {
            for(Chip chip : this.PlayerChips){
                if(chip.getType() == ChipType.Chip) {
                    if(chip.getColor() == ChipColor.Pink) {
                        for(int i = 0; i < chips.length; i++) {
                            chips[i] = chip;
                        }
                    }
                }
            }
        }
        return chips;
    }

    public int getCardvalueAtIndex(int index) {
        return this.CardsInHand.get(index).getRankValue();
    }
}
