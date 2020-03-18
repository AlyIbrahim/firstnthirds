package com.aliction.firstnthirds.team.events;

import java.util.concurrent.Callable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import com.aliction.firstnthirds.team.entities.Team;

public class EventTeamValidationThread implements Callable<EventTeamValidation>{

    public static Logger LOGGER = Logger.getLogger(EventTeamValidationThread.class.getName());

    private EntityManager entitymanager;
    private EventPendingCreated eventPendingCreated;
    private EventTeamValidation eventTeamValidation;

    public EventTeamValidationThread(){}

    public EventTeamValidationThread(EventPendingCreated eventPendingCreated){
        this.eventPendingCreated = eventPendingCreated;
    }

    public EventTeamValidationThread(EntityManager entityManager, EventPendingCreated eventPendingCreated) {
        this.entitymanager = entityManager;
        this.eventPendingCreated = eventPendingCreated;
	}

	// @Override
	// public void run() {
    //     if (entitymanager == null){
    //         System.out.println("NULL");
    //     }
    //     Team team = entitymanager.find(Team.class, eventPendingCreated.getTeamId());
    //     if (team != null){
    //         LOGGER.info("Team with id "+ eventPendingCreated.getTeamId() +" found");
    //         eventTeamValidated = new EventTeamValidated(eventPendingCreated.getEventId(), team.getId(), "Team Found");
    //     }else{
    //         LOGGER.info("Team with id "+ eventPendingCreated.getTeamId() +" not found");
    //         eventTeamValidated = new EventTeamValidated(eventPendingCreated.getEventId(), eventPendingCreated.getTeamId(), "Team Not Found");
    //     }
    //     sendReply();
    // }
    // public TeamAction sendReply(){
    //     LOGGER.info("Team Action Event Sent for event id " + eventTeamValidated.getEventId());
	// 	return eventTeamValidated;
    // }

	@Override
	public EventTeamValidation call() throws Exception {
		if (entitymanager == null){
            System.out.println("NULL");
        }
        LOGGER.info("EVENT ID" + eventPendingCreated.getEventPendingId());
        Team team = entitymanager.find(Team.class, eventPendingCreated.getTeamId());
        if (team != null){
            LOGGER.info("Team with id "+ eventPendingCreated.getTeamId() +" found");
            eventTeamValidation = new EventTeamApproved(eventPendingCreated.getEventPendingId(), team.getId(), EventTeamValidationStatus.EVENT_TEAM_FOUND);
        }else{
            LOGGER.info("Team with id "+ eventPendingCreated.getTeamId() +" not found");
            eventTeamValidation = new EventTeamRejected(eventPendingCreated.getEventPendingId(), eventPendingCreated.getTeamId(), EventTeamValidationStatus.EVENT_TEAM_NOT_FOUND);
        }
		return eventTeamValidation;
	}
}