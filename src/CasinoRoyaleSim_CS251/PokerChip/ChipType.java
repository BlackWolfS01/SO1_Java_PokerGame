package CasinoRoyaleSim_CS251.PokerChip;
public enum ChipType {
     Chip,
     Plaque;
     public String getType(ChipType type){
          String value = "";
          switch(type){
               case Chip:
                    value = "Chip";
                    break;
               case Plaque:
                    value = "Plaque";
                    break;
          }
          return value;
     }
}
