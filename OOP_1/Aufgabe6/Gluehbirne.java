public class Gluehbirne extends Leuchtmittel {
    private int maxTemp;

    /**
     * Konstruktor für die Klasse Gluehbirne
     * @param watt int-Wert für die Anzahl der Watt die die Gluehbirne dann haben soll
     * @param maxTemp int-Wert für die maximale Temperatur die die Gluehbirne während des Betriebes erreicht
     */
    public Gluehbirne(int watt, int maxTemp) {
        super(watt);
        this.maxTemp = maxTemp;
    }

    /**
     * Getter-Methode für die maxTemp der Gluehbirne
     * @return int
     */
    public int getMaxTemp() {
        return maxTemp;
    }

    /**
     * toString-Methode für den Output der Info-Zeile für die jeweilige Gluehbirne
     * @return String
     */
    @Override
    public String toString() {
        return "Gluehbirne mit " + getWatt() + " Watt und " + getMaxTemp() + "° maximaler Temperatur";
    }
}
