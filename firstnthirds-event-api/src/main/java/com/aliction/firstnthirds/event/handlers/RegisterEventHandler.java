package com.aliction.firstnthirds.event.handlers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aliction.firstnthirds.event.entities.Event;
import com.aliction.firstnthirds.event.events.EventRegisterationAction;
import com.aliction.firstnthirds.event.events.EventRegisterationApproved;
import com.aliction.firstnthirds.event.events.EventRegisterationRejected;
import com.aliction.firstnthirds.event.events.EventRegisterationRequested;
import com.aliction.firstnthirds.event.events.EventRegisterationValidationStatus;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

public class RegisterEventHandler {

    public Logger LOGGER = Logger.getLogger(RegisterEventHandler.class.getName());

    @Inject EntityManager entityManager;

    @Transactional
    @Incoming("eventRegisterationRequests")
    @Outgoing("eventRegisterationValidation")
    public EventRegisterationAction eventRegisterationRequestHandler(EventRegisterationRequested eventRegisterationRequested) throws InterruptedException, ExecutionException{
        ManagedExecutor executor = ManagedExecutor.builder()
            .maxAsync(5)
            .propagated(ThreadContext.CDI, 
                        ThreadContext.TRANSACTION).build();
        return executor.submit(new Callable<EventRegisterationAction>() {

			@Override
			public EventRegisterationAction call() throws Exception {
                EventRegisterationAction eventRegisterationAction = null;
                LOGGER.info("Handling event registeration request");
                Event event = entityManager.find(Event.class, eventRegisterationRequested.getEventId());
                LOGGER.info("Event found");
                LOGGER.info("Event status id is : " + event.getStatus().getId());
                LOGGER.info("Event Name is : " + event.getName());
                // TODO: Implement Event Status ENUM instead of Strings
                // TODO: Change switch from status id to status enum value
                switch(event.getStatus().getId().intValue()){
                    case 1:
                    eventRegisterationAction = new EventRegisterationRejected(eventRegisterationRequested.getUserId(), event, eventRegisterationRequested.getUserEventRoleId(), eventRegisterationRequested.getUserEventStatusId());
                    eventRegisterationAction.setEventRegisterationValidationStatus(EventRegisterationValidationStatus.EVENT_NOT_READY_FOR_REGISTERATION);
                    LOGGER.info("Event registeration rejected with " + EventRegisterationValidationStatus.EVENT_NOT_READY_FOR_REGISTERATION);
                    break ;
                case 2:
                    eventRegisterationAction = new EventRegisterationApproved(eventRegisterationRequested.getUserId(), event, eventRegisterationRequested.getUserEventRoleId(), eventRegisterationRequested.getUserEventStatusId());
                    LOGGER.info("Event registeration accepted");
                    break;
                case 3:
                    eventRegisterationAction = new EventRegisterationRejected(eventRegisterationRequested.getUserId(), event, eventRegisterationRequested.getUserEventRoleId(), eventRegisterationRequested.getUserEventStatusId());
                    eventRegisterationAction.setEventRegisterationValidationStatus(EventRegisterationValidationStatus.EVENT_CANCELLED);
                    LOGGER.info("Event registeration rejected with " + EventRegisterationValidationStatus.EVENT_CANCELLED );
                    break;
                case 4:
                    eventRegisterationAction = new EventRegisterationRejected(eventRegisterationRequested.getUserId(), event, eventRegisterationRequested.getUserEventRoleId(), eventRegisterationRequested.getUserEventStatusId());
                    eventRegisterationAction.setEventRegisterationValidationStatus(EventRegisterationValidationStatus.EVENT_RGISTERATION_EXPIRED);
                    LOGGER.info("Event registeration rejected with " + EventRegisterationValidationStatus.EVENT_RGISTERATION_EXPIRED);
                    break;
                }
				return eventRegisterationAction;
			}
            
        }).get();
        
    }
}