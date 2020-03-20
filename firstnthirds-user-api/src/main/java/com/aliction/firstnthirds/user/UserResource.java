package com.aliction.firstnthirds.user;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aliction.firstnthirds.user.entities.Event;
import com.aliction.firstnthirds.user.entities.User;
import com.aliction.firstnthirds.user.entities.UserEvent;
import com.aliction.firstnthirds.user.entities.UserTeam;
import com.aliction.firstnthirds.user.events.JoinTeamRequested;
import com.aliction.firstnthirds.user.services.EventService;
import com.aliction.firstnthirds.user.services.TeamService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Channel;



@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());

    @Inject
    EntityManager entityManager;

    @Inject
    @RestClient
    TeamService teamService;

    @Inject
    @RestClient
    EventService eventService;

    @Inject
    @Channel("joinTeamRequests") Emitter<JoinTeamRequested> joinTeamEmitter;

    @GET
    public User[] getAll() {
        return entityManager.createNamedQuery("User.findAll", User.class)
        .getResultList().toArray(new User[0]);
    }

    @GET
    @Path("/{id}")
    public User get(@PathParam Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
}
        return user;
    }

    @GET
    @Path("/{userId}/teams")
    public List<UserTeam> getUserTeams(@PathParam Long userId){
        User user = get(userId);
        List<UserTeam> userTeams = teamService.getUserTeams(user.getId());
        return userTeams;
    }

    @GET
    @Path("/{userId}/events")
    public List<Event> getUserEvents(@PathParam Long userId){
        
        User user = get(userId);
        if (user == null){
            throw new WebApplicationException("User with id of " + userId + " does not exist.", 404);
        }
        List<UserEvent> userEvents = entityManager.createQuery("SELECT userevent FROM UserEvent userevent WHERE userevent.user = " + user.getId(), UserEvent.class).getResultList();
        if (userEvents == null || userEvents.size() == 0){
            throw new WebApplicationException("User with id of " + userId + " does not exist.", 404);
        }
        List<Event> events = new ArrayList<Event>(userEvents.size());

        for (UserEvent userEvent : userEvents){
            LOGGER.info(userEvent.getEventId().toString());
            events.add(eventService.getEventById(userEvent.getEventId()));
        }

        return events;
    }

    @POST
    @Transactional
    public Response create(User user) {
        entityManager.persist(user);
        return Response.ok(user).status(201).build();        
    }

    @POST
    @Transactional
    @Path("async/join")
    public Response joinTeamRequest(JoinTeamRequested joinTeamRequested){
        joinTeamEmitter.send(joinTeamRequested);
        return Response.ok(joinTeamRequested).status(201).build();
    }
 
    @DELETE
    @Transactional
    public Response delete(User user) {
        entityManager.remove(user);
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        User user = entityManager.getReference(User.class, id);
        if (user == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
}
        entityManager.remove(user);
        return Response.status(204).build();
    }

}