import java.util.Iterator;

/*
Laut Angabe soll auch von Ensemble geerbt werden. Dies wird hier bewusst ignoriert, da meiner Meinung nach die
Connections im Ensemble gespeichert werden sollen, um danach dessen iterator() Funktion verwenden zu können. Dies ist
allerdings nicht möglich, weil eine Connection mehrfach vorhanden sein soll, was ein Ensemble ausschließt.
Deshalb habe ich mich zu einem Mittelweg entschieden, indem ich die Sources und Sinks in Ensembles speichere, die
Composition aber nicht von Ensemble ableite.
*/
class Composition<T, V extends Connection<T>> extends Ensemble<T>{
    private MyList<V> connections = new MyList<>();
    /*
    connect-Methode die eine connection zwischen
    Vorbedingung: toAdd != null
    Nachbedingung: toAdd wird in die connections hinzugefügt und dann durch die if-Statements die jeweilige Source und/oder Sink geaddet
     */
    void connect(V toAdd){
        connections.add(toAdd);
        if(toAdd.source() != null){
            add(toAdd.source());
        }
        if(toAdd.sink() != null){
            add(toAdd.sink());
        }
    }

    Iterator<V> connectionIter() {
        return this.connections.iterator();
    }
}

