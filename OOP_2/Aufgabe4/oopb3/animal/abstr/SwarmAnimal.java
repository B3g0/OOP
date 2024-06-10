package oopb3.animal.abstr;

// kein access modifier weil https://docs.oracle.com/javase/specs/jls/se7/html/jls-9.html#jls-9.4
public abstract class SwarmAnimal extends SocialAnimal {

    /**
     * SwarmAnimal schließt sich beim Aufruf eventuell einem Schwarm an.
     * Nachbedingung: Wenn true zurückgegeben wird, gehört das SwarmAnimal einem Schwarm an. Das muss der Client dieser Methode
     * dementsprechend in der Verwaltung seiner Schwarmliste berücksichtigen.
     *
     * @return Boolscher Wert ob sich das SwarmAnimal einem Schwarm angeschlossen hat oder nicht.
     */
    public abstract boolean swarm();

}
