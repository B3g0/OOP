import java.awt.*;

public class Aquarium {

    /**
     * Groesse des Aquariums (Spalten).
     * VB: 10 <= Spalten*Zeilen <= 80
     */
    private final int spalten;
    /**
     * Groesse des Aquariums (Zeilen).
     * VB: 10 <= Spalten*Zeilen <= 80
     */
    private final int zeilen;

    /**
     * Die Felder des Aquariums.
     * true wenn frei, false wenn besetzt
     * Die 1. Dimension sind die Zeilen
     * Die 2. Dimension sind die Spalten
     * <p>
     * Dieses Array muss entweder:
     * 1. ueber {@link this#setzeFeldstatus(int, int, Feldstatus)} und {@link this#holeFeldStatus(int, int)} angesprochen werden
     * 2. oder in einem synchronized block (z.B. synchronized(felder[0][0]) oder synchronized(felder))
     */
    private Feldstatus[][] felder;

    /**
     * Erzeugt ein neues Aquarium mit angegebener Anzahl an spalten und zeilen.
     * VB: siehe {@link this#spalten} und {@link this#zeilen}
     */
    public Aquarium(int spalten, int zeilen) {
        this.spalten = spalten;
        this.zeilen = zeilen;
        initialisiereFelder();
    }

    /**
     * Initialisiere den Felder-Array, alle Felder sind anfangs frei
     */
    private void initialisiereFelder() {
        // Hole ein Lock auf den gesamten Array, damit die Initialisierung in einem durchlaeuft,
        // ohne das andere Threads auf den Array lesen/schreiben.
        this.felder = new Feldstatus[zeilen][spalten];
        synchronized (felder) {
            for (int x = 0; x < spalten; x++) {
                for (int y = 0; y < zeilen; y++) {
                    felder[y][x] = Feldstatus.FREI;
                }
            }
        }
    }

    /**
     * Gebe zurueck ob ein Feld frei ist.
     *
     * @param x X-Position des Felds
     * @param y Y-Position des Felds
     * @return Ob das Feld frei ist
     */
    public boolean istFeldFrei(int x, int y) {
        return holeFeldStatus(x, y) == Feldstatus.FREI;
    }

    /**
     * Versuche einen Fisch von einem Feld auf ein anderes Feld zu verschieben
     *
     * @param alteHeadPosition Die alte Position
     * @param neueHeadPosition Die gewuenschte Position
     * @return true, falls das Feld frei war, false andernfalls
     */
    public boolean versucheFeldZuWechseln(Point alteHeadPosition, Point neueHeadPosition, Point neueTailPosition, Point alteTailPosition, Fischlage fischlage) {
        //Erste Prüfung ob die neue Position innerhalb des Aquariums ist
        //Falls nicht wird gleich false zurückgegeben und die Methode verlassen
        if (!istPositionImAquarium(neueHeadPosition))
            return false;
        if (!istPositionImAquarium(neueTailPosition))
            return false;

        Feldstatus kopf = fischkopf(fischlage);

        // Hier muss explizit ein Lock fuer beide Felder im Array geholt werden.
        // Ein Aufruf von setzeFeldstatus() fuer die alte Position und anschließendem setzeFeldstatus()
        // fuer die neue Position, kann nicht sicherstellen, dass zwischen den beiden Aufrufen kein anderer Thread
        // auf die Felder schreibt/liest.
        // Nur wenn wir ein Lock auf alle noetigen Felder haben, koennen wir die Position ueberpruefen und aendern.
        if (neueFischPosition(neueHeadPosition, neueTailPosition, kopf)) {
            synchronized (felder[alteHeadPosition.y][alteHeadPosition.x]) {
                felder[alteHeadPosition.y][alteHeadPosition.x] = Feldstatus.FREI;
            }
            synchronized (felder[alteTailPosition.y][alteTailPosition.x]) {
                felder[alteTailPosition.y][alteTailPosition.x] = Feldstatus.FREI;
            }
            return true;
        }
        return false;
    }

    public Feldstatus fischkopf(Fischlage fischlage) {
        switch (fischlage) {
            case OBEN:
                return Feldstatus.KOPFOBEN;
            case RECHTS:
                return Feldstatus.KOPFRECHTS;
            case UNTEN:
                return Feldstatus.KOPFUNTEN;
            case LINKS:
                return Feldstatus.KOPFLINKS;
            default:
                return null;
        }
    }

