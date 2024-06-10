
import oopb3.animal.*;
import oopb3.animal.abstr.SchoolAnimal;
import oopb3.animal.abstr.SwarmAnimal;
import oopb3.animal.interfaces.Bird;
import oopb3.animal.interfaces.Fish;
import oopb3.animal.interfaces.Mammal;
import java.util.ArrayList;
import java.util.List;

public class Test {

    /**
     * Arbeitsaufteilung:
     *
     * Tobias: FlightlessBird, HerdAnimal, PackAnimal, Mammal, SocialAnimal, MigratoryLocust, MigratoryBird
     * Mensur: Animal, SchoolAnimal, SwarmAnimal, Bird, Fish, Insect, Lachs
     * Christoph: Delphin, Emu, Loewe, Nilpferd, Star, Test
     *
     */

    /**
     * Testfaelle, die in den einzelnen Methoden beschrieben werden.
     * @param args
     */
    public static void main(String[] args) {
        lionsAndHippos();
        fishAndSwarmAnimal();
        flightlessOrMigrationBird();
        allSchoolAndSwarmAnimalsAreMammals();
        lionIsHippoAlpha();
        migratoryBirdIsSwarmAnimal();
        herdAnimalIsMammalOrFlightlessBird();
        dolphinInSchoolWithLocust();
        flightlessBirdIsNotSocial();
        birdInsectMammalFishNotNecessarilyAnimals();
    }

    /**
     * Loewen und Nilpferde sind Mammals und daher leicht ersetzbar bzw. als Gruppierung von Mammals verwendbar.
     */
    private static void lionsAndHippos() {
        List<Mammal> mammals = new ArrayList<>();
        mammals.add(new Loewe(24*365*50));
        mammals.add(new Nilpferd());
    }

    /**
     * FlightlessBird und MigratoryBird sind Birds und damit austauschbar/ersetzbar
     */
    private static void flightlessOrMigrationBird() {
        Bird bird = new Emu();
        bird = new Star();
    }

    /**
     * In einer früheren Version hatte SwarmAnimal von SchoolAnimal geerbt.
     * Das hat dazu geführt, dass SwarmAnimal (und somit z.B. Lachs oder Locust) auch von Mammal geerbt hatte.
     * Lachs ist aber eindeutig kein Mammal.
     * Durch diesen Test haben wir den Fehler erkannt und die Vererbungshierarchie korrigiert.
     */
    private static void allSchoolAndSwarmAnimalsAreMammals() {
        Lachs lachs = new Lachs();
        MigratoryLocust locust = new MigratoryLocust();
        Delphin delphin = new Delphin();
        List<Mammal> saeugetiere = new ArrayList<>();
        // Lachs und MigratoryLocust sind richtigerweise keine Mammals, deshalb ist das nicht möglich.
        //saeugetiere.add(lachs);
        //saeugetiere.add(locust);
        saeugetiere.add(delphin);
    }

    /**
     * Trotz Herde ohne Alphatier hat das Nilpferd einen Loewen als Alpha Tier in der Herde.
     */
    private static void lionIsHippoAlpha() {
        Nilpferd hippo = new Nilpferd();
        Loewe lion = new Loewe(24*365*30);
        hippo.setAlpha(lion);
    }

    /**
     * "Herdentiere sind Säugetiere oder flugunfähige Vögel."
     */
    private static void herdAnimalIsMammalOrFlightlessBird() {
        // Es sind durch unsere Loesung keine FlightlessBirds, die HerdAnimals sind, moeglich.
        // Es ist nicht moeglich da FlightlessBird und HerdAnimal abstrakte Klassen sind,
        // und ein flugunfaehiger Vogel nicht von HerdAnimal und FlightlessBird ableiten kann (keine Mehrfachvererbung in Java)
        // Da FlightlessBird die Lebenszeit nur am Boden verbringt, ist es jedoch noetig die Methode air 0 retournieren zu lassen.
        // Deshalb ist FlightlessBird kein Interface.
        // Ein Testfall waere  nicht kompilierbarer Code, daher wurde der Testfall nur als Kommentar beschrieben.
    }

    /**
     * Delphine dürfen nicht in einer Schule mit Locust oder Lachs sein.
     * Dies wird durch unsere Vererbungshierarchie sichergestellt.
     * In einer früheren Version war das noch möglich, weil SwarmAnimal von SchoolAnimal geerbt hatte.
     * Durch diesen Test haben wir diesen Fehler erkannt und korrigiert.
     */
    private static void dolphinInSchoolWithLocust() {
        MigratoryLocust locust = new MigratoryLocust();
        List<SchoolAnimal> school = new ArrayList<>();
        //school.add(locust);
        new Delphin(school);
    }

    /**
     * Lachse sind Fische und SwarmAnimals
     */
    private static void fishAndSwarmAnimal() {
        Lachs lachs = new Lachs();
        Fish fish = lachs;
        SwarmAnimal swarmAnimal = lachs;
    }

    /**
     * "MigratoryBirds legt Strecken in einem Schwarm zurueck."
     */
    private static void migratoryBirdIsSwarmAnimal() {
        // Ein MigratoryBird als abstrakte Klasse kann kein SwarmAnimal sein, aufgrund der nicht moeglichen Mehrfachvererbung.
        // Deshalb ist MigratoryBird ein Interface und somit kann ein Wandervogel sowohl ein Untertyp von MigratoryBird(/Bird) als auch SwarmAnimal sein
        Star star = new Star();
        star.swarm();
        Bird starBird = star;

        // Emu als FlightlessBird ist in keinem Schwarm moeglich
        Emu emu = new Emu();
        //emu.swarm(); - nicht moeglich
    }

    /**
     * Flightless Birds sind nicht sozial und koennen keine Herdentiere sein.
     */
    private static void flightlessBirdIsNotSocial() {
        // FlightlessBird kann nicht von SocialAnimal bzw. HerdAnimal ableiten und gleichzeitig ein FlightlessBird sein. Beispielsweise sind Emus dadurch nicht sozial.
        Emu emu = new Emu();
        // emu.social();
        // emu.isInSocialGroup();
        // beides nicht moeglich
        // und koennen als FlightlessBird auch nicht in einer Herde sein
        // emu.setAlpha(...)
    }

    /**
     * Interfaces Bird, Fish, Insect, Mammal oder MigratoryBird sind keine Untertypen von Animal.
     */
    private static void birdInsectMammalFishNotNecessarilyAnimals() {
        // Eine Klasse die Bird implementiert ist erst ein Untertyp von Animal, wenn direkt von Animal oder Untertypen wie SocialAnimal abgeleitet wird.
        // Da Animal (und Untertypen) Zustaende besitzen und daher zumindest eine abstrakte Klasse sein muss,
        // sind Bird, Fish und co. Interfaces, da sonst keine Birds als SocialAnimals moeglich waeren (fehlende Mehrfachvererbung)
        Bird emu = new Emu();
        //emu.ground();
        //emu.air();
        //emu.water();
        //Animal a = emu;
        //nicht moeglich, da Bird kein Animal ist!
    }
}
