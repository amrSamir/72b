package com.OJToolkit.server;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.PrePersist;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Contest {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long ContestID;

	@Persistent
	private String ContestName;

	@Persistent
	private String ContestAccessCode;

	@Persistent
	private Long startDate ;
	
	@Persistent
	private Long endDate ;
	
	@Persistent
	private Integer contestDuraion ;
	
	@Persistent
	private Long contestOwnerID ;
	
	public Long getContestOwnerID() {
		return contestOwnerID;
	}
	public void setContestOwnerID(Long contestOwnerID) {
		this.contestOwnerID = contestOwnerID;
	}
	/**
	 * @param ContestName
	 *            the contest name
	 */
	public Contest(String ContestName, String ContestAccessCode,Long contestOwnerID) {
		this.ContestName = ContestName;
		this.ContestAccessCode = ContestAccessCode ;
		this.contestOwnerID = contestOwnerID ;
	}
	public Contest(String ContestName, String ContestAccessCode ,Long contestOwnerID, String startDate , String endDate,Integer contestDuration){
		this.ContestName = ContestName;
		this.ContestAccessCode = ContestAccessCode ;
		this.contestDuraion = contestDuration ;
		this.endDate = TimeUtility.getTimeinLinux(endDate) ;
		this.startDate = TimeUtility.getTimeinLinux(startDate) ;
		this.contestOwnerID = contestOwnerID ;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate.getTime() ;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate.getTime() ;
	}
	
	public void setContestDuraion(Integer contestDuraion) {
		this.contestDuraion = contestDuraion;
	}
	
	public Integer getContestDuraion() {
		return contestDuraion;
	}
	
	public Date getStartDate() {
		Date d = new Date(startDate) ;
		return d ;
	}
	
	public Date getEndDate() {
		Date d = new Date(endDate) ;
		return d; 
	}
	
	public String getContestAccessCode() {
		return ContestAccessCode;
	}

	public Long getContestID() {
		return ContestID;
	}

	/**
	 * @return contest name
	 */
	public String getContestName() {
		return ContestName;
	}

	public void setContestAccessCode(String contestAccessCode) {
		ContestAccessCode = contestAccessCode;
	}

	public void setContestID(Long contestID) {
		ContestID = contestID;
	}

	public void setContestName(String contestName) {
		ContestName = contestName;
	}

}
