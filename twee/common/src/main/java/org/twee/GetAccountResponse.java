package org.twee;

import org.vinst.core.requests.CoreResponse;

import java.io.Serializable;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public final class GetAccountResponse implements CoreResponse, Serializable {

    private final TweeAccount tweeAccount;

    public GetAccountResponse(TweeAccount tweeAccount) {
        if (tweeAccount == null){
            throw new NullPointerException("Account should not be null");
        }
        this.tweeAccount = tweeAccount;
    }

    public TweeAccount getAccount(){
        return tweeAccount;
    }
}
