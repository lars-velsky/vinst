package org.vinst.client.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.client.request.RequestProcessor;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lars Velsky
 * @since 22/09/14
 */
@Service
public class RequestProcessorsRegistry {

    @Autowired
    private Collection<RequestProcessor<?, ?>> processors;

    private Map<Class<? extends CoreRequest<?>>, RequestProcessor<?, ?>> processorsMap;

    @PostConstruct
    public void init(){
        HashMap<Class<? extends CoreRequest<?>>, RequestProcessor<?, ?>> map = new HashMap<>();
        for (RequestProcessor<?, ?> processor : processors) {
            map.put(processor.getRequestClass(), processor);
        }
        processorsMap = Collections.unmodifiableMap(map);
    }

    public <REQ extends CoreRequest<RESP>, RESP extends CoreResponse> RequestProcessor<REQ, RESP> getRequestProcessor(REQ req){
        RequestProcessor<?, ?> requestProcessor = processorsMap.get(req);
        return (RequestProcessor<REQ, RESP>) requestProcessor;
    }

}
