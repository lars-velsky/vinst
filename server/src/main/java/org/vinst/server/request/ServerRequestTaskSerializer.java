package org.vinst.server.request;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinst.common.Constants;

import java.io.IOException;

/**
 * @author lars-velsky
 * @since 15/09/14
 */
@Service
public class ServerRequestTaskSerializer implements StreamSerializer<ServerRequestTask> {

    @Autowired
    private RequestsProcessor requestsProcessor;

    @Override
    public void write(ObjectDataOutput out, ServerRequestTask object) throws IOException {
        out.writeObject(object.request);
    }

    @Override
    public ServerRequestTask read(ObjectDataInput in) throws IOException {
        return new ServerRequestTask(requestsProcessor, in.readObject());
    }

    @Override
    public int getTypeId() {
        return Constants.REQUEST_TASK_TYPE;
    }

    @Override
    public void destroy() {

    }
}
