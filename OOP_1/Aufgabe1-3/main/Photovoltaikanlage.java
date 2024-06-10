package main;

import abstr.Abstract_Quelle;

public class Photovoltaikanlage extends Abstract_Quelle {

    /**
     * Konstruktor der Photovoltaikanlage. Nach Abstract_Quelle muss ein MaxValue uebergeben werden.
     * @param maxValue der Max Value
     */
    public Photovoltaikanlage(int maxValue) {
        super(maxValue);
    }

    /**
     * Vorbedingung: 0 <= hotd <= 24
     * Nachbedingung: Je nach hotd wird entweder keine (hour <= 6 || hour >= 18) oder eine gewisse Menge an Energie erzeugt
     * @param hotd the hour of the day (0 == Midnight, 12 == Lunch, 24 == Midnight again)
     * @return Erzeugungsmenge der Photovoltaikanlage
     */
    @Override
    public int get_Erzeugungsmenge(int hotd){
        if (Failure.is_failing()) {
            System.out.println("Systemausfall Photovoltaikanlage!");
            return 0;
        }
        int hour = hotd%24;

        if(hour <= 6 || hour >=18){
            return 0;
        }

        return util.random((int)(get_Maxvalue() * 0.1), get_Maxvalue());


    }

    @Override
    public String getUnit() {
        return "kWh";
    }
}
