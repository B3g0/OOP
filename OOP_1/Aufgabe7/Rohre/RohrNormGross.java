package Rohre;

import Rest.Lager;

public class RohrNormGross extends Rohr {

    /**
     * Konstruktor für RohrNormGross
     * @param len Länge des Rohres in Centimenter
     * @param priceCent Preis des Rohres in Cent
     */
    public RohrNormGross(double len, int priceCent) {
        super(len, priceCent);
    }

    /**
     * Lagert das entsprechende Rohr in das übergebene Lager
     * VB: lager != null
     * NB: Rohr der Art NormGross wird in lager eingelagert
     */
    @Override
    public void wirdEingelagert(Lager lager) {
        lager.getNormGross().add(this);
    }

    /**
     * toString-Methode für Normales Rohr um eine sinnvolle Ausgabe bei Testfällen zu erzeugen
     */
    @Override
    public String toString() {
        return "Normales Rohr - grosser Temperaturbereich mit einer Laenge von " + this.getLength() + "cm und einem Wert von " + this.getPriceCent() + " Cent";
    }
}
