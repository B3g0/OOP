package oopb3;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point3D;
import oopb3.agents.Agent;
import oopb3.agents.Ameise;
import oopb3.agents.Vogel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Erweiterung der Klasse Schwarm aus Aufgabe1.
 * Kuemmert sich um die Initialisierung der Tiere/Agenten und deren anfaengliche Verteilung im dreidimensionalen Raum.
 */
public class Schwarm3d {

    private List<Vogel> voegel;
    private List<Ameise> ameisen;

    private SchwarmKonfiguration schwarmKonfiguration;

    /**
     * Konstruktor der Schwarm3d Klasse
     * @param schwarmKonfiguration Die festgelegte Konfiguration für die Simulation != null
     */
    public Schwarm3d(SchwarmKonfiguration schwarmKonfiguration) {
        setSchwarmKonfiguration(schwarmKonfiguration);

        initialisiereSchwarm();
    }

    /**
     * Erstellt Listen der Agenten und befüllt sie dementsprechend
     *
     * Private Methode, Zusicherungen gelten innerhalb der Klasse
     * Vorbedingungen:
     * 1. SchwarmKonfiguration enthaelt sinnvolle Werte
     * Nachbedingungen:
     * 1. Agent-Listen sind in beliebiger Reihenfolge befuellt
     * Invarianten:
     * keine
     */
    private void initialisiereSchwarm() {
        voegel = new ArrayList<Vogel>(schwarmKonfiguration.getAnzahlVoegel());
        for (int i = 0; i < schwarmKonfiguration.getAnzahlVoegel(); i++) {
            voegel.add(new Vogel(schwarmKonfiguration));
        }
        verteileVoegelZufaellig();
        ameisen = new ArrayList<>(schwarmKonfiguration.getAnzahlAmeisen());
        for (int i = 0; i < schwarmKonfiguration.getAnzahlAmeisen(); i++) {
            ameisen.add(new Ameise(schwarmKonfiguration));
        }
        verteileAmeisenZufaellig();
    }

    /**
     * Führt einen Simulationsschritt aus
     *
     * Vorbedingungen:
     * Keine
     * History-Constraints:
     * 1. initialisiereSchwarm() und setSchwarmKonfiguration() wurde einmal aufgerufen (wird durch Aufruf im Konstruktur sichergestellt)
     * Nachbedingungen:
     * 1. Agents im Schwarm befinden sich nicht mehr an derselben Position wie zuvor
     * 2. Die Listen der Agents sind in einer anderen zufaelligen Reihenfolfge als vor dem Aufruf
     * Invarianten:
     * 1. Anzahl der Agents bleibt immer gleich
     */
    public void simuliereEinenSchritt(boolean waehleNeueAgentsFuerZufaelligeBewegung) {
        int zufaelligeBewegungsZaehler = schwarmKonfiguration.getZufaelligZuBewegendeVoegel();

        if (waehleNeueAgentsFuerZufaelligeBewegung) {
            Collections.shuffle(voegel);
            Collections.shuffle(ameisen);
        }

        for (Vogel aktuellerVogel : voegel) {
            List<Agent> andereVoegel = new ArrayList<Agent>(voegel);
            andereVoegel.remove(aktuellerVogel);
            aktuellerVogel.bewege(andereVoegel, zufaelligeBewegungsZaehler-- > 0);
        }
        //Das gleiche wird nochmal für die Ameisen ausgeführt
        zufaelligeBewegungsZaehler = schwarmKonfiguration.getZufaelligZuBewegendeAmeisen();
        for (Ameise aktuelleAmeise : ameisen) {
            List<Agent> andereAmeisen = new ArrayList<Agent>(ameisen);
            andereAmeisen.remove(aktuelleAmeise);
            aktuelleAmeise.bewege(andereAmeisen, zufaelligeBewegungsZaehler-- > 0);
        }
    }

