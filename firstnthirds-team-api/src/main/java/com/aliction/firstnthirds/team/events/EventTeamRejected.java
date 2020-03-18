package com.aliction.firstnthirds.team.events;

public class EventTeamRejected extends EventTeamValidation{

    public EventTeamRejected() {  }

	public EventTeamRejected(Long eventPendingId, Long teamId, EventTeamValidationStatus validationStatus) {
		super(eventPendingId, teamId);
		setValidationStatus(validationStatus);
	}

}