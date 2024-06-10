
public class Kerzenzieher implements Runnable {

    private final Lager lager;

    /**
     * Konstruktor der Klasse Kerzenzieher
     * @param lager Lager in welches die Kerzen gelagert werden
     */
    public Kerzenzieher(Lager lager){
        this.lager = lager;
    }

    /**
     * run-Methode der Klasse Kerzenzieher
     * Weitere Erläuterungen in der Methode
     */
    @Override
    public void run() {
        while(true) {
            Kerze kerze = null;
            try {
                //Zeit zum Produzieren einer Kerze
                Thread.sleep(util.random(5, 50));

                /*
                 * Random-Wert zum Bestimmen der Kerzenart
                 * Falls kerzenArt == 1 wird eine KurzeKerze hergestellt
                 * Falls kerzenArt == 2 wird eine LangeKerze hergestellt
                 */
                int kerzenArt = util.random(1, 2);
                if (kerzenArt == 1) {
                    kerze = new KurzeKerze();
                } else {
                    kerze = new LangeKerze();
                }
            } catch (InterruptedException e) {
                System.out.println("Kerzenzieher hat gerade Kerze produziert.");
                return;
            }

            try {
                //while-Schleife die wartet bis ein Platz im Lager frei wird und deponiert die dann dort
                while (!lager.putKerze(kerze)) {
                    synchronized (lager) {
                        lager.wait(50);
                    }
                }
            } catch (InterruptedException d) {
                System.out.println("Kerzenzieher hat gerade gewartet ein Kerze im Lager zu deponieren.");
                return;
            }
        }
    }
}
