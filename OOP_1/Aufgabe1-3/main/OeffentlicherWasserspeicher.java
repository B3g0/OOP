package main;

import abstr.Abstract_Quelle;

public class OeffentlicherWasserspeicher extends Abstract_Quelle {

    public OeffentlicherWasserspeicher(int maxValue) {
        super(maxValue);
    }

    @Override
    public int get_Erzeugungsmenge(int hotd) {
        return get_Maxvalue();
    }

    @Override
    public String getUnit() {
        return "liters";
    }
}
