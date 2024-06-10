public class WindTurbine extends Abstract_EnergyGenerator {

    public WindTurbine() {
        //Investment Cost = 15000 - Laut windustry.org: von 3000 bis 1.3m ist alles dabei -> 15000 als ungefähre untere Mitte
        //Energy Ouptut = 3000 - Laut google - von 2000 bis 4000 pro "kleineres" Gerät
        //Energy Input = 0 - Nur Windkraft, keine weitere Energie vonnöten
        super(15000, 3000, 0);
    }
}
