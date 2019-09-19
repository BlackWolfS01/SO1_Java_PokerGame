package CasinoRoyaleSim_CS251.PokerChip;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;

public class Chip implements Cloneable {

     private int value;
     private ChipType type;
     private ChipColor color;
     private String chipColor;
     private String chipType;

     public Chip(int value, ChipType type, ChipColor color) {
          this.value = value;
          this.type = type;
          this.color = color;
          this.chipColor = this.color.getVerb(this.color);
          this.chipType = this.type.getType(this.type);
     }

     public String toString() {
          return this.chipColor + " "+ this.chipType;
     }

     public String getChipColor(){
          return this.chipColor;
     }

     public String getChipType(){
          return this.chipType;
     }
     public int getValue() {
          return value;
     }
     public ChipType getType(){ return type; }
     public ChipColor getColor(){ return color; }
}