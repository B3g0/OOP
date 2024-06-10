package oopb3.agents;

import javafx.geometry.Point3D;
import oopb3.Schwarm3d;
import oopb3.Util;

/**
 * Ameisen - Agent, der sich nur am Boden bewegen kann
 */
public class Ameise extends Agent {

    public Ameise(Schwarm3d.SchwarmKonfiguration schwarmKonfiguration) {
        super(schwarmKonfiguration);
    }

    /**
     * Generiert eine zufällige Bewegung für die festegelegten Richtungen
     * Diese wird im Anschluss weitergegeben
     *
     * VB: Die Ameise hat eine Position, die nicht null ist.
     * NB: Die Ameise hat sich zufaellig bewegt und hat eine neue Position.
     */
    @Override
    protected void bewegeZufaellig() {
        int xBewegung = Util.random(-1, 1);
        int yBewegung = 0;
        int zBewegung = Util.random(-1, 1);

        Point3D oldPosition = getPosition();
        Point3D newPosition = oldPosition.add(xBewegung, yBewegung, zBewegung);

        setPosition(newPosition);
    }
}
