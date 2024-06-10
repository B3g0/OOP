/**
 * Signal-Klasse
 * Dient den Child_Threads dazu dem Parent zu signalisieren, dass abgebrochen werden soll (da es zuviele erfolglose Bewegungen gab)
 */
public class Signal {

    /**
     * Signal Flag. Zu beginn false
     */
    private volatile boolean signal = false;

    /**
     * Setzt das Signal Flag
     * @return
     */
    public synchronized void setSignal() {
        signal = true;
    }

    /**
     * Liefert das Signal Flag
     */
    public synchronized boolean getSignal() {
        return signal;
    }

}
