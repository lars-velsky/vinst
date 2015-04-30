package org.vinst.core.cluster;

import org.vinst.core.Response;

import java.io.Serializable;

/**
 * @author Sergey Mischenko
 * @since 30.04.2015
 */
public class RequestProcessingResult<RESP extends Response> implements Serializable {

    private final RESP response;

    private RequestProcessingResult(RESP response) {
        this.response = response;
    }

    public static <RESP extends Response> RequestProcessingResult response(RESP response) {
        return new RequestProcessingResult<>(response);
    }

    public static RequestProcessingResult error() {
        return new RequestProcessingResult<>(null);
    }

    public boolean hasResponse() {
        return response != null;
    }

    public RESP getResponse() {
        if (response != null) {
            return response;
        } else {
            throw new IllegalStateException("RequestProcessingResult has no response");
        }
    }
}
