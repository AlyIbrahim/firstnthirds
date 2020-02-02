package com.aliction.firstnthirds.team;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aliction.firstnthirds.team.entities.TeamStatus;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/teamStatus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamStatusResource {
    
    private static final Logger LOGGER = Logger.getLogger(TeamResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TeamStatus[] getAll() {
        return entityManager.createNamedQuery("TeamStatus.findAll", TeamStatus.class)
        .getResultList().toArray(new TeamStatus[0]);
    }

    @GET
    @Path("{id}")
    public TeamStatus getTeam(@PathParam Long id){
        TeamStatus teamStatus = entityManager.find(TeamStatus.class, id);
        if (teamStatus == null) {
            throw new WebApplicationException("Team Status with id of " + id + " does not exist.", 404);
    }
        return teamStatus;

    }

    @POST
    @Transactional
    public Response create(TeamStatus teamStatus){
        entityManager.persist(teamStatus);
        return Response.ok().status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id){
        TeamStatus teamStatus = entityManager.getReference(TeamStatus.class, id);
        if (teamStatus == null){
            throw new WebApplicationException("Team Statuswith id of " + id + " does not exist.", 404);
        }
        entityManager.remove(teamStatus);
        return Response.ok(teamStatus).status(204).build();
    }

    @DELETE
    @Transactional
    public Response delete(TeamStatus teamStatusRef){
        TeamStatus teamStatus = entityManager.getReference(TeamStatus.class, teamStatusRef.getId());
        if (teamStatus == null){
            throw new WebApplicationException("Team Status with id of " + teamStatusRef.getId() + " does not exist.", 404);
        }
        entityManager.remove(teamStatus);
        return Response.ok(teamStatus).status(204).build();
    }

}