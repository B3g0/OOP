package Rest;

import Anlagen.Anlage;
import MyStack.MyStack;
import Rohre.*;

public class Lager {

    private MyStack normKlein;
    private MyStack normGross;
    private MyStack sauerKlein;
    private MyStack sauerGross;

    /**
     * Konstruktor der Klasse Lager
     */
    public Lager() {
        normGross = new MyStack();
        normKlein = new MyStack(normGross);
        sauerGross = new MyStack();
        sauerKlein = new MyStack(sauerGross);

    }

    /**
     * Getter Methoden für alle Parameter der Klasse Lager
     */
    public MyStack getNormKlein() {
        return normKlein;
    }

    public MyStack getNormGross() {
        return normGross;
    }

    public MyStack getSauerKlein() {
        return sauerKlein;
    }

    public MyStack getSauerGross() {
        return sauerGross;
    }

    /**
     * Montiert Rohr in Anlage
     * VB: Anlage != null && Lager != null
     * NB: Als return wird Methode in anlage aufgerufen die dann das richtige Rohr für die jeweilige
     * Anlage auswählt und montiert
     */
    public Rohr rohrMontieren(Anlage anlage){
        return anlage.montiereRohr(this);
    }

    /**
     * Lagert das gewählte Rohr in das Lager
     * VB: Rohr != null && Lager != null
     * NB: Methode aus Rohr wirdEingelagert wird ausgerufen die dann das entsprechende Rohr in das Lager einlagert
     */
    public void rohrEinlagern(Rohr rohr){
        rohr.wirdEingelagert(this);
    }

    /**
     * Print-Methode für den Gesamtwert aller im Lager gelagerten Rohre
     */
    public void lagerwert () {
        System.out.println("Wert der Rohre im Lager: "+(getNormKlein().sumValues() + getNormGross().sumValues() + getSauerKlein().sumValues() + getSauerGross().sumValues()));
    }

    /**
     * Print-Methode für alle im Lager gelagerten Rohre
     */
    public void lagerliste(){

        System.out.println("Rohre im Lager: ");
        getNormKlein().printRohre();
        getNormGross().printRohre();
        getSauerKlein().printRohre();
        getSauerGross().printRohre();

    }

}
