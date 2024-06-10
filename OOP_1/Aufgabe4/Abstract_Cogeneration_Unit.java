public abstract class Abstract_Cogeneration_Unit implements Unit{
    protected int investmentCosts;
    private int calorificValuePerLitre;
    private int amountOfMaterialInLitre;
    private double priceOfMaterialPerLitre;
    private double energyloss;

    public Abstract_Cogeneration_Unit(int investmentCosts, double priceOfMaterialPerLitre, int amountOfMaterialInLitre,
                                      int calorificValuePerLitre, double energyloss) {
        this.investmentCosts = investmentCosts;
        this.amountOfMaterialInLitre = amountOfMaterialInLitre;
        this.calorificValuePerLitre = calorificValuePerLitre;
        this.priceOfMaterialPerLitre = priceOfMaterialPerLitre;

    }

    @Override
    public int investmentCosts() {
        return investmentCosts;
    }

    @Override
    public int runningCosts() {
        return (int)Math.round(amountOfMaterialInLitre * priceOfMaterialPerLitre);
    }

    @Override
    public int energyOutput() {
        return (int)Math.round(energyInput()*(1-energyloss));
    }

    @Override
    public int energyInput() {
        return amountOfMaterialInLitre * calorificValuePerLitre;
    }
}
