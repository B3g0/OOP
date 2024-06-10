package oopb3.animal.abstr;

public abstract class SocialAnimal extends Animal {

    /**
     * Zeit in Stunden die das Tier im Sozialverband wahrscheinlich verbringen wird
     *
     * @return Zeit in Stunden als int
     */
    public abstract int social();

    /**
     * Beschreibt ob ein Tier in einer sozilane Gruppe ist oder nicht
     * true falls ja, false falls nein
     */
    private boolean isInSocialGroup;

    /**
     * Trete einer sozialen Gruppe bei.
     * <p>
     * Vorbedingung: Die Liste an Tieren, die in einem Sozialverband sind, muss vom Client selbst verwaltet werden.
     */
    public void joinSocialGroup() {
        this.isInSocialGroup = true;
    }

    /**
     * Verlasse die soziale Gruppe.
     * <p>
     * Vorbedingung: Die Liste an Tieren, die in einem Sozialverband sind, muss vom Client selbst verwaltet werden.
     */
    public void leave() {
        this.isInSocialGroup = false;
    }

    /**
     * Gebe zurück, ob das Tier in einem Sozialverband ist.
     * <p>
     * Vorbedingung: Die Liste an Tieren, die in einem Sozialverband sind, muss vom Client selbst verwaltet werden.
     *
     * @return Boolscher Wert der beschreibt ob das Tier in einem Sozialverband ist.
     */
    public boolean inSocialGroup() {
        return this.isInSocialGroup;
    }

}
