public abstract class Lampe {
    private int nummer;
    private int dauer;
    private Leuchtmittel leuchtmittel;

    /**
     * Konstrukor der Klasse Lampe
     * @param nummer int, Nummer mit der die Lampe dann eindeutig indentifizierbar ist
     * @param leuchtmittel Leuchtmittel, das sich in der Lampe befindet, kann spaeter augetauscht werden.
     */
    public Lampe(int nummer, Leuchtmittel leuchtmittel){
        this.nummer = nummer;
        this.dauer = 0;
        this.leuchtmittel = leuchtmittel;
    }

    /**
     * Getter für die Nummer der Lampe
     * @return int
     */
    public int getNummer() {
        return nummer;
    }

    /**
     * Getter für die Dauer des Nutzung der Lampe
     * @return int
     */
    public int getDauer() {
        return dauer;
    }

    /**
     * Setter um die neue Dauer der Lampe festzulegen
     * VB: dauer != null
     * NB: dauer ist die neue dauer der Lampe
     * @param dauer int
     */
    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    /**
     * Getter um das Leuchtmittel der Lampe zu erlangen
     * @return Leuchtmittel
     */
    public Leuchtmittel getLeuchtmittel() {
        return leuchtmittel;
    }

    /**
     * Setter um einer Lampe ein neues Leuchtmittel zuzuweisen, dabei wird die Dauer zurückgesetzt
     * VB: leuchtmittel != null
     * NB: leuchtmittel wird das neue leuchtmittel der Lampe und dauer = 0
     * @param leuchtmittel Leuchtmittel
     */
    public void setLeuchtmittel(Leuchtmittel leuchtmittel) {
        this.leuchtmittel = leuchtmittel;
        dauer = 0;
    }

    /**
     * toString die die Daten der jeweiligen Lampe printet
     * @return String
     */
    @Override
    public String toString() {
        return "Lampe: " + getNummer() + ", Einschaltdauer: " + getDauer() + ", Leuchtmittel: " + getLeuchtmittel();
    }
}
