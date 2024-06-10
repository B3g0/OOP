package packsimulation;

/**
 * Ein Knotenpunkt in {@link NodeList}
 */

public class Node {

    /**
     * Der vorherige Knotenpunkt in der Liste.
     */
    private Node vorgaenger;
    /**
     * Der nachfolgende Knotenpunkt in der Liste.
     */
    private Node nachfolger;

    /**
     * Das enthaltene Element
     */
    private Nodeable value;

    /**
     * Erzeugt einen neuen, leeren Knotenpunkt.
     */
    public Node() {
    }

    /**
     * Liefert den Vorgaenger des Knotenpunkts.
     * @return Den Vorgaenger des Knotenpunkts.
     */
    public Node getVorgaenger() {
        return vorgaenger;
    }

    /**
     * Setzt den Vorgaenger des Knotenpunkts.
     * @param vorgaenger Den Vorgaenger des Knotenpunkts.
     */
    public void setVorgaenger(Node vorgaenger) {
        this.vorgaenger = vorgaenger;
    }

    /**
     * Liefert den getNachfolger des Knotenpunkts.
     * @return Den getNachfolger des Knotenpunkts.
     */
    public Node getNachfolger() {
        return nachfolger;
    }

    /**
     * Setzt den Nachfolger des Knotenpunkts.
     * @param nachfolger Den Nachfolger des Knotenpunkts.
     */
    public void setNachfolger(Node nachfolger) {
        this.nachfolger = nachfolger;
    }

    /**
     * Liefert das enthaltene Element.
     * @return Das enthaltene Element
     */
    public Nodeable getValue() {
        return value;
    }

    /**
     * Setzt das enthaltene Element.
     * @param value Das enthaltene Element.
     */
    public void setValue(Nodeable value) {
        this.value = value;
    }

    public void add(Nodeable value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if(this.getNachfolger() != null) {
            this.getNachfolger().setVorgaenger(newNode);
        }
        newNode.setNachfolger(this.getNachfolger());
        newNode.setVorgaenger(this);
        this.setNachfolger(newNode);
    }

    /**
     * Entfernt den Node durch Loeschen des vorhergehenden und nachfolgenden Nodes
     */
    public void remove() {
        Node vorgaenger = getVorgaenger();
        Node nachfolger = getNachfolger();
        if(vorgaenger != null) {
            vorgaenger.setNachfolger(nachfolger);
        }
        if(nachfolger != null) {
            nachfolger.setVorgaenger(vorgaenger);
        }
        setVorgaenger(null);
        setNachfolger(null);
    }
}