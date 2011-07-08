package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ContestData implements Serializable {
	private Long contestID;
	private String contestName;
	private String contestAccessCode;
	private Date startTime;
	private Date endTime;
	private Integer contestDuraion;
	private Long contestOwnerID ;
	
	public Long getContestOwnerID() {
		return contestOwnerID;
	}

	public void setContestOwnerID(Long contestOwnerID) {
		this.contestOwnerID = contestOwnerID;
	}
	public ContestData( String contestName,
			String contestAccessCode, Date startTime, Date endTime){
			this.contestName = contestName;
			this.contestAccessCode = contestAccessCode;
			this.startTime = startTime ;
			this.endTime = endTime;
	}
	
	public ContestData(Long contestID, String contestName,
			String contestAccessCode, Date startTime, Date endTime,
			Integer contestDuration) {
		this.contestID = contestID;
		this.contestName = contestName;
		this.contestAccessCode = contestAccessCode;
		this.startTime = startTime;
		this.endTime = endTime;
		this.contestDuraion = contestDuration;
	}

	public ContestData(Long contestID, String contestName,
			String contestAccessCode) {
		this.contestID = contestID;
		this.contestName = contestName;
		this.contestAccessCode = contestAccessCode;
	}

	public ContestData() {

	}

	public Integer getContestDuraion() {
		return contestDuraion;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setContestDuraion(Integer contestDuraion) {
		this.contestDuraion = contestDuraion;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getContestID() {
		return contestID;
	}

	public String getContestAccessCode() {
		return contestAccessCode;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestAccessCode(String contestAccessCode) {
		this.contestAccessCode = contestAccessCode;
	}

	public void setContestID(Long contestID) {
		this.contestID = contestID;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}
}
