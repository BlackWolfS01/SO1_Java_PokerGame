package CasinoRoyaleSim_CS251.Action;

import CasinoRoyaleSim_CS251.Analysis;
import CasinoRoyaleSim_CS251.PokerChip.Chip;
import CasinoRoyaleSim_CS251.PokerChip.ChipColor;
import CasinoRoyaleSim_CS251.PokerChip.ChipType;
import CasinoRoyaleSim_CS251.CardGame;
import CasinoRoyaleSim_CS251.Table;

import java.util.LinkedList;

public class Call extends Action implements Analysis {
    private ChipCounter chipCounter = new ChipCounter();
    protected String playerName;
    protected Chip[] chips;
    public Call () {
        super("Call","calls");

    }
    @Override
    public String actionDescription() {
        String newText = playerName + " " + verb +".\n";
        int totalPlaques = 0, totalChips = 0;
        LinkedList<Chip> bet = chipCounter.Check(chips);
        for(Chip chip : bet) {
            if(chip.getType() == ChipType.Plaque)
                totalPlaques++;
            else
                totalChips++;
        }
        if(totalPlaques != 0) {
            newText += "\n" + playerName + " adds " + totalPlaques + "  plaques to the pot.\n" +
                    "Worth a total of " + TotalValue(chips);
        } else if(totalChips != 0) {
            newText += "\n" + playerName + " adds " + totalChips + " chips to the pot.\n" +
                    "Worth a total of " + TotalValue(chips);
        } else {
            newText += "\n" + playerName + " adds " + totalPlaques + "plaques " + "and" + " " + totalChips + " chips to the pot.\n" +
                    "Worth a total of " + TotalValue(chips);
        }
        return newText;
    }

    private String TotalValue(Chip[] chips){
        calcBetTotalValue(chips);
        String text = "$" +  calcBetTotalValue(chips);
        String newText = "";
        if(text.length() > 3 && text.length() < 6) {
            int index = text.length() - 3;
            for(int i = 0; i < text.length(); i++) {
                newText += text.charAt(i);
                if(i == index) {
                    newText += ",";
                }
            }
        } else {
            int indexOne = text.length() - 6;
            int indexTwo = text.length() - 3;
            for(int  i = 0; i < text.length(); i++) {
                newText += text.charAt(i);
                if(i == indexOne || i == indexTwo) {
                    newText += ",";
                }
            }
        }
        return newText;
    }

    private Integer calcBetTotalValue(Chip[] chips){
        int totalValue = 0;
        for(int i = 0; i < chips.length; i++){
            totalValue += chips[i].getValue();
        }
        return totalValue;
    }


}
