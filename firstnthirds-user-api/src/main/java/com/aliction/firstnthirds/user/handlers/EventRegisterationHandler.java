package com.aliction.firstnthirds.user.handlers;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aliction.firstnthirds.user.entities.User;
import com.aliction.firstnthirds.user.entities.UserEvent;
import com.aliction.firstnthirds.user.entities.UserEventRole;
import com.aliction.firstnthirds.user.entities.UserEventStatus;
import com.aliction.firstnthirds.user.events.EventRegisterationAction;
import com.aliction.firstnthirds.user.events.EventRegisterationValidationStatus;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

public class EventRegisterationHandler{

    public Logger LOGGER = Logger.getLogger(EventRegisterationHandler.class.getName());

    @Inject EntityManager entityManager;

    @Transactional
    @Incoming("eventRegisterationValidation")
    public void eventRegisterationValidationHandler(EventRegisterationAction eventRegisterationAction){
        LOGGER.info("Event Registeration Action is " + eventRegisterationAction.getEventRegisterationValidationStatus());

        ManagedExecutor executor = ManagedExecutor.builder()
        .maxAsync(5)
        .propagated(ThreadContext.CDI, 
                    ThreadContext.TRANSACTION).build();

        executor.runAsync(new Runnable() {

			@Override
			public void run() {
                LOGGER.info("Handling event validation");
				if(eventRegisterationAction.getEventRegisterationValidationStatus().equals(EventRegisterationValidationStatus.EVENT_ACCEPTS_REGISTERATION)){
                    User user = entityManager.find(User.class, eventRegisterationAction.getUserId());
                    UserEventRole role = entityManager.find(UserEventRole.class, eventRegisterationAction.getUserEventRoleId());
                    UserEventStatus status = entityManager.find(UserEventStatus.class, eventRegisterationAction.getUserEventStatusId());

                    UserEvent userEvent = new UserEvent(user, eventRegisterationAction.getEvent().getId(), role, status);
                    entityManager.persist(userEvent);
                }
				
			}
            
        }).join();                
    }

}