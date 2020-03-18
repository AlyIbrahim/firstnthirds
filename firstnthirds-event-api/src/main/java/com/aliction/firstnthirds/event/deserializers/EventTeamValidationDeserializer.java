package com.aliction.firstnthirds.event.deserializers;

import com.aliction.firstnthirds.event.events.EventTeamValidation;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class EventTeamValidationDeserializer extends JsonbDeserializer<EventTeamValidation> {
    public EventTeamValidationDeserializer() {
		//TODO Auto-generated constructor stub
		super(EventTeamValidation.class);
	}
}