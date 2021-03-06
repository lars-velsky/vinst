package org.vinst.client.request;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.springframework.stereotype.Service;
import org.vinst.common.Constants;

import java.io.IOException;

/**
 * @author Lars Velsky
 * @since 16/09/14
 */
@Service
public final class RequestTaskSerializer implements StreamSerializer<RequestTask> {


    @Override
    public void write(ObjectDataOutput out, RequestTask object) throws IOException {
        out.writeObject(object.request);
    }

    @Override
    public RequestTask read(ObjectDataInput in) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTypeId() {
        return Constants.REQUEST_TASK_TYPE;
    }

    @Override
    public void destroy() {

    }
}
