package Rohre;

import Rest.Lager;

public class RohrNormKlein extends Rohr {

    /**
     * Konstruktor der Klasse RohrNormKlein
     * @param len Länge des Rohres in Centimeter
     * @param priceCent Preis des Rohres in Cent
     */
    public RohrNormKlein(double len, int priceCent) {
        super(len, priceCent);
    }

    /**
     * Lagert das entsprechende Rohr in das übergebene Lager
     * VB: lager != null
     * NB: Rohr der Art NormKlein wird in lager eingelagert
     */
    @Override
    public void wirdEingelagert(Lager lager) {
        lager.getNormKlein().add(this);
    }

    /**
     * toString-Methode für Normales Rohr um eine sinnvolle Ausgabe bei Testfällen zu erzeugen
     */
    @Override
    public String toString() {
        return "Normales Rohr - kleiner Temperaturbereich mit einer Laenge von " + this.getLength() + "cm und einem Wert von " + this.getPriceCent() + " Cent";
    }
}
