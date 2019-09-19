package CasinoRoyaleSim_CS251.NeuralNetworkSource;

public class NeuralNetwork {
    private final static double Learning_Rate = 0.5;
    private final static double Bias_Learning_Rate = 0.2;

    private int inputSize;
    private NeuralLayer[] hiddenLayers;
    private NeuralLayer outputLayer;
    private NeuralLayer inputLayer;
    private double[] input;
    private double[] output;
    public NeuralNetwork() {}

    public NeuralNetwork(int i, NeuralLayer in, NeuralLayer[] h, NeuralLayer o ) {
        inputSize = i;
        inputLayer = in;
        hiddenLayers = h;
        outputLayer = o;
    }

    public NeuralNetwork(int numInputs,int[] sizes, int numOutputs) {
        inputSize = numInputs;
        hiddenLayers = new NeuralLayer[sizes.length];
        if(sizes.length > 0) {
            inputLayer = new NeuralLayer(numInputs, sizes[sizes.length - 1], false, true);
        } else {
            inputLayer = new NeuralLayer(numInputs, numInputs, false, true);
        }
        for(int i = 0; i < sizes.length; i++) {
            if(i == 0)
                hiddenLayers[0] = new NeuralLayer(sizes[0], numInputs, false, false);
            else
                hiddenLayers[i] = new NeuralLayer(sizes[i], sizes[i - 1], false, false);
        }

        if(sizes.length > 0){
            outputLayer = new NeuralLayer(numOutputs, sizes[sizes.length - 1], true, false);
        } else {
            outputLayer = new NeuralLayer ( numOutputs , numInputs , true, false );
        }
    }

    public void BackProp(double[][] outputs, double[] expected) {
        int outputSize = outputLayer.getSize();

        if(outputs[outputs.length - 1].length != outputSize ||
                outputSize != expected.length ) {
            //System.err.println("Error: Invalid Output Size");
            return;
        }

        double[][] oldOutWeights = new double[outputSize][outputLayer.getNumDendrites ()];
        double[][][] oldHiddenWeights = new double[hiddenLayers.length][][];

        double[] errTot_out_outLayer = new double[outputSize];
        double[] out_netIn_outLayer = new double[outputSize];

        for(int n = 0; n < outputSize; n++) {
            double o = outputs[outputs.length - 1][n];
            errTot_out_outLayer[n] = o - expected[n];
            out_netIn_outLayer[n] = o * (1 - o);

            for(int d = 0; d < outputLayer.getNumDendrites (); d++) {
                double netIn_weight_outLayer = outputs[outputs.length - 2][d];
                double errTot_weight_outLayer =
                        errTot_out_outLayer[n]
                                * out_netIn_outLayer[n]
                                * netIn_weight_outLayer;
                double newWeight = outputLayer.getNeuron(n).getWeight(d);
                newWeight -= Learning_Rate * errTot_weight_outLayer;
                oldOutWeights[n][d] = outputLayer.getNeuron ( n ).getWeight ( d );
                outputLayer.setWeight(newWeight, n, d);
            }

            double newBias = outputLayer.getNeuron(n).getBias();
            newBias = Bias_Learning_Rate
                    * errTot_out_outLayer[n]

                    * out_netIn_outLayer[n];
            outputLayer.setBias ( newBias, n );
        }

        double[][] errTot_out = new double[hiddenLayers.length][];
        double[][] out_netIn = new double[hiddenLayers.length][];

        for(int l = hiddenLayers.length - 1; l >= 0; l--) {
            oldHiddenWeights[l] = new double[hiddenLayers[l].getSize()][hiddenLayers[l].getNumDendrites ()];
            errTot_out[l] = new double[hiddenLayers[l].getSize()];
            out_netIn[l] = new double[hiddenLayers[l].getSize()];

            for(int n = 0; n < hiddenLayers[l].getSize(); n++) {
                double o = outputs[1+l][n];
                errTot_out[l][n] = 0;
                out_netIn[l][n] = o * (1 - o);

                if(l == hiddenLayers.length - 1) {
                    for(int a = 0; a < outputLayer.getSize(); a++) {
                        double e_netOut = errTot_out_outLayer[a] * out_netIn_outLayer[a];
                        errTot_out[l][n] *= e_netOut * oldOutWeights[a][n];
                    }
                } else {
                    for(int a = 0; a < hiddenLayers[l+1].getSize(); a++) {
                        double e_netOut = errTot_out[l+1][a] * out_netIn[l+1][a];
                        errTot_out[l][n] *= e_netOut * oldHiddenWeights[l + 1][n][a];
                    }
                }

                for(int d = 0; d < hiddenLayers[l].getNumDendrites (); d++) {
                    double netIn_weigh = outputs[l][d];
                    double errTot_weight = errTot_out[l][n]
                            * out_netIn[l][n]
                            * netIn_weigh;
                    double newWeight = hiddenLayers[l].getNeuron(n).getWeight (d);
                    newWeight -= Learning_Rate * errTot_weight;
                    oldHiddenWeights[l][d][n] = hiddenLayers[l].getNeuron(n).getWeight(d);
                    hiddenLayers[l].setWeight(newWeight, n, d);
                }

                double newBias = hiddenLayers[l].getNeuron(n).getBias();
                newBias -= Bias_Learning_Rate
                        * errTot_out[l][n]
                        * out_netIn[l][n];

                hiddenLayers[l].setBias(newBias, n);
            }
        }
    }

    public double[] Evaluate(double[] input) {
        if(input.length != inputSize)
            return null;

        double[] activationFront = new double[0];
        if(hiddenLayers.length > 0) {
            activationFront = hiddenLayers[0].Evaluate (input);
        }

        for(int i = 1; i < hiddenLayers.length; i++) {
            activationFront = hiddenLayers[i].Evaluate ( activationFront );
        }

        activationFront = outputLayer.Evaluate ( activationFront );

        return activationFront;
    }

    public double[][] FullEvaluate(double[] input) {

        if(input.length != inputSize) {
            return null;
        }

        double[][] allOutputs = new double[2 + hiddenLayers.length][];
        allOutputs[0] = input;

        for(int i = 0; i < hiddenLayers.length; i++) {
            allOutputs[i + 1] = hiddenLayers[i].Evaluate ( input );
            input = allOutputs[i+1];
        }

        allOutputs[hiddenLayers.length + 1] = outputLayer.Evaluate ( input );

        return allOutputs;
    }


    public int getNumLayers() {
        return 2 + hiddenLayers.length;
    }

    public int getInputSize() {
        return inputSize;
    }

    public NeuralLayer[] getHiddenLayers() {
        return hiddenLayers;
    }

    public NeuralLayer getOutputLayer() {
        return outputLayer;
    }

    public NeuralLayer getLayer(int i) {
        if (i == 0)
            return null;
        else if (i <= hiddenLayers.length)
            return hiddenLayers[i - 1];
        else if (i == hiddenLayers.length + 1)
            return outputLayer;
        else
            return null;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < hiddenLayers.length; i++) {
            ret += hiddenLayers[i] + "\n";
        }
        ret += outputLayer.toString();
        return "" + ret;
    }
}
