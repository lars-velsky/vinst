package org.twee;

import org.vinst.instrument.Instrument;
import org.vinst.instrument.InstrumentKey;

/**
 * @author Lars Velsky
 * @since 15/11/14
 */
public final class USD implements Instrument {

    public static final InstrumentKey KEY = InstrumentKey.of(0);

    @Override
    public InstrumentKey getInstrumentKey() {
        return KEY;
    }

    @Override
    public String getSymbol() {
        return "USD$";
    }
}
