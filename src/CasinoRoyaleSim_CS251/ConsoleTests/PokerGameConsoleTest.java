package CasinoRoyaleSim_CS251.ConsoleTests;

import CasinoRoyaleSim_CS251.CardGame;
import CasinoRoyaleSim_CS251.Player;

import java.util.*;

public class PokerGameConsoleTest {

    public static void main(String[] args){

      CardGame Card_Game = new CardGame();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Andrew Vega", 15000000));
        players.add(new Player("Jones HIll", 15000000));
        players.add(new Player("Tom Lance", 15000000));
        players.add(new Player("Ashley Williams",15000000));
        players.add(new Player("Simon Smith", 15000000));
       players.add(new Player("Jasmine Summers",15000000));
        players.add(new Player("Marcus Mitchell", 15000000));
        players.add(new Player("Mac Walters", 15000000));
        players.add(new Player("Grace Winters", 15000000));
        players.add(new Player("John Knight", 15000000));

        Card_Game.AddPlayers(players);
        Card_Game.StartGame();
        Card_Game.PlayHand();
    }


}
