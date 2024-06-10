package packsimulation;

/**
 * Ein Tier das Teil eines Rudels sein kann.
 */
public class Animal implements Nodeable {

    /**
     * statischer Int zur Sicherstellung, dass uniqueNumbers ueber alle Tiere eindeutig sind.
     */
    private static int generateID = 0;

    /**
     * Die eindeutige Nummer de Tiers
     * Invariante: uniqueNumber eines Animals muss >= 0 sein.
     * Invariante: uniqueNumber ist ueber alle Tier-Objekte eindeutig.
     */
    private final int uniqueNumber = generateID++;

    /**
     * Der Geburtszeitpunkt des Tieres, gemessen in Minuten ab 01.01.2000 00:00
     */
    private int birthdayInMinutes;

    /**
     * Die Anzahl der Tiere des Kindes
     * Invariante: = 0 wenn maennliches Tier, >= 0 wenn weibliches Tier
     */
    private int childrenCount;

    /**
     * Das Geschlecht des Tiers
     */
    private Sex sex;

    /**
     * Der aktuelle Rang des Tiers
     */
    private Rank rank;

    /**
     * Die Liste der Hormonwerte des aktuellen Rangs
     */
    private NodeList hormoneList;

    /**
     * Der Zeitpunkt seit dem das Tier ein Alpha-Tier ist in Minuten seit der Geburt
     */
    private Integer alphaSince;

    /**
     * Erzeugt ein neues Tier
     * @param birthdayInMinutes siehe Dokumentation der Klassenvariable
     * @param childrenCount siehe Dokumentation der Klassenvariable
     * @param sex siehe Dokumentation der Klassenvariable
     * @param rank siehe Dokumentation der Klassenvariable
     */
    public Animal(int birthdayInMinutes, int childrenCount, Sex sex, Rank rank) {
        setBirthdayInMinutes(birthdayInMinutes);
        // Geschlecht muss vor Kinderanzahl gesetzt werden!
        // Sonst schlaegt Ueberpruefung in setChildrenCount fehl!
        setSex(sex);
        setChildrenCount(childrenCount);
        changeRank(rank, birthdayInMinutes);
    }

    /**
     * Aendern des Rangs (Alpha, Beta) eines Tieres, wobei die Informationen
     * ueber die Hormonwerte des frueheren Rangs verloren gehen.
     *
     * Wir hatten die Ueberlegung, aus Alpha und Beta Tieren Unterklassen von Animal zu machen, haben uns aber dann dagegen entschieden,
     * weil wir, wenn wir konsequent waeren, auch aus Female und Male eigene Unterklassen machen muessten. Damit haetten wir 7 Klassen um
     * alle Eventualitaeten zu modellieren (Animal, Beta, Alpha, FemaleBeta,FemaleAlpha, MaleBeta, MaleAlpha).
     *
     * Dadurch ist das Programm um weitere Rangtypen leichter und uebersichtlicher erweiterbar.
     *
     * @param rank der neue Rang des Tiers
     * @param minutesSince2000 Der Zeitpunkt des Rangwechsels. >= 0 falls der neue Rang ALPHA ist.
     */
    private void changeRank(Rank rank, int minutesSince2000) {
        // Loesche Hormonwerte nur, wenn sich der Rang wirklich aendert.
        if(this.rank != rank) {
            hormoneList = new NodeList();
            this.rank = rank;
        }
        if(rank == Rank.ALPHA) {
            alphaSince = minutesSince2000 - birthdayInMinutes;
        }else {
            alphaSince = null;
        }
    }

    /**
     * Fuegt ein Hormon der Liste hinzu.
     * Wenn das Tier ein Alpha ist, koennen nur Cortisol Werte gespeichert werden.
     *
     * Wir hatten die Idee Hormon als eigene Klasse und Adrenalin und Cortisol als Unterklassen zu modellieren.
     * Da aber sowohl Adrenalin als auch Cortisol die gleiche Funktionalitaet haben, haben wir ein HormonType Enum fuer die Unterscheidung implementiert.
     *
     * @param hormone Das hinzuzufuegende Hormon
     */
    public void addHormone(Hormone hormone) {
        if(hormone.getType() == HormoneType.ADRENALIN && rank == Rank.ALPHA) {
            throw new IllegalArgumentException("Fuer Alpha-Tiere koennen nur Cortisol Hormone gespeichert werden.");
        }
        hormoneList.add(hormone);
    }

