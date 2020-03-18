package com.aliction.firstnthirds.team.desrializers;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import com.aliction.firstnthirds.team.events.EventPendingCreated;

public class EventPendingCreatedDeserializer extends JsonbDeserializer<EventPendingCreated>{

	public EventPendingCreatedDeserializer() {
		super(EventPendingCreated.class);
		//TODO Auto-generated constructor stub
	}
    

}