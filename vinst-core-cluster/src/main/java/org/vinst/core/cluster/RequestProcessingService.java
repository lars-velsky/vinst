package org.vinst.core.cluster;

import org.vinst.core.Request;
import org.vinst.core.Response;

/**
 * @author Sergey Mischenko
 * @since 25.04.2015
 */
public interface RequestProcessingService {
    Response process(Request request);
}
