public class HeatPump extends Abstract_HeatGenerator {

    public HeatPump() {
        //investmentCosts = 16.000 Euro laut Google für den durchschnittlichen Haushalt samt Geräten und Montage
        //energyOutput = 5.840 kWh pro Jahr bei einem 4-Personen-Haushalt und 4kWh pro Person und Tag
        //energyInput = 10.000 kWh pro Jahr für den Durchschnittlichen Haushalt laut Google (5000 je Warmwasser und Heizung)
        super(16000, 5840, 10000);
    }



}
