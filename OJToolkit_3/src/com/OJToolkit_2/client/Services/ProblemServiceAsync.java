package com.OJToolkit_2.client.Services;

import java.util.ArrayList;

import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProblemServiceAsync {
	void getAllProblems(AsyncCallback<ArrayList<ProblemData>> callback);
	void getPorblem(String probID, AsyncCallback<ProblemData> callback);
	void addProblem(ProblemData probData, AsyncCallback<Void> callback);
}
