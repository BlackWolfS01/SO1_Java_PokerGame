package CasinoRoyaleSim_CS251.PokerDeck;

import CasinoRoyaleSim_CS251.Exceptions.CardVisibilityException;

public class Card {
    private static String[] cardFaces = {"Ace", "Two", "Three", "Four", "Five",
                    "Six", "Seven", "Eight", "Nine",
                    "Ten", "Jack", "Queen", "King"};
    private static String[] cardSuits = {"Hearts", "Diamonds",
                        "Clubs", "Spades"};
    private String Suit;
    private String Face;
    private int RankValue;
    private int SuitValue;
    private boolean isVisible;
    private int CardNumber = 0;
    public FaceStatus status;
    public Card() {
        this(null,null,0,0);
    }
    public Card(String Suit, String Face) {
        this.Suit = Suit;
        this.Face = Face;
    }
     public Card(String Suit, String Face, int RankValue, int SuitValue) {
        this.Suit = Suit;
        this.Face = Face;
        this.RankValue = RankValue;
        this.SuitValue = SuitValue;
    }
    /*
    Dev Note: In convertCard, the values 'x' and 'y' refer to the cardSuits Array and
    cardFaces Array, respectfully. buildDeck(0 in Deck Class
    @Perimeter index
     */
    public static Card convertCard(int index) {
        Card newCard = new Card();
        int x = (index / 13) % 52, y = (index % 13);
        for(int i = 0; i < cardSuits.length; i++) {
            if (i == x) {
                newCard.setSuit(cardSuits[i]);
                newCard.setSuitValue(i+1);
            }
        }
        for(int j = 0; j < cardFaces.length; j++) {
            if (j == y) {
                newCard.setFace(cardFaces[j]);
                newCard.setRankValue(j+1);
            }
        }
        newCard.setCardNumber(index);
        newCard.status = FaceStatus.Face_Down;
        return newCard;
    }

    public String getSuit(){
        return this.Suit;
    }

    public String getFace() { return Face; }
    public int getRankValue() { return RankValue; }
    public int getSuitValue() { return SuitValue; }
    public String cardName() { return Face + " of " + Suit; }

    public String[] getCardSuits() {
        return cardSuits;
    }
    public String[] getCardFaces() {
        return cardFaces;
    }
    public int getCardNumber(){
        return CardNumber;
    }

    public void setSuit(String suit) {
        this.Suit = suit;
    }
    public void setFace(String face) {
        this.Face = face;
    }
    public void setRankValue(int rankValue ) {
        this.RankValue = rankValue;
    }
    public void setSuitValue(int suitValue) {
        this.SuitValue = suitValue;
    }
    public void setCardNumber(int index) {
        this.CardNumber=index;
    }

    public String CheckCard() throws CardVisibilityException{
        if(!this.isVisible)
            throw new CardVisibilityException("Card is Face Down");
        else return this.cardName();
    }

    public void FlipCard(){
        if(this.status == FaceStatus.Face_Down)
            this.status = FaceStatus.Face_Up;
        else
            this.status = FaceStatus.Face_Down;
    }

    public void FlipFaceUp(){
        this.status = FaceStatus.Face_Up;
    }
    public void FlipFaceDown(){
        this.status = FaceStatus.Face_Down;
    }

    @Override
    public String toString () { return cardName(); }
}
