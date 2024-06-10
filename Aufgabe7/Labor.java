import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Labor {

    private final List<Vivarium> inventarListe;
    /** Laut Angabe soll stellebereit das Vivarium aus der Inventarliste entfernen.
     * Aus diesem Grund wurde die Liste belegteVivarien eingefuehrt,
     * um die belegten Vivarien verwalten zu koennen.
    */
    private final List<Vivarium> belegteVivarien;

    public Labor() {
        inventarListe = new ArrayList<>();
        belegteVivarien = new ArrayList<>();
    }

    /**
     * Fuegt ein neues Vivarium der Inventarliste zu.
     * VB: vivarium != null
     * NB: vivarium ist in inventarliste
     * @param vivarium
     */
    public void neu(Vivarium vivarium) {
        inventarListe.add(vivarium);
    }

    /**
     * Loescht ein vivarium aus der Inventarliste
     * VB: vivarium != null
     * NB: vivarium ist nicht mehr in der Liste
     * @param vivarium
     */
    public void defekt(Vivarium vivarium) {
        inventarListe.remove(vivarium);
    }

    /**
     * Stellt ein passendes Vivarium fuer den Versuchsschwarm bereit und setzt den Schwarm rein.
     *
     * VB: versuchstierschwarm != null
     * @param versuchstierschwarm Objekt der Klasse Versuchstierschwarm welches ein Vivarium sucht
     * @return null wenn kein benötigtes Vivarium frei ist, Objekt vivarium wenn eines gefunden wurde
     */
    public Vivarium stellebereit(Versuchstierschwarm versuchstierschwarm) {

        // stellebereit besteht aus 3 Schritten:
        // 1. Schritt: suche ein passendes Vivarium, waehle wenn moeglich ein billiges.
        Vivarium vivarium = findePassendesVivarium(versuchstierschwarm,false);
        if(vivarium != null) {
            setzeVersuchstierschwarmInVivarium(vivarium, versuchstierschwarm);
            return vivarium;
        }
        // 2. Schritt: Wenn kein billiges Vivarium frei ist, suche ein teures.
        else {
            vivarium = findePassendesVivarium(versuchstierschwarm, true);
        }
        if(vivarium != null) {
            setzeVersuchstierschwarmInVivarium(vivarium, versuchstierschwarm);
            return vivarium;
        }
        // 3. Schritt: Wenn auch kein teures Vivarium frei ist, gibt es derzeit kein freies Vivarium.
        System.out.println("Kein freies Vivarium für " + versuchstierschwarm.toString() + " verfügbar!");
        return null;
    }

    /**
     * Hilfsmethode zum setzen eines Versuchstierschwarms in ein Vivarium
     * VB: vivarium != null && versuchstierschwarm != null
     * @param vivarium
     * @param versuchstierschwarm
     */
    private void setzeVersuchstierschwarmInVivarium(Vivarium vivarium, Versuchstierschwarm versuchstierschwarm) {
        inventarListe.remove(vivarium);
        belegteVivarien.add(vivarium);
        System.out.println(vivarium.toString() + " durch " + versuchstierschwarm.toString() + " besetzt.");
    }

    /**
     * Hilfsmethode zum Bestimmen eine Vivariums für einen Versuchstierschwarm
     * VB: versuchstierschwarm != null
     * NB: versuchstierschwarm ist in passendem Vivarium oder nicht, wenn keines vorhanden ist
     * @param versuchstierschwarm Objekt der Klasse Versuchstierschwarm, != null
     * @param teuerStattBilligErlaubt true falls Versuchstierschwarm auch in teures Vivarium darf, false andernfalls
     * @return null wenn nichts gefunden wurde, Objekt vivarium wenn eines gefunden wurde
     */
    private Vivarium findePassendesVivarium(Versuchstierschwarm versuchstierschwarm, boolean teuerStattBilligErlaubt) {
        Iterator<Vivarium> iter = inventarListe.iterator();
        Vivarium vivarium = null;
        while(iter.hasNext() && !(vivarium = iter.next()).besetze(versuchstierschwarm, teuerStattBilligErlaubt)){
            vivarium = null;
        }
        return vivarium;
    }

    /**
     * Entfernt die Zuweisung des Versuchstierschwarmes vom Vivarium und setzt es wieder in die inventarListe
     * VB: vivarium != null
     * @param vivarium Objekt der Klasse Vivarium welches in inventarListe hinzugefügt und aus belegteVivarien entfernt wird
     */
    public void retourniere(Vivarium vivarium) {
        vivarium.setSchwarm(null);
        inventarListe.add(vivarium);
        belegteVivarien.remove(vivarium);
        System.out.println(vivarium.toString() + " ist nicht mehr besetzt");
    }

    /**
     * Entfernt die Zuweisung des Versuchstierschwarmes vom Vivarium und setzt es wieder in die inventarListe
     * VB: vivarium != null && iterator != null
     * @param vivarium Objekt der Klasse Vivarium welches in inventarListe hinzugefügt und aus belegteVivarien entfernt wird
     */
    private void retourniere(Vivarium vivarium, Iterator<Vivarium> iterator) {
        vivarium.setSchwarm(null);
        inventarListe.add(vivarium);
        iterator.remove();
        System.out.println(vivarium.toString() + " ist nicht mehr besetzt");
    }

    /**
     * Freies volumen aller Vivarien
     * @return float volumen
     */
    public void volumenfrei() {
        Iterator<Vivarium> iter = inventarListe.iterator();
        float volumen = 0f;
        while(iter.hasNext()) {
            volumen += iter.next().volumen();
        }
        System.out.println("Geamtvolumen der freien Vivarien beträgt " + volumen + " cm^3");
    }

    /**
     * Besetztes Volumen über alle Vivarien hinweg
     * @return float volumen
     */
    public void volumenbelegt() {
        Iterator<Vivarium> iter = belegteVivarien.iterator();
        float volumen = 0f;
        while(iter.hasNext()) {
            volumen += iter.next().volumen();
        }
        System.out.println("Gesamtvolumen der belegten Vivarien beträgt " + volumen + " cm^3");
    }

    /**
     * Ausgabe des aktuellen Standes der inventarListe
     */
    public void inventarliste() {
        Iterator<Vivarium> iter = inventarListe.iterator();
        System.out.println("Freie Vivarien:");
        ausgeben(iter);
    }

    /**
     * Ausgabe des aktuellen Standes der belegteVivarien
     */
    public void schwarmliste() {
        Iterator<Vivarium> iter = belegteVivarien.iterator();
        System.out.println("Besetzte Vivarien: ");
        ausgeben(iter);
    }

    /**
     * Hilfsmethode zur textuellen Ausgabe
     * VB: vivariumIterator != null
     * @param vivariumIterator Iterator
     */
    private void ausgeben(Iterator<Vivarium> vivariumIterator) {
        if (!vivariumIterator.hasNext()) {
            System.out.println("\t-\n");
        } else {
            while(vivariumIterator.hasNext()) {
                System.out.print("\t"+vivariumIterator.next().toString()+"\n");
            }
            System.out.println();
        }
    }

    /**
     * Hilfsmethode die die Versuchstierschwärme ältern lässt und dadurch versterben können, was wiederrum Vivarien freigibt
     * @return true wenn das Tier verstorben ist, false andernfalls
     */
    public void aelter() {
        for (Iterator<Vivarium> iterator = belegteVivarien.iterator(); iterator.hasNext();) {
            Vivarium vivarium = iterator.next();
            Versuchstierschwarm versuchstierschwarm = vivarium.getSchwarm();
            if (versuchstierschwarm.aelter()) {
                retourniere(vivarium, iterator);
            }
        }
    }
}
