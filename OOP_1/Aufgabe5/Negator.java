public class Negator implements Connection<Integer> {
    private int source;

    Negator(int source) {
        this.source = source;
    }

    @Override
    public Integer source() {
        return source;
    }

    @Override
    public Integer sink() {
        return source * (-1);
    }

    @Override
    public String toString() {
        return "Value: " + source + ", Negated Value: " + sink();
    }
}
