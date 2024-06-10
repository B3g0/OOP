package packsimulation;

/**
 * Eine Hormonwert
 */
public class Hormone implements Nodeable{

    /**
     * Zeitpunkt zu dem der Hormon diesen Wert hatte
     */
    private final int timestamp;
    /**
     * Typ des Hormons des Hormonwerts
     */
    private final HormoneType type;
    /**
     * Wert des Hormonwerts
     */
    private final float value;

    /**
     * Erzeugt einen neuen Hormonwert
     * @param timestampInMinutes siehe Klassenvariable
     * @param type siehe Klassenvariable
     * @param value siehe Klassenvariable
     */
    public Hormone(int timestampInMinutes, HormoneType type, float value) {
        this.timestamp = timestampInMinutes;
        this.type = type;
        this.value = value;
    }

    /**
     * Liefert den Zeitpunkt des Hormonwerts zurueck
     * @return Der Zeitpunkt des Hormonwerts
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Liefert den Typ des Hormonwerts
     * @return Der Typ des Hormonwerts
     */
    public HormoneType getType() {
        return type;
    }


    /**
     * Liefert den Wert des Hormonwerts
     * @return Der Wert des Hormonwerts
     */
    public float getValue() {
        return value;
    }
}
