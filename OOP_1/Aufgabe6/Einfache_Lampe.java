public class Einfache_Lampe extends Lampe {

    /*
    Enum Farbe, da die einfache Lampe genau 3 Farben haben kann
     */
    enum Farbe {
        weiss, rot, blau
    }
    private Farbe farbe;

    /**
     * Konstruktor der Klasse Einfache_Lampe
     * @param nummer int, Nummer mit der die Lampe dann eindeutig indentifizierbar ist
     * @param leuchtmittel Leuchtmittel, das sich in der Lampe befindet, kann nachher augetauscht werden
     * @param farbe enum Farbe, beschreibt die Farbe der Lampe, kann nachher nicht mehr veraendert werden
     */
    public Einfache_Lampe(int nummer, Leuchtmittel leuchtmittel, Farbe farbe){
        super(nummer, leuchtmittel);
        this.farbe = farbe;
    }

    /**
     * Getter-Methoder der Farbe der Lampe
     * @return enum Farbe
     */
    public Farbe getFarbe() {
        return farbe;
    }

    /**
     * Override von toString, um einen sinnvollen Text ausgeben zu koennen
     * @return den String, der die einfache Lampe darstellt
     */
    public String toString() {
        return "Einfache Lampe: " + getNummer() + ", Einschaltdauer: " + getDauer() + ", Farbe: "+ getFarbe() +  ", Leuchtmittel: " + getLeuchtmittel();
    }
}
