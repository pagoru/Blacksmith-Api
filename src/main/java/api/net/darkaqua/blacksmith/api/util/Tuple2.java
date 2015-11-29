package net.darkaqua.blacksmith.api.util;

/**
 * Created by cout970 on 22/11/2015.
 */
public class Tuple2<T1, T2> {

    private T1 fist;
    private T2 last;

    public Tuple2(T1 a, T2 b){

        fist = a;
        last = b;
    }

    public T1 getFist() {
        return fist;
    }

    public void setFist(T1 fist) {
        this.fist = fist;
    }

    public T2 getLast() {
        return last;
    }

    public void setLast(T2 last) {
        this.last = last;
    }
}
