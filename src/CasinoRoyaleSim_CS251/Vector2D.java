package CasinoRoyaleSim_CS251;

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
