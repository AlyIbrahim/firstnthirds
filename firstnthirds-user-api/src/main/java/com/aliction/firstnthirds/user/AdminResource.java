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

import com.aliction.firstnthirds.user.entities.Admin;

import org.jboss.resteasy.annotations.jaxrs.PathParam;



@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

    private static final Logger LOGGER = Logger.getLogger(AdminResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    public Admin[] getAll() {
        return entityManager.createNamedQuery("Admin.findAll", Admin.class)
        .getResultList().toArray(new Admin[0]);
    }

    @GET
    @Path("{id}")
    public Admin get(@PathParam Long id) {
        Admin admin = entityManager.find(Admin.class, id);
        if (admin == null) {
            throw new WebApplicationException("Admin with id of " + id + " does not exist.", 404);
}
        return admin;
    }


    @POST
    @Transactional
    public Response create(Admin admin) {
        entityManager.persist(admin);
        return Response.ok(admin).status(201).build();        
    }

    @DELETE
    @Transactional
    public Response delete(Admin admin) {
        entityManager.remove(admin);
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        Admin admin = entityManager.getReference(Admin.class, id);
        if (admin == null) {
            throw new WebApplicationException("Admin with id of " + id + " does not exist.", 404);
}
        entityManager.remove(admin);
        return Response.status(204).build();
    }

}