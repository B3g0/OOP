abstract class Versuchstierschwarm {

    /**
     * Die Lebensdauer des Versuchstierschwarms in Wochen
     */
    private final int lebensdauer;
    /**
     * Das Alter des Versuchstierschwarms in Wochen
     */
    private int alter = 1;

    /**
     * Erstellt einen neuen Versuchstierschwarm mit angegebener Lebensdauer
     * VB: lebensdauer > 0
     * @param lebensdauer Die Lebensdauer des Versuchstierschwarms
     */
    public Versuchstierschwarm(int lebensdauer) {
        this.lebensdauer = lebensdauer;
    }

    /**
     * Gibt an ob das Tier bereits verstorben ist
     * @return ob das Tier bereits verstorben ist.
     */
    public boolean bereitsVerstorben() {
        return alter >= lebensdauer;
    }

    /**
     * Setze Versuchstierschwarm in TerrariumTeuer
     * VB: terrariumTeuer != null
     * NB: Setze Tiere ins Terrarium, sofern sie geeignet sind. Ansonsten retourniere false und mache nichts weiter.
     * @param terrariumTeuer Objekt von Vivarium
     * @param alternativ true falls auch dieses anstatt von TerrariumBillig besetzt werden darf, false andernfalls
     * @return true falls besetzt, false sonst
     */
    public abstract boolean setzeInTerrarium(TerrariumTeuer terrariumTeuer, boolean alternativ);

    /**
     * Setze Versuchstierschwarm in TerrariumBillig
     * VB: terrariumBillig != null
     * NB: Setze Tiere ins Terrarium, sofern sie geeignet sind. Ansonsten retourniere false und mache nichts weiter.
     * @param terrariumBillig Objekt von Vivarium
     * @return true falls besetzt, false sonst
     */
    public abstract boolean setzeInTerrarium(TerrariumBillig terrariumBillig);

    /**
     * Setze Versuchstierschwarm in AquariumBillig
     * VB: aquariumBillig != null
     * NB: Setze Tiere ins Aquarium, sofern sie geeignet sind. Ansonsten retourniere false und mache nichts weiter.
     * @param aquariumBillig Objekt von Vivarium
     * @return true falls besetzt, false sonst
     */
    public abstract boolean setzeInAquarium(AquariumBillig aquariumBillig);

    /**
     * Setze Versuchstierschwarm in AquariumTeuer
     * VB: aquariumTeuer != null
     * NB: Setze Tiere ins Aquarium, sofern sie geeignet sind. Ansonsten retourniere false und mache nichts weiter.
     * @param aquariumTeuer Objekt von Vivarium
     * @param alternativ true falls auch dieses anstatt von AquariumBillig besetzt werden darf, false andernfalls
     * @return true falls besetzt, false sonst
     */
    public abstract boolean setzeInAquarium(AquariumTeuer aquariumTeuer, boolean alternativ);

    /**
     * Laesst den Versuchstierschwarm altern.
     * Falls der Schwarm seine Lebensdauer erreicht, stirbt er.
     * Liefert true wenn der Schwarm gestorben ist, false andernfalls
     *
     * VB: Der Schwarm ist noch nicht verstorben
     * NB: Der Schwarm ist um 1 Woche aelter oder verstorben.
     * @return true wenn der Schwarm gestorben ist, false andernfalls
     */
    public boolean aelter() {
        if (this.alter == this.lebensdauer) {
            System.out.println(toString() + " verstorben");
            return true;
        } else {
            this.alter++;
            return false;
        }
    }
}
