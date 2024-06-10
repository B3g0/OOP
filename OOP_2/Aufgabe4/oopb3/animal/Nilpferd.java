package oopb3.animal;

import oopb3.animal.abstr.HerdAnimal;
import oopb3.animal.interfaces.Mammal;

/**
 * Nilpferde sind Herdentiere aber nicht in einem Pack und haben auch kein Alphatier.
 */
public class Nilpferd extends HerdAnimal implements Mammal {

    public Nilpferd() {
        setErwarteteLebenszeit(24 * 365 * 20);
    }

    /**
     * Nilpferde koennen nicht fliegen
     *
     * @return int, 0
     */
    @Override
    public int air() {
        return 0;
    }

    /**
     * Zeit in Stunden die Nilpferde im Wasser verbringen
     *
     * @return int
     */
    @Override
    public int water() {
        return (int) (getErwarteteLebenszeit() * 0.5f);
    }

    /**
     * Zeit in Stunden die Nilpferde am Boden verbringen
     *
     * @return
     */
    @Override
    public int ground() {
        return (int) (getErwarteteLebenszeit() * 0.5f);
    }

    /**
     * Nilpferde leben in der Regel immer im Sozialverband, deshalb wird die volle erwartete Lebenszeit übernommen
     *
     * @return int getErwarteteLebenszeit
     */
    @Override
    public int social() {
        return getErwarteteLebenszeit();
    }


}
