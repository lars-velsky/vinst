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
public final class ClusterRequestProcessingTaskSerializer implements StreamSerializer<ClusterRequestProcessingTask> {

    private final RequestProcessingService requestProcessingService;

    public ClusterRequestProcessingTaskSerializer(RequestProcessingService requestProcessingService) {
        this.requestProcessingService = requestProcessingService;
    }

    @Override
    public void write(ObjectDataOutput out, ClusterRequestProcessingTask requestTask) throws IOException {
        out.writeObject(requestTask.getRequest());
    }

    @Override
    public ClusterRequestProcessingTask read(ObjectDataInput in) throws IOException {
        Request request = in.readObject();
        return new ClusterRequestProcessingTask(requestProcessingService, request);
    }

    @Override
    public int getTypeId() {
        return RequestTaskSerializer.TYPE_ID;
    }

    @Override
    public void destroy() {

    }
}
