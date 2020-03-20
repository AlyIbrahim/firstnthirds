package com.aliction.firstnthirds.event.events;

import com.aliction.firstnthirds.event.entities.Event;

public class EventRegisterationRejected extends EventRegisterationAction{

    public EventRegisterationRejected(Long userId, Event event, Long userRoleId, Long userStatusId){
        super(userId, event, userRoleId, userStatusId);
        setEventRegisterationValidationStatus(EventRegisterationValidationStatus.EVENT_NOT_READY_FOR_REGISTERATION);
    }

    

}