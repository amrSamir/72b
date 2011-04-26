package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



public class SubmissionServiceImpl extends RemoteServiceServlet implements
		SubmissionService {
	//long DBStartIndex = 14680;
	//long DBStartIndex =73670;
	private static final Logger LOG = Logger
			.getLogger(SubmissionServiceImpl.class.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	public void submitCode(String prblmID, String code, String language) {
		// TODO Auto-generated method stub
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("file", code);
		hashMap.put("lang", language);
		hashMap.put("problemcode", prblmID);
		String spojUsername = DataStoreHandler.getAllCoders().get(0)
				.getSPOJUsername();
		String spojPassword = DataStoreHandler.getAllCoders().get(0)
				.getSPOJPassword();
		hashMap.put("login_user", spojUsername);
		hashMap.put("password", spojPassword);
		try {
			Engine.spojSubmit(hashMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// * KEY VALUE
	// * -------- DATE --> Date and time for the submitted problem.
	// * -------- PROBLEM --> Link of the problem submitted.
	// * -------- RESULT --> The response of the judge.
	// * -------- TIME --> The running time of the solution.
	// * -------- MEM --> The memory used by the solution.
	// *
	//
	@Override
	public ProblemStatusData getLastProblemStatus() throws Exception {
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("login_user", DataStoreHandler.getAllCoders().get(0)
				.getSPOJUsername());
		HashMap<String, String> output = Engine
				.getLastProblemStatus_Spoj(input);
		ProblemStatusData dpStatus = new ProblemStatusData(output.get("DATE"),
				output.get("PROBLEM"), output.get("RESULT"),
				output.get("TIME"), output.get("MEM"));
		return dpStatus;
		// TODO Auto-generated method stub

	}

	public void saveSpojProblemtoDB(ProblemData problemData) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			/*
			 * Query qq = pm.newQuery(Problem.class); List<Problem> ae =
			 * (List<Problem>)qq.execute(); pm.deletePersistentAll(ae);
			 */
			Problem problem = new Problem(problemData.getUrl(),
					problemData.getType(), problemData.getProblemCode(),
					problemData.getProblemName());

			pm.makePersistent(problem);

		} finally {
			pm.close();
		}

		// TODO Auto-generated method stub

	}


	public ArrayList<ProblemData> getProblems(long start) {
		
	//	start = start + DBStartIndex;
		ArrayList<ProblemData> ret = new ArrayList<ProblemData>();
		ProblemData problemData = new ProblemData();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from " + Problem.class.getName();
			Query queryToGetStartIndex = pm.newQuery(select_query);
			queryToGetStartIndex.setRange(0, 1);
			List<Problem> tempProblems = (List<Problem>) queryToGetStartIndex.execute();
			long startIndex = tempProblems.get(0).getProbID();
			start += startIndex;
			Query query = pm.newQuery(select_query);
			// query.setFilter("probID == problemID");
			query.setFilter("probID >= problemID");
			query.setRange(0, 50);
			query.declareParameters("java.lang.String problemID");

			List<Problem> problems = (List<Problem>) query.execute(start);

			for (Problem problem : problems) {
				problemData.setProblemCode(problem.getProblemCode());
				problemData.setProblemName(problem.getProblemName());
				problemData.setType(problem.getType());
				problemData.setUrl(problem.getUrl());
				ret.add(new ProblemData(problemData.getUrl(), problemData
						.getType(), problemData.getProblemCode(), problemData
						.getProblemName()));
			}

		} finally {
			// TODO: handle exception
			pm.close();
		}
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		return ret;
	}

}
