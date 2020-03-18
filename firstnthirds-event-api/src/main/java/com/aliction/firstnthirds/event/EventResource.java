package com.aliction.firstnthirds.event;

import java.util.List;

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
import com.aliction.firstnthirds.event.entities.EventPending;
import com.aliction.firstnthirds.event.entities.EventStatus;
import com.aliction.firstnthirds.event.entities.Team;
import com.aliction.firstnthirds.event.events.EventAction;
import com.aliction.firstnthirds.event.events.EventPendingCreated;
import com.aliction.firstnthirds.event.services.TeamService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

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

    @Inject 
    @Channel("ivy")
    Emitter<Event> eventEmitter;

    @Inject
    @Channel("events") Publisher<Event> events;

    @Inject 
    @Channel("event")
    Emitter<EventAction> eventActionEmitter;
    
    @GET
    public Event[] getAll() {
        return entityManager.createNamedQuery("Event.findAll", Event.class)
        .getResultList().toArray(new Event[0]);
    }

    @GET
    @Path("/{id}")
    public Event get(@PathParam Long id){
        Event event = entityManager.find(Event.class, id);
        if (event ==null){
            throw new WebApplicationException("Event with id of " + id + " does not exist.", 404);
        }
        return event;

    }

    @GET
    @Path("/team/{id}")
    public List<Event> getEventsByTeamId(@PathParam Long id){
        List<Event> events = entityManager.createQuery("SELECT event FROM Event event WHERE event.teamId = " + id, Event.class).getResultList();
        return events;        
    }

    @GET
    @Path("/team/{teamId}/status/{statusId}")
    public List<Event> getEventsByTeamId(@PathParam Long teamId, @PathParam Long statusId){
        List<EventStatus> statusObj = entityManager.createQuery("SELECT eventstatus FROM EventStatus eventstatus WHERE eventstatus.id =" + statusId
        , EventStatus.class).getResultList();
        if (statusObj == null || statusObj.size() == 0){
            throw new WebApplicationException("Event status id of " + statusId + " does not exist.", 404);
        }
        List<Event> events = entityManager.createQuery("SELECT event FROM Event event WHERE event.teamId = " + teamId + "AND event.status = " 
        + statusObj.get(0).getId(), Event.class).getResultList();
        return events;        
    }

    @GET
    @Path("/team/{teamId}/{status}")
    public List<Event> getEventsByTeamId(@PathParam Long teamId, @PathParam String status){
        List<EventStatus> statusObj = entityManager.createQuery("SELECT eventstatus FROM EventStatus eventstatus WHERE eventstatus.status ='" + status + "'"
        , EventStatus.class).getResultList();
        if (statusObj == null || statusObj.size() == 0){
            throw new WebApplicationException("Event status " + status + " does not exist.", 404);
        }
        List<Event> events = entityManager.createQuery("SELECT event FROM Event event WHERE event.teamId = " + teamId + "AND event.status = " 
        + statusObj.get(0).getId(), Event.class).getResultList();
        return events;        
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    @SseElementType(MediaType.APPLICATION_JSON) 
    public Publisher<Event> stream() { 
        LOGGER.info(" FECHING EVENTS ");
        return events;
    }

    @POST
    @Transactional
    public Response create(Event event){
        Team team = teamService.getById(event.getTeamId());
        // } catch(java.net.ConnectException ConnExp){
        // LOGGER.info("Unable to connect to Team Service");
        // TODO: Handle Service Down
        if (team == null){
            throw new WebApplicationException("Team with id of " + event.getTeamId() + " does not exist.", 404);
        }
        LOGGER.info(team.getStatus().getProvisionStatus());
        entityManager.persist(event);
        if(event.getStatus().getId() == 2){
            LOGGER.info(" READY EVENT PUBLISHED ");
            eventEmitter.send(event);
        }
        return Response.ok(event).status(201).build();
    }

    @POST
    @Transactional
    @Path("async")
    public Response createAsync(Event event){
        EventPending eventPending = new EventPending(event);
        entityManager.persist(eventPending);
        EventPendingCreated eventPendingCreated = new EventPendingCreated(eventPending.getId(), event.getTeamId());
        LOGGER.info("PENDING ID : "+ eventPending.getId());
        eventActionEmitter.send(eventPendingCreated);
        return Response.ok(event).status(201).build();
    }

    @DELETE
    @Path("/{id}")
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