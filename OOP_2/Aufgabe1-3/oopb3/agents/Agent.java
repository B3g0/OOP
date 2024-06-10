package oopb3.agents;

import javafx.geometry.Point3D;
import oopb3.Schwarm3d;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Abstrakte Klasse Agent, die sich um die Bewegungen innerhalb einer Menge anderer Agenten kuemmert und Schwarmverhalten
 * simuliert. Die Applikation ist dadurch um eine beliebige Anzahl neuer Agenten, die Schwarmverhalten simulieren sollen, erweiterbar
 */
public abstract class Agent {

    private Point3D position;
    private Point3D bewegung = new Point3D(0, 0, 0);
    private Schwarm3d.SchwarmKonfiguration schwarmKonfiguration;
    private boolean bewegtSichZufaellig;

    public Agent(Schwarm3d.SchwarmKonfiguration schwarmKonfiguration) {
        setSchwarmKonfiguration(schwarmKonfiguration);
        setPosition(new Point3D(0, 0, 0));
    }

    /**
     * Findet die Agents die dem mitgegebenen oopb3.agents.Agent am naehesten sind.
     * Dafuer werden die Distanzen zu allen anderen Agents berechnet und anhand dieser Distanz aufsteigend sortiert.
     * Die ersten n Agents sind die, die dem oopb3.agents.Agent am naehesten sind.
     *
     * VB: andereAgenten != null
     * NB: Findet die Agents die dem mitgegebenen oopb3.agents.Agent am naehesten sind.
     * Dafuer werden die Distanzen zu allen anderen Agents berechnet und anhand dieser Distanz aufsteigend sortiert.
     * Die ersten n Agents sind die, die dem oopb3.agents.Agent am naehesten sind.
     *
     * @return Liste aller Agenten mit aufsteigendem Abstand zueinander
     */
    public List<Agent> findeNaechsteNachbarn(List<Agent> andereAgenten) {
        Agent agent = this;
        // Sortiere andereAgenten Liste ohne vorher eine tiefe Kopie anzulegen.
        Collections.sort(andereAgenten, new Comparator<Agent>() {
            @Override
            public int compare(Agent agent1, Agent agent2) {
                double abstand1 = agent.getPosition().distance(agent1.getPosition().getX(), agent1.getPosition().getY(), agent1.getPosition().getZ());
                double abstand2 = agent.getPosition().distance(agent2.getPosition().getX(), agent2.getPosition().getY(), agent2.getPosition().getZ());
                if (agent == agent1) {
                    abstand1 = Double.MAX_VALUE;
                } else if (agent == agent2) {
                    abstand2 = Double.MAX_VALUE;
                }

                return Double.compare(abstand1, abstand2);
            }
        });

        return andereAgenten.subList(0, getSchwarmKonfiguration().getAnzahlNachbarn());
    }

    /**
     * Retourniert die dreidimensionale Position des Agenten.
     * @return position des Agenten
     */
    public Point3D getPosition() {
        return position;
    }

    /**
     * Setzt die neue Position des Agenten und errechnet anhand der neuen Position die Bewegung, die der Agent zuvor vollfuehrt hat.
     *
     * VB: Die neue Position muss sich innerhalb des Himmels befinden.
     * NB: Der Agent befindet sich jetzt an der gewuenschten Position und sein Bewegungsvektor wurde aktualisiert.
     *
     * @param neuePosition != null
     */
    public void setPosition(Point3D neuePosition) {
        if (this.getPosition() != null) {
            Point3D altePosition = this.getPosition();
            bewegung = neuePosition.subtract(altePosition).normalize();
        }
        this.position = neuePosition;
    }

    /**
     * Retourniert den aktuellen Bewegungsvektors des Agents
     * @return
     */
    public Point3D getBewegung() {
        return bewegung;
    }

    /**
     * Setzt den Bewegungsvektor.
     *
     * VB: Der Bewegungsvektor entspricht der Differenz zwischen der letzten und der aktuellen Position
     * NB: Der Bewegungsvektor ist aktualisiert.
     *
     * @param point != null
     */
    private void setBewegung(Point3D point) {
        bewegung = point;
    }

    /**
     * Getter fuer die Schwarmkonfiguration
     * @return Objekt der Klasse Schwarmkonfiguration
     */
    protected Schwarm3d.SchwarmKonfiguration getSchwarmKonfiguration() {
        return schwarmKonfiguration;
    }

    /**
     * Setter fuer die Schwarmkonfiguration
     *
     * @param schwarmKonfiguration != null
     */
    private void setSchwarmKonfiguration(Schwarm3d.SchwarmKonfiguration schwarmKonfiguration) {
        this.schwarmKonfiguration = schwarmKonfiguration;
    }

