public class Dimmbare_Lampe extends Lampe {
    private double dimmfaktor;

    /**
     * Konstruktor der Klasse Einfache_Lampe
     * @param nummer int, Nummer mit der die Lampe dann eindeutig indentifizierbar ist
     * @param leuchtmittel Leuchtmittel, das sich in der Lampe befindet, kann nachher augetauscht werden
     * @param dimmfaktor double, beschreibt den Dimmfaktor der Lampe, kann nachher veraendert werden
     */
    public Dimmbare_Lampe(int nummer, Leuchtmittel leuchtmittel, double dimmfaktor) {
        super(nummer, leuchtmittel);
        this.dimmfaktor = dimmfaktor;
    }

    /**
     * Getter-Methode für den Dimmfaktor
     * @return double
     */
    public double getDimmfaktor() {
        return dimmfaktor;
    }

    /**
     * Setter-Methode für den Dimmfaktor
     * @param dimmfaktor double, gibt den neuen Dimmfaktor an
     */
    public void setDimmfaktor(double dimmfaktor) {
        this.dimmfaktor = dimmfaktor;
    }

    /**
     * Override von toString, um einen sinnvollen Text ausgeben zu koennen
     * @return den String, der die dimmbare Lampe darstellt
     */
    @Override
    public String toString() {
        return "Dimmbare Lampe: " + getNummer() + ", Einschaltdauer: " + getDauer() + ", Dimmfaktor: "+ getDimmfaktor() +  ", Leuchtmittel: " + getLeuchtmittel();
    }
}
