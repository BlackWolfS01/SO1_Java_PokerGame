package CasinoRoyaleSim_CS251;
import CasinoRoyaleSim_CS251.Disks.*;
import CasinoRoyaleSim_CS251.PokerDeck.Card;
import CasinoRoyaleSim_CS251.PokerDeck.Deck;

import java.util.*;
import java.util.Queue;
/*
Design Notes: Table Gameplay

CasinnRoyaleSim is meant to closely resemble real life Texas Hold'em Poker. That includes simulating
the rules that other games may ignore, or intentionally leave out. In tradiional Texas Hold'em, there's a
concept known as Burn Cards, a dealer will intentional removes the top card from play. This regularly happens
throughout a hand. When cards are dealt out and community cards are put into play.

Mechanically, it's meant more as unpredictable element, a way to indirectly shape gameplay. Depending on a card's
placement within the Deck(Stack). Every time a dealer burns the top most card. They are potentional removing
a card that could drastically affect which player wins that hand. Of course, the same is also true for
the second card drawn and put into play. The original intention here was to introduce slightly deeper gameplay,
without any unnecessarially complexity.

Same goes for the Poker Disks: Dealer, Small Blind, and Big Blind. At the start of each new hand,
every player is dealt one card, face up, and the player with the highest card recieves the dealer disk. Those
players to their left and right recieve the Small Blind and Big Blind disks. These Blinds are meant to add chips/meony to
the pot, and act as inscentive for players to bet.



*/
/*  Table Map;


 */

public class Table implements UtilInterface {
    private List<Player> ActivePlayers = new LinkedList<>();
    private CardGame Card_Game;
    private LinkedList<Card> Community_Cards = new LinkedList<>();
    
    //Separate stack to hold burn cards
    public Stack<Card> Burn_Cards = new Stack<>();
    
    private final Dealer House_Dealer;
    protected Map<String, Player> TableMap = new HashMap<>();
    protected Disk dealerDisk = new DealerDisk();
    protected Disk smallBlindDisk  = new SmallBlindDisk();
    protected Disk bigBlindDisk = new BigBlindDisk();


    private final Deck _Deck;

    public Table(CardGame cardGame) {
        this.Card_Game = cardGame;
        House_Dealer = new  Dealer("Samantha", "Walters");
        _Deck = new Deck();
        House_Dealer.ReceiveDeck(_Deck);
    }
    
    private String[] chairNames = {"Chair One", "Chair Two", "Chair Three", "Chair Four",
            "Chair Five",  "Chair Six", "Chair Seven", "Chair Eight", "Chair Nine", "Chair Ten"};

    public String[] getChairNames(){ return this.chairNames;}

    public void BuildTable(){
        System.out.println("Build Table");
        for(String chair : chairNames) {
            for(Player player : ActivePlayers){
                TableMap.put(chair, player);
                player.setPokerSeat(chair);
            }
        }
    }

    public void ReceivePlayers(ArrayList<Player> players) {
        ActivePlayers.addAll(players);
    }

    private LinkedList<Player> playerOrderForHand = new LinkedList<>();
    public void StartOfGame(){
        System.out.println("House Dealer: : " + House_Dealer.getDealer_FullName());
        System.out.println("Casino Observer: 'There is a $10 million buy in to join the table. Once you have won the game. We we gladly transfer your winnings to any bank in the world'");;
        ReceivePlayerBuyIns();
        House_Dealer.ShuffleDeck();

        System.out.println("Distribute Chips");
        ActivePlayers = Card_Game.ProvideChips(ActivePlayers);
    }
    
