package CasinoRoyaleSim_CS251.Disks;

public abstract class Disk {
    private String name;
    private DiskType type;

    public Disk(String name, DiskType type){
        this.name = name;
        this.type = type;
    }


}
