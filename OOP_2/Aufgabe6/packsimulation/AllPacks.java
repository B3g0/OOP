package packsimulation;

/**
 * Klasse zur Verwaltung mehrerer Rudel
 */
public class AllPacks implements Nodeable{

    /**
     * Liste der Packs die verwaltet werden.
     */
    private NodeList allpacksList;
    /**
     * Name des AllPacks
     */
    private final String allpacksName;

    /**
     * Erzeugt ein AllPacks mit vorgegebenem Namen
     * @param allpacksName Der Name des AllPacks
     */
    public AllPacks(String allpacksName) {
        this.allpacksName = allpacksName;
        allpacksList = new NodeList();
    }

    /**
     * Gibt den Namen des AllPacks zurueck
     * @return Der Name des AllPacks
     */
    private String getAllpacksName() {
        return allpacksName;
    }

    /**
     * Fuegt ein Pack in die Liste der Packs ein
     * @param pack Objekt des Typs Pack
     * VB: pack != null
     */
    public void addPack(Pack pack) {
        if (pack == null) {
            throw new IllegalArgumentException("pack darf nicht null sein!");
        }
        allpacksList.add(pack);
    }

    /**
     * Prüft ob pack in allpacksList vorhanden, falls ja wird dieses aus der Liste entfernt,
     * falls nicht dann wird eine entsprechende Meldung geworfen.
     * @param packName packName des zu entfernenden Packs
     * VB: packName nicht null und nicht leerer String
     */
    public void removePack(String packName) {
        if (packName == null || packName.trim().length() == 0) {
            throw new IllegalArgumentException("packName darf weder null, noch leerer String oder String aus Leerzeichen sein.");
        }
        else {
            Pack foundPack = null;

            for (Object object : allpacksList
            ) {
                Pack pack = (Pack) object;
                if (pack.getPackName().equals(packName)) {
                    foundPack = pack;
                    break;
                }
            }
            if (foundPack != null) {
                allpacksList.remove(foundPack);
            } else {
                throw new IllegalArgumentException("Kein Pack mit angegebenem packName in AllPacks vorhanden!");
            }
        }
    }

    /**
     * VB: packName ist ein nicht leerer String
     * @param packName Name des gewuenschten Packs
     * @return Pack mit gewuenschtem Name oder null
     */
    public Pack getPack(String packName) {
        if(packName == null || packName.trim().length() == 0) {
            throw new IllegalArgumentException("packName darf weder null, noch leerer String oder String aus Leerzeichen sein.");
        }
        else {
            for(Object object : allpacksList) {
                Pack pack = (Pack) object;
                if(pack.getPackName().equals(packName)) {
                    // Wenn ein Pack mit dem gewuenschten packName gefunden wurde, gebe es zurueck.
                    return pack;
                }
            }
            // Wenn kein Pack mit  dem gewuenschten packName gefunden wurde, gebe null zurueck.
            return null;
        }
    }

    /**
     * Gibt eine String-Darstellung des AllPacks zurueck.
     * Zusaetzlich sind String-Darstellungen aller Packs enthalten.
     * @return Eine String-Darstellung des AllPacks.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AllPacks mit Namen \"");
        stringBuilder.append(getAllpacksName());
        stringBuilder.append("\"\n");
        stringBuilder.append("Groesse des AllPacks: ");
        stringBuilder.append(allpacksList.getSize());
        stringBuilder.append('\n');
        stringBuilder.append("Packs des AllPacks: ");
        stringBuilder.append('\n');

        for (Object object : allpacksList ) {
            stringBuilder.append('\t');
            // Fuege Einzug fuer Packs hinzu
            stringBuilder.append(object.toString().replace("\n", "\n\t"));
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    /**
     * Gibt Infos ueber die Packs sowie deren Statistik zu einem bestimmten Zeitpunkt aus.
     * Der Filter auf einen Zeitpunkt betrifft, so wie in der Angabe gefordert, nur die Statistiken.
     * @param minutesSince2000 Zeitpunkt fuer die Statistiken
     */
    public void printInfo(int minutesSince2000) {
        System.out.println("AllPacks: " + getAllpacksName());
        System.out.println("Groesse des Allpacks: " + allpacksList.getSize());
        System.out.println("Packs des AllPacks inklusive Statistiken:");

        for (Object b : allpacksList
        ) {
            Pack p = (Pack) b;
            System.out.println("-----------------------------------");
            System.out.println(p);
            System.out.println("Pack-Statistiken: ");
            System.out.println(p.getStatistics(minutesSince2000));
            System.out.println("-----------------------------------");
        }
    }
}
