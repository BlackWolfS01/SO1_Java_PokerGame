
package CasinoRoyaleSim_CS251.Action;

import CasinoRoyaleSim_CS251.Analysis;
import CasinoRoyaleSim_CS251.UtilInterface;

public abstract class Action implements UtilInterface, Analysis {
    protected String name;
    protected String verb;

    public Action(String name, String verb){
        this.name = name;
        this.verb = verb;
    }
    public abstract String actionDescription();

    public String getName() { return name; }
    public String getVerb() { return verb; }
    @Override
    public String toString() {return name;}
}
