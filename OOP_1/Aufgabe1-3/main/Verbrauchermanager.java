package main;

import abstr.Abstract_Verbraucher;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//GUT: Der Manager kann jeden moeglichen Verbaucher absorbieren und verarbeiten

public class Verbrauchermanager extends Abstract_Verbraucher {
    private List<Abstract_Verbraucher> list;

    /**
     * Konstruktor des Verbrauchermanagers - es wird kein Speicher benoetigt, da dieser fuer jeden angegebenen Verbraucher
     * einzeln bestimmt werden muss
     */
    public Verbrauchermanager() {
        super(null);
        list = new LinkedList<>();
    }

    /**
     * Vorbedingung: 0 <= hotd <= 24
     * Nachbedingung: Methode geht alle Verbraucher in der Liste durch und nutzt hotd um den entsprechenden Verbrauch
     * simulieren zu koennen
     * @param hotd the hour of the day (0 == Midnight, 12 == Lunch, 24 == Midnight again)
     * @throws SpeicherException
     */
    @Override
    public void use(int hotd) throws SpeicherException {
        for(Abstract_Verbraucher verbraucher : list){
            verbraucher.use(hotd);
        }
    }

    /**
     * Getter der Liste von Verbrauchern
     * @return Liste von Verbrauchern
     */
    public List<Abstract_Verbraucher> getList(){
        return list;
    }

    /**
     * Simuliert ein technisches Problem, das verschiedene Verbraucher permanent ausfallen laesst. Dies tritt pro
     * Verbraucher mit 0.1% Wahrscheinlichkeit auf
     * @return Wenn mindestens ein Verbraucher entfernt wurde, True. Sonst False.
     */
    public boolean technicalProblemSimulation(){
        return list.removeIf(abstract_verbraucher -> util.random(0,1000)<=1);
    }

}
