package org.vinst.position;

import org.vinst.instrument.InstrumentKey;

import java.io.Serializable;

/**
 * <p>A position key</p>
 *
 * todo
 *
 * @author Lars Velsky
 * @since 03/08/14
 *
 * @see Position
 */
public final class PositionKey implements Serializable {

    private final String code;
    private final InstrumentKey instrumentKey;

    public static PositionKey of(InstrumentKey instrumentKey, String code){
        return new PositionKey(instrumentKey, code);
    }

    public static PositionKey of(InstrumentKey instrumentKey){
        return new PositionKey(instrumentKey, null);
    }

    private PositionKey(InstrumentKey instrumentKey, String code) {
        if (instrumentKey == null){
            throw new IllegalArgumentException("Instrument key must not be null");
        }

        this.instrumentKey = instrumentKey;
        this.code = code;
    }

    public InstrumentKey getInstrumentKey(){
        return instrumentKey;
    }

    public String getCode(){
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionKey that = (PositionKey) o;

        return !(code != null ? !code.equals(that.code) : that.code != null) &&
                instrumentKey.equals(that.instrumentKey);

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + instrumentKey.hashCode();
        return result;
    }
}
