package MyStack;

import Rohre.Rohr;
import Rohre.RohrNull;

/**
 * Ein Stack (LIFO) für die Klasse Rohre! Er wurde mit der Absicht geschrieben, IF Abfragen weitestgehend zu vermeiden.
 * Nach erneutem Lesen der Angabe haben wir uns aber dazu entschlossen zugunsten der Einfachheit eine NULL Abfrage bei
 * add zu machen. Alle anderen "checks", die normalerweise mit IF gemacht werden würden, werden mit Unterklassen von
 * Node bewerkstelligt.
 */

public class MyStack {

    //head ist anfaenglich immer ein NullNode (bzw die Unterklasse NullFallbackNode, wenn ein Fallback Stack angegeben
    //wird
    private Node head;

    /**
     * ein Fallback Stack wird verwendet, wenn dieser Stack bei einem Poll NULL zurückgeben würde. Dann wird stattdessen
     * poll vom fallback Stack zurück gegeben
     * @param fallback VB: Darf nicht NULL sein!
     */
    public MyStack(MyStack fallback){
        head = new NullFallbackNode(fallback);
    }

    /**
     * Der normale Konstruktor vom Stack
     */
    public MyStack() {
        this.head = new NullNode();
    }

    /**
     * Fuege ein Rohr an. Dieses soll nicht NULL sein (es wird sicherheitshalber trotzdem gecheckt) und wird zuoberst
     * auf den Stack gelegt.
     * @param value Das Rohr, das angefuegt werden soll. VB: darf nicht NULL sein
     */
    public void add(Rohr value){
        if(value == null) return;
        head = new ValueNode(value, head);
    }

    /**
     * Gibt das zuletzt angefuegte Rohr zurueck und entfernt es aus dem Stack. Wenn kein Rohr vorhanden ist, wird null
     * zurueck gegeben
     * @return das zuletzt angefuegte Rohr oder null
     */
    public Rohr poll(){
        Pair<Node, Rohr> pair = head.poll();
        head = pair.getFirst();
        return pair.getSecond();
    }

    /**
     * Gibt das zuletzt angefuegte Rohr zurueck und entfernt es aus dem Stack. Wenn kein Rohr vorhanden ist, wird
     * ein RohrNull Objekt zurueck gegeben. Dieses kann wiederum direkt in ein Lager eingefuegt werden, ohne eine
     * NULLPOINTER Exception zu werfen (es fuegt sich selbst nicht in ein Lager ein, verhaelt sich daher wie erwartet)
     * @return das zuletzt angefuegte Rohr oder ein RohrNull Objekt
     */
    public Rohr pollOrRohrNull(){
        Pair<Node, Rohr> pair = head.pollOrRohrNull();
        head = pair.getFirst();
        return pair.getSecond();
    }

    /**
     * Summiert die Werte der Rohre rekursiv auf
     * @return den Wert alles Rohre aufsummiert
     */
    public int sumValues(){
        return head.sumValues();
    }

    /**
     * Printet alle toStrings der Rohre in STDOUT. Verwendet dabei Rekursion
     */
    public void printRohre(){
        head.printRohre();
    }

    /**
     * Das Interface fuer Node mit den essentiellen Methoden, die von den jeweiligen Methoden des Stacks weitergeleitet
     * werden. Jeder Node muss auch noch auf den naechsten Node verweisen, da es sich hierbei um einen
     * Linked Stack handelt.
     */
    private interface Node{
        //Poll Befehle geben ein Pair zurueck, die den gesuchten Wert und den naechsten Node des Stacks beinhalten
        //Der naechste Node wird der neue Head des Stacks
        Pair<Node, Rohr> poll();
        Pair<Node, Rohr> pollOrRohrNull();

        int sumValues();
        void printRohre();
    }

    /**
     * Ein Node mit einem Wert. Dieser Node wird immer dann verwendet, wenn durch ein add ein Wert angefuegt werden soll
     */
    private class ValueNode implements Node{

        Rohr value;
        Node next;

