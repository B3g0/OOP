package main;

import abstr.Abstract_Quelle;

import java.util.concurrent.ThreadLocalRandom;

public class Regenwasser extends Abstract_Quelle {
    /**
     * Der Konstruktor des Regenwassers
     * @param maxValue der MaxWert pro Stunde, der produziert werden kann
     */
    public Regenwasser(int maxValue) {
        super(maxValue);
    }

    /**
     * Durch Bestimmung einer probability fuer die Regenwahrscheinlichkeit berechnet die Methode die
     * Menge an Regenwasser die anfaellt
     * @param hotd the hour of the day (0 == Midnight, 12 == Lunch, 24 == Midnight again)
     * @return Menge an Regenwasser welches angefallen ist
     */
    @Override
    public int get_Erzeugungsmenge(int hotd) {
        int probability = util.random(0,100);

        if(probability < 80){
            return 0;
        }

        double partOfMax = util.random(0,100);

        return (int)(maxValue * (partOfMax/100));
    }

    @Override
    public String getUnit() {
        return "liters";
    }

}
