package oopb3.animal.abstr;

import oopb3.animal.interfaces.Bird;

public abstract class FlightlessBird extends Animal implements Bird {

    /**
     * VB: FlightlessBird kann nicht fliegen
     * NB: Return value == 0
     *
     * @return int, 0 weil FlightlessBird nicht fliegen kann
     */
    @Override
    public int air() {
        return 0;
    }
}
