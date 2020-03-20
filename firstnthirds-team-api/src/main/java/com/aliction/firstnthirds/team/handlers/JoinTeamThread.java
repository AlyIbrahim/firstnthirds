package com.aliction.firstnthirds.team.handlers;

import java.util.concurrent.Callable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aliction.firstnthirds.team.entities.Team;
import com.aliction.firstnthirds.team.entities.UserTeam;
import com.aliction.firstnthirds.team.entities.UserTeamRole;
import com.aliction.firstnthirds.team.events.JoinTeamRequested;

import org.jboss.logging.Logger;

public class JoinTeamThread implements Runnable {
    public static Logger LOGGER = Logger.getLogger(JoinTeamThread.class.getName());
    EntityManager entityManager;
    JoinTeamRequested joinTeamRequested;

    public JoinTeamThread(EntityManager entityManager, JoinTeamRequested joinTeamRequested){
        this.entityManager = entityManager;
        this.joinTeamRequested = joinTeamRequested;
    }
    
    
    public void run(){
        Team team = entityManager.find(Team.class, joinTeamRequested.getTeamId());
        UserTeamRole role = entityManager.find(UserTeamRole.class, joinTeamRequested.getRoleId());
        UserTeam userTeam = new UserTeam(joinTeamRequested.getUserId(), team, role);
        entityManager.persist(userTeam);
    }


	
	public Object call() throws Exception {
		Team team = entityManager.find(Team.class, joinTeamRequested.getTeamId());
        UserTeamRole role = entityManager.find(UserTeamRole.class, joinTeamRequested.getRoleId());
        UserTeam userTeam = new UserTeam(joinTeamRequested.getUserId(), team, role);
        LOGGER.info(userTeam.getTeam().getName());
        entityManager.persist(userTeam);
		return null;
	}

}