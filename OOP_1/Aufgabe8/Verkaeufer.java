import com.sun.org.apache.xpath.internal.SourceTree;

public class Verkaeufer implements Runnable {

    private Lager lager;

    /**
     * Konstruktor für den Verkäufer
     *
     * @param lager Das Lager in welchem die Kerzen gelagert sind
     */
    public Verkaeufer(Lager lager) {
        this.lager = lager;
    }

    /**
     * run-Methode für den Verkäufer
     * Nähere Erläuterungen sind in der Methode enthalten
     */
    @Override
    public void run() {

        while(true){
            Kerze kerze = null;
            try{
                //Verkäufer wartet auf Kunden und führt Verkaufsgespräch
                Thread.sleep(util.random(5, 50));

                //Random-Wert zum Bestimmen der Kerzenart mit darauffolgender if-Abfrage
                int verkauf = util.random(1, 2);
                if (verkauf == 1) {
                    kerze = new KurzeKerze();
                } else {
                    kerze = new LangeKerze();
                }

            } catch (InterruptedException e){
                System.out.println("Verkaeufer hat ein Verkaufsgespraech gefuehrt");
                return;
            }

            try{
                while (lager.takeKerze(kerze.getClass()) == null) {
                    synchronized (lager) {
                        lager.wait(50);
                    }
                }

                //Print des Lagers und der Bestätigung, dass etwas verkauft wurde
                System.out.println(kerze.getClass().getSimpleName()+" verkauft! \nLagerstand: ");
                lager.printLager();
                System.out.println("#####################");

            } catch (InterruptedException e){
                System.out.println("Verkaeufer hat Kerze aus Lager geholt");
                return;
            }



        }
    }
}
