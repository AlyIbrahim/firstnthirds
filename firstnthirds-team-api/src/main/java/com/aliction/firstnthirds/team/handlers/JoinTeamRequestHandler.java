package com.aliction.firstnthirds.team.handlers;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aliction.firstnthirds.team.entities.Team;
import com.aliction.firstnthirds.team.entities.UserTeam;
import com.aliction.firstnthirds.team.entities.UserTeamRole;
import com.aliction.firstnthirds.team.events.JoinTeamRequested;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import org.eclipse.microprofile.reactive.messaging.Incoming;


// @ApplicationScoped
// @ActivateRequestContext
public class JoinTeamRequestHandler{

    public static Logger LOGGER = Logger.getLogger(JoinTeamRequestHandler.class.getName());

    @Inject
    EntityManager entityManager;


    @Transactional
    @Incoming("joinTeamRequests")
    public void handleJoinTeamRequest(JoinTeamRequested joinTeamRequested) throws InterruptedException, ExecutionException{
        ManagedExecutor executor = ManagedExecutor.builder()
            .maxAsync(5)
            .propagated(ThreadContext.CDI, 
                        ThreadContext.TRANSACTION).build();
                                              
        // JoinTeamThread joinTeamThread = new JoinTeamThread(entityManager, joinTeamRequested);
        // executor.runAsync(joinTeamThread).join();

        executor.runAsync(new Runnable() { 
            public void run(){
            Team team = entityManager.find(Team.class, joinTeamRequested.getTeamId());
            UserTeamRole role = entityManager.find(UserTeamRole.class, joinTeamRequested.getRoleId());
            UserTeam userTeam = new UserTeam(joinTeamRequested.getUserId(), team, role);
            entityManager.persist(userTeam);
            LOGGER.info("User of id " + userTeam.getUser() + " has successfully joined team " + userTeam.getTeam().getName() + " of id " + userTeam.getTeam().getId());
        }}).join();

    }

}