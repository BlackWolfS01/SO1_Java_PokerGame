package CasinoRoyaleSim_CS251.Strategies;

import CasinoRoyaleSim_CS251.Action.Action;

public interface PokerStrategies {

    final class OffensiveStrategy extends Strategy {

        public OffensiveStrategy(){
            super();
        }
        @Override
        public Action[] preferredActions() {
            return new Action[0];
        }
    }
}
