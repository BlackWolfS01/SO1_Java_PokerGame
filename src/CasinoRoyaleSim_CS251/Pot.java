package CasinoRoyaleSim_CS251;
import CasinoRoyaleSim_CS251.PokerChip.Chip;
import CasinoRoyaleSim_CS251.CardGame;
import CasinoRoyaleSim_CS251.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Pot {
    private final List<Chip> ChipsInPot = new ArrayList<>();
    private int totalValue;
    public Pot() {
        this.totalValue = 0;
    }

    public void AcceptChips(Chip[] playerChips) {
        for(Chip chip : playerChips)
            totalValue += chip.getValue();
        ChipsInPot.addAll(Arrays.asList(playerChips));
    }

    public void update(){
        for(Chip c : ChipsInPot)
            totalValue += c.getValue();
    }

    public int getTotalValue(){
        return this.totalValue;
    }
}