package oopb3.animal;

import oopb3.abstr.SteppeHerdAnimal;

public class Zebra extends SteppeHerdAnimal {

    /**
     * Die Mindestgeschwindigkeit, die ein weibliches Zebra braucht um Alphatier zu sein.
     */
    private final static int alphaTierMindestGeschwindigkeit = 60;

    /**
     * Die Wahrscheinlichkeit von Mücken gestochen zu werden.
     */
    private float protection;

    /**
     * Das Geschlecht des Zebras.
     */
    private Sex sex;

    /**
     * Erzeugt ein neues Zebra mit speed, protection und sex.
     * @param speed Die Geschwindigkeit des Zebras, wird als Fitness interpretiert.
     * @param protection Die Wahrscheinlichkeit von Mücken gestochen zu werden.
     * @param sex Das Geschlecht des Zebras.
     */
    public Zebra(int speed, float protection, Sex sex) {
        super(speed);
        this.protection = protection;
        this.sex = sex;
    }

    /**
     * Zebras ordnen sich einem Alphatier unter.
     * @return immer true.
     */
    public boolean hierarchical() {
        return true;
    }

    /**
     * Nur weibliche Zebras mit einer gewissen Mindestgeschwindkeit können Alphatier sein.
     * @return true, falls die Bedingungen, Alphatier zu sein, erfüllt sind.
     */
    public boolean mayBeAlpha() {
        return sex == Sex.female && getFitness() > alphaTierMindestGeschwindigkeit;
    }

    /**
     * Liefert die Wahrscheinlichkeit, dass das Zebra von einer Mücke gestochen wird.
     * @return Die Wahrscheinlichkeit, dass das Zebra von einer Mücke gestochen wird.
     */
    public double protection() {
        return protection;
    }

    /**
     * Erzeugt eine String-Abbildung des Zebras
     * @return Eine String-Abbildung des Zebras
     */
    public String toString() {
        return "Zebra (speed: " + getFitness() + ", protection: " + protection() + ", sex: " + sex + ", hierarchical: " + hierarchical() + ", mayBeAlpha: " + mayBeAlpha() + ", hashCode: " + this.hashCode() + ")";
    }

}
