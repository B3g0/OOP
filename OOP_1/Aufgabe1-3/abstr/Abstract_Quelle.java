package abstr;

public abstract class Abstract_Quelle {
    protected double maxValue;
    protected double aging;

    //GUT: Grundlegende Funktionen, wie das Aging, koennen bereits hier festgelegt werden. Dies erhoeht die Wartbarkeit
    //SCHLECHT: Die Erzeugungsmenge kann nur fuer Hour_of_the_day festgelegt werden, nicht monatsweise bzw jaehrlich.


    /**
     * Konstruktor der Quelle. Der uebergebene MaxValue entspricht der maximalen Erzeugungsmenge pro Stunde. Dieser Wert
     * muss nicht statisch gleich sein, sondern kann in der Implementierung veraendert werden, um z.B. Alterung zu
     * simulieren
     * @param maxValue die maximale Erzeugungsmenge pro Stunde fuer die Quelle
     */
    public Abstract_Quelle(int maxValue){
        this.maxValue = maxValue;
        aging = 0;

    }

    /**
     * Zeigt die grundlegende maximale Erzeugungsmenge nach Aging an. Dieser Wert soll von den Implementierungen
     * hergenommen werden und an die Quelle angepasst werden. Um Alterung zu aendern, verwende setAging
     * @param hotd the hour of the day (0 == Midnight, 12 == Lunch, 24 == Midnight again)
     * @return Erzeugungsmenge for value
     */
    public int get_Erzeugungsmenge(int hotd){
        maxValue = maxValue*(1-this.aging);
        return get_Maxvalue();
    }

    /**
     * Getter des MaxValues - dieser Wert wird durch Aging veraendert
     * @return the current MaxValue
     */
    public int get_Maxvalue(){
        return (int)this.maxValue;
    }

    /**
     * Aging in Percent - the maxvalue will be decreased by this value at every get_Erzeugungsmenge
     * @param d aging in %. Um diesen Wert wird die Maximalspeichergroesse vor jedem Aufruf von getErzeugungsmenge
     *          verkleinert.
     */
    public void setAging(double d){
        this.aging = d;
    }

    /**
     * Getter fuer die jeweilige Unit der Quelle (meistens in Liter, KG, ...)
     * @return die Einheit der erzeugten Substanz.
     */
    public abstract String getUnit();

}
