import packsimulation.*;

class Test {

    /**
     * .) Klassenverteilung:
     * Mensur: Pack, Statistics, HormoneType, Sex
     * Christoph: Animal, Test, NodeList, Rank
     * Tobias: AllPacks, Nodeable, Node, Hormone
     *
     */
    public static void main(String[] args) {
        int testcase = 1;

        System.out.println("#############################################################################################\n");
        System.out.println(String.format("\t%d. TEST (testModifyAnimalsInPack)\n", testcase++));
        System.out.println("#############################################################################################\n");
        testModifyAnimalsInPack();

        System.out.println("#############################################################################################\n");
        System.out.println(String.format("\t%d. TEST (testDeleteAnimalsFromPack)\n", testcase++));
        System.out.println("#############################################################################################\n");
        testDeleteAnimalsFromPack();

        System.out.println("#############################################################################################\n");
        System.out.println(String.format("\t%d. TEST (testSinglePackStatistics)\n", testcase++));
        System.out.println("#############################################################################################\n");
        testSinglePackStatistics();

        System.out.println("#############################################################################################\n");
        System.out.println(String.format("\t%d. TEST (testNoAnimalsInPackStatistics)\n", testcase++));
        System.out.println("#############################################################################################\n");
        testNoAnimalsInPackStatistics();

        System.out.println("#############################################################################################\n");
        System.out.println(String.format("\t%d. TEST (testAllPacksStatistics)\n", testcase++));
        System.out.println("#############################################################################################\n");
        testAllPacksStatistics();

        System.out.println("#############################################################################################\n");
        System.out.println(String.format("\t%d. TEST (testAllPacksStatisticsWhereAnimalsInOnePackDoNotExistYet)\n", testcase++));
        System.out.println("#############################################################################################\n");
        testAllPacksStatisticsWhereAnimalsInOnePackDoNotExistYet();

        System.out.println("#############################################################################################\n");
        System.out.println(String.format("\t%d. TEST (testAllPacksRemovePackByName)\n", testcase++));
        System.out.println("#############################################################################################\n");
        testAllPacksRemovePackByName();
    }

