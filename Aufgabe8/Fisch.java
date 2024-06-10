import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fisch {

    /**
     * Die Anzahl der erfolgslosen Bewegungsversuche, nach denen der Fisch aufgibt (und das gesamte Programm terminiert).
     */
    private final int MAX_WARTEZEIT_SCHRITTE = 32;

    /**
     * Die Anzahl der erfolglosen Bewegungsversuche des Fischs
     */
    private int erfolgloseBewegungsversuche = 0;

    /**
     * Die Anzahl der Schwimmbegewegungen des Fischs
     */
    private int schwimmbewegungen = 0;

    /**
     * Das Aquarium in dem sich der Fisch befindet.
     */
    private final Aquarium aquarium;

    /**
     * Die aktuelle Position des Fischs.
     */
    private Point headPosition;
    private Point tailPosition;

    /**
     * Die Richtung des Fisches in die er schaut.
     */
    private Fischlage fischlage;

    /**
     * Gibt an ob der Fisch ein Leitfisch ist.
     */
    private boolean leitfisch;

    /**
     * Erzeugt einen neuen Fisch an der gewuenschten Position.
     * VB: head != null && Die gewuenschte Position muss frei sein.
     * VB: aquarium != null && fischlage != null
     * @param aquarium     siehe {@link this#aquarium}
     * @param headPosition Die gewuenschte Position
     * @param fischlage    Die Lage des Fisches
     */
    public Fisch(Aquarium aquarium, Point headPosition, Fischlage fischlage) {
        this.aquarium = aquarium;
        setFischlage(fischlage);
        validateAndSetPositionOfFisch(headPosition, fischlage);
    }

    /**
     * Ueberprueft ob die Position im Aquarium ist und noch frei und platziert den Fisch an der Position.
     *
     * VB: head Position != null && fischlage != null
     * NB: Fisch ist bei valider Position im Aquarium platziert.
     * @param headPosition
     * @param fischlage
     */
    private void validateAndSetPositionOfFisch(Point headPosition, Fischlage fischlage) {
        Point tail = findeTailPosition(headPosition,fischlage);
        if (!aquarium.istPositionImAquarium(headPosition) || !aquarium.istPositionImAquarium(tail)) {
            throw new IllegalArgumentException("Fisch ist ausserhalb des Aquarium");
        }
        if (!aquarium.istFeldFrei(headPosition.x, headPosition.y) || !aquarium.istFeldFrei(tail.x, tail.y)) {
            throw new IllegalArgumentException("Fisch hat an der Position keinen Platz");
        }
        setHeadPosition(headPosition);
        setTailPosition(tail);
        if(!aquarium.neueFischPosition(headPosition,tailPosition,aquarium.fischkopf(fischlage))) {
            throw new IllegalArgumentException("Fisch hat bei der angegebenen Position keinen Platz!");
        }
    }

    /**
     * Liefert die aktuelle Position des Kopfes des Fischs zurueck.
     *
     * @return Die aktuelle Position des Kopfes des Fischs.
     */
    private Point getHeadPosition() {
        return headPosition;
    }

    /**
     * Liefert die aktuelle Position der Flosse des Fisches.
     *
     * @return Die aktuelle Position der Flosse des Fisches.
     */
    private Point getTailPosition() {
        return tailPosition;
    }

    /**
     * Liefert die Ausrichtung des Fisches.
     *
     * @return Die Ausrichtung des Fisches als Enum.
     */
    private Fischlage getFischlage() {
        return fischlage;
    }

    /**
     * Bewegt den Fisch, wenn ein Feld frei ist. Probiere dabei alle moeglichen Lagen durch.
     * Sollte keine Bewegung moeglich sein, erhoehe den erfolgloseBewegung-Counter. Erreicht der Counter den Wert 32 wird die Bewegung abgebrochen
     * und ein Signal an den Parent uebermittelt, der anschliessend alle Threads beendet.
     *
     * VB: Fisch wurde mit validen Werten initialisiert.
     * NB: Fisch aendert die Position wenn moeglich, erhoeht warteCounter wenn nicht moeglich bzw. beendet das bewegen sofort
     */
    public boolean bewege() {
        if (erfolgloseBewegungsversuche >= MAX_WARTEZEIT_SCHRITTE) {
                /*
             Der Fisch hat seit dem ersten Versuch zu viele erfolgloe Bewegungsversuche gemacht.
             Die Simulation muss abgebrochen werden und davor muessen diverse Infos ausgegeben werden:
             "Wenn ein Fisch die maximalen Warteschritte (32) erreicht hat, geben Sie von
             allen Fischen den Zählerstand der Warteschritte und die Anzahl der Schwimmbewegungen und
             das Feldpaar (auf welchen Feldern es sich befindet) aus und beenden alle Threads." (Angabe S.1f)
             */
            return false;
        }

        Point alteTailPosition = getTailPosition();
        Point alteHeadPosition = getHeadPosition();
        Point neueHeadPosition;
        Point neueTailPosition;
        List<Fischlage> moeglicheLagen = findeMoeglicheLagen();
        // Fische bewegen sich bei einer bestimmten Lage immer nach dem selben Schema (zuerst links probieren, dann rechts, dann oben, etc)
        // fuer zufaelliges Verhalten, das aber dazu fuehrt dass es weniger kollisionen gibt:
        // nimm immer eine zufaellige reihenfolge in der lagen probiert werden
        // Collections.shuffle(moeglicheLagen);
        boolean feldGefunden;
        for(Fischlage lage : moeglicheLagen) {
            neueHeadPosition = neueKopfPosition(lage);
            neueTailPosition = findeTailPosition(neueHeadPosition, lage);
            feldGefunden = aquarium.versucheFeldZuWechseln(alteHeadPosition, neueHeadPosition, neueTailPosition, alteTailPosition, lage);
            if(feldGefunden) {
                setHeadPosition(neueHeadPosition);
                setTailPosition(neueTailPosition);
                setFischlage(lage);
                schwimmbewegungen++;
                if (isLeitfisch()) {
                    System.out.println(aquarium.toString());
                }
                return true;
            }
        }
        erfolgloseBewegungsversuche++;
        return true;
    }

    /**
     * Finde zum Kopf und der Lage die passende Tail-Position.
     * VB: head != null && fischlage != null
     * NB: findet immer eine Tailposition, die jedoch moeglicherweise ungueltig ist (schon besetzt, bzw ausserhalb des Aquariums)
     * @param head
     * @param fischlage
     * @return
     */
    private Point findeTailPosition(Point head, Fischlage fischlage) {
        Point tailPosition = null;
        switch (fischlage) {
            case OBEN:
                tailPosition = new Point(head.x, head.y + 1);
                break;
            case RECHTS:
                tailPosition = new Point(head.x - 1, head.y);
                break;
            case UNTEN:
                tailPosition = new Point(head.x, head.y - 1);
                break;
            case LINKS:
                tailPosition = new Point(head.x + 1, head.y);
                break;
        }
        return tailPosition;
    }

    /**
     * Ermittelt anhand der lage eine neue kopfposition.
     * VB: Fischlage != null
     * NB: liefert immer eine HeadPosition, auch wenn sie moeglicherweise ausserhalb des Aquariums liegt bzw. besetzt ist.
     * @param fischlage
     * @return
     */
    private Point neueKopfPosition(Fischlage fischlage) {
        Point position = null;
        switch (fischlage) {
            case OBEN:
                position = new Point(getHeadPosition().x, getHeadPosition().y - 2);
                break;
            case RECHTS:
                position = new Point(getHeadPosition().x + 2, getHeadPosition().y);
                break;
            case UNTEN:
                position = new Point(getHeadPosition().x, getHeadPosition().y + 2);
                break;
            case LINKS:
                position = new Point(getHeadPosition().x - 2, getHeadPosition().y);
                break;
        }
        return position;
    }

    /**
     * Liefert die moeglichen Richtungen in die der Fisch schwimmen kann, da 180° Wendungen nicht moeglich sein duerfen.
     * @return
     */
    private List<Fischlage> findeMoeglicheLagen() {
        List<Fischlage> fischlagen = new ArrayList<>();
        switch (getFischlage()) {
            case OBEN:
                fischlagen.add(Fischlage.LINKS);
                fischlagen.add(Fischlage.OBEN);
                fischlagen.add(Fischlage.RECHTS);
                break;
            case RECHTS:
                fischlagen.add(Fischlage.OBEN);
                fischlagen.add(Fischlage.RECHTS);
                fischlagen.add(Fischlage.UNTEN);
                break;
            case UNTEN:
                fischlagen.add(Fischlage.RECHTS);
                fischlagen.add(Fischlage.UNTEN);
                fischlagen.add(Fischlage.LINKS);
                break;
            case LINKS:
                fischlagen.add(Fischlage.OBEN);
                fischlagen.add(Fischlage.LINKS);
                fischlagen.add(Fischlage.UNTEN);
        }
        return fischlagen;
    }

    @Override
    public String toString() {
        return String.format("{Fisch [warteschritte: %d][schwimmbewegungen: %d][kopf: %s][flosse: %s}", erfolgloseBewegungsversuche, schwimmbewegungen, headPosition.toString(), tailPosition.toString());
    }

    /**
     * Prüfe ob Instanz von Klasse Fisch auch Leitfisch ist
     *
     * @return boolean, true falls Fisch ein Leitfisch ist, false sonst
     */
    private boolean isLeitfisch() {
        return leitfisch;
    }

    /**
     * markiert diesen fisch als leitfisch wenn leitfisch true
     */
    public void setLeitfisch(boolean leitfisch) {
        this.leitfisch = leitfisch;
    }

    /**
     * Setzt die HeadPosition
     *
     * @param headPosition
     */
    private void setHeadPosition(Point headPosition) {
        if (headPosition == null) {
            throw new IllegalArgumentException("headPosition kann nicht null sein!");
        }
        this.headPosition = headPosition;
    }

    /**
     * Setzt die Tail Position
     *
     * @param tailPosition
     */
    private void setTailPosition(Point tailPosition) {
        if (tailPosition == null) {
            throw new IllegalArgumentException("tailPosition kann nicht null sein!");
        }
        this.tailPosition = tailPosition;
    }

    /**
     * Setzt die Fischlage
     *
     * @param fischlage
     */
    private void setFischlage(Fischlage fischlage) {
        if (fischlage == null) {
            throw new IllegalArgumentException("fischlage kann nicht null sein!");
        }
        this.fischlage = fischlage;
    }

}