    /**
     * Gibt die eindeutige Id des Tiers zurueck
     * @return Die eindeutige Id des Tiers
     */
    public int getUniqueNumber() {
        return uniqueNumber;
    }

    /**
     * Gibt den Geburstzeitpunkt des Tiers zurueck
     * @return Der Geburtszeitpunk in Minuten seit 01.01.2000 zurueck
     */
    public int getBirthdayInMinutes() {
        return birthdayInMinutes;
    }

    /**
     * Setzt den Geburtszeitpunkt des Tiers
     * @param birthdayInMinutes Der Geburtszeitpunkt des Tiers in Minuten seit 01.01.2000
     */
    private void setBirthdayInMinutes(int birthdayInMinutes) {
        this.birthdayInMinutes = birthdayInMinutes;
    }

    /**
     * Gibt die Kinderanzahl des Tiers zurueck.
     * @return Die Kinderanzahl des Tiers.
     * VB: Tier muss weiblich sein
     */
    public int getChildrenCount() {
        if(sex != Sex.FEMALE) {
            throw new IllegalArgumentException("Tier ist nicht weiblich!");
        }
        return childrenCount;
    }

    /**
     * Setzt die Kinderanzahl des Tiers
     * @param childrenCount Die Kinderanzahl des Tiers.
     * VB: Tier muss weiblich sein
     */
    private void setChildrenCount(int childrenCount) {
        if(sex != Sex.FEMALE && childrenCount > 0) {
            throw new IllegalArgumentException("Tier ist nicht weiblich!");
        }
        this.childrenCount = childrenCount;
    }

    /**
     * Gibt das Geschlecht des Tiers zurueck.
     * @return Das Geschlecht des Tiers
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Setzt das Geschlecht des Tiers
     * @param sex Das Geschlecht des Tiers
     */
    public void setSex(Sex sex) {
        this.sex = sex;
        if(sex == Sex.MALE) {
            setChildrenCount(0);
        }
    }

    /**
     * Gibt den Rang des Tiers zurueck
     * @return Der Rang des Tiers
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Gibt die Liste der Hormone des Tiers zurueck
     * @return Die Liste der Hormone des Tiers
     */
    public NodeList getHormoneList() {
        return hormoneList;
    }

    /**
     * Gibt den Zeitpunkt zurueck, seit dem das Tier ein Alphatier ist.
     * @return Der Zeitpunkt, seit dem das Tier Alphatier ist, in Minuten seit der Geburt des Tiers.
     */
    public Integer getAlphaSince() {
        return alphaSince;
    }

    /**
     * Gibt eine String-Darstellung des Tiers zurueck
     * @return Eine String-Darstellung des Tiers.
     */
    @Override
    public String toString() {
        String string = "Animal mit animalId " + uniqueNumber +
                ", \nGeburtsdatum = " + birthdayInMinutes +
                ", \nAnzahl der Kinder = " + childrenCount +
                ", \nGeschlecht = " + sex +
                ", \nRang = " + rank +
                ", \nHormone = " + hormoneListToString();
        string += "Alphastatus = " + (getAlphaSince() == null ? "Kein Alpha" : "Alpha seit " + alphaSince);

        return string;
    }

    /**
     * Gibt eine String-Darstellung der Hormone des Tiers zurueck
     * @return Eine String-Darstellung der Hormone des Tiers.
     */
    private String hormoneListToString() {
        String hormones = "\n";

        for (Object b : hormoneList
                ) {
            Hormone hormone = (Hormone) b;
            hormones += "\t\tTyp: " + hormone.getType();
            hormones += ", Wert: " + hormone.getValue();
            hormones += "\n";
        }
        return hormones;
    }
}
