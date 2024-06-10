public class LED extends Leuchtmittel {
    private double lumen;

    /**
     * Konstruktor für die Klasse LED
     * @param watt Die Anzahl an Watt, die die LED haben soll. Dieser Wert ist nachher nicht aenderbar
     * @param lumen Die Lumen, die die LED haben soll. Dieser Wert ist nachher nicht aenderbar
     */
    public LED(int watt, double lumen) {
        super(watt);
        this.lumen = lumen;
    }

    /**
     * Getter Funktion für Lumen
     * @return Lumen, die dem Konstruktor uebergeben wurden
     */
    public double getLumen() {
        return lumen;
    }

    /**
     * Override von toString, um einen sinnvollen Text ausgeben zu koennen
     * @return den String, der die LED darstellt
     */
    @Override
    public String toString() {
        return "LED  mit " + getWatt() + " Watt und " + getLumen() + " Lumen";
    }
}
