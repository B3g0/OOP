
import java.util.HashMap;
import java.util.List;

/**
 * Eine Abbildung von Wunsch-Arten auf Wunsch-Staerken
 *
 * Laut Angabe besteht ein Wunsch aus "zwei Zahlen".
 * Um die Vorgaben der Angabe nicht zu verletzen, wurde kein Enum fuer die Wunschart verwendet.
 *
 * Key der WishMap ist Wunsch-Art (Integer):
 * Value der WishMap ist Wunsch-Staerke (Integer: neg. Zahlen bedeuten Ablehnung, 0 bedeutet kein Wunsch/Abneigung, pos. Zahlen bedeuten Wunsch)
 * VB: Keys muessen >= 0 sein
 * VB: -100 <= Values <= 100
 */
public class WishMap extends HashMap<Integer, Integer> {

    /**
     * Die Mindesthoehe von Values (Wunschstaerke) in WishMap
     * Invariante: MIN_VALUE = -100, darf im source code nicht veraendert werden, da viele andere Codestellen davon abhaengen!
     */
    public static final int MIN_VALUE = -100;
    /**
     * Die maximale Hoehe von Values (Wunschstaerke) in WishMap
     * Invariante: MAX_VALUE = 100, darf im source code nicht veraendert werden, da viele andere Codestellen davon abhaengen!
     */
    public static final int MAX_VALUE = 100;

    /**
     * Erzeuge eine neue, leere WishMap
     */
    public WishMap() {
    }

    /**
     * Erzeuge eine WishMap als shallow copy einer bestehenden WishMap
     * @param wishMap Die bestehende WishMap
     */
    public WishMap(WishMap wishMap) {
        super(wishMap);
    }

    /**
     * Manipuliere diese WishMap auf zufaellige Art und Weise.
     * Jedes Item aus dem Parameter wunschArten kann potentiell mit einem zufaelligen Wert in der generierten WishMap landen.
     * Wenn ein zufaellig gewahltes Item aus wunschArten noch nicht in der wunschMap enthalten ist, wird es neuhinzgefuegt.
     * Wenn ein zufaellig gewaehltes Item aus wunschArten schon in der wunschMap enthalten ist, wird die Wunschstaerke durch eine zufaellige Wunschstaerke ersetzt.
     * @param wunschArten Die Wunsch-Arten die potentiell in this manipuliert werden.
     *
     * VB: wunschArten.size() > 0
     * NB: this enthaelt zwischen 0 und wunschArten.size() Wunsch-Arten mit zufaelliger Wunschstaerke
     * NB: Die Wunschstaerke ist immer zwischen this.MIN_VALUE und this.MAX_VALUE
     */
    public void manipuliereWishMapZufaellig(List<Integer> wunschArten) {
        if(wunschArten == null) {
            throw new IllegalArgumentException("Weder wishMap noch wunschArten duerfen null sein!");
        }
        else {
            wunschArten.forEach((wunschart) -> {
                // Der Zufall entscheidet ob eine wunschArt hinzugefuegt bzw. veraendert wird.
                if(Util.randomBool()) {
                    int zufaelligeWunschstaerke = Util.random(WishMap.MIN_VALUE, WishMap.MAX_VALUE);
                    this.put(wunschart, zufaelligeWunschstaerke);
                }
            });
        }
    }
}
