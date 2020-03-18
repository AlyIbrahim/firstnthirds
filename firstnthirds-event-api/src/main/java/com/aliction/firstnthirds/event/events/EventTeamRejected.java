package com.aliction.firstnthirds.event.events;

public class EventTeamRejected extends EventTeamValidation{

    public EventTeamRejected() {  }

	public EventTeamRejected(Long eventPendingId, Long teamId) {
		super(eventPendingId, teamId);
		// setValidationString(EventTeamValidationStatus.EVENT_TEAM_NOT_FOUND);
	}
}