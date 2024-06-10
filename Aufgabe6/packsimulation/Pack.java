package packsimulation;

/**
 * Ein Rudel aus Tieren
 */
public class Pack implements Nodeable{

    /**
     * Die Liste der Tiere
     */
    private NodeList packList;

    /**
     * Der Name des Rudels
     */
    private final String packName;

    /**
     * Konstruktor der Klasse Pack
     * @param packName
     * VB: packName darf nicht null oder leerer String sein
     */
    public Pack(String packName) {
        this.packName = packName;
        packList = new NodeList();
    }

    /**
     * Fügt Animal in die packList ein
     * VB: animal != null
     * NB: animal in packList
     * @param animal Objekt der Klasse Animal
     */
    public void addAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("animal darf nicht null sein!");
        }
        packList.add(animal);
    }

    /**
     * VB: animalId in Pack enthalten
     * NB: Wird geprüft ob animal in packList vorhanden, falls ja wird animal aus packList entfernt, falls nein dann wird eine Exception geworfen
     * @param animalId id des zu entfernenden Animals
     */
    public void deleteAnimal(int animalId){
        if (animalId < 0) {
            throw new IllegalArgumentException("animalId muss groesser gleich null sein!");
        }

        Animal foundAnimal = null;
        for (Object b : packList
                ) {
            Animal a = (Animal) b;
            if (a.getUniqueNumber() == animalId) {
                foundAnimal = a;
                break;
            }
        }

        if (foundAnimal != null) {
            packList.remove(foundAnimal);
        } else {
            throw new IllegalArgumentException("Kein animal mit angegebener animalid im Pack enthalten!");
        }
    }

    /**
     * Liefert die Statistiken zu einem gegebenen Zeitpunkt
     * @param minutesSince2000 Zeitpunkt in Minuten seit 01.01.2000 00:00
     * @return Die Statistiken zum gegebenen Zeitpunkt
     */
    public Statistics getStatistics(int minutesSince2000) {
        Statistics statistics = new Statistics(minutesSince2000);
        statistics.setPackSize(getPackSize(minutesSince2000));
        statistics.setPackAge(getPackAge(minutesSince2000));
        statistics.setPackChildren(getPackChildren(minutesSince2000));
        statistics.setPackCortisolAll(getCortisol(minutesSince2000));
        statistics.setPackCortisolAlpha(getCortisolAlpha(minutesSince2000));
        statistics.setPackAdrenalinBeta(getAdrenalinBeta(minutesSince2000));
        statistics.setPackCortisolBeta(getCortisolBeta(minutesSince2000));
        statistics.setPackAlphaStatusDuration(getAlphaStatusDuration(minutesSince2000));
        return statistics;
    }

    /**
     * Liefert die Groesse des Rudel
     * @return Die Groesse des Rudels
     */
    private int getPackSize() {
        return packList.getSize();
    }

    /**
     * Liefert die Groesse des Rudels, wobei nur Tiere, die zum gegebenen Zeitpunkt schon gelebt haben, gezaehlt werden.
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Die Groesse des Rudels, wobei nur Tiere, die zum gegebenen Zeitpunkt schon gelebt haben, gezaehlt werden.
     */
    private int getPackSize(int minutesSince2000) {
        int size = 0;
        for (Object b : packList) {
            Animal a = (Animal) b;
            if(a.getBirthdayInMinutes() < minutesSince2000) {
                size++;
            }
        }
        return size;
    }

    /**
     * Liefert das durchschnittliche Alter der Tiere, die zum gegebenen Zeitpunkt schon gelebt haben
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Das durchschnittliche Alter der Tiere, die zum gegebenen Zeitpunkt schon gelebt haben
     */
    private float getPackAge(int minutesSince2000) {
        int size = 0;
        float ageTotal = 0;

        for (Object b : packList) {
            Animal a = (Animal) b;
            if(a.getBirthdayInMinutes() < minutesSince2000) {
                ageTotal += a.getBirthdayInMinutes();
                size++;
            }
        }

        if(size == 0) {
            return 0;
        }

        return (ageTotal/size)/(60*24);
    }

    /**
     * Liefert die durchschnittliche Kinderanzahl der Tiere, die zum gegebenen Zeitpunkt schon gelebt haben
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Die durchschnittliche Kinderanzahl der Tiere, die zum gegebenen Zeitpunkt schon gelebt haben
     */
    private float getPackChildren(int minutesSince2000) {
        int size = 0;
        float childrenTotal = 0;

        for (Object b: packList
             ) {
            Animal a = (Animal) b;
            if(a.getSex() == Sex.FEMALE && a.getBirthdayInMinutes() < minutesSince2000) {
                childrenTotal += a.getChildrenCount();
                size++;
            }
        }

        if(size == 0) {
            return 0;
        }

        return childrenTotal/size;
    }

    /**
     * Liefert den durchschnittlichen Cortisol-Wert der Tiere, die zum gegebenen Zeitpunkt schon gelebt haben
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Der durchschnittlichen Cortisol-Wert der Tiere, die zum gegebenen Zeitpunkt schon gelebt haben
     */
    private float getCortisol(int minutesSince2000) {
        int size = 0;
        float cortisol = 0;

        for (Object b: packList
             ) {
            Animal a = (Animal) b;
            if(a.getBirthdayInMinutes() < minutesSince2000) {
                for (Object c : a.getHormoneList()) {
                    Hormone hormone = (Hormone) c;
                    if(hormone.getTimestamp() < minutesSince2000) {
                        cortisol += hormone.getValue();
                        size++;
                    }
                }
            }
        }

        if(size == 0) {
            return 0;
        }

        return cortisol/size;
    }

    /**
     * Liefert den durchschnittlichen Cortisol-Wert der Alphatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Der durchschnittlichen Cortisol-Wert der Alphatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     */
    private float getCortisolAlpha(int minutesSince2000) {
        int size = 0;
        float cortisol = 0;

        for (Object b: packList
             ) {
            Animal a = (Animal) b;
            if (a.getRank().equals(Rank.ALPHA) && a.getBirthdayInMinutes() < minutesSince2000) {
                for (Object c : a.getHormoneList()
                        ) {
                    Hormone hormone = (Hormone) c;
                    if (hormone.getType() == HormoneType.CORTISOL && hormone.getTimestamp() < minutesSince2000) {
                        size++;
                        cortisol += hormone.getValue();
                    }
                }
            }
        }

        if(size == 0) {
            return 0;
        }

        return cortisol/size;
    }

    /**
     * Liefert den durchschnittlichen Cortisol-Wert der Betatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Der durchschnittlichen Cortisol-Wert der Betatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     */
    private float getCortisolBeta(int minutesSince2000) {
        int size = 0;
        float cort = 0;

        for (Object b : packList
                ) {
            Animal a = (Animal) b;
            if (a.getRank().equals(Rank.BETA) && a.getBirthdayInMinutes() < minutesSince2000) {
                for (Object c : a.getHormoneList()) {
                    Hormone hormone = (Hormone) c;
                    if (hormone.getType()==HormoneType.CORTISOL && hormone.getTimestamp() < minutesSince2000) {
                        size++;
                        cort += hormone.getValue();
                    }
                }
            }
        }

        if(size == 0) {
            return 0;
        }

        return cort/size;
    }

    /**
     * Liefert den durchschnittlichen Adrenalin-Wert der Betatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Der durchschnittlichen Adrenalin-Wert der Betatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     */
    private float getAdrenalinBeta(int minutesSince2000) {
        int size = 0;
        float adrenalin = 0;

        for (Object b: packList
             ) {
            Animal a = (Animal) b;
            if (a.getRank().equals(Rank.BETA) && a.getBirthdayInMinutes() < minutesSince2000) {
                for (Object c : a.getHormoneList()
                        ) {
                    Hormone hormone = (Hormone) c;
                    if (hormone.getType().equals(HormoneType.ADRENALIN) && hormone.getTimestamp() < minutesSince2000) {
                        size++;
                        adrenalin += hormone.getValue();
                    }
                }
            }
        }

        if(size == 0) {
            return 0;
        }

        return adrenalin/size;
    }

    /**
     * Liefert die durchschnittliche Dauer des Alphastatus der Alphatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     * @param minutesSince2000 Der gegebene Zeitpunkt
     * @return Die durchschnittliche Dauer des Alphastatus der Alphatiere, die zum gegebenen Zeitpunkt schon gelebt haben
     */
    private float getAlphaStatusDuration(int minutesSince2000) {
        int size = 0;
        float duration = 0;

        for (Object b : packList
                ) {
            Animal a = (Animal) b;
            if (a.getRank().equals(Rank.ALPHA) && a.getBirthdayInMinutes() < minutesSince2000) {
                size++;
                duration += a.getAlphaSince();
            }
        }

        if(size == 0) {
            return 0;
        }

        return (duration/size)/(60*24);
    }

    /**
     * Liefert den Namen des Packs
     * @return Der Name des Packs
     */
    public String getPackName() {
        return packName;
    }

    /**
     * Leifert die Liste des Packs
     * @return Die Liste des Packs
     */
    private NodeList getPackList() {
        return packList;
    }

    /**
     * Liefert ein Animal mit gegebener Id
     * @param animalId Die Id des gesuchten Tiers
     * @return Das gesuchte Tier oder null, wennn es nicht gefunden wurde
     * VB: animalId >= 0
     */
    public Animal getAnimal(int animalId) {
        if(animalId < 0) {
            throw new IllegalArgumentException("animalIds muessen groesser gleich 0 sein!");
        }
        else {
            for(Object object : packList) {
                Animal animal = (Animal) object;
                if(animal.getUniqueNumber() == animalId) {
                    // Wenn ein Animal mit der gewuenschten animalId gefunden wurde, gebe es zurueck.
                    return animal;
                }
            }
            // Wenn kein Animal mit der gewuenschten animalId gefunden wurde, gebe null zurueck.
            return null;
        }
    }

    /**
     * Liefert eine String-Darstellung des Packs.
     * String-Darstellungen aller Tiere sind inkludiert.
     * @return Eine String-Darstellung des Packs.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pack mit Namen \"");
        stringBuilder.append(getPackName());
        stringBuilder.append("\"\n");
        stringBuilder.append("Groesse des Packs: ");
        stringBuilder.append(getPackSize());
        stringBuilder.append('\n');
        stringBuilder.append("Alle Tiere im Pack: \n");
        for (Object object : getPackList()) {
            // Fuege Einzug fuer Animals hinzu
            stringBuilder.append('\t');
            stringBuilder.append(object.toString().replace("\n", "\n\t"));
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }


}