    /**
     *
     * Verteilt Vögel für den Start der Simulation zufällig im Raum
     *
     * Vorbedingungen:
     * 1. SchwarmKonfiguration enthaelt sinnvolle Werte (wird dort ueberprueft)
     * History-Constraints:
     * 1. initialisiereSchwarm() und setSchwarmKonfiguration() wurde einmal aufgerufen (sichergestellt durch Konstruktor)
     * Nachbedingungen:
     * 1. Voegel haben zufaellige Positionen
     * 2. Jeder Vogel hat eine Position
     * 3. Die Voegel halten einen definierten Mindestabstand ein
     * 4. Die Voegel befinden sich innerhalb des definierten Himmels
     * Invarianten:
     * 1. Die Anzahl der Voegel bleibt immer gleich
     */
    private void verteileVoegelZufaellig() {
        List<Vogel> verteilteVoegel = new ArrayList<Vogel>(voegel.size());
        for (Vogel vogel : voegel) {
            int x = 0;
            int y = 0;
            int z = 0;
            boolean platzLeer = false;

            while (!platzLeer) {
                BoundingBox himmel = schwarmKonfiguration.getHimmel();
                x = Util.random(0, (int)himmel.getWidth());
                // Verteile Voegel in kleinerem Bereich der Y-Dimension fuer bessere Uersichtlichkeit
                y = Util.random(0, (int) himmel.getHeight() / 3);
                z = Util.random(0, (int)himmel.getDepth());
                platzLeer = true;
                for (Vogel verteilterVogel : verteilteVoegel) {
                    if (verteilterVogel.getPosition().distance(x, y, z) < schwarmKonfiguration.getMindestabstand()) {
                        platzLeer = false;
                        break;
                    }
                }
            }
            vogel.setPosition(new Point3D(x, y, z));
            verteilteVoegel.add(vogel);
        }
    }

    /**
     * Getter für die Vogel-Liste
     *
     * @return Liste mit Vogel-Objekten
     *
     * Vorbedingungen:
     * Keine
     * History-Constraints:
     * 1. initialisiereSchwarm() und setSchwarmKonfiguration() wurde einmal aufgerufen (sichergestellt durch Konstruktur)
     * Nachbedingungen:
     * 1. Vogel-Liste in Schwarm3d ist unabhaengig von der hier erstellten Kopie
     * Invarianten:
     * 1. Reihenfolge der Voegel in der Liste ist nicht definiert und kann bei jedem Aufruf von getVoegel() anders sein
     */
    public List<Vogel> getVoegel() {
        return new ArrayList<Vogel>(voegel);
    }

    /**
     * Getter für die Ameisen-Liste
     * @return Liste mit Ameise-Objekten
     *
     * Vorbedingungen:
     * Keine
     * History-Constraints:
     * 1. initialisiereSchwarm() und setSchwarmKonfiguration() wurde einmal aufgerufen (sichergestellt durch Konstruktur)
     * Nachbedingungen:
     * 1. Ameisen-Liste in Schwarm3d ist unabhaengig von der hier erstellten Kopie
     * Invarianten:
     * 1. Reihenfolge der Voegel in der Liste ist nicht definiert und kann bei jedem Aufruf von getVoegel() anders sein
     *
     * BAD: Die doppelten Methoden für Voegel und Ameisen entsprechen nicht dem DRY-Pattern.
     * Siehe verteileAmeisenZufaellig() fuer Details.
     *
     */
    public List<Ameise> getAmeisen() {
        return new ArrayList<Ameise>(ameisen);
    }

