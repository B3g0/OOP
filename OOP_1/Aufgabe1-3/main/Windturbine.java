package main;


import abstr.Abstract_Quelle;

import java.util.concurrent.ThreadLocalRandom;

public class Windturbine extends Abstract_Quelle{

    public Windturbine(int maxValue) {
        super(maxValue);
    }

    @Override
    public int get_Erzeugungsmenge(int hotd) {
        int windStrenght = exp(util.random(0,100));

        return get_Maxvalue() * (windStrenght/100);
    }


    @Override
    public String getUnit() {
        return "km/h";
    }

    /**
     * Bei Werten zwischen 1 und 100 werden Werte, die ca zwischen 1 und 100 liegen zurueckgegegeben, allerdings
     * exponentiell verschoben.
     * @param x Wert zwischen 1 und 100, der exponentiell verschoben werden soll
     * @return ein neuer Wert zwischen 1 und 100, der exponentiell verschoben wurde
     */
    private int exp(double x){
        return (int)Math.pow(1.047,x) -1;
    }
}
