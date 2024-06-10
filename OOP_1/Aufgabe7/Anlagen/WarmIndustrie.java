package Anlagen;

import Rest.Lager;
import Rohre.Rohr;

/**
 * Klasse WarmIndustrie verwendet Methode aus Anlage zum Montieren der Rohre
 * VB: Lager != null
 * NB: Ein Rohr der Art SauerGross wird aus dem Lager entfernt und in WarmIndustrie verbaut
 */
public class WarmIndustrie extends Anlage{
    @Override
    public Rohr montiereRohr(Lager lager) {
        Rohr r = lager.getSauerGross().poll();
        getInstalledRohre().add(r);
        return r;
    }
}
