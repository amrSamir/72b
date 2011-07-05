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
import com.google.gwt.view.client.Range;

public class SubmissionServiceImpl extends RemoteServiceServlet implements
		SubmissionService {
	// long DBStartIndex = 14680;
	// long DBStartIndex =73670;
	private static final Logger LOG = Logger
			.getLogger(SubmissionServiceImpl.class.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	public void submitCode(String problemCode, String ojType, String code,
			String language) {
		Judge judge = null;
		String judgeUsername = "";
		String judgePassword = "";
		if (ojType.equals("SPOJ")) {
			System.out.println("Submit in SPOOOOJ");
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
					.getSPOJUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
					.getSPOJPassword();
			judge = new SPOJ();
		} else if (ojType.equals("Timus")) {
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
					.getTimusUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
					.getTimusPassword();
			judge = new Timus();
		} else if (ojType.equals("UVA")) {
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
					.getUVAUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
					.getUVAPassword();
			// judge = new UVA();
		}

		if (judge != null) {
			try {
				System.out.println("language value: " + language);
				System.out.println("Username: " + judgeUsername);
				System.out.println("Password" + judgePassword);
				System.out.println("Problem Code: " + problemCode);
				System.out.println("Code: " + code);
				judge.submitProblem(judgeUsername, judgePassword, problemCode,
						language, code);
				System.out.println("Submitted");
			} catch (IOException e) {
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.Services.SubmissionService#getLastProblemStatus(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ProblemStatusData getLastProblemStatus(String problemCode,
			String ojType) throws Exception {
		Judge judge = null;
		String judgeUsername = "";
		String judgePassword = "";
		if (ojType.equals("SPOJ")) {
			System.out.println("submission result for SPOJ");
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
					.getSPOJUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
					.getSPOJPassword();
			judge = new SPOJ();
		} else if (ojType.equals("Timus")) {
			System.out.println("submission result for timus");
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
					.getTimusUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
					.getTimusPassword();
			judge = new Timus();
		} else if (ojType.equals("UVA")) {
			System.out.println("submission result for UVA");
			judgeUsername = DataStoreHandler.getAllCoders().get(0)
					.getUVAUsername();
			judgePassword = DataStoreHandler.getAllCoders().get(0)
					.getUVAPassword();
			// judge = new UVA();
		}

		Submission s = judge.getLastSubmission(judgeUsername, judgePassword);

		ProblemStatusData dpStatus = new ProblemStatusData(s.getDate(),
				s.getProblemId(), s.getStatus(), s.getRuntime(),
				s.getMemoryUsed());
		addSubmissionResult(DataStoreHandler.getAllCoders().get(0)
		        .getUsername(), judgeUsername, problemCode, ojType,
		        s.getStatus(), s.getRuntime(), s.getMemoryUsed(), s.getDate());
		return dpStatus;

	}

	int counter = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.Services.SubmissionService#saveSpojProblemtoDB(com
	 * .OJToolkit.client.ValueObjects.ProblemData)
	 */
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.OJToolkit.client.Services.SubmissionService#getProblems(long)
	 */
	@Override
	public ArrayList<ProblemData> getProblems(Range range, String sortingQuery,
			String searchQuery) {
		ArrayList<ProblemData> ret = new ArrayList<ProblemData>();
		ProblemData problemData = new ProblemData();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from " + Problem.class.getName();
			Query queryToGetStartIndex = pm.newQuery(select_query);
			queryToGetStartIndex.setRange(0, 1);
			List<Problem> tempProblems = (List<Problem>) queryToGetStartIndex
					.execute();
			// long startIndex = tempProblems.get(0).getProbID();
			// long start = startIndex+range.getStart();
			// System.out.println("new index " + start);
			Query query = pm.newQuery(select_query);
			// query.setFilter("probID == problemID");
			// query.setFilter("probID >= problemID");
			if (!sortingQuery.equals(""))
				query.setOrdering(sortingQuery);
			query.setFilter(searchQuery);
			query.setRange(range.getStart(),
					range.getStart() + range.getLength());
			// query.declareParameters("java.lang.String problemID");
			List<Problem> problems = (List<Problem>) query.execute();

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
			pm.close();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.Services.SubmissionService#getProblem(java.lang.
	 * String)
	 */
	@Override
	public ProblemData getProblem(String problemCode, String ojType) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ProblemData problemData = new ProblemData();
		try {
			String select_query = "select from " + Problem.class.getName();
			Query query = pm.newQuery(select_query);
			// query.setFilter("probID == problemID");
			query.setFilter("problemCode == probCode && ojType == OJType");
			query.declareParameters("java.lang.String probCode, java.lang.String OJType");
			List<Problem> problems = (List<Problem>) query.execute(problemCode,
					ojType);
			problemData.setProblemCode(problems.get(0).getProblemCode());
			problemData.setProblemName(problems.get(0).getProblemName());
			problemData.setOjType(problems.get(0).getOjType());
			problemData.setUrl(problems.get(0).getUrl());

		} finally {

			pm.close();
		}

		return problemData;
	}

	public void addSubmissionResult(String username, String judgeUsername,
	        String problemCode, String judgeType, String judgeResult,
	        String time, String memory, String date) {

		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {

			String select_query = "select from "
			        + UserSubmission.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("judgeUsername  == jUsername && date == jDate");
			query.declareParameters("java.lang.String jUsername,java.lang.String jDate");
			List<UserSubmission> submissions = (List<UserSubmission>) query
			        .execute(judgeUsername, date);
			System.out.println("AE-SubmServImp-" + submissions.size());
			if (submissions.size() > 0) {
				submissions.get(0).setJudgeResult(judgeResult);
				submissions.get(0).setJudgeType(judgeType);
				submissions.get(0).setMemory(memory);
				submissions.get(0).setProblemCode(problemCode);
				submissions.get(0).setTime(time);
				submissions.get(0).setUsername(username);
				pm.makePersistent(submissions.get(0));
			} else {
				UserSubmission submission = new UserSubmission();
				submission.setDate(date);
				submission.setJudgeResult(judgeResult);
				submission.setJudgeType(judgeType);
				submission.setJudgeUsername(judgeUsername);
				submission.setMemory(memory);
				submission.setProblemCode(problemCode);
				submission.setTime(time);
				submission.setUsername(username);
				pm.makePersistent(submission);
			}
		} finally {
			pm.close();
		}

	}

}
