package oopb3.animal;

import oopb3.animal.abstr.SwarmAnimal;

public class MigratoryLocust extends SwarmAnimal {

    private float anschlussWahrscheinlichkeit = 0.5f;

    public MigratoryLocust() {
        setErwarteteLebenszeit(24 * 180);
    }

    /**
     * Jeder Aufruf von touch erhöht die Wahrscheinlichkeit, dass sich MigratoryLocust einem Schwarm anschließt.
     * Nachbedingung: Wenn true zurückgegeben wird, gehört der MigratoryLocust einem Schwarm an. Das muss der Client dieser Methode
     * dementsprechend in der Verwaltung seiner Schwarmliste berücksichtigen.
     */
    public boolean touch(MigratoryLocust otherMigratoryLocust) {
        // Nur wenn touch() nicht mit sich selbst als Argument aufgerufen wird, erhöht sich die Wahrscheinlichkeit
        if (this != otherMigratoryLocust) {
            // erhöhe anschlussWahrscheinlichkeit um 20 %
            this.anschlussWahrscheinlichkeit *= 1.2f;
            // Rufe swarm() mit höherer anschlussWahrscheinlichkeit auf.
            return swarm();
        } else {
            return false;
        }
    }

    /**
     * NB: Zeit die Heuschrecken in der Luft verbringen in Stunden
     *
     * @return int
     */
    @Override
    public int air() {
        return (int) (getErwarteteLebenszeit() * 0.5f);
    }

    /**
     * Heuschrecken leben nicht im Wasser, deshalb 0
     *
     * @return int
     */
    @Override
    public int water() {
        return 0;
    }

    /**
     * Zeit die Heuschrecken im Boden verbringen
     *
     * @return int
     */
    @Override
    public int ground() {
        return (int) (getErwarteteLebenszeit() * 0.5f);
    }

    /**
     * Entscheidet ob sich das Tier einem Schwarm anschliesst
     *
     * @return true falls ja, false sonst
     */
    @Override
    public boolean swarm() {
        if (Math.random() > anschlussWahrscheinlichkeit) {
            this.joinSocialGroup();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Zeit die Heuschrecken in Stunden im Sozialverband verbringen
     *
     * @return int
     */
    @Override
    public int social() {
        return (int) (getErwarteteLebenszeit() * 0.9f);
    }

}
