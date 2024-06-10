package Anlagen;

import MyStack.MyStack;
import Rest.Lager;
import Rohre.Rohr;

/**
 * Abstrakte Klasse der Klasse Anlage
 */
public abstract class Anlage {
    private MyStack installedRohre;

    //Neuer Stack wird für die installierten Rohre erstellt
    Anlage() {
        installedRohre = new MyStack();
    }

    //Getter für die installierten Rohre
    MyStack getInstalledRohre() {
        return installedRohre;
    }

    //Interface Methode für das montieren von Rohren
    public abstract Rohr montiereRohr(Lager lager);

    /**
     * Methode zum Demontieren der Rohre aus dem übergebenen Lager
     * VB: Lager != null && installedRohre != null
     * NB: besagtes Rohr wird in das entsprechende Lager gelagert
     */
    public void demontiere(Lager lager){
        lager.rohrEinlagern(installedRohre.pollOrRohrNull());
    }

    //Print-Methode zum ausgeben des Gesamtwertes aller Rohre in einer Anlage
    public void anlagewert(){
        System.out.println("Wert der installierten Rohre: " + getInstalledRohre().sumValues());
    }

    //Print-Methode zum Ausgeben der installierten Rohre in einer Anlage
    public void anlageliste(){
        System.out.println("Liste der installierten Rohre: ");
        installedRohre.printRohre();
    }

}
