import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Test {

    /**
     * Aufgabenverteilung:
     * Christoph: Labor, AquariumBillig, AquariumTeuer, TerrariumBillig, TerrariumTeuer
     * Mensur: Versuchstierschwarm, Goldfische, Koi, Leguane
     * Tobias: Test, Vivarium, Heuschrecken
     */

    public static void main(String[] args) {
        System.out.println("Labor 1:");
        System.out.println("--------------------------------------------");
        System.out.println("Labor 1 enthält nur teure Vivarien!");
        System.out.println();
        testeLaborMitNurTeurenVivarien();
        System.out.println();
        System.out.println();

        System.out.println("Labor 2:");
        System.out.println("--------------------------------------------");
        System.out.println("Labor 2 enthält billige und teure Vivarien!");
        System.out.println();
        testeLaborMitBilligenUndTeurenVivarien();
        System.out.println();
        System.out.println();

        System.out.println("Labor 3:");
        System.out.println("--------------------------------------------");
        System.out.println("Labor 3 enthält nur billige Vivarien!");
        System.out.println();
        testeLaborMitNurBilligenVivarien();
        System.out.println();
        System.out.println();
    }

    private static void testeLaborMitNurTeurenVivarien() {
        List<Versuchstierschwarm> schwarmListe = erstelleGemischteSchwarmliste();

        Labor labor = new Labor();
        labor.neu(new AquariumTeuer(100, 80, 100));
        labor.neu(new AquariumTeuer(50, 70, 150));
        labor.neu(new TerrariumTeuer(70, 30, 180));
        labor.neu(new TerrariumTeuer(60, 60, 100));
        System.out.println("Volumen der Vivarien: ");
        labor.volumenfrei();
        labor.volumenbelegt();

        simuliereXWochen(4, schwarmListe, labor);
    }

    private static void testeLaborMitBilligenUndTeurenVivarien() {
        List<Versuchstierschwarm> schwarmListe = erstelleGemischteSchwarmliste();

        Labor labor = new Labor();
        labor.neu(new AquariumTeuer(100, 80, 100));
        labor.neu(new AquariumBillig(50, 70, 150));
        labor.neu(new TerrariumTeuer(70, 30, 180));
        labor.neu(new TerrariumBillig(60, 60, 100));
        labor.neu(new TerrariumBillig(60, 20, 110));
        System.out.println("Volumen der Vivarien: ");
        labor.volumenfrei();
        labor.volumenbelegt();

        simuliereXWochen(4, schwarmListe, labor);
    }

    private static void testeLaborMitNurBilligenVivarien() {
        List<Versuchstierschwarm> schwarmListe = erstelleGemischteSchwarmliste();

        Labor labor = new Labor();
        labor.neu(new AquariumBillig(100, 80, 100));
        labor.neu(new AquariumBillig(50, 70, 150));
        labor.neu(new TerrariumBillig(70, 30, 180));
        labor.neu(new TerrariumBillig(60, 60, 100));
        System.out.println("Volumen der Vivarien: ");
        labor.volumenfrei();
        labor.volumenbelegt();

        simuliereXWochen(4, schwarmListe, labor);
    }

    private static void simuliereXWochen(int wochenAnzahl, List<Versuchstierschwarm> schwarmListe, Labor labor) {
        for (int i = 0; i < wochenAnzahl; i++) {
            System.out.println();
            System.out.println("Woche " + i);
            System.out.println("--------------------------------------------");
            System.out.println("Volumen der Vivarien: ");
            labor.volumenfrei();
            labor.volumenbelegt();
            labor.inventarliste();
            labor.schwarmliste();
            Vivarium viv = null;
            for(Versuchstierschwarm versuchstierschwarm : schwarmListe)
            {
                viv = labor.stellebereit(versuchstierschwarm);
            }
            if(viv != null && viv.getSchwarm() != null) {
                System.out.println("Retourniere Vivarium: " + viv);
                labor.retourniere(viv);
            }
            labor.aelter();
            entferneVerstorbeneTiere(schwarmListe);
        }
        labor.inventarliste();
        labor.schwarmliste();
    }

    private static List<Versuchstierschwarm> erstelleGemischteSchwarmliste() {
        List<Versuchstierschwarm> schwarmListe = new ArrayList<>();
        Versuchstierschwarm heuschrecken1 = new Heuschrecken(1);
        schwarmListe.add(heuschrecken1);
        Versuchstierschwarm heuschrecken2 = new Heuschrecken(3);
        schwarmListe.add(heuschrecken2);

        Versuchstierschwarm goldfische1 = new Goldfische(2);
        schwarmListe.add(goldfische1);
        Versuchstierschwarm goldfische2 = new Goldfische(3);
        schwarmListe.add(goldfische2);

        Versuchstierschwarm koi1 = new Koi(1);
        schwarmListe.add(koi1);
        Versuchstierschwarm koi2 = new Koi(4);
        schwarmListe.add(koi2);

        Versuchstierschwarm leguane1 = new Leguane(2);
        schwarmListe.add(leguane1);
        Versuchstierschwarm leguane2 = new Leguane(2);
        schwarmListe.add(leguane2);
        return schwarmListe;
    }

    /**
     * Entfernt bereits verstorbene Tiere aus der Testliste.
     * @param schwarmListe Die Liste des {@link Versuchstierschwarm}
     */
    private static void entferneVerstorbeneTiere(List<Versuchstierschwarm> schwarmListe) {
        for(Iterator<Versuchstierschwarm> iterator = schwarmListe.iterator(); iterator.hasNext();) {
            Versuchstierschwarm versuchstierschwarm = iterator.next();
            if(versuchstierschwarm.bereitsVerstorben())
            {
                iterator.remove();
            }
        }
    }
}
