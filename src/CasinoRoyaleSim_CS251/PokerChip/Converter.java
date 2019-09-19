package CasinoRoyaleSim_CS251.PokerChip;

import java.util.*;

public class Converter {
    private final int numberOfChips = 150;
    private final int Value_BlueChip = 500;
    private final int Value_GreenChip  = 5000;
    private final int Value_PinkChip = 25000;
    private final int Value_BlackChip = 100000;
    private final int Value_BluePlaque = 500000;
    private final int Value_RedPlaque = 1000000;
    private int Num_RedPlaques;
    private int Num_BluePlaques;
    private int Num_BlackChips;
    private int Num_PinkChips;
    private int Num_GreenChips;
    private int Num_BlueChips;

    public Converter(int redp, int bluep, int blackc, int pinkc, int greenc, int bluec){
        this.Num_RedPlaques = redp;
        this.Num_BluePlaques = bluep;
        this.Num_BlackChips = blackc;
        this.Num_PinkChips = pinkc;
        this.Num_GreenChips = greenc;
        this.Num_BlueChips = bluec;
    }

    protected int totalChips(){
        return this.Num_BlackChips + this.Num_BluePlaques
                                + Num_BlackChips
                                + Num_PinkChips
                                + Num_GreenChips
                                + Num_BlueChips;
    }

    public Chip[] buildChips() {
        Chip[] redPlaques = new Chip[Num_RedPlaques];
        Chip[] bluePlaques = new Chip[Num_BluePlaques];
        Chip[] blackChips = new Chip[Num_BlackChips];
        Chip[] pinkChips = new Chip[Num_PinkChips];
        Chip[] greenChips = new Chip[Num_GreenChips];
        Chip[] blueChips = new Chip[Num_BlueChips];

        for(int j = 0; j < Num_RedPlaques; j++){
            Chip redPlaque = new Chip(Value_RedPlaque, ChipType.Plaque, ChipColor.Red);
            redPlaques[j] = redPlaque;
        }
        for(int bp = 0; bp < Num_BluePlaques; bp++){
            Chip bluePlaque = new Chip(Value_BluePlaque, ChipType.Plaque, ChipColor.Blue);
            bluePlaques[bp] = bluePlaque;
        }
        for(int blackC = 0; blackC < Num_BlackChips; blackC++){
            Chip blackChip = new Chip(Value_BlackChip, ChipType.Chip, ChipColor.Black);
            blackChips[blackC] = blackChip;
        }
        for(int pinkC = 0; pinkC < Num_PinkChips; pinkC++){
            Chip pinkChip = new Chip(Value_PinkChip, ChipType.Chip, ChipColor.Pink);
            pinkChips[pinkC] = pinkChip;
        }
        for(int greenC = 0; greenC < Num_GreenChips; greenC++){
            Chip greenChip = new Chip(Value_GreenChip, ChipType.Chip, ChipColor.Green);
            greenChips[greenC] = greenChip;
        }
        for(int blueC = 0; blueC < Num_BlueChips; blueC++){
            Chip blueChip = new Chip(Value_BlueChip, ChipType.Chip, ChipColor.Blue);
            blueChips[blueC] = blueChip;
        }
        Stack<Chip> _StackOfChips = new Stack<>();
        List<Chip[]> listOfChips = new ArrayList<>();
        listOfChips.add(redPlaques);
        listOfChips.add(bluePlaques);
        listOfChips.add(blackChips);
        listOfChips.add(pinkChips);
        listOfChips.add(greenChips);
        listOfChips.add(blueChips);
        try{
            for(Chip[] chipArray : listOfChips){
                _StackOfChips = this.mergeChips(_StackOfChips, chipArray);
            }
        }catch(Exception e){ }
        Chip[] ChipsToProvide = new Chip[_StackOfChips.size()];
        for(int i = 0; i < ChipsToProvide.length; i++){
            ChipsToProvide[i] = _StackOfChips.pop();
        }
        return ChipsToProvide;
    }

    public Chip[] ConvertChips(int amount){
        LinkedList<Chip> chips = new LinkedList<>();

            for(int i = 0; i < Num_RedPlaques; i++) {
                Chip newChip = createChip(Value_RedPlaque);
                System.out.println(newChip.toString());
                chips.add(newChip);
            }
            for(int i = 0; i < Num_BluePlaques; i++) {
                Chip newChip = createChip(Value_BluePlaque);
                System.out.println(newChip.toString());
                chips.add(newChip);
            }
            for(int j = 0; j < Num_BlackChips; j++){
                Chip newChip = createChip(Value_BlackChip);
                System.out.println(newChip.toString());
                chips.add(newChip);
            }
            for(int j = 0; j < Num_PinkChips; j++){
                Chip newChip = createChip(Value_PinkChip);
                System.out.println(newChip.toString());
                chips.add(newChip);
            }
            for(int j = 0; j < Num_GreenChips; j++){
                Chip newChip = createChip(Value_GreenChip);
                System.out.println(newChip.toString());
                chips.add(newChip);
            }
            for(int j = 0; j < Num_BlueChips; j++){
                Chip newChip = createChip(Value_BlueChip);
                System.out.println(newChip.toString());
                chips.add(newChip);
            }
        Chip[] newChips = new Chip[chips.size()];
        for(int i = 9; i < chips.size(); i++){
            newChips[i] = chips.get(i);
        }
        chips.clear();
        return newChips;
    }

    private Chip createChip(int chipAmount){
        ChipColor color;
        ChipType type;
        if(chipAmount == Value_RedPlaque) {
            type = ChipType.Plaque;
        } else if(chipAmount == Value_BluePlaque) {
            type = ChipType.Plaque;
        } else{
            type = ChipType.Chip;
        }

        if(type == ChipType.Plaque){
            if(chipAmount == Value_RedPlaque)
                color = ChipColor.Red;
            else {
                color = ChipColor.Blue;
            }
        } else {
            if(chipAmount == Value_BlackChip) {
                color = ChipColor.Black;
            } else if(chipAmount == Value_PinkChip) {
                color = ChipColor.Pink;
            } else if(chipAmount == Value_GreenChip) {
                color = ChipColor.Green;

            } else {
                color = ChipColor.Blue;
            }
        }
        return new Chip(chipAmount, type, color);
    }

    public int getNumberOfChips(){
        return this.numberOfChips;
    }

    private Stack<Chip> mergeChips(Stack<Chip> chips, Chip[] c){
        for(int i = 0; i < c.length; i++){
            chips.push(c[i]);
        }
        return chips;
    }

    public int CashOut(ArrayList<Chip> chips){
        int CashOut_TotalAmount = 0;
        for(Chip chip : chips) {
            CashOut_TotalAmount += chip.getValue();
        }
        return CashOut_TotalAmount;
    }

    private Chip[] mergeArrays(Chip[] a, Chip[] b){
        Chip[] result = new Chip[a.length + b.length];
        for(int i = 0; i < a.length; i++)
            result[i] = a[i];
        for(int j = 0; j < b.length; j++)
            result[j] = b[j];
        return result;
    }
}
