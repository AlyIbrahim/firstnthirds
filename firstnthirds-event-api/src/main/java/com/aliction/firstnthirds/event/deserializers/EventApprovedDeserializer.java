package com.aliction.firstnthirds.event.deserializers;

import com.aliction.firstnthirds.event.events.EventApproved;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class EventApprovedDeserializer extends JsonbDeserializer<EventApproved> {
    public EventApprovedDeserializer() {
		//TODO Auto-generated constructor stub
		super(EventApproved.class);
	}
}