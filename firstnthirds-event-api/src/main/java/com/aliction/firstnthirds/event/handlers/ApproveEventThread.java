package com.aliction.firstnthirds.event.handlers;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.aliction.firstnthirds.event.entities.Event;
import com.aliction.firstnthirds.event.entities.EventPending;
import com.aliction.firstnthirds.event.events.EventApproved;
import com.aliction.firstnthirds.event.events.EventTeamValidation;
import com.aliction.firstnthirds.event.events.TeamAction;

public class ApproveEventThread implements Callable<EventApproved>{

    public static Logger LOGGER = Logger.getLogger(ApproveEventThread.class.getName());

    private EntityManager entityManager;
    private EventTeamValidation eventTeamValidation;

	public ApproveEventThread(EntityManager entityManager, EventTeamValidation eventTeamValidation) {
        this.entityManager = entityManager;
        this.eventTeamValidation = eventTeamValidation;
    }

	@Override
	public EventApproved call() throws Exception {
        EventPending eventPending = entityManager.find(EventPending.class, eventTeamValidation.getEventPendingId());
        if(eventPending == null){
            LOGGER.info("EventPending ID : " + eventTeamValidation.getEventPendingId());
            LOGGER.info("EventPending Null");
        }
        Event event = new Event(eventPending);
        entityManager.persist(event);
        entityManager.remove(eventPending);
        EventApproved eventApproved = new EventApproved(event);
        if(eventApproved.getEvent() == null){
            LOGGER.info(eventPending.getId().toString());
            LOGGER.info(event.getId().toString());
        }
		return eventApproved;
	}
    


}
