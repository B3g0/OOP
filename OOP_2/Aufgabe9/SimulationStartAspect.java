
import java.util.Arrays;

public aspect SimulationStartAspect {

    pointcut callSimulation() : call (* Test.simulation());

    // Ausgabe der Personen, die mittels Creator annotation eine Methode/Konstruktor/Klasse erzeugt haben
    before() : callSimulation() {

        // man muss die klassen registrieren weil
        // https://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
        Class<?>[] classes = new Class[]{Person.class, Population.class, WishList.class, WishMap.class, Organization.class, Util.class, Test.class};
        Arrays.asList(classes).forEach(clazz -> {
            Creator creators = clazz.getAnnotation(Creator.class);
            if(creators != null) {
                System.out.println(String.format("[class=%s, creators=%s]", clazz.getName(), creators));
            }
            Arrays.asList(clazz.getConstructors()).forEach(constructor -> {
                Creator c = constructor.getAnnotation(Creator.class);
                if(c != null) {
                    System.out.println(String.format("[class=%s, constructor=%s, creators=%s]", clazz.getName(), constructor.getName(), c));
                }
            });
            Arrays.asList(clazz.getMethods()).forEach(method -> {
                Creator c = method.getAnnotation(Creator.class);
                if(c != null) {
                    System.out.println(String.format("[class=%s, method=%s, creators=%s]", clazz.getName(), method.getName(), c));
                }
            });
        });
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }

}
