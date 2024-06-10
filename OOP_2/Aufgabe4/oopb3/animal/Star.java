package oopb3.animal;

import oopb3.animal.abstr.SwarmAnimal;
import oopb3.animal.interfaces.MigratoryBird;

public class Star extends SwarmAnimal implements MigratoryBird {

    public Star() {
        setErwarteteLebenszeit(24*365*5);
    }

    private float anschlussWahrscheinlichkeit = 0.5f;

    @Override
    public boolean swarm() {
        if (Math.random() > anschlussWahrscheinlichkeit) {
            this.joinSocialGroup();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int social() {
        return (int) (getErwarteteLebenszeit()*0.5f);
    }

    @Override
    public int air() {
        return (int) (getErwarteteLebenszeit()*0.7f);
    }

    @Override
    public int water() {
        return (int) (getErwarteteLebenszeit()*0.1f);
    }

    @Override
    public int ground() {
        return (int) (getErwarteteLebenszeit()*0.2f);
    }
}
