package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.Range;

public interface SubmissionServiceAsync {
	void submitCode(boolean isAnonymousSubmission, String problemCode,
	        String ojType, String code, String language,
	        AsyncCallback<Void> callback);

	void getLastProblemStatus(boolean isAnonymousSubmission,
	        String problemCode, String ojType,
	        AsyncCallback<ProblemStatusData> callback);

	/**
	 * @see com.OJToolkit.client.Services.SubmissionService#saveSpojProblemtoDB(ProblemData)
	 */
	void saveSpojProblemtoDB(ProblemData problemData,
	        AsyncCallback<Void> callback);

	void getProblems(Range range, String sortingQuery, String searchQuery,
	        AsyncCallback<ArrayList<ProblemData>> callback);

	/**
	 * @see com.OJToolkit.client.Services.SubmissionService#getProblem(String,
	 *      String)
	 */
	void getProblem(String problemCode, String ojType,
	        AsyncCallback<ProblemData> callback);

	void getSubmissions(Range range, String sortingQuery,
            AsyncCallback<ArrayList<SubmissionData>> callback);

}
