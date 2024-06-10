package oopb3;

import oopb3.abstr.FitAnimal;
import oopb3.list.Node;

import java.util.Iterator;
import java.util.function.Predicate;

public class SocialGroup<T extends FitAnimal> implements Iterable {


    /**
     * SocialGroup enthält Tiere eines Sozialverbands.
     *
     * Die Tiere sind in einer verketteten Liste gespeichert.
     * Invariante: Die Liste ist immer absteigend nach Fitness der Tiere sortiert.
     */
    private Node<T> firstAnimalInList;
    private Node<T> lastAnimalInList;
    private Node<T> next;
    private Node<T> last;

    /**
     * Liefert einen Iterator über Typ {@code T}.
     *
     * Invariante: Der Liste ist immer absteigend nach Fitness der Tiere sortiert.
     * @return einen Iterator<T>.
     */
    @Override
    public Iterator<T> iterator() {
        return createIteratorWithPredicate(new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return true; // gebe alle Tiere zurück
            }
        });
    }

    /**
     * Liefert einen Iterator über Typ {@code T}, die ein Predicate erfüllen.
     * @param predicate Das zu erfüllende Predicate
     * @return Alle Tiere, die predicate erfüllen.
     * Invariante: Der Liste ist immer absteigend nach Fitness der Tiere sortiert.
     */
    private Iterator<T> createIteratorWithPredicate(Predicate<T> predicate) {
        next = firstAnimalInList;

        Iterator<T> it = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if(next != null) {
                    while(!predicate.test(next.getValue())) {
                        next = next.getNachfolger();
                        if(next == null) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if(next == null)
                    return null;

                Node<T> animal = next;
                next = next.getNachfolger();
                if(next == null) {
                    last = animal;
                }
                return animal.getValue();
            }

            @Override
            public void remove() {
                Node<T> animal;
                if(next == null) {
                    if(last != null) {
                        animal = last;
                    } else {
                        return;
                    }
                }else {
                    animal = next.getVorgaenger();
                }

                if(animal != null) {
                    Node<T> vor = animal.getVorgaenger();
                    Node<T> nach = animal.getNachfolger();

                    if (vor != null) {
                        vor.setNachfolger(nach);
                    }
                    if (nach != null) {
                        nach.setVorgaenger(vor);
                    }
                }
            }
        };
        return it;
    }

    /**
     * Überprüft ob alle Tiere der SocialGroup sich in einem hierarchischen Sozialverband einem Untertier unterordnen
     * können.
     * @return Ob alle Tiere sich in einem in einem hierarchischen Sozialverband einem Untertier unterordnen
     * können.
     */
    public boolean hierarchical() {
        Iterator<T> it = iterator();
        while(it.hasNext()) {
            T animal = it.next();
            if(!animal.hierarchical()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gibt einen Iterator zurück, der jene Tiere liefert, für die mayBeAlpha() true ist.
     * @return Einen Iterator, der jene Tiere liefert, für die mayBeAlpha() true ist.
     * Invariante: Die Liste ist immer absteigend nach Fitness der Tiere sortiert.
     */
    public Iterator<T> alpha() {
        return createIteratorWithPredicate(new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return t.mayBeAlpha();
            }
        });
    }

    /**
     * Verschiebt Tiere von group in diese SocialGroup, wenn sie predicate erfüllen.
     * @param group Die SocialGroup aus der verschoben werden soll.
     * @param predicate Das zu erfüllende predicate
     * @param <E> Der Typ der alten und neuen SocialGroup.
     * Invariante: Die Liste ist immer absteigend nach Fitness der Tiere sortiert.
     */
    public <E extends T> void move(SocialGroup<E> group, Predicate<E> predicate) {
        Iterator<E> it = group.iterator();
        while(it.hasNext()) {
            E animal = it.next();
            if (predicate.test(animal)) {
                it.remove();
                this.add(animal);
            }
        }
    }

    /**
     * Fügt ein Tier in die SocialGroup hinzu
     * @param animal Das hinzuzufügende Tier
     * Invariante: Die Liste ist immer absteigend nach Fitness der Tiere sortiert.
     * Nachbedingung: Das Tier ist in der SocialGroup enthalten.
     */
    public void add(T animal) {
        if(firstAnimalInList == null) {
            firstAnimalInList = new Node<>();
            firstAnimalInList.setValue(animal);
            lastAnimalInList = firstAnimalInList;
        } else {
            Node<T> currentAnimal = firstAnimalInList;
            Node<T> newAnimal = new Node<>();
            newAnimal.setValue(animal);

            if(lastAnimalInList.getValue().getFitness() > animal.getFitness()) {
                lastAnimalInList.setNachfolger(newAnimal);
                newAnimal.setVorgaenger(lastAnimalInList);
                lastAnimalInList = newAnimal;
            }else {
                while (currentAnimal != null) {
                    if (currentAnimal.getValue().getFitness() <= animal.getFitness()) {
                        newAnimal.setNachfolger(currentAnimal);
                        newAnimal.setVorgaenger(currentAnimal.getVorgaenger());
                        if (currentAnimal.getVorgaenger() != null) {
                            currentAnimal.getVorgaenger().setNachfolger(newAnimal);
                        } else {
                            firstAnimalInList = newAnimal;
                        }
                        currentAnimal.setVorgaenger(newAnimal);
                        break;
                    }
                    currentAnimal = currentAnimal.getNachfolger();
                }
            }
        }
    }

    /**
     * Entfernt ein Tier aus der SocialGroup
     * Invariante: Die Liste ist immer absteigend nach Fitness der Tiere sortiert.
     * @param animal Das zu entfernende Tier.
     * Nachbedingung: Das Tier ist nicht mehr in der SocialGroup enthalten.
     */
    public void remove(T animal) {
        if(animal != null)
        {
            Iterator<T> it = iterator();
            while(it.hasNext())
            {
                if(it.next() == animal)
                {
                    it.remove();
                }
            }
        }
    }

    /**
     * Lässt zwei Tiere aus der SocialGroup gegeneinander kämpen.
     * Invariante: Die Liste ist immer absteigend nach Fitness der Tiere sortiert.
     * Nachbedinung: Falls vor der Ausführung zwei Tiere mit gleicher Fitness enthalten waren,
     * haben diese nun andere und verschiedene Fitnesswerte
     */
    public void compareAll()
    {
        /*
         * Die Liste ist absteigend nach fitness sortiert, das vereinfacht compareAll():
         * 1. Finde 2 Tiere mit derselben Fitness
         * 2. Lasse sie gegeneinander kämpfen
         * 3. Entfenre sie und füge sie neu hinzu, damit die Sortierung wieder korrekt ist
         */
        Iterator<T> it = iterator();
        while(it.hasNext())
        {
            T firstCompetitor = it.next();
            if(it.hasNext())
            {
                T secondCompetitor = it.next();
                boolean fitnessEqual = firstCompetitor.fitter(secondCompetitor) == 0;

                if(fitnessEqual)
                {
                    boolean firstCompetitorWins = Math.random() > 0.5;
                    T winner = firstCompetitorWins ? firstCompetitor : secondCompetitor;
                    T loser = firstCompetitorWins ? secondCompetitor : firstCompetitor;

                    winner.changeFitness(10);
                    loser.changeFitness(-10);
                    remove(winner);
                    remove(loser);
                    add(winner);
                    add(loser);
                    break;
                }
            }
        }
    }

    /**
     * Erzeugt eine String-Abbildung der SocialGroup
     * @return Eine String-Abbildung der SocialGroup
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<T> it = iterator();

        String newLine = "\n";
        int i = 0;
        while(it.hasNext())
        {
            stringBuilder.append(i++);
            stringBuilder.append(": ");
            stringBuilder.append(it.next().toString());
            stringBuilder.append(newLine);
        }

        return stringBuilder.toString();
    }
}
