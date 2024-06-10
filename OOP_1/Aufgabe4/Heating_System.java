public class Heating_System extends Abstract_Cogeneration_Unit {

    public Heating_System() {
        //investment Cost: 2000 Anschaffung und ca 1000 Montage durchschnittlich
        //priceOfMaterialPerLitre: 300 pro m^3 Holz --> 0.3 pro dm^3 = l
        //amountOfMaterialInLitre: 10 m^3 Holz/Jahr --> 10 000 dm^3 Holz/Jahr = l
        //calorificValuePerLitre: 4 KWh/kg durchschnitt = KWh/l
        //energyloss: ca 70% von Holz wird in Energie umgewandelt --> 30% loss
        super(3000, 0.3, 10000, 4, 0.3);
    }

}
