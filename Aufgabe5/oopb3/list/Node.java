package oopb3.list;

/**
 * Ein Knotenpunkt in der SocialGroup Liste
 * @param <T> Der Typ des enthaltenen Elements.
 */
public class Node<T> {

    /**
     * Der vorherige Knotenpunkt in der Liste.
     */
    private Node<T> vorgaenger;
    /**
     * Der nachfolgende Knotenpunkt in der Liste.
     */
    private Node<T> nachfolger;

    /**
     * Das enthaltene Element
     */
    private T value;

    /**
     * Erzeugt einen neuen, leeren Knotenpunkt.
     */
    public Node() {
    }

    /**
     * Liefert den Vorgaenger des Knotenpunkts.
     * @return Den Vorgaenger des Knotenpunkts.
     */
    public Node<T> getVorgaenger() {
        return vorgaenger;
    }

    /**
     * Setzt den Vorgaenger des Knotenpunkts.
     * @param vorgaenger Den Vorgaenger des Knotenpunkts.
     */
    public void setVorgaenger(Node<T> vorgaenger) {
        this.vorgaenger = vorgaenger;
    }

    /**
     * Liefert den getNachfolger des Knotenpunkts.
     * @return Den getNachfolger des Knotenpunkts.
     */
    public Node<T> getNachfolger() {
        return nachfolger;
    }

    /**
     * Setzt den Nachfolger des Knotenpunkts.
     * @param nachfolger Den Nachfolger des Knotenpunkts.
     */
    public void setNachfolger(Node<T> nachfolger) {
        this.nachfolger = nachfolger;
    }

    /**
     * Liefert das enthaltene Element.
     * @return Das enthaltene Element
     */
    public T getValue() {
        return value;
    }

    /**
     * Setzt das enthaltene Element.
     * @param value Das enthaltene Element.
     */
    public void setValue(T value) {
        this.value = value;
    }
}