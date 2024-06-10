import javafx.application.Application;
import oopb3.Applikation3d;

public class Test {

    /***
     *
     * -1. Biancas Feedback zu UE1-3 eingearbeitet - erledigt.
     *
     * 0. Biancas Feedback von UE1 eingearbeitet:
     * 0.1 Langsamer simulieren - erledigt
     *
     * 0.2 Tiere die sich zufällig bewegen farblich hervorheben - erledigt. Außerdem Simulation übersichtlicher gemacht:
     * Sich zufällig bewegende Tiere ändern sih nicht mehr bei jedem Simulationsschritt, sondern alle x (konfigurierbar) Sekunden.
     *
     * 0.3 Dokumentation verbessern - erledigt. jede Klasse und Funktion hat JavaDoc.
     *
     * 0.4 Keine Zyklischen Abhängigkeiten - erledigt. Keine zyklischen Abhängigkeiten mehr durch Refaktorisierung von Simulation und Schwarm.
     *
     * 0.5 Alles was zusammengehört, gehört auch in eine Klasse - erledigt. SchwarmKonfiguration ist eine innere Klasse von SchwarmKonfiguration.
     *
     * 0.6 Möglichst wenige Parameter der Simulation sollen hardcodiert sein.
     * - erledigt. 4 zusaetzliche Konfigurationsparameter eingeführt:
     *     anzahlAmeisen, zufaelligZuBewegendeAmeisen, simulationsDauer, zufaelligeBewegungsDauer.
     *
     * VB: Noetige Hardware und JavaFX Support vorhanden
     * NB: Applikation wurde gestartet
     */
    public static void main(String[] args) {
        // Starten den JavaFX - Container und startet die Klasse Applikation3d und ruft die Methode Applikation3d.start() auf
        // Aufgrund der Art wie JavaFX designed ist, werden die Simulationskonfigurations-Parameter im Konstruktor der Klasse
        // Applikation3d gesetzt. Dort lassen sich neue Konfigurationen hinzufuegen, oder die bestehenden veraendern
        Application.launch(Applikation3d.class);
    }
}
