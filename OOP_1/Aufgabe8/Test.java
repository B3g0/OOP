import java.util.LinkedList;
import java.util.List;

/**
 * Aufgabenteilung:
 *  Paul: Grundstruktur und Lager
 *  Michi: Kerzenzieher
 *  Mensur: Verkaeufer und Kommentare
 *
 *  Test: Alle zusammen auf einem PC
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("Testlauf mit ausgeglichenen Ziehern/Verkaeufern");
        testLauf(3,3);
        System.out.println();
        System.out.println("Testlauf mit mehr Ziehern");
        testLauf(1,5);
        System.out.println();
        System.out.println("Testlauf mit mehr Verkaeufern");
        testLauf(5,1);
    }

    private static void testLauf(int numVerkauf, int numZieher){
        //Neues Lager erstellen und gleich ausgeben
        Lager lager = new Lager(4,5);
        lager.printLager();

        //Neue Liste mit den Threads die ausgeführt werden erstellen
        List<Thread> threadList = new LinkedList<>();

        //for-Schleife zum Erstellen der Threads für die Kerzenzieher
        //Je nach Wert der in der Abfrage eingegeben wird werden so viele Kerzenzieher erstellt
        for (int i = 0; i < numZieher; i++) {
            Kerzenzieher zieher = new Kerzenzieher(lager);
            Thread thread = new Thread(zieher);
            threadList.add(thread);
            thread.start();
        }

        //for-Schleife zum Erstellen der Threads für die Verkäufer
        //Je nach Wert der in der Abfrage eingegeben wird kann man beliebig viele Verkäufer erstellen
        for (int k = 0; k < numVerkauf; k++) {
            Verkaeufer verkaeufer = new Verkaeufer(lager);
            Thread threadV = new Thread(verkaeufer);
            threadList.add(threadV);
            threadV.start();
        }

        /**
         * Main-Thread wartet 1000 Milisekunden und läutet daraufhin die Glocke, die anderen Threads werden
         * unterbrochen und ausgegeben wer was gerade gemacht hat. Im Anschluss wird das Lager ausgegeben
         */
        try{
            Thread.sleep(1000);
            System.out.println("GING GONG");
            for(Thread t : threadList){
                t.interrupt();
            }
            Thread.sleep(2); //Damit alle Verkaeufer und Zieher ihren Zustand mitteilen können
            lager.printLager();
            //Exception-Handling
        } catch (InterruptedException e){
            System.out.println("Main Thread interrupted??");
            e.printStackTrace();
        }

    }
}
