package oopb3.animal;

import oopb3.animal.abstr.PackAnimal;

/**
 * Loewen sind Pack-Tiere, da Weibchen eher Jagen und Maennchen eher fuer Revierkaempfe zustaendig sind.
 * Da ein Loewenrudel hierarchisch strukturiert ist, wuerde sich ein Tree als Datenstruktur eignen. Einfachkeithalber (und weil es keine Java Klasse fuer Tree gibt)
 * verwenden wir eine List
 */
public class Loewe extends PackAnimal {

    /**
     * Kostruktor der klasse Loewe
     *
     * @param erwarteteLebenszeit int, Lebenszeit in Stunden
     */
    public Loewe(int erwarteteLebenszeit) {
        setErwarteteLebenszeit(erwarteteLebenszeit);
    }

    /**
     * Anzahl an Stunden die das Tier im Sozialverband verbringen wird
     *
     * @return int
     */
    @Override
    public int social() {
        return (int) (getErwarteteLebenszeit() * 0.8f);
    }

    /**
     * VB: Loewen halten sich nicht in der Luft auf
     *
     * @return 0
     */
    @Override
    public int air() {
        return 0;
    }

    /**
     * VB: Loewen können zwar schwimmen, aber nicht lange
     * NB: Zeit in Stunden die sie im Wasser verbringen koennten
     *
     * @return int
     */
    @Override
    public int water() {
        return (int) (getErwarteteLebenszeit() * 0.01f);
    }

    /**
     * Zeit die Loewen am Boden verbringen, in dem Fall nur 99% der Lebenszeit weil 1 Prozent schon für das Wasser eingeteilt ist
     *
     * @return int
     */
    @Override
    public int ground() {
        return (int) (getErwarteteLebenszeit() * 0.99f);
    }

}
