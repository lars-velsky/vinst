package org.vinst.instrument;

/**
 *
 *
 * @author lars-velsky
 * @since 12/08/14
 */
public interface Instrument {

    InstrumentKey getInstrumentKey();

    String getSymbol();
}
