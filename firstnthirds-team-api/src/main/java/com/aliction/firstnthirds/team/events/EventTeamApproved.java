package com.aliction.firstnthirds.team.events;

public class EventTeamApproved extends EventTeamValidation{

    public EventTeamApproved() {  }

	public EventTeamApproved(Long eventPendingId, Long teamId, EventTeamValidationStatus validationStatus) {
		super(eventPendingId, teamId);
		setValidationStatus(validationStatus);
	}
}