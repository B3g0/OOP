package main;

import java.util.concurrent.ThreadLocalRandom;

public class util {
    /**
     * Hilfsmethode um einen Random Wert zu berechnen
     * @param min einschliesslich diesem Mindestwert
     * @param max einschliesslich diesem Maximalwert
     * @return ein zufaelliger Wert zwischen min und max
     */
    public static int random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
