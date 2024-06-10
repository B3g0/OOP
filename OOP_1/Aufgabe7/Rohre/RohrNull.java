package Rohre;

import Rest.Lager;

/**
 * Die Klasse RohrNull wird nicht für Testzwecke benötigt, sondern um zu gewährleisten, dass
 * der Stack keine null-Werte übergeben muss.
 * So wird auch sichergestellt, dass man nicht if(rohr == null) abfragen muss
 */
public class RohrNull extends Rohr {

    /**
     * Konstruktor der Klasse RohrNull
     */
    public RohrNull() {
        super(0,0);
    }

    /**
     * Methode zum Einlager in lager
     * VB: lager != null
     * NB: Rohr wird in entsprechendes Lager gelagert
     */
    @Override
    public void wirdEingelagert(Lager lager) {

    }
}
