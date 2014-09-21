package org.vinst.event;

/**
 * @author lars-velsky
 * @since 12/08/14
 */
public interface Event {

    void visit(Visitor visitor);

    interface Visitor {

        void visitAccountCreation(AccountCreationEvent event);
    }

}
