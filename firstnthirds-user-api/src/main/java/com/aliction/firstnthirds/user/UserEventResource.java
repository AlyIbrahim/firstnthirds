package com.aliction.firstnthirds.user;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aliction.firstnthirds.user.entities.UserEvent;
import com.aliction.firstnthirds.user.entities.UserTeam;
import com.aliction.firstnthirds.user.services.TeamService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;



@Path("/userevent")
@Produces(MediaType.APPLICATION_JSON)
public class UserEventResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());

    @Inject
    EntityManager entityManager;

    @Inject
    @RestClient
    TeamService teamService;

    @GET
    public UserEvent[] getAll() {
        return entityManager.createNamedQuery("UserEvent.findAll", UserEvent.class)
        .getResultList().toArray(new UserEvent[0]);
    }

    @GET
    @Path("/{id}")
    public UserEvent get(@PathParam Long id) {
        UserEvent userEvent = entityManager.find(UserEvent.class, id);
        if (userEvent == null) {
            throw new WebApplicationException("UserEvent with id of " + id + " does not exist.", 404);
}
        return userEvent;
    }

    @POST
    @Transactional
    public Response create(UserEvent userEvent) {
        if(userEvent.getRole().getId() != 4 ){
            // TODO: This action should be done by the Team Lead
        }
        entityManager.persist(userEvent);
        return Response.ok(userEvent).status(201).build();        
    }
 
    @DELETE
    @Transactional
    public Response delete(UserEvent userEvent) {
        entityManager.remove(userEvent);
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        UserEvent userEvent = entityManager.getReference(UserEvent.class, id);
        if (userEvent == null) {
            throw new WebApplicationException("UserEvent with id of " + id + " does not exist.", 404);
}
        entityManager.remove(userEvent);
        return Response.status(204).build();
    }

}