package oopb3.agents;

import javafx.geometry.Point3D;
import oopb3.Schwarm3d;
import oopb3.Util;

/**
 * Vogel - Agent, der sich frei im Raum bewegen kann
 */
public class Vogel extends Agent {

    public Vogel(Schwarm3d.SchwarmKonfiguration schwarmKonfiguration) {
        super(schwarmKonfiguration);
    }

    /**
     * Generiert eine zufällige Bewegung für die festegelegten Richtungen
     * Diese wird im Anschluss weitergegeben
     *
     * VB: Der Vogel hat eine Position, die nicht null ist.
     * NB: Der Vogel hat sich zufaellig bewegt und hat eine neue Position.
     */
    @Override
    protected void bewegeZufaellig() {
        int xBewegung = Util.random(-1, 1);
        int yBewegung = Util.random(-1, 1);
        int zBewegung = Util.random(-1, 1);

        Point3D oldPosition = getPosition();
        Point3D newPosition = oldPosition.add(xBewegung, yBewegung, zBewegung);

        setPosition(newPosition);
    }
}