    /*
    
    */
    public void PlayHand(){
        House_Dealer.ShuffleDeck();
        
        //Method Call: Deals one card to each player
        Card_Game.DealACard(House_Dealer, ActivePlayers);
        Vector2D[] playerVector = new Vector2D[ActivePlayers.size()];
        for(int i = 0; i < playerVector.length; i++){
            playerVector[i] = new Vector2D(ActivePlayers.get(i),
                                ActivePlayers.get(i).getCardvalueAtIndex(0));
        }
        for(Vector2D vector : playerVector){
            System.out.println(vector.getPlayerName() + ", "+ vector.get_CardValue());
        }
        // Insertion Sort
        // Arranges the playerVector array from lowest to highest
        int n = playerVector.length;
        for(int i = 0; i < n; i++){
            Vector2D key = playerVector[i];
            int j = i - 1;
            while(j >= 0 && playerVector[j].get_CardValue() > key.get_CardValue()){
                playerVector[j + 1] = playerVector[j];
                j = j - 1;
            }
            playerVector[j + 1] = key;
        }
        
        // First in-First Out
        // Going through the playerVector array in reverse
        // The player with the highest card starts at the head of the queue
        // And it proceeds from there
        Queue<Player> _PlayerOrder = new LinkedList<>();

        for(int i = playerVector.length - 1; i >= 0; i--){
            _PlayerOrder.add(playerVector[i].get_Player());
        }
        
        //Based on position in the queue
        //All Poker Disks are dealt out to the players
        for(int i = 0; i < _PlayerOrder.size(); i++) {
            for(Player player : _PlayerOrder){
                if(i == 0){
                    player.ReceiveDisk(dealerDisk);
                } else if(i == 1){
                    player.ReceiveDisk(smallBlindDisk);
                } else if(i == 2){
                    player.ReceiveDisk(bigBlindDisk);
                }
            }
        }
        System.out.println("Post Small & Big Blind");
        for(Player player : _PlayerOrder){
            if(player.CheckDealerDisk(dealerDisk)){
                System.out.println(player.getPlayerFirstName() + " has the Dealer Disk");
            }
            if(player.CheckSmallBlindDisk(smallBlindDisk)) {
                System.out.println(player.getPlayerFirstName() + " posts the Small Blind.");
                Card_Game.PostSmallBlind(player);
            }
            if(player.CheckBigBlind(bigBlindDisk)) {
                System.out.println(player.getPlayerFirstName() + " posts the Big Blind.");
                Card_Game.PostBigBlind(player);
            }
        }
        System.out.println("All players return their cards");
        for(Player player : _PlayerOrder){
            Card_Game.ReceivePlayerCards(House_Dealer, player);
        }
        System.out.println("The Dealer returns all burned cards to the deck.");
        Card_Game.ReturnBurnCards(House_Dealer, this.Burn_Cards);
        playerOrderForHand = this.SetPlayerOrderForHand(_PlayerOrder);
        /*
        int[] cardValues = new int[ActivePlayers.size()];
        for(int i = 0; i < cardValues.length; i++){
            for(int j = 0; j < ActivePlayers.size(); j++){
                cardValues[i] = ActivePlayers.get(i).CardValueAtIndex(0);
            }
        }
        for(int value : cardValues)
            System.out.println(value);

        Stack<Player> _PlayerOrder = new Stack<>();
        int n = cardValues.length;
        for(int i = 1; i < n; i++){
            int key = cardValues[i];
            int j = i- 1;
            while(j >= 0 && cardValues[j] > key){
                cardValues[j+1] = cardValues[j];
                j = j - 1;
            }
            cardValues[j + 1] = key;
        }

        for(int j = cardValues.length - 1; j >= 0; j--){
            for(Player player : ActivePlayers){
                if(cardValues[j] == player.CardValueAtIndex(0)) {
                    _PlayerOrder.push(player);
                }
            }
        }

        for(int i = 0; i < _PlayerOrder.size(); i++){
            for(int j = cardValues.length - 1; j >= 0; j--){
                System.out.println(_PlayerOrder.get(i) + ", " + cardValues[j]);
            }
        }*/
        /*
        LinkedList<Player> playerOrder = Card_Game.setPlayerOrder(TableMap, ActivePlayers);
        System.out.println(House_Dealer.getDealer_FirstName() + " hands out the Dealer, Big Blind, and Small Blind disks.");
        HandoutDisks(playerOrder);
        Card_Game.ReceivePlayerCards(House_Dealer, playerOrder);
        Card_Game.ReturnBurnCards(House_Dealer, Burn_Cards);

        //Reorder Player List
        Player playerWithDealerDisk = null;
        for(Player player : playerOrder) {
            if(player.CheckDealerDisk(dealerDisk)){
                playerWithDealerDisk = player;
                playerOrder.remove(player);
            }
        }
        playerOrder.add(playerWithDealerDisk);

        //Post Small Blind
        for(Player player : playerOrder){
            if(player.CheckSmallBlindDisk(smallBlindDisk)){
                Card_Game.PostSmallBlind(player);
            }
        }

        //Post Big Blind
        for(Player player : playerOrder) {
            if(player.CheckBigBlind(bigBlindDisk)) {
                Card_Game.PostBigBlind(player);
            }
        }
        //playerOrder = Card_Game.PlayerFirstRound(House_Dealer, playerOrder);*/
    }

    public LinkedList<Player> SetPlayerOrderForHand(Queue<Player> players) {
        Player lastPlayer = players.poll();
        LinkedList<Player> pList  = new LinkedList<>();
        for(int i = 0; i < players.size(); i++){
            pList.add(players.poll());
        }
        pList.add(lastPlayer);
        return pList;
    }

    public void PrintTableMap(){
        Iterator<Map.Entry<String, Player>> iterator = TableMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Player> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue().getPlayerName());
        }
    }

    private void ReceivePlayerBuyIns(){
        int allPlayerBuyInts = 0;
        for(Player player : ActivePlayers){
            allPlayerBuyInts += player.buyIn(10000000);
        }
        Card_Game.setTotalBankFunds(allPlayerBuyInts);
    }

    public void ShowCommunityCards() {
        for (Card card : Community_Cards) {
            System.out.println("Card" + " : " + (card.toString()));
        }
    }

    private void HandoutDisks(List<Player> playerOrder){
        for(int playerIndex = 0; playerIndex < playerOrder.size(); playerIndex++){
            if(playerIndex == 0) {
                playerOrder.get(playerIndex).ReceiveDisk(dealerDisk);
            } else if(playerIndex == 1){
                playerOrder.get(playerIndex).ReceiveDisk(smallBlindDisk);
            } else if(playerIndex == 2) {
                playerOrder.get(playerIndex).ReceiveDisk(bigBlindDisk);
            }
        }
    }

}
