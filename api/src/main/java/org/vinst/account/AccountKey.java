package org.vinst.account;

/**
 * An account key
 *
 * @author ochtarfear
 * @since 31/07/14
 */
public final class AccountKey {

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
