package packsimulation;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Eine Liste bestehend aus {@link Node}s
 */
public class NodeList implements Iterable {

    /**
     * Die Groesse der liste
     */
    private int size = 0;
    /**
     * Der erste Node der Liste. Erlaubt ein Iterieren von Anfang bis Ende.
     */
    private Node first;
    /**
     * Der letzte Node der Liste. Erlaubt ein Iterieren von Ende bis Anfang.
     */
    private Node last;

    /**
     * Das naechste Element der Liste
     */
    private Node next;
    /**
     * Das aktuelle Element der Liste
     */
    private Node current;

    /**
     * Liefert einen Iterator ohne Predicate
     * @return Ein Iterator ohne Predicate
     */
    @Override
    public Iterator iterator() {
        // take all elements from nodelist
        return iteratorWithPredicate(t -> true);
    }

    /**
     * Liefert einen Iterator mit Predicate
     * @param predicate Das Predicate mit dem gefiltert wird.
     * @return Ein Iterator der nur Elemente liefert fuer die {@link Predicate#test(Object)} true ergibt.
     */
    private Iterator iteratorWithPredicate(Predicate predicate) {
        next = first;
        current = null;
        Iterator it = new Iterator() {
            @Override
            public boolean hasNext() {
                if (next != null) {
                    // Da wir keine Generics verwenden, gibt es hier ein Warning zu einer unchecked operation.
                    // Wenn wir Predicate mit Generics verwenden wuerden, gaebe es kein Warning.
                    while (!predicate.test(next.getValue())) {
                        next = next.getNachfolger();
                        if (next == null) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }

            @Override
            public Nodeable next() {
                if (next == null)
                    return null;

                Node node = next;
                next = next.getNachfolger();
                if (next == null) {
                    current = node;
                }
                return node.getValue();
            }

            /**
             * Use NodeList.remove instead
             */
            @Override
            @Deprecated
            public void remove() {
                Node node;
                if (next == null) {
                    if (current != null) {
                        node = current;
                    } else {
                        return;
                    }
                } else {
                    node = next.getVorgaenger();
                }

                if (node != null) {
                    node.remove();
                }
            }
        };
        return it;
    }

    /**
     * Fuege ein {@link Nodeable} in die Liste hinzu.
     * @param value Ein {@link Nodeable}
     */
    public void add(Nodeable value) {
        size++;
        if (first == null) {
            first = new Node();
            first.setValue(value);
            last = first;
        } else {
            last.add(value);
            last = last.getNachfolger();
        }
    }

    /**
     * Entferne ein {@link Nodeable} aus der Liste
     * @param value Das zu entfernende {@link Nodeable}
     */
    public void remove(Nodeable value) {
        if (value == null) {
            throw new IllegalArgumentException("Value darf nicht null sein!");
        }
        if (first.getValue() == value) {
            first = first.getNachfolger();
            size--;
            if (first != null) {
                first.setVorgaenger(null);
                return;
            }
        }

        if (last.getValue() == value) {
            last = last.getVorgaenger();
            size -= 1;
            if (last != null) {
                last.setNachfolger(null);
                return;
            }
        }

        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            Object n = iterator.next();
            if (n == value) {
                size--;
                iterator.remove();
                return;
            }
        }
    }

    /**
     * Liefert die Groesse der Liste
     * @return Die Groesse der Liste
     */
    public int getSize() {
        return size;
    }
}
