public abstract class Abstract_EnergyGenerator implements Unit {

    private int investmentCost;
    private int energyOutput;
    private int energyInput;

    public Abstract_EnergyGenerator(int investmentCost, int energyOutput, int energyInput) {
        this.investmentCost = investmentCost;
        this.energyOutput = energyOutput;
        this.energyInput = energyInput;
    }

    @Override
    public int investmentCosts() {
        return investmentCost;
    }

    @Override
    public int runningCosts() {
        return (int)Math.round(energyInput() * GlobalVariables.energyCostPerKWH);
    }

    @Override
    public int energyOutput() {
        return energyOutput;
    }

    @Override
    public int energyInput() {
        return energyInput;
    }
}
