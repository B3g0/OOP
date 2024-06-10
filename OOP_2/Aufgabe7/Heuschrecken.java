class Heuschrecken extends Versuchstierschwarm {

    /**
     * Erstellt einen neuen Heuschreckenschwarm mit angegebener Lebensdauer
     * @param lebensdauer Die Lebensdauer des Heuschreckenschwarm
     */
    public Heuschrecken(int lebensdauer) {
        super(lebensdauer);
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInTerrarium(TerrariumTeuer, boolean)}
     * @param terrariumTeuer Objekt von Vivarium
     * @param alternativ true falls auch dieses anstatt von TerrariumBillig besetzt werden darf, false andernfalls
     */
    @Override
    public boolean setzeInTerrarium(TerrariumTeuer terrariumTeuer, boolean alternativ) {
        if(alternativ) {
            terrariumTeuer.setSchwarm(this);
            return true;
        }
        return false;
    }

    /**
     * siehe {@link Versuchstierschwarm#setzeInTerrarium(TerrariumBillig)}
     * @param terrariumBillig Objekt von Vivarium
     */
    @Override
    public boolean setzeInTerrarium(TerrariumBillig terrariumBillig) {
        terrariumBillig.setSchwarm(this);
        return true;
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
        return "Heuschrecken";
    }

}
