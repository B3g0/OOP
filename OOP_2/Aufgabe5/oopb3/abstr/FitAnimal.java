package oopb3.abstr;

public abstract class FitAnimal {

    /**
     * Die Fitness des FitAnimal.
     * Invariante: Fitness ist immer zwischen 0 und 100.
     */
    private int fitness;

    /**
     * Erzeuge ein neues FitAnimal mit fitness.
     * @param fitness Die Fitness des FitAnimal.
     */
    protected FitAnimal(int fitness) {
        setFitness(fitness);
    }

    /**
     * Vergleiche Fitness von diesem Tier und einem anderen Tier.
     * @param otherAnimal Ein anderes FitAnimal
     * @return 1 wenn dieses Tier fitter ist, 0 bei gleicher Fitness, -1 wenn das andere Tier fitter ist.
     * Vorbedingung: Die Fitness darf nur zwischen Tieren aus dem gleichen Sozialverband verglichen werden.
     */
    public int fitter(FitAnimal otherAnimal) {
        // Ein Unterschied von 1 in fitness stellt einen deutlichen Unterschied dar. ("deutlich fitter", Angabe UE5 S. 1)
        if (this.getFitness() > otherAnimal.getFitness()) {
            return 1;
        } else if (this.getFitness() < otherAnimal.getFitness()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Setze die Fitness des Tiers
     * @param fitness Die neue Fitness des Tiers
     * Invariante: Fitness ist immer zwischen 0 und 100. Falls sich ein Wert gesetzt wird, der außerhalb dieses Bereichs
     * liegt, wird er auf 0 bzw. 100 beschränkt.
     */
    private void setFitness(int fitness) {
        if(fitness < 0) {
            this.fitness = 0;
        }
        else if(fitness > 100) {
            this.fitness = 100;
        }
        else {
            this.fitness = fitness;
        }
    }

    /**
     * Liefert die Fitness des FitAnimal
     * @return die Fitness des FitAnimal
     * Invariante: Fitness ist immer zwischen 0 und 100.
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * Ändere die Fitness um einen Wert
     * @param fitness Die hinzuzufügende Differenz
     * Invariante: Fitness ist immer zwischen 0 und 100.
     */
    public void changeFitness(int fitness) {
        int newFitness = this.fitness + fitness;
        setFitness(newFitness);
    }

    public abstract boolean hierarchical();

    public abstract boolean mayBeAlpha();

    /**
     * Erzeugt eine String-Abbildung des FitAnimal
     * @return Eine String-Abbildung des FitAnimal
     */
    public String toString() {
        return "FitAnimal (fitness: " + getFitness() + ", hierarchical: " + hierarchical() + ", mayBeAlpha: " + mayBeAlpha() + ", hashCode: " + this.hashCode() + ")";
    }

}
