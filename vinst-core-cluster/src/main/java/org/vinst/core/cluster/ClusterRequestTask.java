package org.vinst.core.cluster;

import org.vinst.core.Request;
import org.vinst.core.Response;

import java.util.concurrent.Callable;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public final class ClusterRequestTask<RESP extends Response> implements Callable<RESP> {
    private final RequestService requestService;
    private final Request<RESP> request;

    public ClusterRequestTask(RequestService requestService, Request<RESP> request) {
        this.requestService = requestService;
        this.request = request;
    }

    public Request<RESP> getRequest() {
        return request;
    }

    @Override
    public RESP call() throws Exception {
        try {
            return requestService.process(request);
        } catch (Exception e) {
            // Нельзя просто пробросывать оригинальный эксепшен.
            // Клиентский хазелкаст не сможет его десериализовать, просто залогирует это и не вызовет клиентский колбэк.
            // TODO Не бросать RuntimeException, а отправлять на клиент специальный признак.
            e.printStackTrace();
            throw new RuntimeException("INTERNAL CLUSTER ERROR");
        }
    }
}
