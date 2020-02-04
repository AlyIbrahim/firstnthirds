package com.aliction.firstnthirds.team.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aliction.firstnthirds.team.entities.Event;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "eventService")
public interface EventService{

    @GET
    @Path("/team/{id}")
    List<Event> getEventsdByTeamId(@PathParam Long id);

    @GET
    @Path("/team/{id}/{status}")
    List<Event> getEventsdByTeamId(@PathParam Long id, @PathParam String status);

}