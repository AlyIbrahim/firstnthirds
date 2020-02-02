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

import com.aliction.firstnthirds.event.entities.EventStatus;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/eventStatus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventStatusResource {

    @Inject
    EntityManager entityManager;

    @GET
    public EventStatus[] getAll() {
        return entityManager.createNamedQuery("EventStatus.findAll", EventStatus.class)
        .getResultList().toArray(new EventStatus[0]);
    }

    @GET
    @Path("{id}")
    public EventStatus get(@PathParam Long id){
        EventStatus eventStatus = entityManager.find(EventStatus.class, id);
        if (eventStatus ==null){
            throw new WebApplicationException("EventStatus with id of " + id + " does not exist.", 404);
        }
        return eventStatus;

    }

    @POST
    @Transactional
    public Response create(EventStatus eventStatus){
        entityManager.persist(eventStatus);
        return Response.ok(eventStatus).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id){
        EventStatus eventStatus = entityManager.getReference(EventStatus.class, id);
        if (eventStatus == null){
            throw new WebApplicationException("EventStatus with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(eventStatus);
        return Response.ok(eventStatus).status(204).build();
    }

    @DELETE
    @Transactional
    public Response delete(EventStatus eventStatusRef){
        EventStatus eventStatus = entityManager.getReference(EventStatus.class, eventStatusRef.getId());
        if (eventStatus == null){
            throw new WebApplicationException("EventStatus with id of " + eventStatusRef.getId() + " does not exist.", 404);
        }
        entityManager.remove(eventStatus);
        return Response.ok(eventStatus).status(204).build();
    }
}