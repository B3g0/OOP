package abstr;

import main.Speicher;
import main.SpeicherException;

//GUT: Grundlegende zukuenftige Funktionen koennen bereits hier festgelegt werden. Dies erhoeht die Wartbarkeit


public abstract class Abstract_Verbraucher {
    protected Speicher speicher;


    /**
     * Der Konstruktor des Verbrauchers. Es muss ein Speicher angegeben werden, aus dem Material gezogen wird
     * @param speicher der Speicher
     */
    public Abstract_Verbraucher(Speicher speicher){
        this.speicher = speicher;
    }

    /**
     * Der Verbraucher wrid angestossen, Material aus dem Speicher zu holen. Dabei wird die derzeitige Stunde
     * verwendet, um genauere Simulationen zu ermoeglichen. Dieser Wert soll in Implementationen verwendet werden
     * Diese Funktion kann immer aufgerufen werden, wenn der Speicher voll ist oder ein anderer Fehlerfall aufscheint, 
     * wird eine SpeicherException geworfen
     * @param hotd the hour of the day (0 == Midnight, 12 == Lunch, 24 == Midnight again)
     */
    public abstract void use(int hotd) throws SpeicherException;
}