    /**
     * Verteilt Ameisen für den Start zufällig im Raum
     *
     * Vorbedingungen:
     * 1. SchwarmKonfiguration enthaelt sinnvolle Werte (wird dort ueberprueft)
     * History-Constraints:
     * 1. initialisiereSchwarm() und setSchwarmKonfiguration() wurde einmal aufgerufen (sichergestellt durch Konstruktor)
     * Nachbedingungen:
     * 1. Ameisen haben zufaellige Positionen
     * 2. Jede Ameise hat eine Position
     * 3. Die Ameisen halten einen definierten Mindestabstand ein
     * 4. Die Ameisen befinden sich innerhalb des definierten Himmels
     * Invarianten:
     * 1. Die Anzahl der Ameisen bleibt immer gleich
     */
    private void verteileAmeisenZufaellig() {
        List<Ameise> verteilteAmeisen = new ArrayList<>(ameisen.size());
        for (Ameise ameise : ameisen) {
            int x = 0;
            int y = 390;
            int z = 0;
            boolean platzLeer = false;

            while (!platzLeer) {
                BoundingBox himmel = schwarmKonfiguration.getHimmel();
                x = Util.random(0, (int) himmel.getWidth());
                z = Util.random(0, (int) himmel.getHeight());
                platzLeer = true;
                for (Ameise verteilteAmeise : verteilteAmeisen) {
                    if (verteilteAmeise.getPosition().distance(x, y, z) < schwarmKonfiguration.getMindestabstand()) {
                        platzLeer = false;
                        break;
                    }
                }
            }
            ameise.setPosition(new Point3D(x, y, z));
            verteilteAmeisen.add(ameise);
        }
    }


    /**
     * Setter für die Schwarmkonfiguration
     *
     * Vorbedingungen:
     *  1. schwarmKonfiguration ist nicht null und enthaelt sinnvolle Werte
     * Nachbedingungen:
     * 1. Schwarm3d ist so konfiguriert, wie in der schwarmKonfiguration festgelegt.
     * Invarianten:
     * Keine
     */
    public void setSchwarmKonfiguration(SchwarmKonfiguration schwarmKonfiguration) {
        if (schwarmKonfiguration == null) {
            throw new IllegalArgumentException("oopb3.Schwarm3d.SchwarmKonfiguration darf nicht null sein!");
        } else {
            this.schwarmKonfiguration = schwarmKonfiguration;
        }
    }

    public static class SchwarmKonfiguration {
        /**
         * Innere Klasse SchwarmKonfiguration.
         * Kapselt die Konfigurationsparameter der Klasse Schwarm3d und sorgt somit für mehr Übersichtlichkeit.
         */

        private int anzahlVoegel;
        private int anzahlAmeisen;
        private int anzahlNachbarn;
        private int mindestabstand;
        private int zufaelligZuBewegendeVoegel;
        private int zufaelligZuBewegendeAmeisen;
        // Der Himmel kennt - der Einfachkeit wegen - nur positive ganzzahle Werte (auch wenn BoundingBox Kommazahlen und negative Werte unterstützen würde).
        private BoundingBox himmel;
        private int simulationsDauer;
        private int zufaelligeBewegungsDauer;

        /**
         * @param anzahlVoegel                > 0
         * @param anzahlAmeisen               > 0
         * @param anzahlNachbarn              > 0
         * @param mindestabstand              > 0
         * @param zufaelligZuBewegendeVoegel  > 0
         * @param zufaelligZuBewegendeAmeisen > 0
         * @param himmel
         * @param simulationsDauer            > 0
         * @param zufaelligeBewegungsDauer    > 0
         */
        public SchwarmKonfiguration(int anzahlVoegel, int anzahlAmeisen, int anzahlNachbarn, int mindestabstand, int zufaelligZuBewegendeVoegel, int zufaelligZuBewegendeAmeisen, BoundingBox himmel, int simulationsDauer, int zufaelligeBewegungsDauer) {
            // Die Reihenfolge der Setter kann nicht beliebig verändert werden.
            setAnzahlVoegel(anzahlVoegel);
            setAnzahlAmeisen(anzahlAmeisen);
            setAnzahlNachbarn(anzahlNachbarn);
            setMindestabstand(mindestabstand);
            setZufaelligZuBewegendeVoegel(zufaelligZuBewegendeVoegel);
            setZufaelligZuBewegendeAmeisen(zufaelligZuBewegendeAmeisen);
            setHimmel(himmel);
            setSimulationsDauer(simulationsDauer);
            setZufaelligeBewegungsDauer(zufaelligeBewegungsDauer);
        }

        public SchwarmKonfiguration() {
            this(200, 1000, 20, 3, 5, 5, new BoundingBox(0, 0, 0, 400, 400, 400), 5, 3);
        }

        /**
         * Getter für die Anzahl der Vögel
         *
         * @return int Anzahl der Vögel
         */
        public int getAnzahlVoegel() {
            return anzahlVoegel;
        }

