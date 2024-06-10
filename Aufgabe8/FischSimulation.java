import java.util.ArrayList;
import java.util.List;

public class FischSimulation {

    // die minimale/maximale Wartezeit die ein Fisch zu warten hat zwischen bewegungsablaeufen
    private static final int MIN_WARTEZEIT_MILLIS = 5;
    private static final int MAX_WARTEZEIT_MILLIS = 50;
    // size of stack for threads
    public static final int STACK_SIZE = 16 * 1024;

    // dauer in Millis
    private int simulationsdauer;

    private Signal signal;
    private Aquarium aquarium;
    private List<Fisch> fische;
    private List<Thread> fischSimulationen;

    /**
     * Konstruktoren der FischSimulation-Klasse
     * VB: aquarium != null && fische != null
     */
    public FischSimulation(int simulationsDauer, Aquarium aquarium, List<Fisch> fische) {
        this.simulationsdauer = simulationsDauer;
        this.aquarium = aquarium;
        this.fische = fische;
    }

    /**
     * Ueberprueft ob alle Fische im Aquarium Platz haben und selektiert einen Leitfisch
     */
    private void erzeugeAquariumUndFische() {
        if(fische.size()*2 >= (aquarium.getSpalten() * aquarium.getZeilen())) {
            throw new IllegalArgumentException("Aquarium ist zu klein fuer die Menge an fischen!");
        }

        int leitfisch = Util.random(0, fische.size()-1);
        fische.get(leitfisch).setLeitfisch(true);
    }

    /**
     * Erzeugt die Fischthreads fuer jeden Fisch.
     */
    private void erzeugeFischThreads() {
        signal = new Signal();
        this.fischSimulationen = new ArrayList<>();
        for (int i = 0; i < fische.size(); i++) {
            int wartezeit = Util.random(MIN_WARTEZEIT_MILLIS, MAX_WARTEZEIT_MILLIS);
            FischThread fischSimulation = new FischThread(fische.get(i), wartezeit, signal);
            Thread thread = new Thread(null,fischSimulation,String.format("fisch thread %d",i), STACK_SIZE);
            fischSimulationen.add(thread);
        }
    }

    /**
     * Startet die Threads der einzelnen Fische und wartet solange bis ein Signal zum Beenden gesetzt wurde.
     * Nachdem das Signal gesetzt wurde, werden die Threads interrupted und die gewuenschten Infos ausgegeben.
     *
     * Sollte kein Signal gesetzt werden, weil die Fische genug Platz haben sich zu bewegen ohne erfolglose Bewegungen, werden die Threads nach
     * simulationsdauer interrupted.
     */
    public void startFischSimulation() {
        erzeugeAquariumUndFische();
        erzeugeFischThreads();

        // starte alle fisch threads
        for(Thread thread : fischSimulationen) {
            thread.start();
        }

        long simulationRunningSince = System.currentTimeMillis();
        while(!signal.getSignal()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException("parent thread got interrupted.");
            }
            // nach 'simulationsdauer' Millisekunden gab es noch nicht genug erfolglose Bewegungsablaeufe in einem Fisch,
            // weshalb die Schleife verlassen wird.
            if(System.currentTimeMillis() - simulationRunningSince > simulationsdauer) {
                signal.setSignal();
            }
        }

        // Signal zum beenden wurde erhalten. Interrupte alle Threads
        for(Thread thread : fischSimulationen) {
            thread.interrupt();
        }

        for(Fisch fisch : fische) {
            System.out.println(fisch);
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Letzter Stand des Aquariums:");
        System.out.println(aquarium.toString());

    }

}
