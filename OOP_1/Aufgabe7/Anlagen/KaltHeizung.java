package Anlagen;

import Rest.Lager;
import Rohre.Rohr;

/**
 * Klasse KaltHeizung verwendet Methode aus Anlage zum Montieren der Rohre
 * VB: Lager != null
 * NB: Ein Rohr der Art NormKlein wird aus dem Lager entfernt und in KaltHeizung verbaut
 */
public class KaltHeizung extends Anlage {
    @Override
    public Rohr montiereRohr(Lager lager) {
        Rohr r = lager.getNormKlein().poll();
        getInstalledRohre().add(r);
        return r;
    }
}
