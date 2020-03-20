package com.aliction.firstnthirds.user.events;

public class EventRegisterationRequested{

    private Long userId;
    private Long eventId;
    private Long userEventRoleId;
    private Long userEventStatusId;

    public EventRegisterationRequested(){}

	public EventRegisterationRequested(Long eventId, Long userEventRoleId, Long userEventStatusId, Long userId) {
		this.eventId = eventId;
		this.userEventRoleId = userEventRoleId;
		this.userEventStatusId = userEventStatusId;
		this.userId = userId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getUserEventRoleId() {
		return userEventRoleId;
	}
	public void setUserEventRoleId(Long userEventRoleId) {
		this.userEventRoleId = userEventRoleId;
	}
	public Long getUserEventStatusId() {
		return userEventStatusId;
	}
	public void setUserEventStatusId(Long userEventStatusId) {
		this.userEventStatusId = userEventStatusId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}