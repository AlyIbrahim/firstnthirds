package com.aliction.firstnthirds.user.events;

import com.aliction.firstnthirds.user.entities.Event;

public class EventRegisterationApproved extends EventRegisterationAction{

    public EventRegisterationApproved(Long userId, Event event, Long userRoleId, Long userStatusId){
        super(userId, event, userRoleId, userStatusId);
        setEventRegisterationValidationStatus(EventRegisterationValidationStatus.EVENT_ACCEPTS_REGISTERATION);
    }

    

}
