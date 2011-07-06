package com.OJToolkit.client.Services;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ContestData; 
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.SubmissionData;

@RemoteServiceRelativePath("contestadmin")
public interface ContestServices extends RemoteService{
	/**
	 * add new contest 
	 * @param contestName
	 * @param contestAccessCode
	 */
	public boolean addContest(String contestName,String contestAccessCode,Date startDate,Date endDate);
	public boolean changeAccessCode(String contestName ,String newAccessCode) ;
	public boolean changeContestName(String contestName, String newContestName);
	public ArrayList<ContestData> getContests();
	public boolean addUserToContest(String contestName, String contestAccessCode);
	public boolean addProblemToContest(String contestName, String prolemName , String OJ);
	public ArrayList<CoderData> getCodersForContest(String contestName) ;
	public ArrayList<ProblemData> getProblemForContest(String contestName) ;
	public ArrayList<SubmissionData> getContestSubmissions(String contestName) ;
	public ArrayList<ContestData> getContestForAdmin();
}
