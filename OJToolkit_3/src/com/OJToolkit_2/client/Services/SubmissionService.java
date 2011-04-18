package com.OJToolkit_2.client.Services;

import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.OJToolkit_2.client.ValueObjects.ProblemStatusData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("submission")
public interface SubmissionService extends RemoteService {
	public void submitCode(String prblmID, String code, String language);
	public ProblemStatusData getLastProblemStatus() throws Exception;
	public  void saveSpojProblemtoDB(ProblemData problemData); 

}
