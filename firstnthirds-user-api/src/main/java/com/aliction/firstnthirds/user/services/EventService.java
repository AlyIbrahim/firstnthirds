package com.aliction.firstnthirds.user.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.aliction.firstnthirds.user.entities.Event;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/event")
@RegisterRestClient(configKey = "eventService")
public interface EventService{

    @GET
    @Path("/{eventId}")
    Event getEventById(@PathParam Long eventId);
}