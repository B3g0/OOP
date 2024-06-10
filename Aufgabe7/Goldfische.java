class Goldfische extends Versuchstierschwarm {

    /**
     * Erstellt einen neuen Goldfischschwarm mit angegebener Lebensdauer
     * @param lebensdauer Die Lebensdauer des Goldfischschwarm
     */
    public Goldfische(int lebensdauer) {
        super(lebensdauer);
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInTerrarium(TerrariumTeuer, boolean)}
     * @param terrariumTeuer Objekt von Vivarium
     * @param alternativ true falls auch dieses anstatt von TerrariumBillig besetzt werden darf, false andernfalls
     */
    @Override
    public boolean setzeInTerrarium(TerrariumTeuer terrariumTeuer, boolean alternativ) {
        return false;
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
        aquariumBillig.setSchwarm(this);
        return true;
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInAquarium(AquariumTeuer, boolean)}
     * @param aquariumTeuer Objekt von Vivarium
     * @param alternativ true falls auch dieses anstatt von AquariumBillig besetzt werden darf, false andernfalls
     */
    @Override
    public boolean setzeInAquarium(AquariumTeuer aquariumTeuer, boolean alternativ) {
        if(alternativ) {
            aquariumTeuer.setSchwarm(this);
            return true;
        }
        return false;
    }

    /**
     * siehe {@link Versuchstierschwarm#toString()}
     */
    public String toString() {
        return "Goldfische";
    }
}
