class AquariumBillig extends Vivarium {

    /**
     * Erzeugt ein neues AquariumBillig
     * @param breite siehe {@link Vivarium#Vivarium(int, int, int)}
     * @param hoehe siehe {@link Vivarium#Vivarium(int, int, int)}
     * @param laenge siehe {@link Vivarium#Vivarium(int, int, int)}
     */
    public AquariumBillig(int breite, int hoehe, int laenge) {
        super(breite, hoehe, laenge);
    }

    /**
     * Besetze ein AquariumBillig mit einem Versuchstierschwarm
     * VB: versuchstierSchwarm != null
     * NB: versuchstierSchwarm wird ins Aquarium gesetzt, sofern es geeignet ist.
     * @param versuchstierschwarm Objekt der Klasse Versuchstierschwarm, != null
     * @param alternativ true falls andere Vivarien erlaubt, false sonst
     * @return wenn besetzt, false wenn nicht
     */
    @Override
    public boolean besetze(Versuchstierschwarm versuchstierschwarm, boolean alternativ) {
        return versuchstierschwarm.setzeInAquarium(this);
    }

    public String toString() {
        return "Aquarium Billig " + super.toString();
    }

}
