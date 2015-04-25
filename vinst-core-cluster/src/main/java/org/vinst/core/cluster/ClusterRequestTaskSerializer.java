package org.vinst.core.cluster;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.vinst.core.Request;
import org.vinst.core.RequestTaskSerializer;

import java.io.IOException;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public class ClusterRequestTaskSerializer implements StreamSerializer<ClusterRequestTask>, ApplicationContextAware {

    private volatile ApplicationContext context;

    @Override
    public void write(ObjectDataOutput out, ClusterRequestTask requestTask) throws IOException {
        out.writeObject(requestTask.getRequest());
    }

    @Override
    public ClusterRequestTask read(ObjectDataInput in) throws IOException {
        Request<?> request = in.readObject();
        RequestService requestService = context.getBean(RequestService.class);
        return new ClusterRequestTask<>(requestService, request);
    }

    @Override
    public int getTypeId() {
        return RequestTaskSerializer.TYPE_ID;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
