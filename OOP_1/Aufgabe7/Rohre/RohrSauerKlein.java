package Rohre;

import Rest.Lager;

public class RohrSauerKlein extends Rohr {

    /**
     * Konstruktor der Klasse RohrsauerKlein
     * @param len Länge des Rohres in Centimeter
     * @param priceCent Preis des Rohres in Cent
     */
    public RohrSauerKlein(double len, int priceCent) {
        super(len, priceCent);
    }

    /**
     * Lagert das entsprechende Rohr in das übergebene Lager
     * VB: lager != null
     * NB: Rohr der Art SauerKlein wird in lager eingelagert
     */
    @Override
    public void wirdEingelagert(Lager lager) {
        lager.getSauerKlein().add(this);
    }

    /**
     * toString-Methode für Säurebeständiges Rohr um eine sinnvolle Ausgabe bei Testfällen zu erzeugen
     */
    @Override
    public String toString() {
        return "Saeurebestaendiges Rohr - kleiner Temperaturbereich mit einer Laenge von " + this.getLength() + "cm und einem Wert von " + this.getPriceCent() + " Cent";
    }
}
