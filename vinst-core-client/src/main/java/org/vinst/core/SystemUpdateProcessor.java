package org.vinst.core;

import java.util.Optional;

/**
 * @author Sergey Mischenko
 */
public interface SystemUpdateProcessor<U> {

    Optional<U> process(SystemUpdate update);
}
