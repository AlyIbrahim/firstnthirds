package com.aliction.firstnthirds.event.events;


public class EventTeamValidation{

    private Long eventPendingId;
    private Long teamId;
    private EventTeamValidationStatus validationStatus;

    public EventTeamValidation() {}

	public EventTeamValidation(Long eventPendingId, Long teamId) {
		this.eventPendingId = eventPendingId;
		this.teamId = teamId;
	}

	public Long getEventPendingId() {
		return eventPendingId;
	}

	public void setEventPendingId(Long eventPendingId) {
		this.eventPendingId = eventPendingId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public EventTeamValidationStatus getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(EventTeamValidationStatus validationStatus) {
		this.validationStatus = validationStatus;
	}
    
    

    

}