package com.aliction.firstnthirds.team.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aliction.firstnthirds.team.entities.User;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "userService")
public interface UserService{

    @GET
    @Path("/{id}")
    User getUserById(@PathParam Long id);
}