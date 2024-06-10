package oopb3.animal;

import oopb3.abstr.FitAnimal;

public class Starling extends FitAnimal {

    /**
     * Erzeugt einen Starling mit longDistancePerseverance und canChangeDirectionQuickly
     * @param longDistancePerseverance Die Ausdauer auf Langstreckenflügen, wird als Teil der Fitness interpretiert.
     * @param canChangeDirectionQuickly Legt fest ob der Starling seine Richtung schnell ändern kann, wird als
     *                                  Teil der Fitness interpretiert.
     */
    public Starling(int longDistancePerseverance, boolean canChangeDirectionQuickly) {
        // Die Fitness von Starling setzt sich zusammen aus longDistancePerseverance und canChangeDirectionQuickly.
        // Starlings mit canChangeDirectionQuickly=true gelten als doppelt so fit.
        super(longDistancePerseverance * (canChangeDirectionQuickly ? 2 : 1));
    }

    /**
     * Starlings ordnen sich keinem Alphatiert unter.
     * @return immer false
     */
    public boolean hierarchical() {
        return false;
    }

    /**
     * Starlings können keine Alphatiere sein.
     * @return immer false
     */
    public boolean mayBeAlpha() {
        return false;
    }

    /**
     * Erzeugt eine String-Abbildung des Starling
     * @return Eine String-Abbildung des Starling
     */
    public String toString() {
        return "Starling (fitness: " + getFitness() + ", hierarchical: " + hierarchical() + ", mayBeAlpha: " + mayBeAlpha() + ", hashCode: " + this.hashCode() + ")";
    }
}