    /**
     * Ueberprueft ob die uebergebene Position Frei ist. Sofern Frei, wird sie von dem Fisch besetzt
     * VB: neueHeadPosition != null && neueTailPosition != null && kopf != null
     * NB: Fisch wird im Aquarium platziert, sollte der Platz frei sein
     * @param neueHeadPosition
     * @param neueTailPosition
     * @param kopf
     * @return
     */
    public boolean neueFischPosition(Point neueHeadPosition, Point neueTailPosition, Feldstatus kopf) {
        synchronized (felder[neueHeadPosition.y][neueHeadPosition.x]) {
            if(felder[neueHeadPosition.y][neueHeadPosition.x] == Feldstatus.FREI) {
                synchronized (felder[neueTailPosition.y][neueTailPosition.x]) {
                    if (felder[neueTailPosition.y][neueTailPosition.x] == Feldstatus.FREI) {
                        felder[neueHeadPosition.y][neueHeadPosition.x] = kopf;
                        felder[neueTailPosition.y][neueTailPosition.x] = Feldstatus.FLOSSE;
                        return true;
                    } else {
                        return false;
                    }
                }
            }else {
                return false;
            }
        }
    }

    /**
     * Gebe zurueck ob sich eine Position in oder ausserhalb des Aquariums befindet
     *
     * @param position Die Position
     * @return boolean, Ob sich die Position in (true) oder ausserhalb (false) des Aquariums befindet
     */
    public boolean istPositionImAquarium(Point position) {
        return position.x >= 0 && position.x < spalten
                && position.y >= 0 && position.y < zeilen;
    }


    /**
     * Setze den Status einen Feldes.
     * Vor dem Setzen wird ein Lock fuer dieses Element im Array geholt.
     * <p>
     * Der Array {@link this#felder} sollte nur ueber diese Methode geschrieben werden, weil nur hier sichergestellt ist,
     * dass nicht mehr als 1 Thread gleichzeitig auf ein Feld des Arrays schreibt.
     *
     * @param x          Die X-Position des Feldes
     * @param y          Die Y-Position des Feldes
     * @param feldstatus Der neue Feldstatus
     */
    private void setzeFeldstatus(int x, int y, Feldstatus feldstatus) {
        synchronized (felder[y][x]) {
            felder[y][x] = feldstatus;
        }
    }

    /**
     * Hole den Status eines Feldes.
     * Vor dem Setzen wird ein Lock fuer dieses Element im Array geholt.
     *
     * @param x Die X-Position des Feldes
     * @param y Die Y-Position des Feldes
     * @return Den Feldstatus
     */
    private Feldstatus holeFeldStatus(int x, int y) {
        synchronized (felder[y][x]) {
            return felder[y][x];
        }
    }

    @Override
    public String toString() {
        // Für die Ausgabe muss ein Lock auf den gesamten Array geholt werden.
        // Sonst bekommt man keinen konsistenten Zustand, weil ja weiterhin Fische in den Array schreiben koennen.

        synchronized (felder) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Aquarium:\n");
            stringBuilder.append("x ... Feld mit Flosse\n");
            stringBuilder.append("A/</>/V Feld mit Kopf in einer der 4 Richtungen\n");

            for (int i = 0; i < spalten + 2; i++) {
                // Erzeuge oberen Rand: ------------
                stringBuilder.append("-");
            }
            stringBuilder.append('\n');

            for (int y = 0; y < zeilen; y++) {
                stringBuilder.append('|');
                for (int x = 0; x < spalten; x++) {
                    // Ein Feld mit Fisch wird als X dargestellt.
                    stringBuilder.append(felder[y][x] == Feldstatus.FLOSSE ? 'X' : felder[y][x] == Feldstatus.KOPFOBEN ? 'A' : felder[y][x] == Feldstatus.KOPFRECHTS ? '>' : felder[y][x] == Feldstatus.KOPFUNTEN ? 'V' : felder[y][x] == Feldstatus.KOPFLINKS ? '<' : ' ');
                }
                stringBuilder.append('|');
                stringBuilder.append('\n');
            }
            for (int i = 0; i < spalten + 2; i++) {
                // Erzeuge unteren Rand: ------------
                stringBuilder.append("-");
            }

            stringBuilder.append('\n');

            return stringBuilder.toString();
        }
    }

    public int getSpalten() {
        return spalten;
    }

    public int getZeilen() {
        return zeilen;
    }
}
