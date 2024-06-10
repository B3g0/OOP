package main;

import abstr.Abstract_Verbraucher;
import com.sun.org.apache.xpath.internal.SourceTree;

public class Waermepumpe extends Abstract_Verbraucher {
    /**
     * Konstruktor
     * @param speicher der Speicher
     */
    public Waermepumpe(Speicher speicher) {
        super(speicher);
    }

    /**
     * Vorbedingung: 0 <= hotd <= 24
     * Nachbedingung: val bestimmt ob Energie vom Speicher entnommen werden kann oder nicht
     * @param hotd the hour of the day (0 == Midnight, 12 == Lunch, 24 == Midnight again)
     * @throws SpeicherException Wenn nicht genug Energie im Speicher wird Exception geworfen
     */
    @Override
    public void use(int hotd) throws SpeicherException {
        if (Failure.is_failing()) {
            System.out.println("Systemausfall Waermepumpe!");
            return;
        }
        double val = (double)speicher.getCurrent() / (double)speicher.getMax();

        if(val >= 0.3){
            int drained = speicher.drain(speicher.getMax()/40, false);
            if(drained == 0){
                throw new SpeicherException("Use electricity from public energy storage");
            }
        }
    }
}
