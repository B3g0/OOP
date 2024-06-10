public class PhotovoltaicSystem extends Abstract_EnergyGenerator {

    public PhotovoltaicSystem() {
        //Investment Cost = 5500 - Laut Google: 5500 für Privathaushalt Richtwert
        //Energy Ouptut = 1300 - Durchschnittlich laut Google 1.100  und 1.400 kWh pro m^2 im Jahr
        //Energy Input = 0 - Nur Sonnenenergie, keine weitere Energie vonnöten
        super(5500, 1300, 0);
    }
}
