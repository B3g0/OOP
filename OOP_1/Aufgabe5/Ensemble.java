import java.util.Iterator;

class Ensemble<T> implements Iterable<T> {

    //Erstelle eine Liste mit HIlfe der Klasse MyList
    private MyList<T> list;
    Ensemble(){
        list = new MyList<T>();
    }
    /*
    Hilfsmethode add
    Vorbedingung: toAdd != null
    Nachbedingung: Falls nicht schon vorhanden, wird toAdd in die Liste eingefügt
     */
    boolean add(T toAdd){
        if(!list.contains(toAdd)){
            list.add(toAdd);
            return true;
        }
        return false;
    }

    public Iterator<T> iterator(){
        return list.iterator();
    }


}
