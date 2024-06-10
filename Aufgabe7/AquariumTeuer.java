class AquariumTeuer extends Vivarium {

    /**
     * Erzeugt ein neues AquariumTeuer
     * @param breite siehe {@link Vivarium#Vivarium(int, int, int)}
     * @param hoehe siehe {@link Vivarium#Vivarium(int, int, int)}
     * @param laenge siehe {@link Vivarium#Vivarium(int, int, int)}
     */
    public AquariumTeuer(int breite, int hoehe, int laenge) {
        super(breite, hoehe, laenge);
    }

    /**
     * Besetze ein AquariumTeuer mit einem Versuchstierschwarm
     * VB: versuchstierSchwarm != null
     * NB: versuchstierSchwarm wird ins Aquarium gesetzt, sofern es geeignet ist.
     * @param versuchstierschwarm Objekt der Klasse Versuchstierschwarm, != null
     * @param alternativ true falls andere Vivarien erlaubt, false sonst
     * @return wenn besetzt, false wenn nicht
     */
    @Override
    public boolean besetze(Versuchstierschwarm versuchstierschwarm, boolean alternativ) {
        return versuchstierschwarm.setzeInAquarium(this, alternativ);
    }

    /**
     * Gibt eine String-Darstellung des AquariumTeuer zurueck
     * @return eine String-Darstellung des AquariumTeuer
     */
    public String toString() {
        return "Aquarium Teuer " + super.toString();
    }

}
