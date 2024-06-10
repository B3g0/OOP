import java.util.concurrent.ThreadLocalRandom;

/**
 * Hilfsklasse fuer diverse mathematische Funktionen
 */
class Util {
    /**
     * Hilfsmethode um einen Random Wert zu berechnen
     *
     * VB: min < max && max < Integer.MAX_VALUE
     * NB: retourniert pseudo - random Nummer zwischen min und max
     * NOTE: Hilfsmethode um einen Random Wert zu berechnen
     * ERROR: wenn max = Integer.MAX_VALUE ist, wird, da max um 1 erhoeht wird (overflow vom int!), in den meisten Faellen eine IllegalArgumentException geworfen werden (von ...nextInt(..))
     * @param min einschliesslich diesem Mindestwert
     * @param max einschliesslich diesem Maximalwert
     * @return ein zufaelliger Wert zwischen min und max
     */
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * VB: min < max
     * NB:
     *  Wenn Value < min ist, wird min zurueck gegeben.
     *  Wenn Value > max ist, wird max zurueck gegeben.
     *  Wenn Value zwischen min und max liegt, wird value zurueck gegeben.
     *
     * @param min   der minimale Wert der von clamp geliefert werden kann
     * @param max   der maximale Wert der von clamp geliefert werden kann
     * @param value der Wert der "gekuerzt" werden soll
     * @return
     */
    public static int clampValue(int min, int max, int value) {
        if (min > max) {
            throw new IllegalArgumentException("Min darf nicht groeßer als Max sein!");
        }
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Wandle Sekunden in Nanosekunden um
     *
     * VB: seconds > 0
     * VB: seconds passt als Nanosekunden in long. Dafür muss gelten: seconds < 9223372036 bzw. seconds*10^9 < 9223372036854776000
     *
     * @param seconds Sekunden als int > 0
     * @return Nanosekunden als long
     */
    public static long secondsToNanoseconds(long seconds) {
        return seconds * 1000000000L;
    }
}
