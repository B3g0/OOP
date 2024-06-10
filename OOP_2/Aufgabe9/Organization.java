import java.util.List;

public class Organization {

    /**
     * Wuensche der Organization
     */
    private final WishMap wuensche;

    public Organization(List<Integer> wunschArten) {
        this.wuensche = new WishMap();
        this.wuensche.manipuliereWishMapZufaellig(wunschArten);
    }

    /**
     * Manipuliere die Persons einer Population anhand ihrer WishList
     * @param population Objekt der Klasse Population != null
     */
    @Creator(who = GroupMember.Mensur)
    public void manipulierePopulationAnhandVonWishList(Population population) {
        if (population == null || wuensche == null) {
            throw new IllegalArgumentException("Population oder wuensche darf nicht null sein!");
        }
        // Beachte: Organisationen laut Angabe koennen Person.widerstand nur anhand von wishList schaetzen, nicht lesen.
        // Und: Als Datenquellen dienen neben der aktuellen Bevölkerung nur die Listen des Christkinds.
        // Und: Der Algorithmus ist nicht vorgegeben; er soll möglichst einfach sein

        WishList wishList = population.getWishList();

        population.getPeople().forEach(person -> {
            wishList.get(person).forEach(wishart ->{
                person.organizationVersuchtWunschZuWecken(wishart, wuensche.get(wishart) == null ? 0 : wuensche.get(wishart));
            });
            });

    }

    public WishMap getWuensche() {
        return wuensche;
    }
}