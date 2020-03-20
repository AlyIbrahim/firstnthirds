package com.aliction.firstnthirds.team.desrializers;


import com.aliction.firstnthirds.team.events.JoinTeamRequested;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class JoinTeamRequestedDeserializer extends JsonbDeserializer<JoinTeamRequested>{

	public JoinTeamRequestedDeserializer() {
		super(JoinTeamRequested.class);
		//TODO Auto-generated constructor stub
	}

}