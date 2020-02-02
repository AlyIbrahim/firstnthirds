package com.aliction.firstnthirds.user;

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

import com.aliction.firstnthirds.user.entities.User;

import org.jboss.resteasy.annotations.jaxrs.PathParam;



@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    public User[] getAll() {
        return entityManager.createNamedQuery("User.findAll", User.class)
        .getResultList().toArray(new User[0]);
    }

    @GET
    @Path("{id}")
    public User get(@PathParam Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
}
        return user;
    }


    @POST
    @Transactional
    public Response create(User user) {
        entityManager.persist(user);
        return Response.ok(user).status(201).build();        
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