        ValueNode(Rohr value, Node next){
            this.value = value;
            this.next = next;
        }

        /**
         * Da dieser Node einen Value besitzt, wird dieser zusammen mit dem naechsten Node zurueck gegeben.
         * @return den Wert des Nodes und den naechsten Node
         */
        @Override
        public Pair<Node, Rohr> poll() {
            return new Pair<>(next, value);
        }

        /**
         * Da dieser Node einen Value besitzt, wird dieser zusammen mit dem naechsten Node zurueck gegeben.
         * @return den Wert des Nodes und den naechsten Node
         */
        @Override
        public Pair<Node, Rohr> pollOrRohrNull() {
            return poll();
        }

        /**
         * Addiert den Wert von diesem Node mit den Werten aller darauffolgenden Nodes
         * @return den aufsummierten Wert vom untersten bis zu diesem Node
         */
        @Override
        public int sumValues() {
            return value.getPriceCent()+next.sumValues();
        }

        /**
         * Printet dieses Rohr und leitet den Befehl an den naechsten Node weiter, um rekursiv durch alle Nodes gehen
         * zu koennen
         */
        @Override
        public void printRohre() {
            System.out.println(value);
            next.printRohre();
        }
    }

    /**
     * Dieser oder die extendede version NullFallbackNode befinden sich immer 1x im Stack als unterstes Objekt und sie
     * reagieren auf Poll Befehle, als ob sie NULL waeren
     */
    private class NullNode implements Node{
        /**
         * Da dieser Node immer zuunterst sein muss, wird er selbst als neuer head uebergeben und der auszugebene Wert
         * ist null
         * @return ein Pair von diesem Node und null
         */
        @Override
        public Pair<Node, Rohr> poll() {
            return new Pair<>(this, null);
        }

        /**
         * Da dieser Node immer zuunterst sein muss, wird er selbst als neuer head uebergeben und der auszugebene Wert
         * ist ein neues RohrNull
         * @return ein Pair von diesem Node und ein neues RohrNull
         */
        @Override
        public Pair<Node, Rohr> pollOrRohrNull() {
            return new Pair<>(this, new RohrNull());
        }

        /**
         * Dieser Node ist die Abschluss Bedingung der rekursiven Funktionen und gibt daher 0 zurueck
         * @return 0, um die anderen Nodes aufsummieren zu koennen
         */
        @Override
        public int sumValues() {
            return 0;
        }

        /**
         * Dieser Node ist die Abschluss Bedingung der rekursiven Funktionen und besitzt daher keine eigenstaendige
         * Funktionalitaet
         */
        @Override
        public void printRohre() {
            //Nothing here
        }
    }

    /**
     * eine spezielle Version des NullNodes, in dem ein Poll den obersten Wert des Fallbacks zurueck gibt anstatt NULL
     * (Sollte der Fallback auch NULL zurueck geben, wird dies weiter geleitet)
     */
    private class NullFallbackNode extends NullNode{
        MyStack fallback;

        /**
         * Konstruktor
         * @param fallback ein weiterer Stack, auf den Poll Befehle weiter geleitet werden. VB: darf nicht NULL sein!
         */
        NullFallbackNode(MyStack fallback){
            this.fallback = fallback;
        }

        /**
         * Da dieser Node immer zuunterst sein muss, wird er als neuer Head uebergeben. Der Wert wird vom Poll des
         * fallbacks weiter geleitet
         * @return ein Pair aus diesem Objekt und dem Wert des Fallbacks
         */
        @Override
        public Pair<Node, Rohr> poll() {
            return new Pair<>(this, fallback.poll());
        }

        /**
         * Da dieser Node immer zuunterst sein muss, wird er als neuer Head uebergeben. Der Wert wird vom speziellen Poll
         * des fallbacks weiter geleitet
         * @return ein Pair aus diesem Objekt und dem Wert des Fallbacks (von pollOrRohrNull)
         */
        @Override
        public Pair<Node, Rohr> pollOrRohrNull() {
            return new Pair<>(this, fallback.pollOrRohrNull());
        }
    }



}
