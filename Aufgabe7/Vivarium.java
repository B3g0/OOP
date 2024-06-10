abstract class Vivarium {

    /**
     * Breite in cm, > 0
     */
    private final int breite;
    /**
     * Hoehe in cm, > 0
     */
    private final int hoehe;
    /**
     * Laenge in cm, > 0
     */
    private final int laenge;

    /**
     * Den Versuchstierschwarm, der sich im Vivarium befindet.
     */
    private Versuchstierschwarm schwarm;

    /**
     * Erzeugt ein neues Vivarium mit gegebenem Volumen
     * VB: breite, hoehe, laenge > 0
     * @param breite siehe Klassenvariablen
     * @param hoehe siehe Klassenvariablen
     * @param laenge siehe Klassenvariablen
     */
    public Vivarium(int breite, int hoehe, int laenge) {
        this.breite = breite;
        this.hoehe = hoehe;
        this.laenge = laenge;
    }
    /**
     * Gibt das Volumen in Kubikcentimetern zurück
     * @return float volumen des Objekts Vivarium
     */
    public int volumen() {
        return (breite * laenge * hoehe);
    }

    public Versuchstierschwarm getSchwarm() {
        return schwarm;
    }

    /**
     * Setzt den Schwarm, der sich im Vivarium befindet.
     * VB: schwarm != null
     * VB: schwarm befindet sich in noch keinem Vivarium.
     * NB: schwarm befindet sich in diesem Vivarium.
     * @param schwarm Der Schwarm, der sich im Vivarium befindet.
     */
    public void setSchwarm(Versuchstierschwarm schwarm) {
        this.schwarm = schwarm;
    }

    /**
     * Besetze ein Vivarium mit einem Versuchstierschwarm
     * VB: versuchstierschwarm != null
     * @param versuchstierschwarm Objekt der Klasse Versuchstierschwarm, != null
     * @param alternativ true falls andere Vivarien erlaubt, false sonst
     * @return true wenn besetzt, false wenn nicht
     */
    public abstract boolean besetze(Versuchstierschwarm versuchstierschwarm, boolean alternativ);

    /**
     * Gibt eine String-Darstellung des Vivariums zurück
     * @return Eine String-Darstellung des Vivariums
     */
    public String toString() {
        return "[Vivarium - Volumen: " + volumen() + " cm^3]";
    }
}
