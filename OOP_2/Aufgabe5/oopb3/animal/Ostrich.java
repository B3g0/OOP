package oopb3.animal;

import oopb3.abstr.SteppeHerdAnimal;

public class Ostrich extends SteppeHerdAnimal {

    /**
     * Die Schlagkraft des Ostrich.
     */
    private int power;

    /**
     * Erzeuge einen neuen Ostrich mit speed und power.
     * @param speed Die Geschwindigkeit des Ostrich. Wird als Fitness interpretiert.
     * @param power Die Schlagkraft des Ostrich.
     */
    public Ostrich(int speed, int power) {
        super(speed);
        this.power = power;
    }

    /**
     * Ostrichs ordnen sich keinem Alphatiert unter.
     * @return immer false
     */
    public boolean hierarchical() {
        return false;
    }

    /**
     * Ostrichs können keine Alphatiere sein.
     * @return immer false
     */
    public boolean mayBeAlpha() {
        return false;
    }

    /**
     * Liefert die Schlagkraft des Ostrich
     * @return Die Schlagkraft des Ostrich
     */
    public int power() {
        return power;
    }

    /**
     * Erzeugt eine String-Abbildung des Ostrich
     * @return Eine String-Abbildung des Ostrich
     */
    public String toString() {
        return "Ostrich (speed: " + getFitness() + ", power: " + power() + ", hierarchical: " + hierarchical() + ", mayBeAlpha: " + mayBeAlpha() + ", hashCode: " + this.hashCode() + ")";
    }

}
