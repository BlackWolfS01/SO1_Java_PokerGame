package CasinoRoyaleSim_CS251.ConsoleTests;

public class NeuralNetworkConsoleTest {
    public static void main(String[] args){
        int[] hiddenLayers = {250};
        /*
        NeuralNetwork network = new NeuralNetwork(3*(int)Math.pow(5,3), hiddenLayers, 5);
        Player player = new Player("John Knight", 15000000);
        player.SetupAIEmotionalArrays();
        int square =(int) Math.pow(5,3);
        double[] input = new double[4];
        try{
            for(int i = 0; i < 25; i++) {
                player.updateAggression(Math.random() * 100);
                player.updateDoubt(Math.random() * 100);
                player.updateDespair(Math.random() * 100);
                player.updateConfidence(Math.random()  * 100);

                for(int j = 0; j < input.length; j++) {
                    if(i == 5|| i == 10 || i == 15 || i == 20){
                        input[j] = player.calcEmotions(
                                           player.calcAggression(),
                                            player.calcDoubt(),
                                            player.calcDespair(),
                                            player.calcCondfidence());
                    }
                }
            }


        }catch(Exception e){

        }


        double[] aggression = player.getEmotion_Aggression();
        double[] despair = player.getEmotion_Despair();
        double[] doublt = player.getEmotion_Doubt();
        double[] confidence = player.getEmotion_Confidence();

        int confLength = confidence.length - 1;
        int confLengthOne = confidence.length - 2;
        double confExp  =Math.round( Math.exp(confidence[confLength] - confidence[confLengthOne]));
        System.out.println(confExp);

        //double playerTell  = player.getValueAtIndex(player.getEmotion_Confidence(),sizeOne) - player.getValueAtIndex(player.getEmotion_Confidence(), sizeTwo);
       // System.out.println("Player Tell : " + playerTell);
        //double[] input = new double[square * 3];
      //  for(int i =0; i < input.length; i++)*/
    }
}
