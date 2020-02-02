package com.aliction.firstnthirds.event.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.aliction.firstnthirds.event.entities.Team;

@Path("/team")
@RegisterRestClient(configKey = "teamService")
public interface TeamService{


    @GET
    @Path("{id}")
    Team getById(@PathParam Long id);

    
    
}