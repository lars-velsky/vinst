package org.vinst.server.request;

import org.springframework.stereotype.Service;
import org.vinst.account.AccountKey;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;
import org.vinst.core.requests.CreateAccountRequest;
import org.vinst.core.requests.CreateAccountResponse;

import java.util.Random;

/**
 * @author lars-velsky
 * @since 15/09/14
 */
@Service
public class RequestsProcessor {

    private static final Random rnd = new Random();

    public CoreResponse process(CoreRequest request){
        if (request instanceof CreateAccountRequest){
            long id = rnd.nextLong();
            System.out.println("Account " + id + " created");
            return new CreateAccountResponse(AccountKey.of(id));
        }
        return null;
    }
}
