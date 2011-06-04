package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmissionServiceAsync {
	void submitCode(String problemCode, String code, String language,
	        AsyncCallback<Void> callback);

	void getLastProblemStatus(AsyncCallback<ProblemStatusData> callback);

	void saveSpojProblemtoDB(ProblemData problemData,
	        AsyncCallback<Void> callback);

	void getProblems(long start, AsyncCallback<ArrayList<ProblemData>> callback);

	void getProblem(String problemCode, AsyncCallback<ProblemData> callback);

}
