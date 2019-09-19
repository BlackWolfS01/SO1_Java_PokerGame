package CasinoRoyaleSim_CS251.NeuralNetworkSource;

public class NeuralLayer {
    private Neuron[] neurons;
    private int numOfDendites;
    private boolean outputLayer;
    private boolean inputLayer;
    public NeuralLayer() {}

    public NeuralLayer(int size, int numD, boolean isOut, boolean isIn) {
        neurons = new Neuron[size];
        numOfDendites = numD;
        for(int i = 0; i < size; i++) {
            neurons[i] = new Neuron(numOfDendites);
        }
        outputLayer = isOut;
        inputLayer = isIn;
    }

    public double[] Evaluate(double[] inputs) {
        if(inputs.length != neurons[0].getNumDendrites ())
            return null;

        double[] output = new double[neurons.length];

        for(int i = 0; i < neurons.length; i++) {
            neurons[i].Evaluate ( inputs );
            output[i] = neurons[i].getActivationLevel ();
        }

        return output;
    }

    public Neuron getNeuron(int i) {
        return neurons[i];
    }

    public int getSize() {
        return neurons.length;
    }

    public int getNumDendrites() {
        return numOfDendites;
    }

    public void setNeuron(Neuron n, int i) {
        neurons[i] = n;
    }

    public void setWeight(double w, int n, int i) {
        neurons[n].setWeight(w, i);
    }

    public void setBias(double b, int n) {
        neurons[n].setBias(b);
    }

    public boolean checkOutputStatus(){
        return outputLayer;
    }

    public boolean checkInputStatus(){
        return inputLayer;
    }
    public String toString() {
        String ret = "";
        for (int i = 0; i < neurons.length; i++) {
            ret += neurons[i].toString();
            if (i < neurons.length - 1)
                ret += "\n";
        }
        return "" + ret;
    }
}
