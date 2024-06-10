import javafx.geometry.Pos;
import sun.font.CompositeStrike;

import java.util.LinkedList;
import java.util.List;

public class Lager {

    /**
     * Private Klasse Position für die Position der Kerzen
     */
    private class Position{
        private int x,y;
        //Konstruktor der Klasse
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        //Getter für X-Koordinate
        public int getX() {
            return x;
        }

        //Getter für Y-Koordinate
        public int getY() {
            return y;
        }
    }

    /**
     * Klasse für den Platz einer Kerze
     */
    private class KerzenPlatz{
        private Kerze kerze;
        private List<Position> positions;

        //Konstruktor der Klasse für eine Position
        public KerzenPlatz(Kerze k, Position pos){
            this.kerze = k;
            this.positions = new LinkedList<>();
            this.positions.add(pos);
        }

        //Konstruktor der Klasse für 2 Positions
        public KerzenPlatz(Kerze k, Position pos1, Position pos2){
            this(k, pos1);
            this.positions.add(pos2);
        }
    }

    private enum posStat {leer, kurz, lang};
    private posStat[][] plaetze;
    private List<KerzenPlatz> kerzenPlaetze;
    private int laenge, breite;

    /**
     * Konstruktor für Lager-Klasse
     * VB: laenge > 0 && breite > 0
     * NB: Lager mit den definierten Größen wird erstellt und die Plätze als anfangs leer markiert
     * @param laenge Wert für Länge
     * @param breite Wert für Breite
     */
    public Lager(int laenge, int breite){
        this.laenge = laenge;
        this.breite = breite;
        plaetze = new posStat[laenge][breite];
        for (int i = 0; i < laenge; i++) {
            for (int j = 0; j < breite; j++) {
                plaetze[i][j] = posStat.leer;
            }
        }
        kerzenPlaetze = new LinkedList<>();
    }

    /**
     * Methode zum deponieren einer Kerze
     * VB: kerze != null
     * NB: Länge der Kerze wird überprüft und daraufhin ein Platz für sie gesucht, sobald dieser gefunden
     * wird sie auf dem Platz gelagert und der Platz den sie einnimmt markiert und true zurückgegeben wenn erfolgreich
     * Dabei gibt es unterschiedliche Abläufe je nach Kerze die in der Methode mit KOmmentaren markiert sind
     * @param kerze Objekt Kerze welches übergeben wird
     * @return true wenn Platz gefunden, false sonst
     */
    public synchronized boolean putKerze(Kerze kerze){
        boolean lang = kerze.laenge() == 2;
        for (int i = 0; i < laenge; i++) {
            for (int j = 0; j < breite; j++) {
                if(plaetze[i][j] == posStat.leer && lang){
                    Position pos1 = new Position(i, j);
                    Position pos2 = null;
                    //Lange Kerze
                    if(j+1 < breite && plaetze[i][j+1] == posStat.leer){
                        pos2 = new Position(i, j+1);
                    } else if(i+1 < laenge && plaetze[i+1][j] == posStat.leer){
                        pos2 = new Position(i+1, j);
                    }

                    if(pos2 != null){
                        KerzenPlatz platz = new KerzenPlatz(kerze, pos1, pos2);
                        kerzenPlaetze.add(platz);
                        plaetze[i][j] = posStat.lang;
                        plaetze[pos2.x][pos2.y] = posStat.lang;
                        notifyAll();
                        return true;
                    }

                } else if(plaetze[i][j] == posStat.leer){
                    //Kurze Kerze
                    Position pos = new Position(i, j);
                    KerzenPlatz platz = new KerzenPlatz(kerze, pos);
                    kerzenPlaetze.add(platz);
                    plaetze[i][j] = posStat.kurz;
                    notifyAll();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Methode zum entfernen der Kerze aus dem Lager
     * VB: kerzenArt != null
     * NB: Die Kerzenpläze werden in einer foreach-Schleife durchlaufen und dabei überprüft ob es sich bei der kerzenArt
     * um Kerzen handelt. Dann wird die Kerze aus dem Lager entfernt und Platzmarkierungen aktualisiert
     * @param kerzenArt Klasse der Kerze
     * @return null wenn Kerze nicht gefunden die entfernt werden sollte
     */
    public synchronized Kerze takeKerze(Class kerzenArt){
        for(KerzenPlatz kp : kerzenPlaetze){
            if(kerzenArt.isInstance(kp.kerze)){
                kerzenPlaetze.remove(kp);
                for(Position pos : kp.positions){
                    plaetze[pos.x][pos.y] = posStat.leer;
                }
                notifyAll();
                return kp.kerze;
            }
        }
        return null;
    }

    /**
     * Print-Methode für das Lager um eine visuelle Veranschaulichung zu gewährleisten
     */
    public synchronized void printLager(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < breite*2; i++) {
            sb.append('-');
        }
        System.out.println(sb.toString());

        for (int i = 0; i < laenge; i++) {
            for (int j = 0; j < breite; j++) {
                switch(plaetze[i][j]){
                    case leer:
                        System.out.print("o ");
                        break;
                    case kurz:
                        System.out.print("+ ");
                        break;
                    case lang:
                        System.out.print("# ");
                        break;
                }
                if(j == breite-1){
                    System.out.println();
                }
            }
        }

        System.out.println(sb.toString());
    }

}
