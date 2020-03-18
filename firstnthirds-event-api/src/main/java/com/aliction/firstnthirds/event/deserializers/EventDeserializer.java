package com.aliction.firstnthirds.event.deserializers;

import com.aliction.firstnthirds.event.entities.Event;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class EventDeserializer extends JsonbDeserializer<Event> {
    public EventDeserializer(){
        // pass the class to the parent.
        super(Event.class);
    }
}