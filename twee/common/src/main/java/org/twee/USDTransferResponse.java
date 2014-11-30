package org.twee;

import org.vinst.core.requests.CoreResponse;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 15/11/14
 */
public final class USDTransferResponse implements CoreResponse, Serializable {

    private final double resultingBalance;

    public USDTransferResponse(double resultingBalance) {
        this.resultingBalance = resultingBalance;
    }

    public double getResultingBalance(){
        return resultingBalance;
    }
}
