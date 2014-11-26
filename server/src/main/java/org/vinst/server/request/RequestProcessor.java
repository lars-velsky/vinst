package org.vinst.server.request;

import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

/**
 * @author Lars Velsky
 * @since 19/11/14
 */
public interface RequestProcessor<REQ extends CoreRequest<RESP>, RESP extends CoreResponse>  {

    Class<REQ> getRequestClass();

    RESP processRequest(REQ request);

}
