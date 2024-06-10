package Rohre;

import Rest.Lager;

public class RohrSauerGross extends Rohr {

    /**
     * Konstruktor der Klasse RohrSauerGross
     * @param len Länge des Rohres in Centimeter
     * @param priceCent Preis des Rohres in Cent
     */
    public RohrSauerGross(double len, int priceCent) {
        super(len, priceCent);
    }

    /**
     * Lagert das entsprechende Rohr in das übergebene Lager
     * VB: lager != null
     * NB: Rohr der Art SauerGross wird in lager eingelagert
     */
    @Override
    public void wirdEingelagert(Lager lager) {
        lager.getSauerGross().add(this);
    }

    /**
     * toString-Methode für Säurebeständiges Rohr um eine sinnvolle Ausgabe bei Testfällen zu erzeugen
     */
    @Override
    public String toString() {
        return "Saeurebestaendiges Rohr - grosser Temperaturbereich mit einer Laenge von " + this.getLength() + "cm und einem Wert von " + this.getPriceCent() + " Cent";
    }
}
