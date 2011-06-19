package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.Range;

public interface SubmissionServiceAsync {
	/**
	 * @see com.OJToolkit.client.Services.SubmissionService#submitCode(String, String, String, String)
	 */
	void submitCode(String problemCode, String ojType, String code,
            String language, AsyncCallback<Void> callback);
	/**
	 * @see com.OJToolkit.client.Services.SubmissionService#getLastProblemStatus(String, String)
	 */
	void getLastProblemStatus(String problemCode, String ojType,
            AsyncCallback<ProblemStatusData> callback);
	/**
	 * @see com.OJToolkit.client.Services.SubmissionService#saveSpojProblemtoDB(ProblemData)
	 */
	void saveSpojProblemtoDB(ProblemData problemData,
			AsyncCallback<Void> callback);

	/**
	 * @see com.OJToolkit.client.Services.SubmissionService#getProblems(long)
	 */
	void getProblems(long start, AsyncCallback<ArrayList<ProblemData>> callback);

	/**
	 * @see com.OJToolkit.client.Services.SubmissionService#getProblem(String, String)
	 */
	void getProblem(String problemCode, String ojType,
            AsyncCallback<ProblemData> callback);

}
