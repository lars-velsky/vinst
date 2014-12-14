package org.vinst.account;

import java.io.Serializable;

/**
 * <p>An account key</p>
 *
 * todo
 *
 * @author Lars Velsky
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
        return "AccountKey #" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountKey that = (AccountKey) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
