package com.aliction.firstnthirds.event.handlers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aliction.firstnthirds.event.events.EventApproved;
import com.aliction.firstnthirds.event.events.EventTeamValidation;
import com.aliction.firstnthirds.event.events.EventTeamValidationStatus;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
@ActivateRequestContext
public class TeamValidatedHandler{

    private static Logger LOGGER = Logger.getLogger(TeamValidatedHandler.class.getName());
    
    @Inject
    EntityManager entityManager;

    public TeamValidatedHandler(){}

    @Transactional
    @Incoming("eventValidation")
    @Outgoing("eventApproved")
    @Broadcast
    public EventApproved ApproveEvent(EventTeamValidation eventTeamValidation) throws InterruptedException, ExecutionException{
        ManagedExecutor executor = ManagedExecutor.builder()
            .maxAsync(5)
            .propagated(ThreadContext.CDI, 
                        ThreadContext.TRANSACTION).build();
        if(eventTeamValidation == null){
            LOGGER.info("Validation Object is NULL");
        }else{
            LOGGER.info("Validation eventPending Id is " + eventTeamValidation.getEventPendingId());
        }
        LOGGER.info("Validation String : " + eventTeamValidation.getValidationStatus());
        if(eventTeamValidation.getValidationStatus().equals(EventTeamValidationStatus.EVENT_TEAM_FOUND)){
            LOGGER.info("Event with id : " + eventTeamValidation.getEventPendingId() + " is validated with team id " + eventTeamValidation.getTeamId());
            ApproveEventThread approveEventThread = new ApproveEventThread(entityManager, eventTeamValidation);
            return executor.submit(approveEventThread).get();
        }else{
            LOGGER.info("Event with id : " + eventTeamValidation.getEventPendingId() + " could not validate with team id " + eventTeamValidation.getTeamId());
            // executor.runAsync(new RejectEventThread(entityManager, eventTeamValidated));
            RejectEventThread rejectEventThread = new RejectEventThread(entityManager, eventTeamValidation);
            return executor.submit(rejectEventThread).get();
        }
    };

	// @Incoming("team")
    // public CompletableFuture<Void> RejectEvent(EventTeamValidated eventTeamValidated){

    //     ManagedExecutor executor = ManagedExecutor.builder()
    //         .maxAsync(5)
    //         .propagated(ThreadContext.CDI, 
    //                     ThreadContext.TRANSACTION).build();
    //     return executor.runAsync(new RejectEventThread(entityManager, eventTeamValidated));
    // };
}