public class Hose implements Connection<WaterSupply>{
    private WaterSupply source;
    private WaterSupply sink;
    private double diameter;

    Hose(WaterSupply source, WaterSupply sink, double diameter) {
        this.diameter = diameter;
        this.source = source;
        this.sink = sink;
    }

    @Override
    public WaterSupply source() {
        return source;
    }

    @Override
    public WaterSupply sink() {
        return sink;
    }

    double diameter(){
        return diameter;
    }

    @Override
    public String toString() {
        return "Cable: " + source + " -> " + sink +" ("+ diameter + " inches)";
    }
}
