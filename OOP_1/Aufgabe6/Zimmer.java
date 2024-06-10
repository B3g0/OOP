public class Zimmer {
    private String name;
    private My_List lampen;

    /**
     * Konstructor der Klasse Zimmer
     * @param name string, beschreibt den Namen des Zimmers, ueber welches es angeprochen wird
     */
    public Zimmer(String name) {
        this.name = name;
        this.lampen = new My_List();
    }

    /**
     * Getter-Methode für den Namen
     * @return string
     */
    public String getName() {
        return name;
    }

    /**
     * Methode zum hinzufuegen von Lampen in ein Zimmer
     * @param lampe ist die Lampe, die hinzugefuegt wird
     */
    public void add_Lampe(Lampe lampe){
        lampen.add(lampe);
    }

    /**
     * Methode zum loeschen von Lampen aus einem Zimmer
     * @param lampe ist die Lampe, die geloescht wird
     * @return True bei erfolg, sonst False
     */
    public boolean delete_Lampe(Lampe lampe){
        return lampen.remove(lampe);
    }

    /**
     * Getter-Methode, die eine Lampe anhand ihrer eindeutigen Nummer zurueckgibt
     * @param num ist die Nummer der Lampe
     * @return die Lampe mit der angegebenen Nummer, falls es diese gibt. Sonst wird Null zurueckgegeben
     */
    public Lampe getLampeMitNummer(int num){

        for(Object lamp_obj : lampen){
            Lampe lampe = (Lampe) lamp_obj;

            if(lampe.getNummer() == num){
                return lampe;
            }
        }
        return null;
    }

    /**
     * berechnet die Gesamtwatt aller Leuchtmittel
     * Vorbedingung: object != null
     * @return double, die Summe an Watt aller Leuchtmittel
     */
    public double calc_Watt_aller_Leuchtmittel_raw() {

        double sum = 0;

        for (Object object : lampen) {
            Lampe lampe = (Lampe)object;
            sum += lampe.getLeuchtmittel().getWatt();
        }
        return sum;
    }

    /**
     * berechnet die durchschnittliche Wattzahl eines Leuchtmittels
     * @param clazz ist das Leuchtmittel, fuer welches der Durchschnitt berechnet werden soll
     * Vorbedingung: object != null
     * Nachbedingung: Summiert die Watt aller Eintraege eines Leuchtmittels auf und dividiert das Ergebnis durch die Anzahl
     * Im Fall Anzahl == 0 liefert sie 0 zurueck um Division durch 0 zu vermeiden.
     * @return double, den Durchschnitt
     */
    public double calc_Watt_nach_Leuchtmittel_Art(Class clazz){

        double sum = 0;
        int anzahl = 0;

        for(Object object: lampen){

            Lampe lampe = (Lampe)object;

            if (clazz.isInstance(lampe.getLeuchtmittel())){

                sum += lampe.getLeuchtmittel().getWatt();
                anzahl++;
            }
        }
        if (anzahl == 0){
            return 0;
        }
        return sum/anzahl;
    }

    /**
     * berechnet die durchschnittliche Wattzahl fuer alle Leuchtmittel
     * @return double, den Durchschnitt
     */
    public double calc_Watt_von_allen_Leuchtmitteln(){
        return calc_Watt_nach_Leuchtmittel_Art(Leuchtmittel.class);
    }

    /**
     * berechnet die durchschnittliche Wattzahl einer  Lampenart
     * @param clazz ist die Lampenart, fuer welche der Durchschnitt berechnet werden soll
     * Vorbedingung: object != null
     * Nachbedingung: Summiert die Watt aller Eintraege einer Lampenart auf und dividiert das Ergebnis durch die Anzahl
     * Im Fall Anzahl == 0 liefert sie 0 zurueck um Division durch 0 zu vermeiden.
     * @return double, den Durchschnitt
     */
    public double calc_Watt_nach_Lampen_Art(Class clazz){

        double sum = 0;
        int anzahl = 0;

        for(Object object: lampen){

            Lampe lampe = (Lampe)object;

            if (clazz.isInstance(lampe)){

                sum += lampe.getLeuchtmittel().getWatt();
                anzahl++;
            }
        }
        if (anzahl == 0){
            return 0;
        }
        return sum/anzahl;
    }

    /**
     * berechnet die durchschnittliche Einschaltdauer einer Lampen- und Leuchtmittelart
     * @param lampen_clazz ist die Lampe, fuer die der Durchschnitt berechnet werden soll
     * @param leuchtmittel_clazz ist das Leuchtmittel, fuer das Der Durchschnitt berechnet werden soll
     * Vorbedingung: object != null
     * Nachbedingung: Summiert die Einschaltdauer aller Eintraege einer Lampenart und eines Leuchtmittels auf und
     * dividiert das Ergebnis durch die Anzahl
     * Im Fall Anzahl == 0 liefert sie 0 zurueck um Division durch 0 zu vermeiden.
     * @return double, den Durchschnitt
     */
    public double calc_Dauer_nach_Lampen_und_Leuchtmittel_Art(Class lampen_clazz, Class leuchtmittel_clazz){

        double sum = 0;
        int anzahl = 0;

        for(Object object: lampen){

            Lampe lampe = (Lampe)object;

            if (lampen_clazz.isInstance(lampe) && leuchtmittel_clazz.isInstance(lampe.getLeuchtmittel())){

                sum += lampe.getDauer();
                anzahl++;
            }
        }
        if (anzahl == 0){
            return 0;
        }
        return sum/anzahl;
    }

    /**
     * berechnet die durchschnittliche Einschaltdauer aller einfachen Lampen
     * @return double, den Durchschnitt
     */
    public double calc_Dauer_einfacher_Lampen(){
        return calc_Dauer_nach_Lampen_und_Leuchtmittel_Art(Einfache_Lampe.class, Leuchtmittel.class);
    }

    /**
     * berechnet die durchschnittliche Einschaltdauer eines Leuchtmittels der einfachen Lampen
     * @param leuchtmittel_clazz ist das Leuchtmittel, fuer welches der Durchschnitt berechnet werden soll
     * @return double, den Durchschnitt
     */
    public double calc_Dauer_einfacher_Lampen_nach_Leuchtmittel(Class leuchtmittel_clazz){
        return calc_Dauer_nach_Lampen_und_Leuchtmittel_Art(Einfache_Lampe.class, leuchtmittel_clazz);
    }

    /**
     * berechnet die durchschnittliche Einschaltdauer aller dimmbaren Lampen
     * @return double, den Durchschnitt
     */
    public double calc_Dauer_dimmbare_Lampen(){
        return calc_Dauer_nach_Lampen_und_Leuchtmittel_Art(Dimmbare_Lampe.class, Leuchtmittel.class);
    }

    /**
     * berechnet die durchschnittliche Einschaltdauer eines Leuchtmittels der dimmbaren Lampen
     * @param leuchtmittel_clazz ist das Leuchtmittel, fuer welches der Durchschnitt berechnet werden soll
     * @return double, den Durchschnitt
     */
    public double calc_Dauer_dimmbarer_Lampen_nach_Leuchtmittel(Class leuchtmittel_clazz){
        return calc_Dauer_nach_Lampen_und_Leuchtmittel_Art(Dimmbare_Lampe.class, leuchtmittel_clazz);
    }


    /**
     * Hilfsmethode zur Berechnung von erzeugten Lumen pro Watt für LED's
     * Vorbedingung: lampen != null
     * Nachbedingung: Zählt die Lumen der LED's zusammen und liefert als Rückgabe die Summe daraus durch die Anzahl der Watt.
     * Im Fall watt == 0 liefert sie 0 zurueck um Division durch 0 zu vermeiden.
     * @return double
     */
    public double calc_Lichtausbeute_in_Lumen_pro_Watt(){

        double sum = 0;
        int watt = 0;

        for(Object object: lampen){

            Lampe lampe = (Lampe)object;

            if (lampe.getLeuchtmittel() instanceof LED){

                sum += ((LED) lampe.getLeuchtmittel()).getLumen();
                watt += lampe.getLeuchtmittel().getWatt();
            }
        }
        if (watt == 0){
            return 0;
        }
        return sum/watt;

    }
    
    /**
     * Hilfsmethode zur Berechnung von der erzeugten Abwärme der Gluehbirnen
     * Vorbedingung: object != null
     * Nachbedingung: Summiert die erzeugte Abwaerme und liefert sie zurueck. Im Fall, dass keine Abwaerme entsteht,
     * bzw maxTemp nicht neu belegt wird, wird 0 zurueckgegeben.
     * @return double
     */
    public double calc_Max_Temp(){

        double maxTemp = 0;

        for(Object object: lampen){

            Lampe lampe = (Lampe)object;

            if (lampe.getLeuchtmittel() instanceof Gluehbirne){

                double tmpTemp = ((Gluehbirne) lampe.getLeuchtmittel()).getMaxTemp();

                if (tmpTemp > maxTemp){
                    maxTemp = tmpTemp;
                }
            }
        }

        return maxTemp;
    }

    /**
     * Methode, die ein ganzes Zimmer mit allen Lampen und ihren durchschnittswerten ausgibt
     *  Vorbedingung: object != null
     */
    public void print(){
        System.out.println("Zimmer: " + getName());
        System.out.println();
        System.out.println("Lampen: ");


        for (Object object: lampen){

            System.out.println(object);
        }

        System.out.println();
        System.out.println("Durchschnittlich berechnete Werte:");
        System.out.println(" Nennleistung aller Lampen: " + calc_Watt_von_allen_Leuchtmitteln());
        System.out.println(" Nennleistung aller Lampen mit LEDs: " + calc_Watt_nach_Leuchtmittel_Art(LED.class));
        System.out.println(" Nennleistung aller Lampen mit Gluehbirnen: " + calc_Watt_nach_Leuchtmittel_Art(Gluehbirne.class));
        System.out.println(" Nennleistung aller einfachen Lampen: " + calc_Watt_nach_Lampen_Art(Einfache_Lampe.class));
        System.out.println(" Nennleistung aller dimmbaren Lampen: " + calc_Watt_nach_Lampen_Art(Dimmbare_Lampe.class));
        System.out.println(" Einschaltdauer aller einfachen Lampen: " + calc_Dauer_einfacher_Lampen());
        System.out.println(" Einschaltdauer aller einfachen Lampen mit LEDs: " + calc_Dauer_einfacher_Lampen_nach_Leuchtmittel(LED.class));
        System.out.println(" Einschaltdauer aller einfachen Lampen mit Gluehbirnen: " + calc_Dauer_einfacher_Lampen_nach_Leuchtmittel(Gluehbirne.class));
        System.out.println(" Einschaltdauer aller dimmbaren Lampen: " + calc_Dauer_dimmbare_Lampen());
        System.out.println(" Einschaltdauer aller dimmbaren Lampen mit LEDs: " + calc_Dauer_dimmbarer_Lampen_nach_Leuchtmittel(LED.class));
        System.out.println(" Einschaltdauer aller dimmbaren Lampen mit Gluehbirnen: " + calc_Dauer_dimmbarer_Lampen_nach_Leuchtmittel(Gluehbirne.class));
        System.out.println(" Lichtausbeute aller LEDs: " + calc_Lichtausbeute_in_Lumen_pro_Watt() + " Lumen/Watt");
        System.out.println(" Maximale Temperatur aller Gluehbirnen: " + calc_Max_Temp());
        System.out.println();
}
    
}
