package com.aliction.firstnthirds.user.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.aliction.firstnthirds.user.entities.UserTeam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/userteam")
@RegisterRestClient(configKey = "teamService")
public interface TeamService{

    @GET
    @Path("/user/{userId}")
    List<UserTeam> getUserTeams(@PathParam Long userId);
}