/**
 * "Container" der ein Runnable um einen Fisch wrapped und signale an den Parent mittels Signal uebermittelt, dass beendet werden soll.
 */
public class FischThread implements Runnable {

    /**
     * Die Zeit die gewartet wird, bis ein Fisch sich erneut bewegt.
     */
    private final int wartezeitMillis;

    private final Fisch fisch;

    private final Signal signal;

    /**
     * Konstruktor der Klasse FischThread
     * VB: fisch != null && wartezeitMillis >= 0 && signal != null
     * @param fisch Objekt der Klasse Fisch
     * @param wartezeitMillis Wartezeit in Millisekunden
     * @param signal Objekt der Klasse Signal
     */
    public FischThread(Fisch fisch, int wartezeitMillis, Signal signal) {
        this.fisch = fisch;
        this.wartezeitMillis = wartezeitMillis;
        this.signal = signal;
    }

    /**
     * Implementierung des Runnable interfaces.
     * Laeuft in einer Schleife bis der Thread interrupted wurde bzw. das Signal Flag gesetzt ist.
     */
    @Override
    public void run() {
        // "Verwenden Sie Thread.interrupt() um einen Thread zu unterbrechen."
        while (!signal.getSignal()) {
            if(!fisch.bewege()) {
                signal.setSignal();
            }
            try {
                Thread.sleep(wartezeitMillis);
            } catch (InterruptedException e) {
                // Thread wurde interrupted, daher wird das Warten und die Bewegungsschleife beendet.
                break;
            }
        }
    }
}
