package com.aliction.firstnthirds.user.events;

import com.aliction.firstnthirds.user.entities.Event;

public class EventRegisterationAction {

    private Long userId;
	private Event event;
	private Long userEventRoleId;
	private Long userEventStatusId;
    private EventRegisterationValidationStatus eventRegisterationValidationStatus;

    public EventRegisterationAction(){}

	public EventRegisterationAction(Long userId, Event event, Long userEventRoleId, Long userEventStatusId) {
		this.userId = userId;
		this.event = event;
		this.userEventRoleId = userEventRoleId;
		this.userEventStatusId = userEventStatusId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Event getEvent() {
		return event;
	}

	public Long getUserEventRoleId() {
		return userEventRoleId;
	}

	public void setUserEventRoleId(Long userRoleId) {
		this.userEventRoleId = userRoleId;
	}

	public Long getUserEventStatusId() {
		return userEventStatusId;
	}

	public void setUserEventStatusId(Long userStatusId) {
		this.userEventStatusId = userStatusId;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventRegisterationValidationStatus getEventRegisterationValidationStatus() {
		return eventRegisterationValidationStatus;
	}

	public void setEventRegisterationValidationStatus(
			EventRegisterationValidationStatus eventRegisterationValidationStatus) {
		this.eventRegisterationValidationStatus = eventRegisterationValidationStatus;
	}
}
