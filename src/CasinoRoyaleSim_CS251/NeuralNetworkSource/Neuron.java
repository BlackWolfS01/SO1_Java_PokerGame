package CasinoRoyaleSim_CS251.NeuralNetworkSource;

public class Neuron {
    private final double Default_Weight = 1;
    private final double Default_Bias = 0;
    private double bias;
    private double[] weights;
    private double activationLevel;

    public Neuron() {
        this(9);
    }

    public Neuron(int numOfDendrites) {
        bias = Default_Bias;
        weights = new double[numOfDendrites];

        for(int i = 0; i < numOfDendrites; i++){
            weights[i] = Default_Weight * 2 / numOfDendrites;
            weights[i] = weights[i] * Math.random();
        }

        activationLevel = 0;
    }

    public void Evaluate(double[] inputs) {
        if(inputs.length != weights.length)
            return;

        double sum = 0.0;
        for(int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        sum += bias;

        activationLevel = 1 / (1 * Math.pow (Math.E, (-1*sum)  ));
    }

    public void SpecialEvaluation(double[] inputs, boolean node) {
        Evaluate(inputs);
    }

    public void deactivate() {
        activationLevel = 0.0;
    }

    public double getActivationLevel() {
        return activationLevel;
    }

    public double getBias() {
        return bias;
    }

    public int getNumDendrites() {
        return weights.length;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getWeight(int i) {
        return weights[i];
    }

    public void setBias(double b) {
        bias = b;
    }

    public void setWeights(double[] w) {
        weights = w;
    }

    public void setWeight(double w, int i) {
        weights[i] = w;
    }

    public String toString() {
        return "[" + bias + ", " + weightsToString() + "]";
    }

    public String weightsToString() {
        String ret = "";
        for (int i = 0; i < weights.length; i++) {
            ret += weights[i];
            if (i < weights.length - 1)
                ret += ", ";
        }
        return "" + ret;
    }
}