    private static void testModifyAnimalsInPack() {
        Pack pack = new Pack("Pack 1");
        Animal a1 = new Animal(1000, 10, Sex.FEMALE, Rank.ALPHA);
        a1.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.4f));
        Animal a2 = new Animal(2000, 0, Sex.MALE, Rank.BETA);
        a2.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.4f));
        a2.addHormone(new Hormone(12, HormoneType.CORTISOL, 0.1f));
        Animal a3 = new Animal(400, 2, Sex.FEMALE, Rank.BETA);
        a3.addHormone(new Hormone(4001, HormoneType.ADRENALIN, 0.5f));
        a3.addHormone(new Hormone(401, HormoneType.CORTISOL, 0.5f));
        pack.addAnimal(a1);
        pack.addAnimal(a2);
        pack.addAnimal(a3);
        System.out.println(pack);

        int uniqueNumber = a1.getUniqueNumber();

        Animal toModify = pack.getAnimal(uniqueNumber);
        toModify.addHormone(new Hormone(10, HormoneType.CORTISOL, 50.4f));
        toModify.setSex(Sex.MALE);
        System.out.println(pack);
    }

    private static void testDeleteAnimalsFromPack() {
        Pack pack = new Pack("Pack 1");
        Animal a1 = new Animal(30000, 10, Sex.FEMALE, Rank.ALPHA);
        a1.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.4f));
        Animal a2 = new Animal(1000, 0, Sex.MALE, Rank.BETA);
        a2.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.4f));
        a2.addHormone(new Hormone(12, HormoneType.CORTISOL, 0.1f));
        Animal a3 = new Animal(3000, 2, Sex.FEMALE, Rank.BETA);
        a3.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.5f));
        a3.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.5f));
        pack.addAnimal(a1);
        pack.addAnimal(a2);
        pack.addAnimal(a3);
        System.out.println(pack);
        pack.deleteAnimal(a1.getUniqueNumber());
        pack.deleteAnimal(a3.getUniqueNumber());
        System.out.println(pack);
    }

    private static void testSinglePackStatistics() {
        Pack pack = new Pack("Pack 1");
        Animal a1 = new Animal(30000, 10, Sex.FEMALE, Rank.ALPHA);
        a1.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.4f));
        Animal a2 = new Animal(1000, 0, Sex.MALE, Rank.BETA);
        a2.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.4f));
        a2.addHormone(new Hormone(12, HormoneType.CORTISOL, 0.1f));
        Animal a3 = new Animal(3000, 2, Sex.FEMALE, Rank.BETA);
        a3.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.5f));
        a3.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.5f));
        pack.addAnimal(a1);
        pack.addAnimal(a2);
        pack.addAnimal(a3);
        System.out.println(pack.getStatistics(100000));
    }

    private static void testNoAnimalsInPackStatistics() {
        Pack pack = new Pack("Pack 1");
        // Hier wuerde eine Division durch 0 passieren, das wird aber in Pack ueberprueft und abgefangen.
        System.out.println(pack.getStatistics(100000));
    }

    private static void testAllPacksStatistics() {
        Pack pack = new Pack("Pack 1");
        Animal a1 = new Animal(30000, 10, Sex.FEMALE, Rank.ALPHA);
        a1.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.4f));
        Animal a2 = new Animal(1000, 0, Sex.MALE, Rank.BETA);
        a2.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.4f));
        a2.addHormone(new Hormone(12, HormoneType.CORTISOL, 0.1f));
        Animal a3 = new Animal(3000, 2, Sex.FEMALE, Rank.BETA);
        a3.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.5f));
        a3.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.5f));
        pack.addAnimal(a1);
        pack.addAnimal(a2);
        pack.addAnimal(a3);

        Pack pack2 = new Pack("Pack 2");
        Animal a4 = new Animal(3000, 1, Sex.FEMALE, Rank.BETA);
        a4.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.2f));
        Animal a5 = new Animal(3000, 2, Sex.FEMALE, Rank.ALPHA);
        a5.addHormone(new Hormone(5000, HormoneType.CORTISOL, 0.7f));
        a5.addHormone(new Hormone(50000, HormoneType.CORTISOL, 0.1f));
        pack2.addAnimal(a4);
        pack2.addAnimal(a5);

        AllPacks allPacks1 = new AllPacks("AllPacks 1");
        allPacks1.addPack(pack);
        allPacks1.addPack(pack2);
        allPacks1.printInfo(100000);
    }

    private static void testAllPacksStatisticsWhereAnimalsInOnePackDoNotExistYet() {
        Pack pack = new Pack("Pack 1");
        Animal a1 = new Animal(30000, 10, Sex.FEMALE, Rank.ALPHA);
        a1.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.4f));
        Animal a2 = new Animal(1000, 0, Sex.MALE, Rank.BETA);
        a2.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.4f));
        a2.addHormone(new Hormone(12, HormoneType.CORTISOL, 0.1f));
        Animal a3 = new Animal(3000, 2, Sex.FEMALE, Rank.BETA);
        a3.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.5f));
        a3.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.5f));
        pack.addAnimal(a1);
        pack.addAnimal(a2);
        pack.addAnimal(a3);

        Pack pack2 = new Pack("Pack 2");
        Animal a4 = new Animal(1000000, 1, Sex.FEMALE, Rank.BETA);
        a4.addHormone(new Hormone(1000000, HormoneType.CORTISOL, 0.2f));
        Animal a5 = new Animal(1000000, 2, Sex.FEMALE, Rank.ALPHA);
        a5.addHormone(new Hormone(1000000, HormoneType.CORTISOL, 0.7f));
        a5.addHormone(new Hormone(1000000, HormoneType.CORTISOL, 0.1f));
        pack2.addAnimal(a4);
        pack2.addAnimal(a5);

        AllPacks allPacks1 = new AllPacks("AllPacks 1");
        allPacks1.addPack(pack);
        allPacks1.addPack(pack2);
        allPacks1.printInfo(100000);
    }

    private static void testAllPacksRemovePackByName() {
        Pack pack = new Pack("Pack 1");
        Animal a1 = new Animal(30000, 10, Sex.FEMALE, Rank.ALPHA);
        a1.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.4f));
        Animal a2 = new Animal(1000, 0, Sex.MALE, Rank.BETA);
        a2.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.4f));
        a2.addHormone(new Hormone(12, HormoneType.CORTISOL, 0.1f));
        Animal a3 = new Animal(3000, 2, Sex.FEMALE, Rank.BETA);
        a3.addHormone(new Hormone(10, HormoneType.ADRENALIN, 0.5f));
        a3.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.5f));
        pack.addAnimal(a1);
        pack.addAnimal(a2);
        pack.addAnimal(a3);

        Pack pack2 = new Pack("Pack 2");
        Animal a4 = new Animal(3000, 1, Sex.FEMALE, Rank.BETA);
        a4.addHormone(new Hormone(10, HormoneType.CORTISOL, 0.2f));
        Animal a5 = new Animal(3000, 2, Sex.FEMALE, Rank.ALPHA);
        a5.addHormone(new Hormone(5000, HormoneType.CORTISOL, 0.7f));
        a5.addHormone(new Hormone(50000, HormoneType.CORTISOL, 0.1f));
        pack2.addAnimal(a4);
        pack2.addAnimal(a5);

        AllPacks allPacks1 = new AllPacks("AllPacks 1");
        allPacks1.addPack(pack);
        allPacks1.addPack(pack2);
        allPacks1.printInfo(100000);
        allPacks1.removePack("Pack 2");
        allPacks1.printInfo(100000);
    }

}