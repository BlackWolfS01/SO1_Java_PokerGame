package CasinoRoyaleSim_CS251.Action;
public interface Actions {
   Action Raise = new Raise ();
   Action Check = new Check();
   Action Fold = new Fold();
   Action Call = new Call();
   Action All_In = new All_In();
}
