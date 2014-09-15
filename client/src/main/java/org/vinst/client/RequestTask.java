package org.vinst.client;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.vinst.common.Constants;
import org.vinst.core.requests.CoreRequest;
import org.vinst.core.requests.CoreResponse;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * @author lars-velsky
 * @since 14/09/14
 */
public class RequestTask<REQ extends CoreRequest<RESP>, RESP extends CoreResponse> implements Callable<RESP>, IdentifiedDataSerializable {

    private final REQ request;

    RequestTask(REQ request) {
        this.request = request;
    }

    @Override
    public RESP call() throws Exception {
        throw new UnsupportedOperationException();
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
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeObject(request);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        throw new UnsupportedOperationException();
    }
}
