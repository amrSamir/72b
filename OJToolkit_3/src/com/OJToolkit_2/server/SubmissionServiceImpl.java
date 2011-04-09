package com.OJToolkit_2.server;

import java.util.HashMap;

import com.OJToolkit_2.client.DataProblemStatus;
import com.OJToolkit_2.client.SubmissionService;

public class SubmissionServiceImpl implements SubmissionService {

	@Override
	
	public void submitCode(String prblmID, String code, String language) {
		// TODO Auto-generated method stub
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("file", code);
		hashMap.put("lang", language);
		hashMap.put("problemcode", prblmID);
		String spojUsername = DataStoreHandler.getAllCoders().get(0).getSPOJUsername();
		String spojPassword = DataStoreHandler.getAllCoders().get(0).getSPOJPassword();
		hashMap.put("login_user", spojUsername);
		hashMap.put("password", spojPassword);
		try {
			Engine.spojSubmit(hashMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

//	 * 				KEY			VALUE
//	 * --------	DATE		-->	Date and time for the submitted problem.
//	 * --------	PROBLEM		-->	Link of the problem submitted.
//	 * --------	RESULT		-->	The response of the judge.
//	 * --------	TIME		-->	The running time of the solution.
//	 * --------	MEM			-->	The memory used by the solution.
//	 * 
//	 
	@Override
	public DataProblemStatus getLastProblemStatus() throws Exception {
		HashMap<String,String> input = new HashMap<String, String>();
		input.put("login_user", DataStoreHandler.getAllCoders().get(0).getSPOJUsername());
		HashMap<String, String> output = Engine.getLastProblemStatus_Spoj(input);
		DataProblemStatus dpStatus = new DataProblemStatus(output.get("DATE"), output.get("PROBLEM"), output.get("RESULT"), output.get("TIME"), output.get("MEM"));
		return dpStatus;
		// TODO Auto-generated method stub
		
	}
	
	



}
