package org.vinst.server.mongo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountUpdateKey;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
* @author Lars Velsky
* @since 28/09/14
*/
@Service
public class AccountUpdateKeyWriteConverter implements Converter<AccountUpdateKey, BigInteger> {

    @Override
    public BigInteger convert(AccountUpdateKey source) {
        return new BigInteger(
                ByteBuffer.allocate(16).
                        putLong(source.getAccountKey().getId()).
                        putLong(source.getVersion()).
                        array());
    }
}
