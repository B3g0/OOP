import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Test {


    /**
     * Aufgabenverteilung:
     * Tobias: Aquarium Fisch Feldstatus Fischlage
     * Mensur: Fisch Fischsimulation Util Test
     * Christoph: Fisch FischSimulation FischThread Signal
     *
     * @param args
     */
    public static void main(String[] args) {
        testSmallAquariumFewFish();
        System.out.println("####################################################################################################################");
        System.out.println("####################################################################################################################");
        System.out.println("####################################################################################################################\n");
        testMediumAquariumSomeFish();
        System.out.println("####################################################################################################################");
        System.out.println("####################################################################################################################");
        System.out.println("####################################################################################################################\n");
        testLargeAquariumALotFish();
    }

    public static void testSmallAquariumFewFish() {
        Aquarium aquarium = new Aquarium(10, 8);
        List<Fisch> fische = new ArrayList<>();
        fische.add(new Fisch(aquarium, new Point(0, 3), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(8, 6), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(3, 6), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(0, 6), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(6, 6), Fischlage.LINKS));
        System.out.println(aquarium.toString());
        FischSimulation simulation = new FischSimulation(3000, aquarium, fische);
        System.out.println("Initiales Aquarium:");
        System.out.println(aquarium.toString());
        simulation.startFischSimulation();
    }

    public static void testMediumAquariumSomeFish() {
        Aquarium aquarium = new Aquarium(20, 18);
        List<Fisch> fische = new ArrayList<>();
        fische.add(new Fisch(aquarium, new Point(0, 3), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(8, 6), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(3, 6), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(0, 6), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(6, 6), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(14, 14), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(10, 12), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(1, 14), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(12, 6), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(16, 8), Fischlage.LINKS));
        FischSimulation simulation = new FischSimulation(3000, aquarium, fische);
        System.out.println("Initiales Aquarium:");
        System.out.println(aquarium.toString());
        simulation.startFischSimulation();
    }

    public static void testLargeAquariumALotFish() {
        Aquarium aquarium = new Aquarium(60, 54);
        List<Fisch> fische = new ArrayList<>();
        fische.add(new Fisch(aquarium, new Point(0, 3), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(8, 6), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(3, 6), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(0, 6), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(6, 6), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(14, 14), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(10, 12), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(1, 14), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(12, 24), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(16, 26), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(20, 21), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(28, 24), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(23, 24), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(20, 24), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(26, 24), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(34, 32), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(30, 30), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(21, 32), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(32, 42), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(36, 44), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(40, 49), Fischlage.LINKS));
        fische.add(new Fisch(aquarium, new Point(48, 42), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(43, 42), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(40, 44), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(46, 44), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(54, 50), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(50, 48), Fischlage.OBEN));
        fische.add(new Fisch(aquarium, new Point(41, 50), Fischlage.RECHTS));
        fische.add(new Fisch(aquarium, new Point(52, 42), Fischlage.UNTEN));
        fische.add(new Fisch(aquarium, new Point(56, 44), Fischlage.LINKS));

        FischSimulation simulation = new FischSimulation(3000, aquarium, fische);
        System.out.println("Initiales Aquarium:");
        System.out.println(aquarium.toString());
        simulation.startFischSimulation();
    }

}
