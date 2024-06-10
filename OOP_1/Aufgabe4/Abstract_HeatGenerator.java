public abstract class Abstract_HeatGenerator implements Unit{

    private int investmentCosts;
    private int energyOutput;
    private int energyInput;

    public Abstract_HeatGenerator(int investmentCosts, int energyOutput, int energyInput) {
        this.investmentCosts = investmentCosts;
        this.energyOutput = energyOutput;
        this.energyInput = energyInput;
    }

    @Override
    public int investmentCosts() {
        return investmentCosts;
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
