package main;

//GUT: Der Manager kann jede moegliche Quelle absorbieren und verarbeiten
//SCHLECHT: Die Unit wird von der ersten Quelle genommen. Wenn verschiedene Quellen verwendet werden, ist dies nicht
//mehr aussagekraeftig

import abstr.Abstract_Quelle;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Quellenmanager extends Abstract_Quelle {
    private List<Abstract_Quelle> sources;

    /**
     * Der Konstruktor des Quellenmanagers. Es wird kein MaxValue benoetigt, weil dieser aus allen angegebenen Quellen
     * errechnet wird.
     */
    public Quellenmanager(){
        super(0);
        sources = new LinkedList<>();
    }

    /**
     * Getter der Sources Liste. Hier wird nur ein Pointer uebergeben, daher werden alle Aenderungen berruecksichtigt!
     * @return eine Liste der Quellen
     */
    public List<Abstract_Quelle> getSources(){
        return sources;
    }

    @Override
    public int get_Erzeugungsmenge(int hotd) {
        int res = 0;
        for(Abstract_Quelle source : sources){
            res+=source.get_Erzeugungsmenge(hotd);
        }
        return res;
    }

    @Override
    public int get_Maxvalue() {
        int res = 0;
        for(Abstract_Quelle source : sources){
            res+=source.get_Maxvalue();
        }
        return res;
    }

    @Override
    public String getUnit() {
        if(sources.isEmpty())
            return null;
        else
            return sources.get(0).getUnit();

    }

    /**
     * Simuliert ein technisches Problem, das verschiedene Sources permanent ausfallen laesst. Dies tritt pro Quelle
     * mit 0.1% Wahrscheinlichkeit auf
     * @return Wenn mindestens eine Quelle entfernt wurde, True. Sonst False.
     */
    public boolean technicalProblemSimulation(){
        return sources.removeIf(abstract_quelle -> util.random(0,1000)<=1);
    }

}
