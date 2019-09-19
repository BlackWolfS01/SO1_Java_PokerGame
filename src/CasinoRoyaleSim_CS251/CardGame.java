package CasinoRoyaleSim_CS251;
import CasinoRoyaleSim_CS251.PokerChip.Chip;
import CasinoRoyaleSim_CS251.PokerChip.Converter;
import CasinoRoyaleSim_CS251.PokerDeck.Card;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class CardGame {
    private static int Games;
    private static int Rounds;
    private int minBuyInLimit;
    private Converter _Converter;
    protected Table _Table;
    protected Pot _Pot;
    private int TotalBankFunds;
    protected int smallBlindAmount;
    protected int bigBlindAmount;

    public CardGame() {
        _Table = new Table(this);
        _Pot = new Pot();
        _Table.BuildTable();
        this.setSmallBlindAmount(15000);
        this.setBigBlindAmount(75000);
        this.setConverter(2,2,10, 20, 25, 25);;
    }

    public void setConverter(int redPlaques, int bluePlaques, int blackChips, int pinkChips, int greenChips, int blueChips){
        this._Converter = new Converter(redPlaques, bluePlaques, blackChips, pinkChips,
                    greenChips, blueChips);
    }

    public void AddPlayers(ArrayList<Player> players) {
            _Table.ReceivePlayers(players);
    }

    public void StartGame(){
        _Table.StartOfGame();
    }
    public void PlayHand() { _Table.PlayHand(); }

    public LinkedList<Player> PlayFirstRound(Dealer _HouseDealer, LinkedList<Player> activePlayers) {
        System.out.println("Play first round of the hand");
        _HouseDealer.ShuffleDeck();
        DealHands(_HouseDealer, activePlayers);
        for(Player _ActivePlayer : activePlayers){
            _ActivePlayer.TakeTurn();
        }
        return activePlayers;
    }

    public void DealACard(Dealer dealer, List<Player> activePlayers){
        for(Player player : activePlayers){
            for(int i = 0; i < 1; i++){
                _Table.Burn_Cards.push(dealer.drawCard());
                player.ReceiveCard(dealer.drawOneCard());
            }
        }
    }

    public void DealHands(Dealer dealer, List<Player> activePlayers) {
        for(Player player : activePlayers) {
            for (int i = 0; i < 2; i++) {
                _Table.Burn_Cards.push(dealer.drawCard());
                player.ReceiveCard(dealer.drawCard());
            }
        }
    }

    public void ReceivePlayerCards(Dealer dealer, Player player){
        Card[] cards = player.ReturnCards();
        for(Card card : cards){
            dealer.receiveCard(card);
        }
    }

    public void ReturnBurnCards(Dealer dealer, Stack<Card> burnCards){
        for(int i = 0; i < burnCards.size(); i++)
            dealer.receiveCard(burnCards.pop());

        _Table.Burn_Cards.clear();
    }

    public void PostBigBlind(Player player) {
        Chip[] playerChips = player.postBlind(this.bigBlindAmount);
        this._Pot.AcceptChips(playerChips);
    }
    public void PostSmallBlind(Player player) {
        Chip[] playerChips = player.postBlind(this.smallBlindAmount);
        this._Pot.AcceptChips(playerChips);
    }

    public LinkedList<Card> PlayFlopCards(Dealer dealer, LinkedList<Card> Community_Cards){
        for(int i = 0; i < 3; i++)
            Community_Cards.push(dealer.drawCard());
        return Community_Cards;
    }

    public LinkedList<Card> PlayTurnCard(Dealer dealer, LinkedList<Card> Community_Cards) {
        Community_Cards.push(dealer.drawCard());
        return Community_Cards;
    }

    public LinkedList<Card> PlayRiverCard(Dealer dealer, LinkedList<Card> Community_Cards) {
        Community_Cards.push(dealer.drawCard());
        return Community_Cards;
    }

    public void setTotalBankFunds(int funds) {
        this.TotalBankFunds = funds;
    }

    public int getTotalBankFunds(){
        return this.TotalBankFunds;
    }

    public Converter getConverter() {
        return this._Converter;
    }

    public List<Player> ProvideChips(List<Player> players) {
        for(Player player : players){
            System.out.println(player.getPlayerFirstName() + " receives chips");
            player.ReceiveChips(this._Converter.buildChips());
        }
        return players;
    }

    /*
    public LinkedList<Player> setPlayerOrder(Map<String, Player> tableMap, List<Player> activePlayers) {
        String key = DeterminePlayerOrder(activePlayers);
        LinkedList<Player> playerOrder = new LinkedList<>();
        int index = 0;
        String[] chairNames = _Table.getChairNames();

        Iterator<Map.Entry<String, Player>> iterator = tableMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Player> entry = iterator.next();
            if(entry.getKey().equals(key)){
                playerOrder.add(entry.getValue());
            }

            for(int i = 0; i < chairNames.length; i++){
                if(chairNames[i].equals(key))
                    index = i;
            }

            for(int j = index + 1; j < chairNames.length; j++){
                if(entry.getKey().equals(chairNames[j])){
                    playerOrder.add(entry.getValue());
                }
            }

            for(int l = 0; l < index; l++){
                if(entry.getKey().equals(chairNames[l])){
                    playerOrder.add(entry.getValue());
                }
            }
        }
        return playerOrder;
    }

    private String DeterminePlayerOrder(List<Player> players) {
        LinkedList<Player> orderedPlayer = new LinkedList<>();
        int[] cardValues = new int[players.size()];
        for(int i = 0; i < cardValues.length; i++)
            cardValues[i] = players.get(i).getCardvalueAtIndex(0);
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
        for(int i = 0; i < players.size(); i++) {
            for(int j = 0; j < cardValues.length; j++){
                if(players.get(i).getCardvalueAtIndex(0) == cardValues[j])
                    orderedPlayer.push(players.get(i));
            }
        }
        return orderedPlayer.getFirst().getPokerSeat();
    }*/

    public void setSmallBlindAmount(int amount) { this.smallBlindAmount = amount; }
    public void setBigBlindAmount(int amount) { this.bigBlindAmount = amount; }

    public int getSmallBlindAmount(){ return this.smallBlindAmount; }
    public int getBigBlindAmount() { return this.bigBlindAmount; }
}