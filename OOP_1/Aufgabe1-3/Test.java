 import abstr.Abstract_Quelle;
 import abstr.Abstract_Verbraucher;
 import main.*;
/*
Wer hat was gemacht:

Wir haben alle gemeinsam in der Gruppe uns grunsaetzliche Vorgehensweisen ueberlegt und dann auf einem PC gemeinsam
den Code geschrieben

Aufgabe 2:
Siehe Email

Aufgabe 3:
Wir haben uns gemeinsam getroffen und haben die Kommentare auf einem PC verfasst


SCHLECHT: Die Manager koennen zwar als normale Abstract_quelle/Verbraucher angesprochen werden, allerdings bleiben dann
die Manager spezifischen Funktionen aussen vor - Deshalb ist auch managerRun noetig
 */

public class Test {
    public static void main(String[] args) {

        printTitle("Wasser Test");
        Speicher wasserSpeicher = new Speicher(100);
        Abstract_Quelle regen = new Regenwasser(30);
        Abstract_Verbraucher planties = new Giessanlage(wasserSpeicher);
        run(planties,regen,wasserSpeicher);

        printTitle("Strom Test");
        Speicher batterie = new Speicher(1000);
        Abstract_Quelle lichtStrom = new Photovoltaikanlage(200);
        Abstract_Verbraucher pumpe = new Waermepumpe(batterie);
        run(pumpe,lichtStrom, batterie);

        printTitle("Manager Test");
        Speicher speicher = new Speicher(1000000);
        Quellenmanager sources = new Quellenmanager();   //Hier kein Abstract, um die Manager Qualitaeten mit managerRun sehen koennen!
        Verbrauchermanager verbraucher = new Verbrauchermanager();
        for(int i = 0; i < 205; i++){
            sources.getSources().add(new Regenwasser(util.random(10,500)));
            verbraucher.getList().add(new Giessanlage(speicher));
        }
        managerRun(verbraucher,sources, speicher);  //Nimmt nur Manager entgegen!


    }

    private static void printTitle(String msg){
        System.out.println("========");
        System.out.println(msg);
        System.out.println("========");
    }

    private static void run(Abstract_Verbraucher verbraucher, Abstract_Quelle quelle, Speicher speicher){
        for(int i = 0; i < 30; i++){
            runStep(verbraucher, quelle, speicher, i);
        }
    }

    private static void runStep(Abstract_Verbraucher verbraucher, Abstract_Quelle quelle, Speicher speicher, int hotd){
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Fill Storage ...");
        try {
            speicher.fill(quelle, hotd);
        } catch (SpeicherException e){
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println("Storage Fuellung: " + speicher);
        System.out.println("Emptiing Storage ...");
        try {
            verbraucher.use(hotd);
        } catch (SpeicherException e){
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println("After Hour: "+hotd+", Storage Used Space: " + speicher + " from " + speicher.getMax());

    }

    private static void managerRun(Verbrauchermanager verbraucher, Quellenmanager quelle, Speicher speicher){
        for(int i = 0; i < 30; i++){
            runStep(verbraucher, quelle, speicher, i);
            if(verbraucher.technicalProblemSimulation()){
                System.out.println("Technisches Gebrechen! Nur noch "+verbraucher.getList().size() + " Verbraucher");
            }
            if(quelle.technicalProblemSimulation()){
                System.out.println("Technisches Gebrechen! Nur noch "+verbraucher.getList().size() + " Quellen");
            }
        }
    }
}
