package com.aliction.firstnthirds.event.events;

public class EventTeamApproved extends EventTeamValidation{

    public EventTeamApproved() {  }

	public EventTeamApproved(Long eventPendingId, Long teamId) {
		super(eventPendingId, teamId);
		// setValidationString(EventTeamValidationStatus.EVENT_TEAM_FOUND);
	}

}