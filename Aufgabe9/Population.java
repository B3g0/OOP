
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {

    private final List<Integer> wunschArten;

    private List<Person> people;

    /**
     * Erzeuge eine neue Population
     * @param wunschArten Die Wunsch-Arten die in der Population existieren sollen.
     */
    public Population(List<Integer> wunschArten, int populationsGroesse) {
        this.wunschArten = wunschArten;
        // Verwende Collectors.toCollection statt Collectors.toList(), denn nur dann sind "type, mutability, serializability or thread-safety"
        // (siehe JavaDoc von Collectors.toList()) der erzeugten Liste eindeutig festgelegt!
        this.people = Stream.generate(()->new Person(wunschArten)).limit(populationsGroesse).collect(Collectors.toCollection(ArrayList::new));
    }

    public WishList getWishList() {
        WishList peopleAndTheirHighestWishes = new WishList();

        people.forEach(person -> {
            List<Integer> highestWishes = person.getHighestWishes(5);

            peopleAndTheirHighestWishes.put(person, highestWishes);
        });

        return peopleAndTheirHighestWishes;
    }

    /**
     * Generiert jedes Jahr neue Wünsche für Population
     */
    @Creator(who = GroupMember.Christoph)
    public void removeAndAddNew() {
        List<Person> survivors = people.stream().filter(p -> Util.randomBool()).collect(Collectors.toCollection(ArrayList::new));
        // add a new person for every survivor to the list
        this.people = Stream.generate(()->new Person(wunschArten)).limit(survivors.size()).collect(Collectors.toCollection(ArrayList::new));
        this.people.addAll(survivors);
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
    }
}
