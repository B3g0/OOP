import java.util.Iterator;

public class Haus {
    private My_List zimmer_list;
    private String name;

    public Haus(String name) {
        this.name = name;
        this.zimmer_list = new My_List();
    }

    /**
     * Getter für den Namen des Hauses
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Fügt das Zimmer in die Liste der Zimmer hinzu
     * VB: zimmer != null && zimmer_list != null
     * NB: zimmer wird in die zimmer_list hinzugefügt
     */
    public void add_Zimmer(Zimmer zimmer){
        zimmer_list.add(zimmer);
    }

    /**
     * Entfernt das Zimmer aus der Liste
     * VB: zimmer_list != null && zimmer != null
     * NB: zimmer wird aus zimmer_list entfernt
     */
    public void remove_Zimmer(Zimmer zimmer){
        zimmer_list.remove(zimmer);
    }

    /**
     * print_Zimmer_Info gibt die Daten aller Zimmer das Hauses zurück
     * Vorbedingung: zimmer_list != null
     * Nachbedingung: Methode iteriert über alle Zimmer hinweg und ruft dabei die Methode print()
     * in Zimmer auf, die alle Daten für sämtliche Gegenstände bereits enthält.
     */
    public void print_Zimmer_Info(){

        System.out.println("Haus: "+ getName());
        System.out.println("Gesamtverbrauch des Hauses: "+calc_Gesamtverbrauch()+" kWh");
        System.out.println();

        for (Object object : zimmer_list) {
            Zimmer zimmer = (Zimmer)object;
            zimmer.print();
            System.out.println();
        }
    }

    /**
     * calc_Gesamtverbrauch berechnet den Gesamtverbrauch aller Lampen im gesamten Haus
     * Vorbedingung: zimmer_list != null
     * Nachbedingung: Die Methode iteriert über alle Zimmer hinweg und ruft die Hilfsmethode
     * calc_Watt_von_allen_Leuchtmitteln() auf um die Watt aller Lampen pro Zimmer zu allWatt zu addieren.
     * Dieser Wert wird dann zurückgegeben.
     * @return double
     */
    public double calc_Gesamtverbrauch(){

        int allWatt = 0;

        for (Object object : zimmer_list) {
            Zimmer zimmer = (Zimmer)object;

            allWatt += zimmer.calc_Watt_aller_Leuchtmittel_raw();
        }
        return allWatt;
    }

    /**
     * Getter-Methode zur Suche von Zimmern nach dem spezifischen Namen der übergeben wird
     * VB: name != null && zimmer_list != null
     * NB: foreach-Schleife durchläuft die Liste und sucht nach dem Zimmer mit dem übergebenen Namen
     * @param name String des Zimmers der den Namen enthält
     * @return zimmer welches diesen Namen hat
     */
    public Zimmer getZimmerMitName(String name){

        for (Object zimmer_obj : zimmer_list) {
            Zimmer val = (Zimmer) zimmer_obj;
            if (val.getName().equals(name)) {
                return val;
            }
        }
        return null;


    }
}
