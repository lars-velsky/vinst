package org.vinst.account;

import java.io.Serializable;

/**
 * @author lars-velsky
 * @since 21/09/14
 */
public final class AccountUpdateKey implements Serializable {

    public static AccountUpdateKey of(AccountKey accountKey, long version){
        if (accountKey == null){
            throw new IllegalArgumentException("Account key cannot be null");
        }
        if (version < 0){
            throw new IllegalArgumentException("Account version must be non-negative");
        }

        return new AccountUpdateKey(accountKey, version);
    }

    private final AccountKey accountKey;
    private final long version;

    private AccountUpdateKey(AccountKey accountKey, long version) {
        this.accountKey = accountKey;
        this.version = version;
    }

    public AccountKey getAccountKey() {
        return accountKey;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountUpdateKey that = (AccountUpdateKey) o;

        if (version != that.version) return false;
        if (!accountKey.equals(that.accountKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountKey.hashCode();
        result = 31 * result + (int) (version ^ (version >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AccountUpdateKey{" +
                "accountKey=" + accountKey +
                ", version=" + version +
                '}';
    }
}
