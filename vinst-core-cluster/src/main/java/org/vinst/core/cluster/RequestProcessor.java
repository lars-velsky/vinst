package org.vinst.core.cluster;


import org.vinst.core.Request;
import org.vinst.core.Response;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public interface RequestProcessor<REQ extends Request<RESP>, RESP extends Response> {

    Class<REQ> getRequestClass();

    RESP process(REQ request);
}
