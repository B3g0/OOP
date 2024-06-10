package oopb3.animal.abstr;

/*
Anmerkung zur Umsetzung Animal als abstrake Klasse:
Wir haben laenger diskutiert, ob Animal eine abstrakte Klasse oder ein Interface sein soll.
Da Animal einen Zustand zu speichern hat (z.B. erwarteteLebenszeit), muss es eindeutig eine abstrakte Klasse sein.
(siehe z.B. S. 91 im Skriptum: "Interfaces in Java sind eine besondere Form abstrakter Klassen, die keine
Objektvariablen beschreiben duerfen, in der Fachliteratur haeufig Trait genannt.").
 */

public abstract class Animal {

    /**
     * Zu erwartende Lebenszeit in Stunden.
     */
    private int erwarteteLebenszeit;

    /* Anmerkung zur Umsetzung air(), water() und ground() als abstrakte Methoden:
Wir haben laenger diskutiert, ob diese Methoden in Animal abstrakt oder konkret sein sollen.

Fuer eine Umsetzung als konkrete Methoden spricht:
Nur wenige Tiere liefern fuer mehr als 1 bzw. 2 Methoden einen Wert ungleich 0. Daher spart man sich viel redundanten
Code in den erbenden Klassen, wenn die Methoden in Animal konkret implementiert werden und standardmaessig 0 zurueckgeben.

Fuer eine Umsetzung als abstrakte Methoden spricht:
Nur so kann sichergestellt werden, dass Entwickler*innen beim Erstellen neuer Klassen, die von Animal erben,
nicht vergessen air(), water() und ground() zu implementieren.

Wir haben in unserer Diskussion beschlossen, dass es uns wichtiger ist, die Implementierung zu forcieren, als weniger
redundanten Code zu schreiben.
     */

    /**
     * Der Wert bestimmt ob ein Tier sich in der Luftaufhaelt oder lebt
     * NB: 0 wenn sich das Tier nicht in diesem Gebiet aufhaelt, > 0 falls ja
     *
     * @return int
     */
    public abstract int air();

    /**
     * Der Wert bestimmt ob ein Tier sich im Wasser aufhaelt oder lebt
     * NB: 0 wenn sich das Tier nicht in diesem Gebiet aufhaelt, > 0 falls ja
     *
     * @return int
     */
    public abstract int water();

    /**
     * Der Wert bestimmt ob ein Tier sich am Boden aufhaelt oder lebt
     * NB: 0 wenn sich das Tier nicht in diesem Gebiet aufhaelt, > 0 falls ja
     *
     * @return int
     */
    public abstract int ground();

    /**
     * Getter fuer die erwartete Lebenszeit
     *
     * @return int erwarteteLebenszeit
     */
    protected int getErwarteteLebenszeit() {
        return erwarteteLebenszeit;
    }

    /**
     * VB: erwarteteLebenszeit > 0
     *
     * @param erwarteteLebenszeit int, Erwartete Lebenszeit des Tieres
     */
    protected void setErwarteteLebenszeit(int erwarteteLebenszeit) {
        if (erwarteteLebenszeit < 0) {
            throw new IllegalArgumentException("Erwartete Lebenszeit darf nicht kleiner 0 sein!");
        }
        this.erwarteteLebenszeit = erwarteteLebenszeit;
    }
}
