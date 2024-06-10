public class SolarThermalSystem extends Abstract_HeatGenerator {

    public SolarThermalSystem() {
        //investmenstCosts: 8000 Euro im Durchschnitt für einen 4-Köpfigen Haushalt samt Geräten und Montage
        //energyOutput: 6.000 kWh bei einem 12m² Kollektor und 500kWh pro m² Ertrag im Jahr
        //energyInput: keine, weil nur durch Sonne betrieben
        super(8000,500,0);
    }
}
