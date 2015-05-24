package org.vinst.core;

/**
 * @author Sergey Mischenko
 */
public interface SubscriptionManager<InitialResponse extends Response, RecoveryResponse extends Response, SubscriptionUpdate> {

    Request<InitialResponse> getInitialRequest();

    void initialResponseReceived(InitialResponse response);

    Request<RecoveryResponse> getRecoveryRequest();

    void recoveryResponseReceived(RecoveryResponse response);

    SystemUpdateProcessor<SubscriptionUpdate> getSystemUpdateProcessor();

    void subscriptionUpdateReceived(SubscriptionUpdate update);
}
