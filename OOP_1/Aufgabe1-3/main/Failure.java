package main;

public class Failure {
    /**
     * Simuliert einen moeglichen Ausfall eines Geraets. Die Wahrscheinlichkeit dafuer betraegt 0.1% pro Aufruf der Methode
     * @return True, wenn das System ausfallen soll, sonst False
     */
    public static boolean is_failing() {
        int min = 0;
        int max = 1000;
        return (util.random(min, max) <= 1);
    }
}
