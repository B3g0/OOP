public class Cable implements Connection<PowerSupply> {
    private PowerSupply source;
    private PowerSupply sink;
    private int strands;

    public Cable(PowerSupply source, PowerSupply sink, int strands) {
        this.source = source;
        this.sink = sink;
        this.strands = strands;
    }

    @Override
    public PowerSupply source() {
        return source;
    }

    @Override
    public PowerSupply sink() {
        return sink;
    }

    public int strands(){
        return strands;
    }

    /*
    toString-Methode zur Erstellung eines gut lesbaren und informativen Strings bei der Ausgabe
     */
    @Override
    public String toString() {
        return "Cable: " + source + " -> " + sink +" ("+ strands + " strands)";
    }
}
