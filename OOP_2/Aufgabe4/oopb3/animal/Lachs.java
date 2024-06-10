package oopb3.animal;

import oopb3.animal.abstr.SwarmAnimal;
import oopb3.animal.interfaces.Fish;

public class Lachs extends SwarmAnimal implements Fish {

    public Lachs() {
        setErwarteteLebenszeit(24 * 365 * 2);
    }

    /**
     * VB: Lachs ist ein Fisch, kann sich deshalb nicht in der Luft aufhalten
     * NB: Return value = 0
     *
     * @return int
     */
    @Override
    public int air() {
        return 0;
    }

    /**
     * Gibt die erwartete Lebenszeit in Stunden als int zurück
     *
     * @return Lebenszeit in Stunden als int
     */
    @Override
    public int water() {
        return getErwarteteLebenszeit();
    }

    /**
     * VB: Lachs ist ein Fisch, kann sich deshalb nicht auf dem Boden aufhalten
     * NB: return value = 0
     *
     * @return int
     */
    @Override
    public int ground() {
        return 0;
    }


    /**
     * Berechnet mit einer gewissen Wahrscheinlichkeit ob sich ein Tier vom Typ Lachs einem Schwarm anschliesst
     *
     * @return true falls sich Lachs einem Schwarm anschliesst, false sonst
     */
    @Override
    public boolean swarm() {
        float wahrscheinlichkeitInProzent = .4f;
        if (Math.random() > (1 - wahrscheinlichkeitInProzent)) {
            this.joinSocialGroup();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Zeit in Stunden die das Tier im Sozialverband wahrscheinlich verbringen wird
     *
     * @return Zeit in Stunden als int
     */
    @Override
    public int social() {
        return (int) (getErwarteteLebenszeit() * 0.9f);
    }
}
