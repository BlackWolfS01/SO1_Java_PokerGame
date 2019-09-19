package CasinoRoyaleSim_CS251;

import CasinoRoyaleSim_CS251.PokerChip.Chip;
import CasinoRoyaleSim_CS251.PokerChip.ChipColor;
import CasinoRoyaleSim_CS251.PokerChip.ChipType;
import CasinoRoyaleSim_CS251.CardGame;
import CasinoRoyaleSim_CS251.Table;
import java.util.LinkedList;
import java.util.List;

public interface Analysis {

    class CardAnalyzer {
        public CardAnalyzer(){ }


    }

    class ChipCounter{

        public ChipCounter() {}

        public LinkedList<Chip> Check(Chip[] chips) {
            LinkedList<Chip> returnChips = new LinkedList<>();
            for(int i = 0; i < chips.length; i++){
                if(CheckRedPlaque(chips[i]) != null || CheckBluePlaque(chips[i]) != null || CheckBlackChip(chips[i]) != null
                        || CheckPinkChip(chips[i]) != null || CheckGreenChip(chips[i]) != null || CheckBlueChip(chips[i]) != null) {
                    returnChips.add(chips[i]);
                }
            }
            return returnChips;
        }

        protected Chip CheckRedPlaque(Chip chip){
            if(chip.getType() == ChipType.Plaque) {
                if (chip.getColor() == ChipColor.Red) {
                    return chip;
                }
            }
            return null;
        }

        protected Chip CheckBluePlaque(Chip chip) {
            if(chip.getType() == ChipType.Plaque) {
                if(chip.getColor() == ChipColor.Blue) {
                    return chip;
                }
            }
            return null;
        }

        protected Chip CheckBlackChip(Chip chip) {
            if(chip.getType() == ChipType.Chip) {
                if(chip.getColor() == ChipColor.Black) {
                    return chip;
                }
            }
            return null;
        }

        protected Chip CheckPinkChip(Chip chip) {
            if(chip.getType() == ChipType.Chip) {
                if(chip.getColor() == ChipColor.Pink) {
                    return chip;
                }
            }
            return null;
        }

        protected Chip CheckGreenChip(Chip chip) {
            if(chip.getType() == ChipType.Chip) {
                if(chip.getColor() == ChipColor.Green) {
                    return chip;
                }
            }
            return null;
        }

        protected Chip CheckBlueChip(Chip chip) {
            if(chip.getType() == ChipType.Chip) {
                if(chip.getColor() == ChipColor.Blue) {
                    return chip;
                }
            }
            return null;
        }
    }

}
