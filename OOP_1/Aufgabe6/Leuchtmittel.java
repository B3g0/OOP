public abstract class Leuchtmittel {
    private int watt;

    /**
     * Konstruktor der Klasse Leuchtmittel
     * @param watt Die Anzahl an Watt. Dieser Wert ist nachher nicht aenderbar.
     */
    public Leuchtmittel(int watt) {
        this.watt = watt;
    }

    /**
     * Getter von Watt
     * @return Anzahl Watt, das beim Konstruktor verwendet wurde
     */
    public int getWatt() {
        return watt;
    }

    /**
     * Diese toString Funktion wird nie verwendet, weil jede Implementation von Leuchtmittel einen eigenen toString
     * besitzt. Sie wurde aber fuer good-measures eingeführt.
     * @return ein String, der das Leuchtmittel identifiziert
     */
    @Override
    public String toString() {
        return "Leuchtmittel mit " + getWatt() + " Watt";
    }

}
