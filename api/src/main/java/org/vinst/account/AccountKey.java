package org.vinst.account;

import java.io.Serializable;

/**
 * <p>An account key</p>
 *
 * todo
 *
 * @author lars-velsky
 * @since 31/07/14
 *
 * @see Account
 */
public final class AccountKey implements Serializable {

    public static AccountKey of(long id){
        return new AccountKey(id);
    }

    private final long id;

    private AccountKey(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AccountKey{" +
                "id=" + id +
                '}';
    }
}
