package com.OJToolkit.client.Services;

import java.util.ArrayList;
import java.util.Date;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContestServicesAsync {

	void addContest(String contestName, String contestAccessCode,
			Date startDate, Date endDate, AsyncCallback<Boolean> callback);

	void changeAccessCode(String contestName, String newAccessCode,
			AsyncCallback<Boolean> callback);

	void changeContestName(String contestName, String newContestName,
			AsyncCallback<Boolean> callback);

	void getContests(AsyncCallback<ArrayList<ContestData>> callback);

	void addUserToContest(String contestName, String contestAccessCode,
			AsyncCallback<Boolean> asyncCallback);

	void addProblemToContest(String contestName, String prolemName, String OJ,
			AsyncCallback<Boolean> callback);

	void getCodersForContest(String contestName,
			AsyncCallback<ArrayList<CoderData>> callback);

	void getProblemForContest(String contestName,
			AsyncCallback<ArrayList<ProblemData>> callback);

	void getContestSubmissions(String contestName,
			AsyncCallback<ArrayList<SubmissionData>> callback);

	void getContestForAdmin(AsyncCallback<ArrayList<ContestData>> callback);

}
