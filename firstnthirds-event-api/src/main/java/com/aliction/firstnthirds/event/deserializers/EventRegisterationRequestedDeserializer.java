package com.aliction.firstnthirds.event.deserializers;

import com.aliction.firstnthirds.event.events.EventRegisterationRequested;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class EventRegisterationRequestedDeserializer extends JsonbDeserializer<EventRegisterationRequested>{
    public EventRegisterationRequestedDeserializer(){
        super(EventRegisterationRequested.class);
    }
}