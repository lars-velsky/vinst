package org.vinst.core.cluster;

import org.springframework.jdbc.core.JdbcTemplate;
import org.vinst.core.AccountCountRequest;
import org.vinst.core.AccountCountResponse;

import javax.sql.DataSource;

/**
 * @author Sergey Mischenko
 * @since 27.04.2015
 */
public class AccountCountRequestProcessor implements RequestProcessor<AccountCountRequest, AccountCountResponse> {

    private final JdbcTemplate jdbcTemplate;

    public AccountCountRequestProcessor(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Class<AccountCountRequest> getRequestClass() {
        return AccountCountRequest.class;
    }

    @Override
    public AccountCountResponse process(AccountCountRequest request) {
        Integer accountCount = jdbcTemplate.queryForObject("SELECT count(*) FROM accounts", Integer.class);
        return new AccountCountResponse(accountCount);
    }
}
