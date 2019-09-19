package CasinoRoyaleSim_CS251.NeuralNetworkSource;
/*Developer Notes:
Fuzzy Inference system. Modeled after the system originially outlined in Mat Buckland's
book, Programming Game AI by Example. Though, it was written in C++, instead of Java.
*/
public class FuzzyLogic
{
    public FuzzyLogic() {}

    public double FuzzyInference(double x, double x0, double x1, double x2, double x3) {
        if(x <= x0 || x >= x3) {
            return 0;
        }
        else if(x >= x1 && x <= x2) {
            return 1;
        }
        else if ((x > x0) && (x < x1)) {
            return (x - x0) / (x1 - x0);
        }
        else {
            return (x3 - x) / (x3 - x2);
        }
    }
}
