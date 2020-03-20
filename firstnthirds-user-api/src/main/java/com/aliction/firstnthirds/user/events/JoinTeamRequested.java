package com.aliction.firstnthirds.user.events;

public class JoinTeamRequested{

    private Long userId;
    private Long teamId;
    private Long roleId;

    public JoinTeamRequested(){}

	public JoinTeamRequested(Long userId, Long teamId, Long roleId) {
		this.userId = userId;
		this.teamId = teamId;
		this.roleId = roleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

    

}