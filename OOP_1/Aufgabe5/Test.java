import com.sun.org.apache.xpath.internal.operations.Neg;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyList<Composition> compList = new MyList<>();

        /*
        Test Aufrufe unseres Tests
         */
        System.out.println("###Tests 1###");
        System.out.println();
        ensembleTest();
        System.out.println("########################");
        intNegTest();
        System.out.println("########################");
        compList.add(powerTest());
        System.out.println("########################");
        compList.add(waterTest());
        System.out.println("########################");


        System.out.println();
        System.out.println("###Test 2###");
        System.out.println();
        /*
        Erzeuge neue Composition
         */
        Composition<Supply, Connection<Supply>> supplyComp = new Composition<>();
        for(Composition cmp : compList){
            addSupplyOrConnToComp(supplyComp, cmp.connectionIter());
            addSupplyOrConnToComp(supplyComp, cmp.iterator());
        }
        printComp(supplyComp);

        System.out.println();
        System.out.println("###Test 3###");
        System.out.println();
        Ensemble<Supply> supplyEnsemble = supplyComp;
        printEach(supplyEnsemble);

    }

    private static void ensembleTest(){
        System.out.println("Ensemble Test");
        //Erzeuge neues Ensemble
        Ensemble<String> ensemble = new Ensemble<>();

        System.out.println("Adding Strings");
        ensemble.add("String 1");
        ensemble.add("String 2");
        ensemble.add("String 2");

        printEach(ensemble);

        System.out.println("Removing last String");
        Iterator<String> it = ensemble.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println("Adding new String");
        ensemble.add("New String");
        printEach(ensemble);
    }

    private static Composition intNegTest(){
        System.out.println("Composition Integer Negator Test");
        Composition<Integer, Negator> compNegInt = new Composition<>();
        Negator n1 = new Negator(1);
        Negator n2 = new Negator(2);
        compNegInt.connect(n1);
        compNegInt.connect(n2);
        compNegInt.connect(n2);

        printComp(compNegInt);

        System.out.println("Removing some, adding some");
        Iterator negIt = compNegInt.connectionIter();
        negIt.next();
        negIt.remove();
        compNegInt.connect(n1);
        printComp(compNegInt);
        return compNegInt;
    }

    private static Composition powerTest(){
        System.out.println("Composition Power Test");
        //Erzeuge neue Composition aus PowerSupply und Cable
        Composition<PowerSupply, Cable> powerComp = new Composition<>();
        PowerSupply p1 = new PowerSupply();
        PowerSupply p2 = new PowerSupply();
        PowerSupply p3 = new PowerSupply();
        Cable c1 = new Cable(p1, p2, 5);
        Cable c2 = new Cable(p2, p3, 9);
        powerComp.connect(c1);
        powerComp.connect(c2);

        printComp(powerComp);
        Iterator<Cable> powerIt = powerComp.connectionIter();
        powerIt.next();
        powerIt.remove();
        powerComp.connect(c1);
        printComp(powerComp);

        powerIt = powerComp.connectionIter();

        int sum = 0;
        while (powerIt.hasNext()){
            sum += powerIt.next().strands();
        }
        System.out.println("Sum of Strands: "+sum);

        return powerComp;
    }

    private static Composition waterTest(){
        System.out.println("Composition Water Test");
        //Erzeuge neue Composition aus WaterSupply und Hose
        Composition<WaterSupply, Hose> waterComp = new Composition<>();
        WaterSupply w1 = new WaterSupply();
        WaterSupply w2 = new WaterSupply();
        WaterSupply w3 = new WaterSupply();
        Hose h1 = new Hose(w1, w2, 5);
        Hose h2 = new Hose(w2, w3, 0.2);
        waterComp.connect(h1);
        waterComp.connect(h2);
        waterComp.connect(h2);

        printComp(waterComp);
        System.out.println("Removing some, adding some");
        Iterator<Hose> waterIt = waterComp.connectionIter();
        waterIt.next();
        waterIt.remove();
        waterComp.connect(h1);
        printComp(waterComp);

        waterIt = waterComp.connectionIter();
        double sum = 0;
        while(waterIt.hasNext()){
            sum+=waterIt.next().diameter();
        }
        System.out.println("Sum of Diameters: "+sum);

        return waterComp;
    }

    private static void printEach(Iterable it){
        System.out.println("---");
        for(Object s : it){
            System.out.println(s);
        }
        System.out.println("---");
    }

    private static void printEach(Iterator it){
        System.out.println("---");
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("---");
    }

    private static void printComp(Composition comp){
        System.out.println("Values in Composition:");
        printEach(comp);
        System.out.println("");
        System.out.println("Connections in Composition:");
        Iterator it = comp.connectionIter();
        printEach(it);
    }


    /*
    addSupplyOrConnToComp-Methode entscheidet ob die Supply oder die Connection zur Composition hinzugefügt werden
    Vorbedingung:   Composition und Iterator sollten != null sein
    Nachbedingung:  Wenn current instance of Supply ist wird current zur Composition hinzugefügt, andernfalls wenn es eine instance of
                    Connection ist und deren source instance of Supply ist wird current zur Composition angeschlossen
     */
    private static void addSupplyOrConnToComp(Composition<Supply, Connection<Supply>> comp, Iterator it){
        while(it.hasNext()){
            Object current = it.next();
            if(current instanceof Supply){
                comp.add((Supply) current);
            } else if(current instanceof Connection){
                Connection currConnection = (Connection) current;
                if(currConnection.source() instanceof Supply )
                    comp.connect((Connection<Supply>) currConnection);
            }
        }
    }

}