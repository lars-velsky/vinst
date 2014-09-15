package org.vinst.server.request;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.vinst.common.Constants;

/**
 * @author lars-velsky
 * @since 15/09/14
 */
public class ServerRequestTaskFactory implements DataSerializableFactory {


    private RequestsProcessor requestsProcessor;

    public ServerRequestTaskFactory(RequestsProcessor requestsProcessor) {
        this.requestsProcessor = requestsProcessor;
    }

    @Override
    public IdentifiedDataSerializable create(int typeId) {
        if (typeId == Constants.REQUEST_TASK_TYPE){
            return new ServerRequestTask(requestsProcessor);
        } else {
            throw new IllegalArgumentException("Wrong type id for the ServerRequestFactory: " + typeId);
        }
    }
}
