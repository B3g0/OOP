import java.util.Iterator;

/*
Unsere eigene Implementierung einer Liste bestehend aus den Klassen My_List, Node und MyIterator
 */
public class My_List implements Iterable<Object>{

    private Node lastNode;
    private int nodeCount;

    My_List() {
        this.lastNode = null;
        this.nodeCount = 0;
    }

    int size() {
        return nodeCount;
    }

    boolean isEmpty() {
        return this.nodeCount == 0;
    }

    /*
    contains-Methode der Liste die mit Hilfe des Iterators das gesuchte Element sucht und einen bool-Wert als Rückgabewert
    zurückgibt
    VB(Vorbedingung): data != null
    NB(Nachbedingung): gibt true oder false zurueck wenn data in der Liste enthalten ist
     */
    boolean contains(Object data){
        if (this.isEmpty()) {
            return false;
        }
        Node currentNode = new Node(data);
        java.util.Iterator it = iterator();
        while(it.hasNext()){
            if(it.next().equals(data)){
                return true;
            }
        }
        return false;

    }

    @Override
    public My_Iterator iterator(){
        return new My_Iterator(getNode(0));
    }

    /*
    add-Methode die data in die Liste einfügt
    Die Methode überprüft zuerst ob die Liste leer ist und führt dann die dementsprechenden Schritte aus. Falls nein
    wird das Element mit einem Index versehen, welcher auf die letzte Stelle zeigt, und als letztes Element der Liste
    angefügt. Im Anschluss wird die neue lastNode deklariert und der nodeCount um 1 erhöht.
    VB: data != null
    NB: fügt data in Liste an letzte Stelle ein
     */
    void add(Object data) {
        Node currentNode = new Node(data);

        if (this.lastNode != null) {
            currentNode.index = lastNode.index + 1;
            currentNode.previousNode = lastNode;
            lastNode.nextNode = currentNode;
        } else {
            currentNode.previousNode = null;
            currentNode.index = 0;
        }
        this.lastNode = currentNode;
        this.nodeCount++;
    }

    /*
    get-Methode
    Holt durch den Index das entsprechende Element aus der Liste (wird nicht entfernt)
    Zuerst wird durch das if-Statement auf Fehler überprüft um Exceptions zu vermeiden
    Dann wird count berechnet, mit dem man dann in der while-Schleife auf die Position des gesuchten Index kommt
    und data returnen kann
    VB: index >= 0
    NB: return value ist data auf dem jeweiligen Index
     */
    Object get(int index) {
        //error handling
        if (this.isEmpty() || index < 0 || index >= this.nodeCount) {
            return null;
        }
        //
        Node currentNode;
        int count = lastNode.index - index;
        currentNode = lastNode;

        while (count > 0) {
            currentNode = currentNode.previousNode;
            count--;
        }

        return currentNode.data;
    }

    /*
    get-Methode macht im Prinzip fast das gleiche wie get, nur wird statt data die ganze Node als return value
    zurück gegeben.
    VB: index > 0
    NB: return value ist die ganze Node
     */
    Node getNode(int index) {
        //error handling
        if (this.isEmpty() || index < 0 || index >= this.nodeCount) {
            return null;
        }

        int count = lastNode.index - index;
        Node currentNode = lastNode;

        while (count > 0) {
            currentNode = currentNode.previousNode;
            count--;
        }

        return currentNode;
    }

    /*
    insert fügt data an der gewünschten Stelle index ein
    Dabei werden die pointer der Nodes neu ausgerichtet um die Reihenfolge zu wahren und anschließend deren indizes
    angepasst
    VB: index > 0
    NB: data wird an der Stelle index eingefügt und gibt true bei erfolgreichem insert zurück, sonst false
     */
    boolean insert(Object data, int index) {
        Node currentNode;

        if (this.getNode(index) != null) {
            Node newNode = new Node(data);
            currentNode = this.getNode(index);
            newNode.index = index;

            if (currentNode.previousNode != null) {
                currentNode.previousNode.nextNode = newNode;
                newNode.previousNode = currentNode.previousNode;
                currentNode.previousNode = newNode;
                newNode.nextNode = currentNode;
            } else {
                currentNode.previousNode = newNode;
                newNode.nextNode = currentNode;
            }
            currentNode = newNode;

            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
                currentNode.index++;
            }

            this.nodeCount++;
            return true;
        } else {
            return false;
        }
    }

    /*
    remove-Methode
    Entfernt das Element obj aus der Liste, sollte es vorhanden sein.
    VB: obj != null
    NB: entfernt den Eintrag equals zu obj und gibt true zurück, sonst false
     */
    boolean remove(Object obj){

        My_Iterator it = iterator();

        while(it.hasNext()){
            if(it.next().equals(obj)){
                it.remove();
                return true;
            }
        }
        return false;
    }

    /*
    remove-Methode
    Entfernt die Node auf dem jeweiligen Index und passt dann die anderen Nodes sowie deren Indizes an
    VB: index >= 0
    NB: entfernt den Eintrag bei index und gibt true zurück, sonst false
     */
    boolean remove(int index) {
        Node currentNode = this.getNode(index);

        if (currentNode != null) {
            if(currentNode.previousNode != null){
                currentNode.previousNode.nextNode = currentNode.nextNode;
            }
            if(currentNode.nextNode != null){
                currentNode.nextNode.previousNode = currentNode.previousNode;
            } else {
                this.lastNode = currentNode.previousNode;
            }
            if (this.isEmpty()) {
                this.lastNode = null;
            }

            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
                currentNode.index--;
            }

            this.nodeCount--;
            return true;
        } else {
            return false;
        }
    }
    /*
    Klasse Node für die Nodes
     */
    private class Node {
        Node nextNode = null;
        Node previousNode = null;
        int index;
        Object data;

        Node(Object data){
            this.data = data;
        }
    }

    /*
    My_Iterator Klasse für den My_Iterator mit entsprechenden Hilfsmethoden hasNext, next und remove
     */
    private class My_Iterator implements Iterator {

        private Node currentNode;
        private Node lastNode;

        My_Iterator(Node currentNode) {

            this.currentNode = currentNode;
        }

        @Override
        public boolean hasNext() {
            if (currentNode == null) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Object next() {
            Object result = currentNode.data;
            lastNode = currentNode;
            currentNode = currentNode.nextNode;
            return result;
        }

        @Override
        public void remove() {
            if (lastNode == null) return;
            My_List.this.remove(lastNode.index);
        }
    }

}