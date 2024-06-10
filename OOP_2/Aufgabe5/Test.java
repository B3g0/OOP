import oopb3.SocialGroup;
import oopb3.abstr.SteppeHerdAnimal;
import oopb3.animal.Ostrich;
import oopb3.animal.Sex;
import oopb3.animal.Starling;
import oopb3.animal.Zebra;

import java.util.Iterator;
import java.util.function.Predicate;

public class Test {

    /**
     * Aufgabenverteilung:
     * Tobias: SocialGroup, FitAnimal, SteppeHerdAnimal
     * Mensur: Ostrich, Sex, Starling, Zebra
     * Christoph: SocialGroup, Test, Node
     */

    public static void main(String[] args) {
        System.out.println("################### ZEBRA #########################");
        SocialGroup<Zebra> zebras = new SocialGroup<Zebra>();
        zebras.add(new Zebra(100, 1.0f, Sex.female));
        zebras.add(new Zebra(99, 0.3f, Sex.male));
        zebras.add(new Zebra(50, 0.5f, Sex.female));
        zebras.add(new Zebra(50, 0.7f, Sex.female));
        zebras.add(new Zebra(50, 0.5f, Sex.female));
        zebras.add(new Zebra(60, 0.6f, Sex.female));
        zebras.add(new Zebra(61, 0.5f, Sex.female));
        zebras.add(new Zebra(50, 0.5f, Sex.female));
        System.out.println("###################### PRINT #########################");
        System.out.print(zebras);
        System.out.println("######################################################\n\n");
        zebras.compareAll();
        System.out.println("################## COMPARE ALL #####################");
        System.out.print(zebras);
        System.out.println("######################################################\n\n");

        System.out.println("################## ALPHA TEST #####################");
        Iterator<Zebra> alphaIterator = zebras.alpha();
        while(alphaIterator.hasNext()) {
            System.out.println(alphaIterator.next());
        }
        System.out.println("######################################################\n\n");

        System.out.println("################## HIERARCHY TEST #####################");
        System.out.println(zebras.hierarchical());
        System.out.println("######################################################\n\n");

        System.out.println("\n##################### OSTRICH ########################");
        SocialGroup<Ostrich> ostriches = new SocialGroup<Ostrich>();
        ostriches.add(new Ostrich(100, 20));
        ostriches.add(new Ostrich(90, 30));
        ostriches.add(new Ostrich(80, 40));
        System.out.println("######################## PRINT ##########################");
        System.out.print(ostriches);
        System.out.println("######################################################\n\n");
        ostriches.compareAll();
        System.out.println("################## COMPARE ALL #####################");
        System.out.print(ostriches);
        System.out.println("######################################################\n\n");

        System.out.println("################## ALPHA TEST #####################");
        Iterator<Ostrich> alphaIt = ostriches.alpha();
        while(alphaIt.hasNext()) {
            System.out.println(alphaIt.next());
        }
        System.out.println("######################################################\n\n");

        System.out.println("################## HIERARCHY TEST #####################");
        System.out.println(ostriches.hierarchical());
        System.out.println("######################################################\n\n");

        System.out.println("################## STARLING #####################");

        SocialGroup<Starling> starling = new SocialGroup<Starling>();
        starling.add(new Starling(100, false));
        starling.add(new Starling(140, true));
        starling.add(new Starling(130, false));
        System.out.println("###################### PRINT ########################");
        System.out.print(starling);
        System.out.println("######################################################\n\n");
        System.out.println("################## COMPARE ALL #####################");
        starling.compareAll();
        System.out.print(starling);
        System.out.println("######################################################\n\n");

        System.out.println("################## ALPHA TEST #####################");
        Iterator<Starling> sterlIt = starling.alpha();
        while(sterlIt.hasNext()) {
            System.out.println(sterlIt.next());
        }
        System.out.println("######################################################\n\n");

        System.out.println("################## HIERARCHY TEST #####################");
        System.out.println(starling.hierarchical());
        System.out.println("######################################################\n\n");



        System.out.println("################## STEPPE HERD #####################");
        SocialGroup<SteppeHerdAnimal> steppeHerd = new SocialGroup<SteppeHerdAnimal>();
        steppeHerd.move(zebras, new Predicate<Zebra>() {
            @Override
            public boolean test(Zebra zebra) {
                return zebra.protection() > 0.59f;
            }
        });
        steppeHerd.move(ostriches, new Predicate<Ostrich>() {
            @Override
            public boolean test(Ostrich o) {
                return o.power() > 25;
            }
        });
        System.out.println("###################### PRINT ########################");
        System.out.print(steppeHerd);
        System.out.println("######################################################\n\n");
        starling.compareAll();
        System.out.println("################## COMPARE ALL #####################");
        System.out.print(steppeHerd);
        System.out.println("######################################################\n\n");

        System.out.println("################## ALPHA TEST #####################");
        Iterator<SteppeHerdAnimal> steppe = steppeHerd.alpha();
        while(steppe.hasNext()) {
            System.out.println(steppe.next());
        }
        System.out.println("######################################################\n\n");

        System.out.println("################## HIERARCHY TEST #####################");
        System.out.println(steppeHerd.hierarchical());
        System.out.println("######################################################\n\n");
    }

}
