package org.vinst.core.cluster;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.vinst.core.Request;
import org.vinst.core.RequestTaskSerializer;

import java.io.IOException;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class ClusterRequestTaskSerializer implements StreamSerializer<ClusterRequestTask> {

    private final RequestService requestService;

    public ClusterRequestTaskSerializer(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void write(ObjectDataOutput out, ClusterRequestTask requestTask) throws IOException {
        out.writeObject(requestTask.getRequest());
    }

    @Override
    public ClusterRequestTask read(ObjectDataInput in) throws IOException {
        Request<?> request = in.readObject();
        return new ClusterRequestTask(requestService, request);
    }

    @Override
    public int getTypeId() {
        return RequestTaskSerializer.TYPE_ID;
    }

    @Override
    public void destroy() {

    }
}
