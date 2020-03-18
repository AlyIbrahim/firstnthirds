package com.aliction.firstnthirds.team.events;

import com.aliction.firstnthirds.team.entities.Event;

public class EventApproved implements EventAction{
	
    private Event event;

    public EventApproved(){}

    public EventApproved(Event event){

    }

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


}