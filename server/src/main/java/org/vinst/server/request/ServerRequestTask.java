package org.vinst.server.request;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.vinst.common.Constants;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * @author lars-velsky
 * @since 15/09/14
 */
public class ServerRequestTask implements Callable<CoreResponse>, IdentifiedDataSerializable {

    CoreRequest request;
    private RequestsProcessor requestsProcessor;

    public ServerRequestTask(RequestsProcessor requestsProcessor) {
        this.requestsProcessor = requestsProcessor;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        request = in.readObject();
    }

    @Override
    public int getFactoryId() {
        return Constants.REQUEST_TASK_FACTORY;
    }

    @Override
    public int getId() {
        return Constants.REQUEST_TASK_TYPE;
    }

    @Override
    public CoreResponse call() throws Exception {
        return requestsProcessor.process(request);
    }
}
