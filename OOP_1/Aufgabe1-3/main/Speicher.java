package main;

import abstr.Abstract_Quelle;

public class Speicher {

    private String unit;

    private double max_size, current_size;
    private double aging, transportloss;

    /**
     * Konstruktor des Speichers, bei dem Aging und Transportloss 0 gesetzt werden
     * @param size bezeichnet die Ausgangsgroesse ders Speichers.
     */
    public Speicher(int size){
        this(size, 0,0);
    }

    /**
     * Konstruktor des Speichers, wenn man Transportloss und Aging angeben moechte
     * @param size bezeichnet die Ausgangsgroesse des Speichers
     * @param aging um diesen Wert wird der Speicher bei jedem Befuellen und Entleeren verkleinert
     * @param transportloss der Materialverlust pro Uebertragung
     */
    public Speicher(int size, double aging, double transportloss){
        this.aging = aging;
        max_size = size;
        current_size = 0;
        this.transportloss = transportloss;
    }

    /**
     * Fuellt den Speicher
     * Vorbedingung: Die Quelle muss in der Lage sein, Material zu liefern - dies wird durch den uebergebenen hotd angefragt
     * Nachbedingung: Wenn nicht genug Speicher vorhanden ist, wird dieser voll gefuellt und eine Exception wird geworfen
     * @param quelle Source, von dem Material kommt
     * @param hotd Hour of the Day, das an die Quelle weitergegeben wird
     * @throws SpeicherException Wenn der Speicher ausfaellt oder ueberfuellt ist
     */
    public void fill(Abstract_Quelle quelle, int hotd) throws SpeicherException {
        unit = quelle.getUnit();
        age();
        if (Failure.is_failing()) {
            current_size = 0;
            throw new SpeicherException("Speicher ausgefallen!");
        }
        int toFill = quelle.get_Erzeugungsmenge(hotd);

        if (toFill + getCurrent() > max_size) {
            current_size = max_size;
            throw new SpeicherException("Speicher ueberfuellt!");
        } else {
            current_size = (toFill* (1-transportloss)) + getCurrent();
        }
    }

    /**
     * Leert den Speicher
     * Vorbedingung: value sollte uebergeben werden
     * Nachbedingung: Je nachdem ob der Speicher genug liefern kann oder nicht (ueber getCurrent bekommt
     * man aktuellen Speicherstand) wird der Wert value vom Speicher entzogen, sonst wird eine Exception geworfen
     * @param value wie viel entnommen werden sollte
     * @param drainIfNotEnough boolean flag, If set: Storage will be totally emptied if there is not enough stored
     *                         else nothing will be drained if there is not enough in storage
     * @return the amount of storage drained
     */
    public int drain(int value, boolean drainIfNotEnough) throws SpeicherException {
        if (Failure.is_failing()) {
            throw new SpeicherException("Speicher ausgefallen!");
        }
        int result = 0;
        if (getCurrent() < value && drainIfNotEnough) {
            result = getCurrent();
            current_size = 0;
        } else if (getCurrent() >= value) {
            result = value;
            current_size = getCurrent() - value;
        }
        age();
        result = (int)(result * (1-transportloss));
        return result;
    }

    /**
     * Getter des derzeitigen Inhalts
     * @return den derzeitigen Inhalt
     */
    public int getCurrent(){
        return (int)current_size;
    }

    /**
     * Getter des dertzeitigen Maximalwerts des Speichers
     * @return der Maximalwert des Speichers
     */
    public int getMax(){
        return (int)max_size;
    }

    /**
     * Hilfsmethode um Aging zu simulieren
     */
    private void age(){
        max_size = max_size*(1-aging);
    }

    @Override
    public String toString() {
        return getCurrent() + " " + unit;
    }
}
