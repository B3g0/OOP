
/*
Wer machte was:
    Paul hat die grundsaetzlichen Klassen geschrieben
    Michael hat das Zimmer erledigt
    Mensur hat das Haus gemacht

    Die Test Klasse wurde von uns allen gemeinsam implementiert.
 */


public class Test {
    public static void main(String[] args) {
        //Test eines leeren Zimmers nach 2. Absatz von "Wie die Aufgabe zu loesen ist"
        System.out.println("Test eiens leeren Zimmers --> Test von Div durch 0");
        Zimmer empty = new Zimmer("Empty");
        empty.print();

        System.out.println("Normaler Test:");
        System.out.println();

        //Namen der moeglichen Haeuser und Zimmer
        String[] hausName = new String[] {"Freihaus", "Hauptgebaude", "Bibliothek", "Inflab", "Fachschaft"};
        String[] zimmerName = new String[] {"Wohnzimmer", "wC", "Kueche", "Schlafzimmer", "Abstellkammer", "Windfang","Garage"};

        //Anzahl der Haeuser und Zimmer ist zufaellig fuer jeden Test, entspricht aber max der Anzahl der uebergebenen
        // Namen

        int anzahlHaeuser = util.random(1, hausName.length-1);
        for (int i = 0; i < anzahlHaeuser; i++) {

            //Haus wird mithilfe von Helper Methoden generiert, geprintet, veraendert und nochmal geprintet
            System.out.println("Gerneriere neues Haus "+hausName[i]);
            Haus h = new Haus(hausName[i]);
            int anzahlZimmer = util.random(1, zimmerName.length-1);

            for (int j = 0; j < anzahlZimmer; j++) {
                h.add_Zimmer(generateZimmer(zimmerName[j]));
            }
            h.print_Zimmer_Info();

            System.out.println("Aendere Haus...");

            //Ein Zimmer wird mit 20% Wahrscheinlichkeit entfernt. Sonst wird es veraendert.
            int j = 0;
            Zimmer currentZimmer = h.getZimmerMitName(zimmerName[j]);
            while(currentZimmer != null){
                if(util.random(0,5) == 0){
                    h.remove_Zimmer(currentZimmer);
                } else {
                    modifyZimmer(currentZimmer);
                }
                j++;
                currentZimmer = h.getZimmerMitName(zimmerName[j]);
            }
            //Neue Zimmer werden nur dann angefuegt, wenn vorher noch nicht die maximale Anzahl an Zimmern vorhanden war
            // Wird ein neues Zimmer angefuegt, wird ein Name verwendet, der noch nie zuvor fuer dieses Haus verwendet
            //wurde.
            if(j < zimmerName.length){
                int numNewZimmer = util.random(0,3);
                for (int k = 0; k < numNewZimmer && j<zimmerName.length; k++) {
                    Zimmer tmpZimmer = generateZimmer(zimmerName[j]);
                    h.add_Zimmer(tmpZimmer);
                    j++;
                }
            }

            h.print_Zimmer_Info();
        }
    }


    /**
     * Generiere ein neues Zimmer mit angegebenen Namen und einer zufaelligen Anzahl (zwischen 2 und 6) an Lampen.
     * Diese Lampen werden durch eine weitere Helper-Methode von zufaelligen Werten generiert.
     * @param name der Name des Zimmers
     * @return ein Zimmer mit zufaelligen Werten (2-6 Lampen)
     */
    private static Zimmer generateZimmer(String name){
        Zimmer z = new Zimmer(name);
        int number_lamps = util.random(2,6);

        for(int i = 0; i<number_lamps; i++){
            Lampe lampe = generateLampe(i);
            z.add_Lampe(lampe);
        }

        return z;
    }

    /**
     * Generiert eine Lampe mit angegebener Nummer und zufaelligen Werten. Das Leuchtmittel wird wiederum von einer
     * Helper-Methode zufaellig generiert.
     * @param num die eindeutige Nummer der Lampe
     * @return eine Lampe mit zufaelligen Werten
     */
    private static Lampe generateLampe(int num){
        Lampe lampe;
        Leuchtmittel leucht = generateLeuchtmittel();

        if(util.random(0,9)%2 ==0){
            double dimmfaktor = util.random(0,100)/100.0;
            lampe = new Dimmbare_Lampe(num, leucht, dimmfaktor);

        } else {
            int randFarbe = util.random(0,2);
            Einfache_Lampe.Farbe farbe = Einfache_Lampe.Farbe.values()[randFarbe];
            lampe = new Einfache_Lampe(num, leucht, farbe);
        }

        lampe.setDauer(util.random(0,1000));

        return lampe;

    }

    /**
     * Generiert ein zufaelliges Leuchtmittel mit semi-realistischen Werten fuer Watt und Lumen oder Temperatur
     * @return das neue Leuchtmittel
     */
    private static Leuchtmittel generateLeuchtmittel(){
        Leuchtmittel leucht;

        if(util.random(0,9)%2 ==0){
            leucht = new LED(util.random(5,20), util.random(100,1000));
        }else{
            leucht = new Gluehbirne(util.random(60, 200), util.random(50,110));
        }

        return leucht;
    }

    /**
     * Veraendert von einem vorhandenem Zimmer jede Lampe. Mit 20% Wahrscheinlichkeit wird jede Lampe entfernt und es
     * werden bis zu 2 weitere Lampen hinzu gefuegt. Wird eine Lampe nicht entfernt, wird sie an die Helper Methode
     * modifyLamp weiter geleitet
     * Vorbedingung: Zimmer darf nicht NULL sein!
     * @param zimmer das Zimmer, das veraendert werden soll
     * @return ein veraendertes Zimmer
     */
    private static Zimmer modifyZimmer(Zimmer zimmer){
        int i = 0;
        Lampe lampe = zimmer.getLampeMitNummer(i);

        while(lampe != null){
            if(util.random(0,5)==0){
                zimmer.delete_Lampe(lampe);
            } else {
                modifyLampe(lampe);
            }

            i++;
            lampe = zimmer.getLampeMitNummer(i);
        }

        int numNewLamps = util.random(0,3);
        for (int j = 0; j < numNewLamps; j++) {
            Lampe l = generateLampe(i);
            zimmer.add_Lampe(l);
            i++;
        }

        return zimmer;
    }

    /**
     * Veraendere eine vorhandene Lampe. Mit 20% Wahrscheinlichkeit wird das Leuchtmittel ersetzt. Sosnt wird
     * die Dauer um bis zu 10 Std erhoeht und im Fall einer Dimmbaren Lampe die Dimmbarkeit veraendert.
     * Vorbedingung: lampe darf nicht NULL sein
     * @param lampe Die Lampe, die veraendert werden soll
     * @return die veraenderte Lampe
     */
    private static Lampe modifyLampe(Lampe lampe){
        int dauer = lampe.getDauer();
        dauer+= util.random(0,10);
        lampe.setDauer(dauer);

        boolean leuchtmittelErsetzen = util.random(0,5)==0;

        if(leuchtmittelErsetzen){
            Leuchtmittel leucht = generateLeuchtmittel();
            lampe.setLeuchtmittel(leucht);
        }

        if(lampe instanceof Dimmbare_Lampe){
            double dimmfaktor = util.random(0,100)/100.0;
            ((Dimmbare_Lampe) lampe).setDimmfaktor(dimmfaktor);
        }

        return lampe;
    }

}
