package oopb3.abstr;

public abstract class SteppeHerdAnimal extends FitAnimal {

    /**
     * Erzeuge ein neues SteppeHerdAnimal mit speed
     * @param speed Die Geschwindigkeit des SteppeHerdAnimal. Wird als Fitness interpretiert.
     */
    protected SteppeHerdAnimal(int speed) {
        // Rufe Konstruktor von FitAnimal auf, übergebe Geschwindigkeit als Fitness.
        super(speed);
    }

    /**
     * Erzeugt eine String-Abbildung des SteppeHerdAnimal
     * @return Eine String-Abbildung des SteppeHerdAnimal
     */
    public String toString() {
        return "SteppeHerdAnimal (speed: " + getFitness() + ", hierarchical: " + hierarchical() + ", mayBeAlpha: " + mayBeAlpha() + "hashCode: " + this.hashCode() + ")";
    }
}
