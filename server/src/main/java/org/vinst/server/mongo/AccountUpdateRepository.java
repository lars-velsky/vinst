package org.vinst.server.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.vinst.account.AccountUpdateKey;
import org.vinst.common.account.AccountUpdateImpl;

/**
 * @author Lars Velsky
 * @since 25/09/14
 */
public interface AccountUpdateRepository extends MongoRepository<AccountUpdateImpl, AccountUpdateKey> {
}
