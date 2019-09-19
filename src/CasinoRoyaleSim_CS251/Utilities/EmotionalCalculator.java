package CasinoRoyaleSim_CS251.Utilities;

public class EmotionalCalculator {
    public EmotionalCalculator(){}

    public double _CalcAggression(double[] _AIAggression){
        int indexOne = _AIAggression.length -1;
        int indexTwo = _AIAggression.length - 2;
        return Math.expm1(_AIAggression[indexOne] - _AIAggression[indexTwo]);
    }

    public double _CalcDoubt(double[] _AIDoubt){
        int indexOne = _AIDoubt.length - 1;
        int indexTwo = _AIDoubt.length - 2;
        return Math.expm1(_AIDoubt[indexOne] - _AIDoubt[indexTwo]);
    }

    public double _CalcDespair(double[] _AIDespair){
        int indexOne = _AIDespair.length - 1;
        int indexTwo  = _AIDespair.length - 2;
        return Math.expm1(_AIDespair[indexOne] - _AIDespair[indexTwo]);
    }

    public double _CalcConfidence(double[] _AIConfidence){
        int indexOne = _AIConfidence.length - 1;
        int indexTwo = _AIConfidence.length - 2;
        return Math.expm1(_AIConfidence[indexOne] - _AIConfidence[indexTwo]);
    }
}

