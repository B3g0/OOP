import Anlagen.*;
import Rest.Lager;
import Rohre.*;

public class Test {

    public static void main(String[] args) {

        //Generieren des Lagers

        Lager lager = new Lager();

        //Generieren der Rohre

        Rohr rohrNormKlein = new RohrNormKlein(150, 3750);
        Rohr rohrNormGross = new RohrNormGross(150,7500);

        Rohr rohrSauerKlein = new RohrSauerKlein(150, 11250);
        Rohr rohrSauerGross = new RohrSauerGross(150, 15000);

        Rohr rohrNormKleinLeer = new RohrNormKlein(200, 0);
        Rohr rohrNormGrossLeer = new RohrNormGross(0, 0);

        //Einlagern der Rohre ins Lager

        lager.rohrEinlagern(rohrNormKlein);
        lager.rohrEinlagern(rohrNormGross);
        lager.rohrEinlagern(rohrSauerKlein);
        lager.rohrEinlagern(rohrSauerGross);
        lager.rohrEinlagern(rohrNormGrossLeer);
        lager.rohrEinlagern(rohrNormKleinLeer);

        //Printen des Lagers

        printLager(lager);

        //Generieren der Anlagen

        Anlage kaltHeizung = new KaltHeizung();
        Anlage warmHeizung = new WarmHeizung();
        Anlage kaltIndustrie = new KaltIndustrie();
        Anlage warmIndustrie = new WarmIndustrie();

        System.out.println("#############################");
        System.out.println("Montiere Rohre....");

        //Montieren der Rohre mit anschließendem Ausdruck der Anlage und des Lagers

        lager.rohrMontieren(kaltHeizung);
        printAnlage(kaltHeizung);
        printLager(lager);

        lager.rohrMontieren(warmHeizung);
        printAnlage(warmHeizung);
        printLager(lager);

        lager.rohrMontieren(warmIndustrie);
        printAnlage(warmIndustrie);
        printLager(lager);

        lager.rohrMontieren(kaltIndustrie);
        printAnlage(kaltIndustrie);
        printLager(lager);

        lager.rohrMontieren(kaltHeizung);
        printAnlage(kaltHeizung);
        printLager(lager);

        //Montage eines grossen Rohres in eine kaltHeizung, da kein kleines mehr vorhanden ist

        lager.rohrMontieren(kaltHeizung);
        printAnlage(kaltHeizung);
        printLager(lager);

        //Montage eines Rohres, das nicht vohrhanden ist

        lager.rohrMontieren(warmHeizung);
        printAnlage(warmHeizung);
        printLager(lager);

        System.out.println("############################");
        System.out.println("Demontiere Rohre....");
        System.out.println();

        //Demontieren der Rohre mit anschließendem Ausdruck der Anlage

        System.out.println("Vor Demontage:");
        printAnlage(warmHeizung);
        warmHeizung.demontiere(lager);

        System.out.println("Nach Demontage:");
        printAnlage(warmHeizung);
        warmHeizung.demontiere(lager);

        System.out.println("Nach erneuter Demontage:");
        printAnlage(warmHeizung);

        System.out.println("Vor Demontage:");
        printAnlage(warmIndustrie);
        warmIndustrie.demontiere(lager);

        //Demontieren eines Rohres aus einer leeren Anlage

        System.out.println("Nach Demontage:");
        printAnlage(warmIndustrie);

        System.out.println("Nach erneuter Demontage:");
        warmIndustrie.demontiere(lager);
        printAnlage(warmIndustrie);

        //Abschließendes printen des Lagers

        printLager(lager);

    }

    /**
     * Diese Methode printet eine Anlage - den Namen, die Liste aller Rohre und den Wert aller Rohre in der Anlage - in der Konsole aus
     * @param a ist die Anlage, die geprintet werden soll
     */
    private static void printAnlage(Anlage a){
        System.out.println(a.getClass().getSimpleName()+ ": ");
        a.anlageliste();
        a.anlagewert();
        System.out.println();
    }

    /**
     * Diese Methode printet das Lager - die Liste aller Rohre und den Wert aller Rohre im Lager - in der Konsole aus
     * @param l ist das Lager, das geprintet werden soll
     */
    private static void printLager(Lager l){
        l.lagerliste();
        l.lagerwert();
        System.out.println();
    }


}
