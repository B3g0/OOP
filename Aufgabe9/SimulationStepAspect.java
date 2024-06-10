import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public aspect SimulationStepAspect {

    pointcut callSimulationJahr() : target(Test) && (call (void simuliereJahr(List<Organization>, Population)));

    before() : callSimulationJahr() {
        Object[] args = thisJoinPoint.getArgs();
        List<Organization> organizations = (List<Organization>)args[0];
        Population population = (Population)args[1];

        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Simulating a new year. Before Christmas Statistics: ");
        System.out.println(String.format("Population size: %d", population.getPeople().size()));
        System.out.println(String.format("Average amount of Wishes per Person: %4.2f", population.getPeople().stream().mapToInt(p -> p.wishesCount()).sum()/(double)population.getPeople().size()));
        WishList list = population.getWishList();
        organizations.forEach(o -> {
            int wieOftKommenOrgWuenscheInWishListVor = list.values().stream().mapToInt(value -> value.stream().filter(integer -> o.getWuensche().keySet().contains(integer)).collect(Collectors.toCollection(ArrayList::new)).size()).sum();
            System.out.println(String.format("A wish of %s is on average %4.2f times on the Christmas-List", o,(double)wieOftKommenOrgWuenscheInWishListVor/list.size()));
        });
    }
}
