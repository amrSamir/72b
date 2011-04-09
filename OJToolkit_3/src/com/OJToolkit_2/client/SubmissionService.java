package com.OJToolkit_2.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface SubmissionService extends RemoteService {
	public void submitCode(String prblmID, String code, String language);
	public DataProblemStatus getLastProblemStatus() throws Exception;

}
