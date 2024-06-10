package oopb3.animal.abstr;

public abstract class HerdAnimal extends SocialAnimal {

    private HerdAnimal alphaTier;

    public HerdAnimal getAlpha() {
        return this.alphaTier;
    }

    /**
     * Setze im HerdAnimal das Alphatier der Herde
     * <p>
     * Vorbedingung: Es darf pro Herde nur ein Alphatier geben
     * Vorbedinung: setAlpha darf nur fuer Tiere, die in einer hierarchischen Herde leben, auf einen Wert ungleich null
     * gesetzt werden.
     * Nachbedingung: Das Tier ist nach dem Aufruf in einem Sozialverband.
     *
     * @param alphaTier Das neue Alphatier des Rudels
     */
    public void setAlpha(HerdAnimal alphaTier) {
        this.alphaTier = alphaTier;
        // Wenn alphaTier aufgerufen wird, ist das Tier ab sofort in einem Sozialverband.
        // Ob alphaTier null ist oder nicht, ist egal.
        this.joinSocialGroup();
    }

    /**
     * VB: HerdAnimal lebt laut Angabe am Boden
     */
    @Override
    public int air() {
        return 0;
    }

    /**
     * VB: HerdAnimal lebt laut Angabe am Boden
     */
    @Override
    public int ground() {
        return this.getErwarteteLebenszeit();
    }

    /**
     * VB: HerdAnimal lebt laut Angabe am Boden
     */
    @Override
    public int water() {
        return 0;
    }
}
