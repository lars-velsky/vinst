package org.vinst.server.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    private static Logger log = LoggerFactory.getLogger(RequestProcessorsRegistry.class);

    @Autowired
    private Collection<RequestProcessor<?, ?>> processors;

    private Map<Class<? extends CoreRequest<?>>, RequestProcessor<?, ?>> processorsMap;

    @PostConstruct
    public void init(){
        log.debug("Initializing request processors");
        HashMap<Class<? extends CoreRequest<?>>, RequestProcessor<?, ?>> map = new HashMap<>();

        for (RequestProcessor<?, ?> processor : processors) {
            Class<? extends CoreRequest<?>> clazz = processor.getRequestClass();
            RequestProcessor<?, ?> previousProcessor = map.put(clazz, processor);
            if (previousProcessor != null){
                throw new IllegalStateException("Found two processors for the same class " +
                        clazz.getCanonicalName() + ": " + previousProcessor.getClass().getCanonicalName() + " and " +
                        processor.getClass().getCanonicalName()
                );
            } else {
                log.debug("{} -> {}", clazz.getCanonicalName(), processor.getClass().getCanonicalName());
            }
        }

        processorsMap = Collections.unmodifiableMap(map);
    }

    public <REQ extends CoreRequest<RESP>, RESP extends CoreResponse> RequestProcessor<REQ, RESP> getRequestProcessor(REQ req){
        Class<? extends CoreRequest> aClass = req.getClass();
        RequestProcessor<?, ?> requestProcessor = processorsMap.get(aClass);
        if (requestProcessor == null) {
            throw new IllegalArgumentException("No processor for " + req.getClass().getCanonicalName());
        } else {
            log.debug("Processing {} with {}", aClass.getCanonicalName(), requestProcessor.getClass().getCanonicalName());
        }
        return (RequestProcessor<REQ, RESP>) requestProcessor;
    }

}
