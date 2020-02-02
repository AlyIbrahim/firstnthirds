package com.aliction.firstnthirds.event;

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

import com.aliction.firstnthirds.event.entities.Event;
import com.aliction.firstnthirds.event.entities.Team;
import com.aliction.firstnthirds.event.services.TeamService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    public static Logger LOGGER = Logger.getLogger(EventResource.class);

    @Inject
    EntityManager entityManager;

    @Inject
    @RestClient
    TeamService teamService;

    @GET
    public Event[] getAll() {
        return entityManager.createNamedQuery("Event.findAll", Event.class)
        .getResultList().toArray(new Event[0]);
    }

    @GET
    @Path("{id}")
    public Event get(@PathParam Long id){
        Event event = entityManager.find(Event.class, id);
        if (event ==null){
            throw new WebApplicationException("Event with id of " + id + " does not exist.", 404);
        }
        return event;

    }

    @POST
    @Transactional
    public Response create(Event event){
        Team team = teamService.getById(event.getTeamId());
        if (team == null){
            throw new WebApplicationException("Team with id of " + event.getTeamId() + " does not exist.", 404);
        }
        LOGGER.info(team.getStatus().getProvisionStatus());
        entityManager.persist(event);
        return Response.ok(event).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id){
        Event event = entityManager.getReference(Event.class, id);
        if (event == null){
            throw new WebApplicationException("Event with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(event);
        return Response.ok(event).status(204).build();
    }

    @DELETE
    @Transactional
    public Response delete(Event eventRef){
        Event event = entityManager.getReference(Event.class, eventRef.getId());
        if (event == null){
            throw new WebApplicationException("Event with id of " + eventRef.getId() + " does not exist.", 404);
        }
        entityManager.remove(event);
        return Response.ok(event).status(204).build();
    }
}