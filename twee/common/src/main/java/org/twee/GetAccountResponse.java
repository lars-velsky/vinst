package org.twee;

import org.vinst.core.requests.CoreResponse;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public final class GetAccountResponse implements CoreResponse, Serializable {

    private final TweeAccount tweeAccount;

    public GetAccountResponse(TweeAccount tweeAccount) {
        this.tweeAccount = tweeAccount;
    }

    public Optional<TweeAccount> getAccount(){
        return Optional.ofNullable(tweeAccount);
    }
}