    /**
     * Bewegt den Agenten abhaengig von anderen Agenten, falls der Agent sich nicht zufaellig bewegen soll.
     * Die Schwarm-Klasse bestimmt, ob sich ein Agent zufaellig zu bewegen hat.
     *
     * VB: Der Agent selbst darf nicht in der Liste andereAgents enthalten sein.
     * VB: Die Liste andereAgents enthaelt andere Agents die dem Agent (sehr) nahe sind.
     * VB: Wenn sich der Agent zufaellig bewegen soll, kann eine leere Liste andereAgents uebergeben werden.
     * NB: Die Position des Agents ist (zufaellig oder anhand der Nachbarn) veraendert
     *
     * @param andereAgents != null
     */
    public void bewege(List<Agent> andereAgents, boolean zufaelligeBewegung) {
        // setze Flag bewegtSichZufaellig zurück
        setBewegtSichZufaellig(false);

        if (zufaelligeBewegung) {
            // setze Flag bewegtSichZufaellig auf true
            setBewegtSichZufaellig(true);
            bewegeZufaellig();
        } else {
            bewegeAnhandNachbarn(andereAgents);
        }

        halteMindestabstandEin(andereAgents);

        halteBegrenzungenEin();
    }

    /**
     * Fuer jeden Agenten anderes verhalten moeglich, daher muss sie bei jeder Klasse, die von Agent erbt, implementiert werden.
     *
     * NB: Der Agent hat sich zufaellig bewegt und hat eine neue Position.
     */
    protected abstract void bewegeZufaellig();

    /**
     * Liefert Flag ob sich der Agent gerade zufaellig bewegt.
     *
     * @return Flag ob sich der Agent gerade zufaellig bewegt.
     */
    public boolean bewegtSichZufaellig() {
        return this.bewegtSichZufaellig;
    }

    /**
     * Setzt Flag ob Agent sich gerade zufaellig bewegt.
     * @param bewegtSichZufaellig Flag ob Agent sich gerade zufaellig bewegt.
     */
    private void setBewegtSichZufaellig(boolean bewegtSichZufaellig) {
        this.bewegtSichZufaellig = bewegtSichZufaellig;
    }

    /**
     * Bewegt den Agenten abhaengig von den naechsten Nachbarn
     *
     * VB: Der Agent darf nicht in der Liste andereAgents enthalten sein.
     * VB: Die Liste andereAgents enthaelt andere Agents die dem Agent (sehr) nahe sind
     * NB: Der Agent hat seine Position anhand der uebergebenen anderen Agents veraendert.
     *
     * @param andereAgents != null
     */
    protected void bewegeAnhandNachbarn(List<Agent> andereAgents) {
        List<Agent> nachbarn = findeNaechsteNachbarn(andereAgents);

        Point3D bewegungsDurchschnitt = new Point3D(0, 0, 0);

        for (Agent nachbar : nachbarn) {
            bewegungsDurchschnitt = bewegungsDurchschnitt.add(nachbar.getBewegung());
        }

        bewegungsDurchschnitt = bewegungsDurchschnitt.multiply(1 / (double) nachbarn.size());

        setPosition(getPosition().add(bewegungsDurchschnitt));
    }

    /**
     * Ueberprueft ob Voegel beim Durchfuehren der Bewegung miteinander kollidieren bzw. den Mindestabstand nicht mehr einhalten wuerden.
     * Falls ein oopb3.agents.Vogel mit einem anderen zusammentreffen wuerde, macht er eine 180 Grad Wende.
     *
     * VB: Der Agent darf nicht in der Liste andereAgents enthalten sein.
     * VB: Die Liste andereAgents enthaelt andere Agents die dem Agent (sehr) nahe sind
     * NB: Der Agent hat einen gewissen Mindestabstand zu den uebergebenen anderen Agents
     *
     * @param andereAgents != null
     */
    protected void halteMindestabstandEin(List<Agent> andereAgents) {
        for (Agent andererAgent : andereAgents) {
            if (getPosition().distance(andererAgent.getPosition()) < getSchwarmKonfiguration().getMindestabstand()) {
                setPosition(getPosition().add(getBewegung().multiply(-1)));
            }
        }
    }

    /**
     * Wenn ein Agent aus dem "Himmel" fliegen wuerde, macht er mit doppelter Geschwindigkeit eine 180 Grad Wende.
     *
     * NB: Der Agent befindet sich innerhalb des Himmels
     * NB: Der Agent fliegt in die entgegengesetzte Richtung.
     */
    protected void halteBegrenzungenEin() {
        if (!getSchwarmKonfiguration().getHimmel().contains(getPosition())) {
            setPosition(getPosition().add(getBewegung().multiply(-1)));
        }
    }

}
