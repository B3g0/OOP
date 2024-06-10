import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Test {

    /**
     * VORSICHT:
     * Manchmal passierts, dass die Aspekte nicht mit ausgefuehrt werden wenn man das Programm ueber Intellij ausfuehrt.
     * Dann einfach kurz den Aspect editieren, dann sollts wieder gehen.
     *
     * Todos:
     *
     * -3. Alle Vorgaben der Angabe mittels Streams und AspectJ umsetzen
     *
     * -2. Test-Output pro Jahr soll via Aspekte in die Konsole ausgegeben werden.
     *
     * 1. Eventuell: WunschArten als Variable in die am besten geeignetste Klasse geben und von den anderen Klassen darauf zugreifen.
     * Das ist besser als WunschArten ueber Konstruktoren an alle moeglichen Objekte (Population, Person, ...) zu uebergeben.
     * Wobei die aktuelle Umsetzung vielelicht eh passt?
     * Kommentar von Christoph: meiner Meinung nach passts.
     *
     * 2. Sicherstellen, dass Organization keinen Zugriff auf Person.widerstand hat - erledigt.
     * Gibt es laut Angabe noch andere Zugriffe/Sichtbarkeiten, die wir einschraenken muessen?
     */

    public static void main(String[] args) {
        Test test = new Test();
        test.simulation();
    }

    @Creator(who={GroupMember.Christoph, GroupMember.Tobias})
    public void simulation() {
        // make simulation here

        // Erzeuge 10 Arten von Wuenschen
        // Laut Angabe sind mindestens 5 Wunscharten notwendig.
        final List<Integer> wunschArten = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toCollection(ArrayList::new));

        // Erzeuge eine Population mit gewisser Groesse, laut Angabe "tausend oder mehr"
        int populationsGroesse = 2000;
        Population population = new Population(wunschArten, populationsGroesse);

        // Erzeuge 3 Organizations, laut Angabe :
        int anzahlOrganizations = 3;
        final List<Organization> organizations = Stream.generate(() -> new Organization(wunschArten)).limit(anzahlOrganizations).collect(Collectors.toCollection(ArrayList::new));

        // Simuliere 10 Jahre (in Angabe gefordert)
        IntStream.rangeClosed(2018, 2018 + 10).forEach(jahreszahl -> {
            simuliereJahr(organizations, population);
        });

    }

    @Creator(who=GroupMember.Christoph)
    public void simuliereJahr(List<Organization> organizations, Population population) {
        // zwischen Weihnachten:
        // lasse jede Organization die Population manipulieren
        organizations.forEach(organization -> {
            organization.manipulierePopulationAnhandVonWishList(population);
        });
        population.removeAndAddNew();
    }
}
