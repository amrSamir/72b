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
	private String problemName ;
	
	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getProblemOJ() {
		return problemOJ;
	}

	public void setProblemOJ(String problemOJ) {
		this.problemOJ = problemOJ;
	}

	@Persistent
	private String problemOJ ;
	
	public ContestProblems(Long contestID, String problemName ,String problemOJ ) {
		this.contestID = contestID ;
		this.problemName = problemName ;
		this.problemOJ = problemOJ ;
	}
	
	public Long getContestID() {
		return contestID;
	}
	public Long getContestproblemID() {
		return contestproblemID;
	}
	
	
	public void setContestID(Long contestID) {
		this.contestID = contestID;
	}
	
	public void setContestproblemID(Long contestproblemID) {
		this.contestproblemID = contestproblemID;
	}
	
	
	

}
