package org.vinst.server.request;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.account.AccountKey;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.Constants;
import org.vinst.common.account.AccountCreationEventImpl;
import org.vinst.common.account.AccountUpdateImpl;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;
import org.vinst.core.requests.CreateAccountRequest;
import org.vinst.core.requests.CreateAccountResponse;

import java.util.Collections;
import java.util.Random;

/**
 * @author lars-velsky
 * @since 15/09/14
 */
@Service
public class RequestsProcessor {

    private static final Random rnd = new Random();

    @Autowired
    private HazelcastInstance hzInstance;

    public CoreResponse process(CoreRequest request){
        if (request instanceof CreateAccountRequest){
            return createAccount();
        }
        throw new UnsupportedOperationException(request.getClass().getSimpleName() + " requests aren't supported");
    }

    private CreateAccountResponse createAccount() {
        final long id = rnd.nextLong();
        AccountKey accountKey = AccountKey.of(id);
        AccountCreationEventImpl accountCreationEvent = new AccountCreationEventImpl(accountKey);
        AccountUpdateKey accountUpdateKey = AccountUpdateKey.of(accountKey, 0);
        AccountUpdateImpl accountUpdate = new AccountUpdateImpl(accountUpdateKey, Collections.singletonList(accountCreationEvent));
        IMap<AccountUpdateKey, AccountUpdateImpl> map = hzInstance.getMap(Constants.ACCOUNT_UPDATES);
        map.put(accountUpdateKey, accountUpdate);
        System.out.println("Account " + id + " created");
        return new CreateAccountResponse(accountKey);
    }
}
