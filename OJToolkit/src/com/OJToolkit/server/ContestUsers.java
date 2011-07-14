package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ContestUsers {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long contestUserID ;
	
	@Persistent
	private Long ContestID ; 
	
	@Persistent
	private Long UserID ;

	public ContestUsers(Long contestID, Long userID) {
		this.ContestID = contestID ;
		this.UserID = userID ;
	}

	public Long getContestUserID() {
		return contestUserID;
	}

	public void setContestproblemID(Long contestUserID) {
		this.contestUserID = contestUserID;
	}

	public Long getContestID() {
		return ContestID;
	}

	public void setContestID(Long contestID) {
		this.ContestID = contestID;
	}

	public Long getuserID() {
		return UserID;
	}

	public void setuserID(Long userID) {
		this.UserID = userID;
	}
	
}
