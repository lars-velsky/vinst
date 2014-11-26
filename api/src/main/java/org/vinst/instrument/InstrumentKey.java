package org.vinst.instrument;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 03/08/14
 */
public final class InstrumentKey implements Serializable {

    private final long id;

    public static InstrumentKey of(long id){
        return new InstrumentKey(id);
    }

    private InstrumentKey(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "InstrumentKey{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstrumentKey that = (InstrumentKey) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
