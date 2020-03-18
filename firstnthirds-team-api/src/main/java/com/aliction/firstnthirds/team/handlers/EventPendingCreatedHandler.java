package com.aliction.firstnthirds.team.handlers;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aliction.firstnthirds.team.events.EventTeamValidationThread;
import com.aliction.firstnthirds.team.events.EventPendingCreated;
import com.aliction.firstnthirds.team.events.EventTeamValidation;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
@ActivateRequestContext
public class EventPendingCreatedHandler{

    public static Logger LOGGER = Logger.getLogger(EventPendingCreatedHandler.class.getName());

    @Inject
    EntityManager entityManager;

    @Transactional
    @Incoming("event")
    @Outgoing("eventValidation")
    @Broadcast
    public EventTeamValidation checkParentTeam(EventPendingCreated eventPendingCreated) throws InterruptedException, ExecutionException{
            LOGGER.info("Event CREATED ID : " + eventPendingCreated.getEventPendingId());
            LOGGER.info("Event CREATED Team ID : " + eventPendingCreated.getTeamId());
            ManagedExecutor executor = ManagedExecutor.builder()
            .maxAsync(5)
            .propagated(ThreadContext.CDI, 
                        ThreadContext.TRANSACTION).build();
            EventTeamValidationThread eventTeamValidationThread = new EventTeamValidationThread(entityManager, eventPendingCreated);
            // executor.runAsync(ctt).join();
        // return ctt.sendReply();
        return executor.submit(eventTeamValidationThread).get();
    }
}