package org.vinst.core;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class ClientRequestTaskSerializer implements StreamSerializer<ClientRequestTask> {
    @Override
    public void write(ObjectDataOutput out, ClientRequestTask requestTask) throws IOException {
        out.writeObject(requestTask.getRequest());
    }

    @Override
    public ClientRequestTask read(ObjectDataInput in) throws IOException {
        throw new AssertionError("Method call() of ClientRequestTaskSerializer must never be invoked");
    }

    @Override
    public int getTypeId() {
        return RequestTaskSerializer.TYPE_ID;
    }

    @Override
    public void destroy() {

    }
}
