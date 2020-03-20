package com.aliction.firstnthirds.user.deserializers;

import com.aliction.firstnthirds.user.events.EventRegisterationAction;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class EventRegisterationActionDeserializer extends JsonbDeserializer<EventRegisterationAction>{
    public EventRegisterationActionDeserializer(){
        super(EventRegisterationAction.class);
    }
}