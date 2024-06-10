package oopb3.animal;

import oopb3.animal.abstr.FlightlessBird;

public class Emu extends FlightlessBird {

    public Emu() {
        setErwarteteLebenszeit(24*365*10);
    }

    @Override
    public int water() {
        return (int)(getErwarteteLebenszeit()*0.1f);
    }

    @Override
    public int ground() {
        return (int)(getErwarteteLebenszeit()*0.9f);
    }
}
