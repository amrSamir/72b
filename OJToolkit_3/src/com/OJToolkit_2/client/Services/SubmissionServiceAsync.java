package com.OJToolkit_2.client.Services;

import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.OJToolkit_2.client.ValueObjects.ProblemStatusData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmissionServiceAsync {



	void submitCode(String prblmID, String code, String language,
			AsyncCallback<Void> callback);

	void getLastProblemStatus(AsyncCallback<ProblemStatusData> callback);

	void saveSpojProblemtoDB(ProblemData problemData,
			AsyncCallback<Void> callback);

}
