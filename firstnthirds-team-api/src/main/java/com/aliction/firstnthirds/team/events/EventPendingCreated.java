package com.aliction.firstnthirds.team.events;

public class EventPendingCreated implements EventAction{

    private Long eventPendingId;
    private Long teamId;
    // private Event pendingEvent;

    public EventPendingCreated (){  }

    public EventPendingCreated(Long eventPendingId, Long teamId) {
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

}