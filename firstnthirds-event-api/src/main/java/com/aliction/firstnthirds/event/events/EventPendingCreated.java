package com.aliction.firstnthirds.event.events;

public class EventPendingCreated implements EventAction{

    private Long eventPendingId;
    private Long teamId;
    // private Event eventPending;

    public EventPendingCreated() {}

    public EventPendingCreated(Long eventPendingId, Long teamId) {
		this.eventPendingId = eventPendingId;
		this.teamId = teamId;
	}

	// public EventCreated(Long eventPendingId, Long teamId, Event eventPending) {
	// 	this.eventPendingId = eventPendingId;
	// 	this.teamId = teamId;
	// 	this.eventPending = eventPending;
	// }
    
	public Long getEventPendingId() {
		return eventPendingId;
	}
	public void setEventId(Long eventPendingId) {
		this.eventPendingId = eventPendingId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	// public Event geteventPending() {
	// 	return eventPending;
	// }
	// public void seteventPending(Event eventPending) {
	// 	this.eventPending = eventPending;
	// }

    


}