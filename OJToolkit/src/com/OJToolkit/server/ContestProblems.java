package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ContestProblems {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long contestproblemID ; 
	
	@Persistent
	private Long contestID ;
	
	@Persistent
	private Long problemID ;
	
	public ContestProblems(Long contestID,Long problemID) {
		this.contestID = contestID ;
		this.problemID = problemID ;
	}
	
	public Long getContestID() {
		return contestID;
	}
	public Long getContestproblemID() {
		return contestproblemID;
	}
	public Long getProblemID() {
		return problemID;
	}
	
	public void setContestID(Long contestID) {
		this.contestID = contestID;
	}
	
	public void setContestproblemID(Long contestproblemID) {
		this.contestproblemID = contestproblemID;
	}
	
	
	public void setProblemID(Long problemID) {
		this.problemID = problemID;
	}

}