        /**
         * Getter für die Anzahl der Ameisen
         * @return int Anzahl der Ameisen
         */
        public int getAnzahlAmeisen() {
            return anzahlAmeisen;
        }

        /**
         * Setter für die Anzahl der Vögel
         * @param anzahlVoegel int, >= 1
         */
        private void setAnzahlVoegel(int anzahlVoegel) {
            if (anzahlVoegel < 1) {
                throw new IllegalArgumentException("anzahlVoegel muss größer als 0 sein!");
            } else {
                this.anzahlVoegel = anzahlVoegel;
            }
        }

        /**
         * Setter für die Anzahl der Ameisen
         * @param anzahlAmeisen int, >= 1
         */
        public void setAnzahlAmeisen(int anzahlAmeisen) {
            if (anzahlAmeisen < 1) {
                throw new IllegalArgumentException("anzahlAmeisen muss groesser 0 sein!");
            } else {
                this.anzahlAmeisen = anzahlAmeisen;
            }
        }

        /**
         * Getter für die Anzahl der Nachbarn
         * @return int, Anzahl der Nachbarn die ein Objekt betrachtet
         */
        public int getAnzahlNachbarn() {
            return anzahlNachbarn;
        }

        /**
         * Setter für die Anzahl der Nachbarn
         *
         * @param anzahlNachbarn int, >= 1
         *                       Vorbedingungen:
         *                       1. anzahlNachbarn <= anzahlVoegel
         *                       History-Constraints:
         *                       1. setAnzahlVoegel() muss vorher aufgerufen werden
         *                       BAD: History-Constraint wird nicht programmatisch ueberprueft!
         */
        private void setAnzahlNachbarn(int anzahlNachbarn) {
            if (anzahlNachbarn < 1) {
                throw new IllegalArgumentException("anzahlNachbarn muss groeßer als 0 sein!");
            } else if (anzahlNachbarn > anzahlVoegel) {
                throw new IllegalArgumentException("anzahlNachbarn muss kleiner gleich anzahlVoegel sein!");
            } else {
                this.anzahlNachbarn = anzahlNachbarn;
            }
        }

        /**
         * Getter für den Mindestabstand
         * @return int
         */
        public int getMindestabstand() {
            return mindestabstand;
        }

        /**
         * Setter für den Mindestabstand
         * @param mindestabstand int, >= 1
         */
        private void setMindestabstand(int mindestabstand) {
            if (mindestabstand < 1) {
                throw new IllegalArgumentException("mindestabstand muss größer als 0 sein!");
            } else {
                this.mindestabstand = mindestabstand;
            }
        }

        /**
         * Getter für die Anzahl der sich zufällig bewegenden Vögel
         * @return int
         */
        public int getZufaelligZuBewegendeVoegel() {
            return zufaelligZuBewegendeVoegel;
        }

        /**
         * Setter für die Anzahl der sich zufällig bewegenden Vögel
         *
         * @param zufaelligZuBewegendeVoegel int, >= 1, <= anzahlVoegel
         *                                   Vorbedingungen:
         *                                   1. zufaelligZuBewegendeVoegel <= anzahlVoegel
         *                                   History-Constraints:
         *                                   1. setAnzahlVoegel() muss vorher aufgerufen werden
         *                                   BAD: History-Constraint wird nicht programmatisch ueberprueft!
         */
        private void setZufaelligZuBewegendeVoegel(int zufaelligZuBewegendeVoegel) {
            if (zufaelligZuBewegendeVoegel < 1) {
                throw new IllegalArgumentException("zufaelligZuBewegendeVoegel muss größer als 0 sein!");
            } else if (zufaelligZuBewegendeVoegel > this.anzahlVoegel) {
                throw new IllegalArgumentException("zufaelligZuBewegendeVoegel (war " + zufaelligZuBewegendeVoegel + ") muss kleiner gleich anzahlVoegel (war " + anzahlVoegel + ") sein");
            } else {
                this.zufaelligZuBewegendeVoegel = zufaelligZuBewegendeVoegel;
            }
        }

