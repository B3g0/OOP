public class Electric_Power_Unit extends Abstract_Cogeneration_Unit {
    private int quality;

    public Electric_Power_Unit(double priceOfMaterialPerLitre, int amountOfMaterialInLitre, int calorificValuePerLitre,
                               double loss, int quality) {
        //Investmentcosts = 2000 - durchschnittlicher Preis eines Generators/einer Batterie für Haushalte
        super(2000, priceOfMaterialPerLitre, amountOfMaterialInLitre, calorificValuePerLitre, loss);
        this.quality = quality;
    }

    public int quality(){
        return quality;
    }
}
