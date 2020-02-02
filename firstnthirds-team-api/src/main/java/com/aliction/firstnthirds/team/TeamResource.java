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

import com.aliction.firstnthirds.team.entities.Team;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/team")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {
    
    private static final Logger LOGGER = Logger.getLogger(TeamResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Team[] getAll() {
        return entityManager.createNamedQuery("Team.findAll", Team.class)
        .getResultList().toArray(new Team[0]);
    }

    @GET
    @Path("{id}")
    public Team getTeam(@PathParam Long id){
        Team team = entityManager.find(Team.class, id);
        if (team == null) {
            throw new WebApplicationException("Team with id of " + id + " does not exist.", 404);
    }
        return team;
    }

    @POST
    @Transactional
    public Response create(Team team){
        entityManager.persist(team);
        return Response.ok(team).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id){
        Team team = entityManager.getReference(Team.class, id);
        if (team == null){
            throw new WebApplicationException("Team with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(team);
        return Response.ok(team).status(204).build();
    }

    @DELETE
    @Transactional
    public Response delete(Team teamRef){
        Team team = entityManager.getReference(Team.class, teamRef.getId());
        if (team == null){
            throw new WebApplicationException("Team with id of " + teamRef.getId() + " does not exist.", 404);
        }
        entityManager.remove(team);
        return Response.ok(team).status(204).build();
    }

}