package com.aliction.firstnthirds.event.events;

import com.aliction.firstnthirds.event.entities.Event;

public class EventApproved implements EventAction{
    private Event event;

    public EventApproved(){}

    public EventApproved(Event event){
		this.event = event;
    }

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}