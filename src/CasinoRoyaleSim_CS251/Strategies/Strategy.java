package CasinoRoyaleSim_CS251.Strategies;
import CasinoRoyaleSim_CS251.Action.Action;
public abstract class Strategy {
    private String StrategyName;
    private StrategyType strategyType;
    protected Double tacticalPreference;

    public Strategy() {

    }

    public abstract Action[] preferredActions();

    public String getStrategyName() { return StrategyName; }
    public StrategyType getStrategyType() { return strategyType; }

}
