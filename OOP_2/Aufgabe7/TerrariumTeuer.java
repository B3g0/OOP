class TerrariumTeuer extends Vivarium {

    /**
     * Erzeugt ein neues TerrariumTeuer
     * @param breite siehe {@link Vivarium#Vivarium(int, int, int)}
     * @param hoehe siehe {@link Vivarium#Vivarium(int, int, int)}
     * @param laenge siehe {@link Vivarium#Vivarium(int, int, int)}
     */
    public TerrariumTeuer(int breite, int hoehe, int laenge) {
        super(breite, hoehe, laenge);
    }

    /**
     * Besetze ein TerrariumBillig mit einem Versuchstierschwarm
     * VB: versuchstierSchwarm != null
     * NB: versuchstierSchwarm wird ins Terrarium gesetzt, sofern es geeignet ist.
     * @param versuchstierschwarm Objekt der Klasse Versuchstierschwarm, != null
     * @param alternativ true falls andere Vivarien erlaubt, false sonst
     * @return wenn besetzt, false wenn nicht
     */
    @Override
    public boolean besetze(Versuchstierschwarm versuchstierschwarm, boolean alternativ) {
        return versuchstierschwarm.setzeInTerrarium(this, alternativ);
    }

    /**
     * Gibt eine String-Darstellung des TerrariumTeuer zurueck
     * @return eine String-Darstellung des TerrariumTeuer
     */
    public String toString() {
        return "Terrarium Teuer " + super.toString();
    }

}
