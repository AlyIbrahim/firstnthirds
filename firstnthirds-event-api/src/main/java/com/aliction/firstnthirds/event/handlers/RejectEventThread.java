package com.aliction.firstnthirds.event.handlers;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aliction.firstnthirds.event.entities.Event;
import com.aliction.firstnthirds.event.entities.EventPending;
import com.aliction.firstnthirds.event.events.EventApproved;
import com.aliction.firstnthirds.event.events.EventTeamValidation;

public class RejectEventThread implements Callable<EventApproved>{
	public static Logger LOGGER = Logger.getLogger(RejectEventThread.class.getName());

	private EntityManager entityManager;
	private EventTeamValidation eventTeamValidation;

	public RejectEventThread(EntityManager entityManager, EventTeamValidation eventTeamValidation) {
        this.entityManager = entityManager;
        this.eventTeamValidation = eventTeamValidation;
	}

	// @Override
	// public void run() {
	// 	EventPending eventPending = entityManager.find(EventPending.class, eventTeamValidation.getEventId());
	// 	LOGGER.info("Removing event with id " + eventPending.getId());
	// 	// deletePendingEvent(eventPending);
	// 	entityManager.remove(eventPending);	
	// 	// entityManager.flush();	
	// }

	@Transactional
	public void deletePendingEvent(EventPending eventPending){
		entityManager.remove(eventPending);	
	}

	@Override
	public EventApproved call() throws Exception {
		EventPending eventPending = entityManager.find(EventPending.class, eventTeamValidation.getEventPendingId());
		LOGGER.info("Removing event with id " + eventPending.getId());
		EventApproved eventApproved = new EventApproved(new Event(eventPending));
		entityManager.remove(eventPending);	
		return null;
		// return eventApproved;
	}

}
