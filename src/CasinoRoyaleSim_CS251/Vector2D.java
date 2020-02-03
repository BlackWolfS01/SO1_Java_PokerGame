package CasinoRoyaleSim_CS251;
/*
A simple utility class for determming player order. The two variables are simply for 
quick reference.
*/
public class Vector2D {
    private Player _Player;
    private int _CardValue;
    public Vector2D(Player player, int cardValue){
        this._Player = player;
        this._CardValue = cardValue;
    }
    public Player get_Player(){ return this._Player; }
    public int get_CardValue(){ return this._CardValue; }
    public String getPlayerName(){ return this._Player.getPlayerName();}
}
