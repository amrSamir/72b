package com.OJToolkit_2.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmissionServiceAsync {



	void submitCode(String prblmID, String code, String language,
			AsyncCallback<Void> callback);

	void getLastProblemStatus(AsyncCallback<DataProblemStatus> callback);

}
