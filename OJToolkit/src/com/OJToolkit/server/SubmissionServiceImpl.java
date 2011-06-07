package com.OJToolkit.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.OJToolkit.server.engine.Judge;
import com.OJToolkit.server.engine.SPOJ;
import com.OJToolkit.server.engine.Submission;
import com.OJToolkit.server.engine.Timus;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SubmissionServiceImpl extends RemoteServiceServlet implements
        SubmissionService {
	// long DBStartIndex = 14680;
	// long DBStartIndex =73670;
	private static final Logger LOG = Logger
	        .getLogger(SubmissionServiceImpl.class.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	public void submitCode(String problemCode, String code, String language) {
		// TODO Auto-generated method stub
		Judge judge = null;
		ProblemData problem = getProblem(problemCode);
		String judgeUsername = "";
		String judgePassword = "";
		if (problem.getOjType().equals("SPOJ")) {
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
			        .getSPOJUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
			        .getSPOJPassword();
			judge = new SPOJ();
		} else if (problem.getOjType().equals("Timus")) {
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
			        .getTimusUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
			        .getTimusPassword();
			judge = new Timus();
		}
		if (judge != null) {
			try {
				System.out.println("language value: " + language);
				System.out.println("Username: " + judgeUsername);
				System.out.println("Password" + judgePassword);
				System.out.println("Problem Code: " + problemCode);
				System.out.println("Code: " + code);
				judge.submitProblem(judgeUsername, judgePassword,
				        problemCode.replaceAll(" ", ""), language, code);
				System.out.println("Submitted");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public ProblemStatusData getLastProblemStatus(String problemCode)
	        throws Exception {
		Judge judge = null;
		ProblemData problem = getProblem(problemCode);
		String judgeUsername = "";
		String judgePassword = "";
		if (problem.getOjType().equals("SPOJ")) {
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
			        .getSPOJUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
			        .getSPOJPassword();
			judge = new SPOJ();
		} else if (problem.getOjType().equals("Timus")) {
			System.out.println("submission result for timus");
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
			        .getTimusUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
			        .getTimusPassword();
			judge = new Timus();
		}
		
		Submission s = judge.getLastSubmission(judgeUsername, judgePassword);

		ProblemStatusData dpStatus = new ProblemStatusData(s.getDate(),
		        s.getProblemId(), s.getStatus(), s.getRuntime(),
		        s.getMemoryUsed());
		return dpStatus;
		// TODO Auto-generated method stub

	}

	int counter = 0;

	@Override
	public void saveSpojProblemtoDB(ProblemData problemData) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {

			// Query qq = pm.newQuery(Problem.class); List<Problem> ae =
			// (List<Problem>)qq.execute(); pm.deletePersistentAll(ae);

			Problem problem = new Problem(problemData.getProblemCode(),
			        problemData.getProblemName(), problemData.getUrl(),
			        problemData.getOjType());

			pm.makePersistent(problem);

			counter++;
			System.out.println(counter);

		} finally {
			pm.close();
		}

		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<ProblemData> getProblems(long start) {
		// System.out.println("startttttttt getproblems " + start);
		// start = start + DBStartIndex;
		ArrayList<ProblemData> ret = new ArrayList<ProblemData>();
		ProblemData problemData = new ProblemData();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from " + Problem.class.getName();
			Query queryToGetStartIndex = pm.newQuery(select_query);
			queryToGetStartIndex.setRange(0, 1);
			List<Problem> tempProblems = (List<Problem>) queryToGetStartIndex
			        .execute();
			long startIndex = tempProblems.get(0).getProbID();
			start += startIndex;
			// System.out.println("new index " + start);
			Query query = pm.newQuery(select_query);
			// query.setFilter("probID == problemID");
			query.setFilter("probID >= problemID");
			query.setRange(0, 50);
			query.declareParameters("java.lang.String problemID");

			List<Problem> problems = (List<Problem>) query.execute(start);

			for (Problem problem : problems) {
				problemData.setProblemCode(problem.getProblemCode());
				problemData.setProblemName(problem.getProblemName());
				problemData.setOjType(problem.getOjType());
				problemData.setUrl(problem.getUrl());
				ret.add(new ProblemData(problemData.getProblemCode(),
				        problemData.getProblemName(), problemData.getUrl(),
				        problemData.getOjType()));
			}

		} finally {
			// TODO: handle exception
			pm.close();
		}
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.Services.SubmissionService#getProblem(java.lang.
	 * String)
	 */
	@Override
	public ProblemData getProblem(String problemCode) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ProblemData problemData = new ProblemData();
		try {
			String select_query = "select from " + Problem.class.getName();
			Query query = pm.newQuery(select_query);
			// query.setFilter("probID == problemID");
			query.setFilter("problemCode == probCode");
			query.declareParameters("java.lang.String probCode");
			List<Problem> problems = (List<Problem>) query.execute(problemCode);
			problemData.setProblemCode(problems.get(0).getProblemCode());
			problemData.setProblemName(problems.get(0).getProblemName());
			problemData.setOjType(problems.get(0).getOjType());
			problemData.setUrl(problems.get(0).getUrl());

		} finally {
			// TODO: handle exception
			pm.close();
		}

		// TODO Auto-generated method stub
		return problemData;
	}

}
