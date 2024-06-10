package main;

import abstr.Abstract_Verbraucher;

import java.util.concurrent.ThreadLocalRandom;

public class Giessanlage extends Abstract_Verbraucher {

    private int[] hoursToWater = {6,7,17,18};

    /**
     * Konstruktor der Giessanlage. Nach Abstract_Verbraucher muss ein Speicher uebergeben werden.
     * @param speicher der Speicher, in den der Verbraucher Zeug pusht
     */
    public Giessanlage(Speicher speicher) {
        super(speicher);
    }

    //Siehe Abstract_Verbraucher

    /**
     * Vorbedingung: hotd liegt zwischen 0 und 24
     * Nachbedingung: Methode entscheided je nach hotd ob Pflanzen gegossen werden muessen, und wenn ja dann mit welcher
     * Menge an Wasser abhaengig von dem rand-Faktor.
     * @param hotd the hour of the day (0 == Midnight, 12 == Lunch, 24 == Midnight again)
     * @throws SpeicherException Wenn zu wenig bis gar kein Wasser im Speicher vorhanden, dann wirft die Methode die
     * jeweilige Exception
     */
    @Override
    public void use(int hotd) throws SpeicherException {
        if (Failure.is_failing()) {
            System.out.println("Systemausfall Giessanlage!");
            return;
        }
        boolean waterPlants = false;
        for(int i : hoursToWater){
            if(i == hotd){
                waterPlants = true;
                break;
            }
        }

        if(waterPlants){
            int rand = util.random(0, 100);
            int drained = 0;
            int shouldDrain = 0;
            if(rand > 30 && rand < 80){
                shouldDrain = 10;
            }else if(rand >= 80) {
                shouldDrain = 20;
            }

            if(shouldDrain > 0){
                drained = speicher.drain(shouldDrain, true);

                if(drained == 0){
                    throw new SpeicherException("Get water from public water storage");
                } else if(drained < shouldDrain){
                    throw new SpeicherException("Get water partially from public water storage");
                }
            }

        }
    }

}
