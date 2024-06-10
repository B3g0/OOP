package packsimulation;

/**
 * Die Werte einer Statistik zu einem gewissen Zeitpunkt (in Minuten ab 01.01.2000 00:00)
 */
public class Statistics {
    private int statisticDate;
    private int packSize;
    private float packAge;
    private float packChildren;
    private float packCortisolAll;
    private float packCortisolAlpha;
    private float packCortisolBeta;
    private float packAdrenalinBeta;
    private float packAlphaStatusDuration;

    /**
     * Erzeugt ein neues Statistik-Objekt
     * @param statisticDate Zeitpunkt der Statistik
     */
    public Statistics(int statisticDate) {
        setStatisticDate(statisticDate);
    }

    /**
     * Liefert den Zeitpunkt der Statistik
     * @return Der Zeitpunkt der Statistik
     */
    public int getStatisticDate() {
        return statisticDate;
    }

    /**
     * Setzt den Zeitpunkt der Statistik
     * @param statisticDate Der Zeitpunkt der Statistik
     */
    private void setStatisticDate(int statisticDate) {
        if (statisticDate < 0) {
            throw new IllegalArgumentException("statisticDate kann nicht kleiner als 0 sein!");
        }
        this.statisticDate = statisticDate;
    }

    /**
     * Liefert die Groesse des Packs
     * @return Die Groesse des Packs
     */
    public int getPackSize() {
        return packSize;
    }

    /**
     * Setzt die Groesse des Packs
     * @param packSize Die Groesse des Packs
     */
    public void setPackSize(int packSize) {
        if (packSize < 0) {
            throw new IllegalArgumentException("packSize kann nicht kleiner als 0 sein!");
        }
        this.packSize = packSize;
    }

    /**
     * Liefert das durchschnittliche Alter des Packs
     * @return Das durchschnittliche Alter des Packs
     */
    public float getPackAge() {
        return packAge;
    }

    /**
     * Setzt das durchschnittliche Alter des Packs
     * @param packAge Das durchschnittliche Alter des Packs
     */
    public void setPackAge(float packAge) {
        if (packAge < 0) {
            throw new IllegalArgumentException("packAge kann nicht kleiner als 0 sein!");
        }
        this.packAge = packAge;
    }

    /**
     * Liefert die durchschnittliche Kinderanzahl der Tiere des Packs
     * @return Die durchschnittliche Kinderanzahl der Tiere des Packs
     */
    public float getPackChildren() {
        return packChildren;
    }

    /**
     * Setzt die durchschnittliche Kinderanzahl der Tiere des Packs
     * @param packChildren Die durchschnittliche Kinderanzahl der Tiere des Packs
     */
    public void setPackChildren(float packChildren) {
        if (packChildren < 0) {
            throw new IllegalArgumentException("packChildren kann nicht kleiner als 0 sein!");
        }
        this.packChildren = packChildren;
    }

    /**
     * Liefert den durchschnittlichen Cortisol-Wert der Tiere
     * @return Der durchschnittliche Cortisol-Wert der Tiere
     */
    public float getPackCortisolAll() {
        return packCortisolAll;
    }

    /**
     * Setzt den durchschnittlichen Cortisol-Wert der Tiere
     * @param packCortisolAll Der durchschnittlichen Cortisol-Wert der Tiere
     */
    public void setPackCortisolAll(float packCortisolAll) {
        if (packCortisolAll < 0) {
            throw new IllegalArgumentException("packCortisolAll kann nicht kleiner als 0 sein!");
        }
        this.packCortisolAll = packCortisolAll;
    }

    /**
     * Liefert den durchschnittlichen Cortisol-Wert der Alphatiere
     * @return Der durchschnittliche Cortisol-Wert der Alphatiere
     */
    public float getPackCortisolAlpha() {
        return packCortisolAlpha;
    }

    /**
     * Setzt den durchschnittlichen Cortisol-Wert der Alphatiere
     * @param packCortisolAlpha Der durchschnittliche Cortisol-Wert der Alphatiere
     */
    public void setPackCortisolAlpha(float packCortisolAlpha) {
        if (packCortisolAlpha < 0) {
            throw new IllegalArgumentException("packCortisolAlpha kann nicht kleiner als 0 sein!");
        }
        this.packCortisolAlpha = packCortisolAlpha;
    }

    /**
     * Liefert den durchschnittlichen Cortisol-Wert der Betatiere
     * @return Der durchschnittliche Cortisol-Wert der Betatiere
     */
    public float getPackCortisolBeta() {
        return packCortisolBeta;
    }

    /**
     * Setzt den durchschnittlichen Cortisol-Wert der Betatiere
     * @param packCortisolBeta Der durchschnittliche Cortisol-Wert der Betatiere
     */
    public void setPackCortisolBeta(float packCortisolBeta) {
        if (packCortisolBeta < 0) {
            throw new IllegalArgumentException("packCortisolBeta kann nicht kleiner als 0 sein!");
        }
        this.packCortisolBeta = packCortisolBeta;
    }

    /**
     * Liefert den durchschnittlichen Adrenalin-Wert der Betatiere
     * @return Der durchschnittliche Adrenalin-Wert der Betatiere
     */
    public float getPackAdrenalinBeta() {
        return packAdrenalinBeta;
    }

    /**
     * Setzt den durchschnittlichen Adrenalin-Wert der Betatiere
     * @param packAdrenalinBeta Der durchschnittliche Adrenalin-Wert der Betatiere
     */
    public void setPackAdrenalinBeta(float packAdrenalinBeta) {
        if (packAdrenalinBeta < 0) {
            throw new IllegalArgumentException("packAdrenalinBeta kann nicht kleiner als 0 sein!");
        }
        this.packAdrenalinBeta = packAdrenalinBeta;
    }

    /**
     * Liefert die durchschnittliche Dauer des Alphastatus der Alphatiere
     * @return Die durchschnittliche Dauer des Alphastatus der Alphatiere
     */
    public float getPackAlphaStatusDuration() {
        return packAlphaStatusDuration;
    }

    /**
     * Setzt die durchschnittliche Dauer des Alphastatus der Alphatiere
     * @param packAlphaStatusDuration Die durchschnittliche Dauer des Alphastatus der Alphatiere
     */
    public void setPackAlphaStatusDuration(float packAlphaStatusDuration) {
        if (packAlphaStatusDuration < 0) {
            throw new IllegalArgumentException("packAlphaStatusDuration kann nicht kleiner als 0 sein!");
        }
        this.packAlphaStatusDuration = packAlphaStatusDuration;
    }

    /**
     * Liefert eine String-Darstellung der Statistik
     * @return Eine String-Darstellung der Statistik
     */
    @Override
    public String toString() {
        return "Statistics zum Pack " +
                "Datum = " + statisticDate +
                ", \n\tGroesse = " + packSize +
                ", \n\tDurchschnittsalter = " + packAge +
                ", \n\tDurchschnittliche Anzahl der Kinder = " + packChildren +
                ", \n\tDurchschnittlicher Cortisolspiegel = " + packCortisolAll +
                ", \n\tDurschnittlicher Cortisolspiegel der Alphas = " + packCortisolAlpha +
                ", \n\tDurschnittlicher Cortisolspiegel der Betas = " + packCortisolBeta +
                ", \n\tDurchschnittlicher Adrenalinspiegel der Betas = " + packAdrenalinBeta +
                ", \n\tDurchschnittliche Dauer des Alphastatus = " + packAlphaStatusDuration + "\n";
    }

}
