package Anlagen;

import Rest.Lager;
import Rohre.Rohr;

/**
 * Klasse WarmHeizung verwendet Methode aus Anlage zum Montieren der Rohre
 * VB: Lager != null
 * NB: Ein Rohr der Art NormGross wird aus dem Lager entfernt und in WarmHeizung verbaut
 */
public class WarmHeizung extends Anlage {
    @Override
    public Rohr montiereRohr(Lager lager) {
        Rohr r = lager.getNormGross().poll();
        getInstalledRohre().add(r);
        return r;
    }
}
