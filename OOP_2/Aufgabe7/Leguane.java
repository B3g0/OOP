class Leguane extends Versuchstierschwarm {

    /**
     * Erstellt einen neuen Leguanschwarm mit angegebener Lebensdauer
     * @param lebensdauer Die Lebensdauer des Leguanschwarm
     */
    public Leguane(int lebensdauer) {
        super(lebensdauer);
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInTerrarium(TerrariumTeuer, boolean)}
     * @param terrariumTeuer Objekt von Vivarium
     * @param alternativ true falls auch dieses anstatt von TerrariumBillig besetzt werden darf, false andernfalls
     */
    @Override
    public boolean setzeInTerrarium(TerrariumTeuer terrariumTeuer, boolean alternativ) {
        terrariumTeuer.setSchwarm(this);
        return true;
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInTerrarium(TerrariumBillig)}
     * @param terrariumBillig Objekt von Vivarium
     */
    @Override
    public boolean setzeInTerrarium(TerrariumBillig terrariumBillig) {
        return false;
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInAquarium(AquariumBillig)}
     * @param aquariumBillig Objekt von Vivarium
     */
    @Override
    public boolean setzeInAquarium(AquariumBillig aquariumBillig) {
        return false;
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInAquarium(AquariumTeuer, boolean)}
     * @param aquariumTeuer Objekt von Vivarium
     * @param alternativ true falls auch dieses anstatt von AquariumBillig besetzt werden darf, false andernfalls
     */
    @Override
    public boolean setzeInAquarium(AquariumTeuer aquariumTeuer, boolean alternativ) {
        return false;
    }

    /**
     * siehe {@link Versuchstierschwarm#toString()}
     */
    public String toString() {
        return "Leguane";
    }
}
