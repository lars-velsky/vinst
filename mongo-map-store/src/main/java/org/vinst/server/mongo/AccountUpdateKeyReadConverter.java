package org.vinst.server.mongo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
* @author Lars Velsky
* @since 28/09/14
*/
@Service
public class AccountUpdateKeyReadConverter implements Converter<BigInteger, AccountUpdateKey> {

    @Override
    public AccountUpdateKey convert(BigInteger source) {
        ByteBuffer buffer = ByteBuffer.wrap(source.toByteArray());
        long accountId = buffer.getLong();
        long version = buffer.getLong();
        return AccountUpdateKey.of(AccountKey.of(accountId), version);
    }
}
