package Rohre;

import Rest.Lager;

public abstract class Rohr {
    private double length;
    private int priceCent;

    /**
     * Konstruktor der Klasse Rohr
     * @param len Länge des Rohres in Centimeter
     * @param priceCent Preis pro Rohr in Cent
     */
    public Rohr(double len, int priceCent){
        length = len;
        this.priceCent = priceCent;
    }

    /**
     * Getter für die Länge des Rohres
     * @return Länge des Rohres in Centimeter
     */
    public double getLength() {
        return length;
    }

    /**
     * Getter für den Preis des Rohres
     * @return Preis des Rohres in Cent
     */
    public int getPriceCent() {
        return priceCent;
    }

    /**
     * Abstrakte Methode für das Einlagern von Rohren in das gewählte Lager
     * VB: lager != null
     * NB: Rohr wird in lager eingelagert
     */
    public abstract void wirdEingelagert(Lager lager);

}
