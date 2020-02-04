package com.aliction.firstnthirds.team;

import java.util.List;
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

import com.aliction.firstnthirds.team.entities.UserTeam;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/userteam")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserTeamResource {
    
    private static final Logger LOGGER = Logger.getLogger(UserTeamResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserTeam[] getAll() {
        return entityManager.createNamedQuery("UserTeam.findAll", UserTeam.class)
        .getResultList().toArray(new UserTeam[0]);
    }

    @GET
    @Path("/{id}")
    public UserTeam getUserTeam(@PathParam Long id){
        UserTeam userteam = entityManager.find(UserTeam.class, id);
        if (userteam == null) {
            throw new WebApplicationException("UserTeam with id of " + id + " does not exist.", 404);
    }
        return userteam;
    }

    @GET
    @Path("/user/{userId}")
    public List<UserTeam> getUserTeams(@PathParam Long userId){
        List<UserTeam> userteams = entityManager.createQuery("SELECT userteam FROM UserTeam userteam INNER JOIN Team team ON userteam.team.id = team.id WHERE userteam.user = " + userId, UserTeam.class).getResultList();
        if(userteams == null || userteams.size() == 0){
            throw new WebApplicationException("User with id of " + userId + " is not subscribed to any team.", 404);
        }
        return userteams;
    }

    @POST
    @Transactional
    public Response create(UserTeam userteam){
        entityManager.persist(userteam);
        return Response.ok(userteam).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id){
        UserTeam userteam = entityManager.getReference(UserTeam.class, id);
        if (userteam == null){
            throw new WebApplicationException("UserTeam with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(userteam);
        return Response.ok(userteam).status(204).build();
    }

    @DELETE
    @Transactional
    public Response delete(UserTeam teamRef){
        UserTeam userteam = entityManager.getReference(UserTeam.class, teamRef.getId());
        if (userteam == null){
            throw new WebApplicationException("UserTeam with id of " + teamRef.getId() + " does not exist.", 404);
        }
        entityManager.remove(userteam);
        return Response.ok(userteam).status(204).build();
    }

}