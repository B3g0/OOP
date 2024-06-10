package MyStack;

/**
 * Ein Pair Objekt, das als Rueckgabewert fungieren kann, wenn 2 Werte gleichzeitig uebergeben werden sollen. Wird bei
 * uns in MyStack verwendet, um bei einem Poll von einem Node den neuen Head und den Wert gleichzeitig zu erhalten
 * @param <T> erster Teil des Pairs (bei uns immer ein Node)
 * @param <V> zweiter Teil des Pairs (bei uns immer ein Rohr)
 */
public class Pair<T, V> {
    private T t;
    private V v;

    Pair(T t, V v){
        this.t = t;
        this.v = v;
    }

    public T getFirst() {
        return t;
    }

    public V getSecond() {
        return v;
    }
}
