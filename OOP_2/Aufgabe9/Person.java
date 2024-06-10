
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Person {

    /**
     * Wuensche der Person
     */
    private final WishMap wuensche;

    /**
     * Widerstand gegenueber Beeinflussung der Wuensche durch Organizations
     * Invariante: {@link Person#MIN_WIDERSTAND} <= widerstand <= {@link Person#MAX_WIDERSTAND}
     */
    private final Integer widerstand;

    /**
     * Der geringste moegliche Widerstand gegen Beeinflussung durch Organizations
     * Invariante: MIN_WIDERSTAND = 0, darf im source code nicht veraendert werden, da viele andere Codestellen davon abhaengen!
     */
    private static final Integer MIN_WIDERSTAND = 0;
    /**
     * Der hoechste moegliche Widerstand gegen Beeinflussung durch Organizations
     * Invariante: MAX_WIDERSTAND = 100, darf im source code nicht veraendert werden, da viele andere Codestellen davon abhaengen!
     */
    private static final Integer MAX_WIDERSTAND = 100;

    @Creator(who = {GroupMember.Christoph, GroupMember.Tobias})
    public Person(List<Integer> wunscharten) {
        this.widerstand = Util.random(Person.MIN_WIDERSTAND, Person.MAX_WIDERSTAND);

        // Erzeuge WishMap der Person mit zufaelliger Wunschsstaerke
        this.wuensche = new WishMap();
        aendereWuenscheZufaellig(wunscharten);
    }


    /**
     * Aendere "ein[en] kleine[n] Teil der Wünsche zufällig" (Angabe Aufgabe9, S. 1)
     * Wird zumindest benoetigt fuer: 1. Eine neue Person hat anfangs zufaellige Wuensche
     * und 2. jedes Jahr nach Weihnachten wird ein kleiner Teil der Wuensche zufaellig veraendert.
     *
     * @param wunschArten Die Keys der Wunsch-Arten, die zufaellig veraendert werden koennen.
     */
    void aendereWuenscheZufaellig(List<Integer> wunschArten) {
        this.wuensche.manipuliereWishMapZufaellig(wunschArten);
    }

    /**
     * Eine Organization versucht einen Wunsch mit einer bestimmten Wunschstaerke zu erwecken.
     * @param wunschArt Die Wunsch-Art
     * @param zuErweckendeWunschstaerke Die gewuenschte Wunschstaerke
     *
     * VB: wunschArt muss in {@link Person#wuensche} enthalten sein
     * VB: {@link WishMap#MIN_VALUE} <= zuErweckendeWunschstaerke <= {@link WishMap#MAX_VALUE}
     */
    void organizationVersuchtWunschZuWecken(Integer wunschArt, Integer zuErweckendeWunschstaerke) {
        /**
         * Laut Angabe:
         * Hat der Mensch schon einen Wunsch dieser Art, wird dessen
         * Stärke mit einem Faktor zwischen 0 und 2 multipliziert, abhän-
         * gig vom Widerstand gegen Beeinflussungen und der Richtung
         * (gleiche oder unterschiedliche Vorzeichen der Stärken von zu
         * erweckendem und bestehendem Wunsch). Dabei kann die Ab-
         * sicht des Influencers auch ins Gegenteil verkehrt werden.
         * •
         * Hat der Mensch noch keinen Wunsch dieser Art, kommt der
         * zu erweckende Wunsch hinzu, aber abgeschwächt entsprechend
         * dem Widerstand gegen Beeinflussung.
         */

        Integer bestehendeWunschstaerke = wuensche.get(wunschArt);
        if(bestehendeWunschstaerke == 0) {
            // Die Abschwaechung wird durch Multiplikation mit 1 + der Differenz aus MAX_WIDERSTAND und widerstand erreicht.
            // Wenn eine Person maximalen Widerstand leistet, wird der Wunsch nicht erweckt.
            // Wenn eine Person minimalen Widerstand leistet, wird der Wunsch mit voller gewuenschter Staerke erweckt.
            // Bug: (MAX_WIDERSTAND - 0) * 100 = 10000 -> wenn kein Widerstand und erwartendeWunschstaerke 100
            // dann ist Wunschstaerke ueber dem vereinbarten MAX_VALUE von WishMap
            wuensche.put(wunschArt, (int)((double)(MAX_WIDERSTAND - widerstand)/MAX_WIDERSTAND) * zuErweckendeWunschstaerke);
        }
        else {
            double faktor = ((double)(MAX_WIDERSTAND - widerstand)/MAX_WIDERSTAND) * 2;
            wuensche.put(wunschArt, Util.clampValue(WishMap.MIN_VALUE, WishMap.MAX_VALUE,
                    (int)(bestehendeWunschstaerke * faktor * Util.clampValue(-1,1,zuErweckendeWunschstaerke))));
        }
    }

    /**
     * Gebe die Wunscharten der n (numberOfWishes) hoechsten Wuensche der Person zurueck
     * @param numberOfWishes Die Anzahl der zurueckzugebenden Wuensche
     * @return Eine Liste der Wunscharten der hoechsten Wuensche der Person
     * VB: numberOfWishes >= Anzahl der Wuensche dieser Person (also: numberOfWishes >= Person.wuensche.entrySet().size())
     */
    @Creator(who=GroupMember.Tobias)
    public List<Integer> getHighestWishes(int numberOfWishes) {
        // Folgende Schritte sind notwendig:
        // 1. Hole Keys und Values aus der wuensche Map als Set
        // 2. Sortiere das Set nach den Values (=Wunschstaerke) im Set (Default-Comparator von Integer sortiert aufsteigend, wir brauchen absteigende Sortierung)
        // 3. Verwerfe Value (=Wunsch-Staerke) von Entry und behalte nur Key (=Wunsch-Art) (mit Stream.map())
        // 4. Erstelle eine neue ArrayList mit den Wunsch-Arten
        // 5. Gebe nur die n (numberOfWishes) hoechsten Wuensche zurueck.
        // Verwende Collectors.toCollection statt Collectors.toList(), denn nur dann sind "type, mutability, serializability or thread-safety"
        // (siehe JavaDoc von Collectors.toList()) der erzeugten Liste eindeutig festgelegt!
        return wuensche.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new)).subList(0, (numberOfWishes > wuensche.size() ? wuensche.size() : numberOfWishes));
    }

    @Creator(who=GroupMember.Christoph)
    public int wishesCount() {
        return wuensche.values().stream().filter(i -> i != 0).collect(Collectors.toCollection(ArrayList::new)).size();
    }
}
