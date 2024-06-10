package Anlagen;

import Rest.Lager;
import Rohre.Rohr;
/**
 * Klasse KaltIndustrie verwendet Methode aus Anlage zum Montieren der Rohre
 * VB: Lager != null
 * NB: Ein Rohr der Art SauerKlein wird aus dem Lager entfernt und in KaltIndustrie verbaut
 */
public class KaltIndustrie extends Anlage {
    @Override
    public Rohr montiereRohr(Lager lager) {
        Rohr r = lager.getSauerKlein().poll();
        getInstalledRohre().add(r);
        return r;
    }
}
