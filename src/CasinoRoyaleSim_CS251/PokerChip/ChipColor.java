package CasinoRoyaleSim_CS251.PokerChip;
public enum ChipColor {
     Blue,
     Green,
     Pink,
     Red,
     Black;

     public String getVerb(ChipColor color){
          String verb = "";
          switch(color){
               case Blue:
                    verb = "Blue";
                    break;
               case Green:
                    verb = "Green";
                    break;
               case Pink:
                    verb = "Pink";
                    break;
               case Red:
                    verb = "Red";
                    break;
               case Black:
                    verb = "Black";
                    break;
          }
          return verb;
     }
}