        /**
         * Getter für die Anzahl der sich zufällig bewegenden Ameisen
         * @return int
         */
        public int getZufaelligZuBewegendeAmeisen() {
            return zufaelligZuBewegendeAmeisen;
        }

        /**
         * Setter für die Anzahl der sich zufällig bewegenden Ameisen
         *
         * @param zufaelligZuBewegendeAmeisen int, >= 1, <= anzahlAmeisen
         *                                    Vorbedingungen:
         *                                    1. zufaelligZuBewegendeAmeisen <= anzahlAmeisen
         *                                    History-Constraints:
         *                                    1. setAnzahlVoegel() muss vorher aufgerufen werden
         *                                    BAD: History-Constraint wird nicht programmatisch ueberprueft!
         */
        public void setZufaelligZuBewegendeAmeisen(int zufaelligZuBewegendeAmeisen) {
            if (zufaelligZuBewegendeAmeisen < 1) {
                throw new IllegalArgumentException("zufaelligZuBewegendeAmeisen muss größer als 0 sein!");
            } else if (zufaelligZuBewegendeAmeisen > this.anzahlAmeisen) {
                throw new IllegalArgumentException("zufaelligZuBewegendeVoegel (war " + zufaelligZuBewegendeAmeisen + ") muss kleiner gleich anzahlVoegel (war " + anzahlAmeisen + ") sein");
            } else {
                this.zufaelligZuBewegendeAmeisen = zufaelligZuBewegendeAmeisen;
            }
        }

        /**
         * Getter für den Himmel
         * @return BoundingBox
         */
        public BoundingBox getHimmel() {
            // Gebe immutable BoundingBox zurück, ohne eine Objektkopie zu erstellen.
            return himmel;
        }

        /**
         * Setter für den Himmel
         *
         * @param himmel BoundingBox, != null
         *               Vorbedingungen:
         *               1. himmel muss genug Platz fuer alle Voegel haben (siehe Code)
         *               History-Constraints:
         *               setAnzahlVoegel() muss vorher aufgerufen werden
         *               BAD: History-Constraint wird nicht programmatisch ueberprueft!
         */
        private void setHimmel(BoundingBox himmel) {
            if (himmel == null) {
                throw new IllegalArgumentException("himmel darf nicht null sein!");
            } else if ((himmel.getWidth() * himmel.getHeight() * himmel.getDepth()) < anzahlVoegel) {
                throw new IllegalArgumentException("himmel hat zu wenige Plätze (" + himmel.getWidth() * himmel.getHeight() * himmel.getDepth() + "), nicht alle Vögel (" + anzahlVoegel + ") haben Platz!");
            } else {
                this.himmel = himmel;
            }
        }

        /**
         * Gibt Dauer der Simulation zurueck
         *
         * @return Dauer der Simulation in Sekunden
         */
        public int getSimulationsDauer() {
            return simulationsDauer;
        }

        /**
         * Setze Simulationsdauer
         * Vorbedingung: simulationsDauer in Sekunden > 0
         *
         * @param simulationsDauer > 0
         */
        private void setSimulationsDauer(int simulationsDauer) {
            if (simulationsDauer <= 0) {
                throw new IllegalArgumentException("simulationsDauer muss größer als 0 sein!");
            } else {
                this.simulationsDauer = simulationsDauer;
            }
        }

        /**
         * Gibt Dauer einer zufaelligen Bewegung zurueck.
         *
         * @return Dauer einer zufaelligen Bewegung in Sekunden.
         */
        public int getZufaelligeBewegungsDauer() {
            return zufaelligeBewegungsDauer;
        }

        /**
         * Setze Dauer einer zufälligen Bewegung in Sekunden
         *
         * @param zufaelligeBewegungsDauer in Sekunden > 0
         */
        private void setZufaelligeBewegungsDauer(int zufaelligeBewegungsDauer) {
            if (zufaelligeBewegungsDauer <= 0) {
                throw new IllegalArgumentException("simulationsDauer muss größer als 0 sein!");
            } else {
                this.zufaelligeBewegungsDauer = zufaelligeBewegungsDauer;
            }
        }
    }
}
