package com.aliction.firstnthirds.team;

import java.util.ArrayList;
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

import com.aliction.firstnthirds.team.entities.Event;
import com.aliction.firstnthirds.team.entities.Team;
import com.aliction.firstnthirds.team.entities.User;
import com.aliction.firstnthirds.team.entities.UserTeam;
import com.aliction.firstnthirds.team.services.EventService;
import com.aliction.firstnthirds.team.services.UserService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/team")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {
    
    private static final Logger LOGGER = Logger.getLogger(TeamResource.class.getName());

    @Inject
    EntityManager entityManager;

    @Inject
    @RestClient
    UserService userService;
    
    @Inject
    @RestClient
    EventService eventService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Team[] getAll() {
        return entityManager.createNamedQuery("Team.findAll", Team.class)
        .getResultList().toArray(new Team[0]);
    }

    @GET
    @Path("/{id}")
    public Team getTeam(@PathParam Long id){
        Team team = entityManager.find(Team.class, id);
        if (team == null) {
            throw new WebApplicationException("Team with id of " + id + " does not exist.", 404);
    }
        return team;
    }


    @GET
    @Path("/{team}/leads")
    public List<User> getTeamLeads(@PathParam String team){
        List<Team> teams = entityManager.createQuery("SELECT team FROM Team team WHERE team.name = '" + team + "'", Team.class).getResultList();
        List<UserTeam> userTeams = null;
        if(teams == null || teams.size() == 0){
            throw new WebApplicationException("Team with name " + team + " does not exist.", 404);
        }else if(teams.size() != 1){
            throw new WebApplicationException("Duplicate teams has the same name " + team, 404);
        }else{
            userTeams = entityManager.createQuery("SELECT userTeam FROM UserTeam userTeam WHERE userTeam.team = " + teams.get(0).getId() + " AND userTeam.role = 1", UserTeam.class).getResultList();
        }

        if (userTeams == null || userTeams.size() == 0){
            throw new WebApplicationException("Team with name " + team + " has no leads assgined.", 404);
        }
        List<User> leads = new ArrayList<User>(userTeams.size());
        for ( UserTeam userteam : userTeams){
            leads.add(userService.getUserById(userteam.getUser()));
        }
        return leads;
    }

    @GET
    @Path("/{team}/members")
    public List<User> getTeamMembers(@PathParam String team){
        List<UserTeam> userTeams = null;
        Team teamObj = getTeamByName(team);
        
        userTeams = entityManager.createQuery("SELECT userTeam FROM UserTeam userTeam WHERE userTeam.team = " + teamObj.getId() + " AND userTeam.role = 2", UserTeam.class).getResultList();
        

        if (userTeams == null || userTeams.size() == 0 ){
            throw new WebApplicationException("Team with name " + team + " has no members.", 404);
        }
        List<User> members = new ArrayList<User>(userTeams.size());
        for ( UserTeam userteam : userTeams){
            members.add(userService.getUserById(userteam.getUser()));
        }
        return members;
    }

    @GET
    @Path("/{team}/events")
    public List<Event> getTeamEvents(@PathParam String team){
        List<Event> events = new ArrayList<Event>();
        Team teamObj = getTeamByName(team);
        events = eventService.getEventsdByTeamId(teamObj.getId());
        return events;
    }

    @GET
    @Path("/{team}/events/{status}")
    public List<Event> getTeamEvents(@PathParam String team, @PathParam String status){
        List<Event> events = new ArrayList<Event>();
        Team teamObj = getTeamByName(team);
        events = eventService.getEventsdByTeamId(teamObj.getId(), status);
        return events;
    }

    public Team getTeamByName(String teamName) {
        List<Team> teams = entityManager
                .createQuery("SELECT team FROM Team team WHERE team.name = '" + teamName + "'", Team.class)
                .getResultList();
        if (teams == null || teams.size() == 0) {
            throw new WebApplicationException("Team with name " + teamName + " does not exist.", 404);
        } else if (teams.size() > 1) {
            throw new WebApplicationException("Duplicate teams has the same name " + teamName, 404);
        }
        return teams.get(0);
    }

    @POST
    @Transactional
    public Response create(Team team){
        entityManager.persist(team);
        return Response.ok(team).status(201).build();
    }

    @DELETE
    @Path("/{id}")
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