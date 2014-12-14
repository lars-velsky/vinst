package org.twee;

import org.vinst.core.requests.CoreResponse;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Lars Velsky
 * @since 15/11/14
 */
public final class USDTransferResponse implements CoreResponse, Serializable {

    private final double resultingBalance;
    private final Exception e;

    public USDTransferResponse(Exception e) {
        this.e = e;
        resultingBalance = Double.NaN;
    }

    public USDTransferResponse(double resultingBalance) {
        this.resultingBalance = resultingBalance;
        e = null;
    }

    public double getResultingBalance(){
        return resultingBalance;
    }

    public Optional<Exception> getException(){
        return Optional.ofNullable(e);
    }
}
