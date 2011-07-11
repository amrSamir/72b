package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.OJToolkit.client.ValueObjects.ProblemTextData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.OJToolkit.server.engine.Judge;
import com.OJToolkit.server.engine.SPOJ;
import com.OJToolkit.server.engine.Submission;
import com.OJToolkit.server.engine.Timus;
import com.OJToolkit.server.engine.UVA;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.view.client.Range;

public class SubmissionServiceImpl extends RemoteServiceServlet implements
        SubmissionService {
	private static final Logger LOG = Logger
	        .getLogger(SubmissionServiceImpl.class.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	public void submitCode(boolean isAnonymousSubmission, String problemCode,
	        String ojType, String code, String language) throws Exception {
		Judge judge = null;
		String judgeUsername = "";
		String judgePassword = "";

		if (isAnonymousSubmission) {
			if (ojType.equals("SPOJ")) {
				System.out.println("Submit in SPOOOOJ");
				judgeUsername = "ojtest";
				judgePassword = "123456";
				judge = new SPOJ();
			} else if (ojType.equals("Timus")) {
				judgeUsername = "108015CD";
				judgePassword = "123456";
				judge = new Timus();
			} else if (ojType.equals("UVA")) {
				judgeUsername = "uvatest72";
				judgePassword = "123456";
				judge = new UVA();
			}

		} else {

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
				judge = new UVA();

			}
		}

		if (judge != null) {

			System.out.println("language value: " + language);
			System.out.println("Username: " + judgeUsername);
			System.out.println("Password" + judgePassword);
			System.out.println("Problem Code: " + problemCode);
			System.out.println("Code: " + code);
			judge.submitProblem(judgeUsername, judgePassword, problemCode,
			        language, code);
			System.out.println("Submitted");

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
	 * @see
	 * com.OJToolkit.client.Services.SubmissionService#getLastProblemStatus(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ProblemStatusData getLastProblemStatus(
	        boolean isAnonymousSubmission, String problemCode, String ojType,
	        String sourceCode, boolean isVisible) throws Exception {
		Judge judge = null;
		String judgeUsername = "";
		String judgePassword = "";
		if (isAnonymousSubmission) {
			if (ojType.equals("SPOJ")) {
				System.out.println("Submit in SPOOOOJ");
				judgeUsername = "ojtest";
				judgePassword = "123456";
				judge = new SPOJ();
			} else if (ojType.equals("Timus")) {
				judgeUsername = "108015CD";
				judgePassword = "123456";
				judge = new Timus();
			} else if (ojType.equals("UVA")) {
				judgeUsername = "uvatest72";
				judgePassword = "123456";
				judge = new UVA();
			}
		} else {

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
				judge = new UVA();
			}
		}
		System.out.println(judgeUsername);
		System.out.println(judgePassword);
		Submission s = judge.getLastSubmission(judgeUsername, judgePassword);
		System.out.println(s.getProblemId());
		System.out.println(s.getStatus());
		ProblemStatusData dpStatus = new ProblemStatusData(new Date(
		        TimeUtility.getTimeinLinux(s.getDate())), s.getProblemId(),
		        s.getStatus(), s.getRuntime(), s.getMemoryUsed());
		System.out.println("notime");
		addSubmissionResult(DataStoreHandler.getAllCoders().get(0)
		        .getUsername(), judgeUsername, problemCode, ojType,
		        s.getStatus(), s.getRuntime(), s.getMemoryUsed(), new Date(
		                TimeUtility.getTimeinLinux(s.getDate())), sourceCode,
		        isVisible);
		return dpStatus;

	}

	int counter = 0;

	/*
	 * (non-Javadoc)
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
	 * @see com.OJToolkit.client.Services.SubmissionService#getProblems(long)
	 */
	@Override
	public ArrayList<ProblemData> getProblems(Range range, String sortingQuery,
	        String searchQuery) {
		ArrayList<ProblemData> ret = new ArrayList<ProblemData>();
		ProblemData problemData = new ProblemData();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			LOG.info(sortingQuery);
			String select_query = "select from " + Problem.class.getName();
			Query query = pm.newQuery(select_query);
			if (!sortingQuery.equals(""))
				query.setOrdering(sortingQuery);
			query.setFilter(searchQuery);
			query.setRange(range.getStart(),
			        range.getStart() + range.getLength());
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

	public static ProblemData getProblem(Long problemID) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ProblemData problemData = new ProblemData();
		try {
			String select_query = "select from " + Problem.class.getName();
			Query query = pm.newQuery(select_query);
			// query.setFilter("probID == problemID");
			query.setFilter("probID == problemID");
			query.declareParameters("java.lang.Long problemID");
			List<Problem> execute = (List<Problem>) query.execute(problemID);
			List<Problem> problems = execute;
			problemData.setProblemCode(problems.get(0).getProblemCode());
			problemData.setProblemName(problems.get(0).getProblemName());
			problemData.setOjType(problems.get(0).getOjType());
			problemData.setUrl(problems.get(0).getUrl());

		} finally {

			pm.close();
		}
		return problemData;

	}

	/*
	 * (non-Javadoc)
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
			query.setFilter("problemCode == probCode && ojType == OJType");
			query.declareParameters("java.lang.String probCode, java.lang.String OJType");
			List<Problem> problems = (List<Problem>) query.execute(problemCode,
			        ojType);
			problemData.setProblemID(problems.get(0).getProbID());
			problemData.setProblemCode(problems.get(0).getProblemCode());
			problemData.setProblemName(problems.get(0).getProblemName());
			problemData.setOjType(problems.get(0).getOjType());
			problemData.setUrl(problems.get(0).getUrl());
		} finally {
			pm.close();
		}

		return problemData;
	}

	@SuppressWarnings("unchecked")
	static public ArrayList<SubmissionData> getSubmissionByDate(Long startTime,
	        Long endTime) {
		ArrayList<SubmissionData> userSubmitions = new ArrayList<SubmissionData>();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from "
			        + UserSubmission.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("date >= startTime && date <= endTime");
			query.declareParameters("java.lang.Long startTime,java.lang.Long endTime");
			List<UserSubmission> submissions = (List<UserSubmission>) query
			        .execute(startTime, endTime);
			for (UserSubmission us : submissions) {

				userSubmitions.add(new SubmissionData(us.getUsername(), us
				        .getJudgeUsername(), us.getProblemCode(), us
				        .getJudgeType(), us.getJudgeResult(), us.getTime(), us
				        .getMemory(), us.getDate().toString()));
			}
			// userSubmitions.addAll(submissions);
		} finally {
			pm.close();
		}
		return userSubmitions;
	}

	public void addSubmissionResult(String username, String judgeUsername,
	        String problemCode, String judgeType, String judgeResult,
	        String time, String memory, Date date, String sourceCode,
	        boolean isVisible) {

		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {

			String select_query = "select from "
			        + UserSubmission.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("judgeUsername  == jUsername && date == jDate");
			query.declareParameters("java.lang.String jUsername,java.lang.Long jDate");
			List<UserSubmission> submissions = (List<UserSubmission>) query
			        .execute(judgeUsername, date.getTime());
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

				String select_query2 = "select from "
				        + UserSubmission.class.getName();
				Query query2 = pm.newQuery(select_query2);
				query2.setFilter("judgeUsername  == jUsername && date == jDate");
				query2.declareParameters("java.lang.String jUsername,java.lang.Long jDate");
				List<UserSubmission> submissions2 = (List<UserSubmission>) query2
				        .execute(judgeUsername, date.getTime());
				SourceCode sc = new SourceCode();
				sc.setSourceCode(sourceCode);
				sc.setVisible(isVisible);
				sc.setSubmissionID(submissions2.get(0).getSubmissionID());
				pm.makePersistent(sc);
			}
		} finally {
			pm.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.Services.SubmissionService#getSubmissions()
	 */
	@Override
	public ArrayList<SubmissionData> getSubmissions(Range range,
	        String sortingQuery) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ArrayList<SubmissionData> ret = new ArrayList<SubmissionData>();
		SubmissionData submissionData;
		try {
			String select_query = "select from "
			        + UserSubmission.class.getName();
			Query query = pm.newQuery(select_query);

			if (!sortingQuery.equals("")) {
				query.setOrdering(sortingQuery);
			} else
				query.setOrdering("date desc");
			query.setRange(range.getStart(),
			        range.getStart() + range.getLength());
			List<UserSubmission> submissions = (List<UserSubmission>) query
			        .execute();

			for (UserSubmission userSubmission : submissions) {
				submissionData = new SubmissionData();
				submissionData
				        .setSubmissionID(userSubmission.getSubmissionID());
				submissionData.setUsername(userSubmission.getUsername());
				submissionData.setProblemCode(userSubmission.getProblemCode());
				submissionData.setJudgeType(userSubmission.getJudgeType());
				submissionData.setDate(userSubmission.getDate().toString());
				submissionData.setJudgeResult(userSubmission.getJudgeResult());
				submissionData.setMemory(userSubmission.getMemory());
				submissionData.setTime(userSubmission.getTime());
				select_query = "select from " + Problem.class.getName();
				query = pm.newQuery(select_query);
				query.setFilter("problemCode == probCode && ojType == OJType");
				query.declareParameters("java.lang.String probCode, java.lang.String OJType");
				List<Problem> problems = (List<Problem>) query.execute(
				        userSubmission.getProblemCode(),
				        userSubmission.getJudgeType());
				submissionData
				        .setProblemTitle(problems.get(0).getProblemName());
				ret.add(submissionData);
			}

		} finally {
			pm.close();
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.Services.SubmissionService#addProblemTextToDB(com
	 * .OJToolkit.client.ValueObjects.ProblemTextData)
	 */
	@Override
	public void addProblemTextToDB(ProblemTextData problemTextData) {

		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {

			/*
			 * Query qq = pm.newQuery(ProblemText.class);
			 * List<Problem> ae = (List<Problem>) qq.execute();
			 * pm.deletePersistentAll(ae);
			 */

			pm.makePersistent(new ProblemText(problemTextData.getJudgeType(),
			        problemTextData.getProblemCode(), problemTextData
			                .getIsDividable(), problemTextData.getFullText(),
			        problemTextData.getProblemStatement(), problemTextData
			                .getInputConstraints(), problemTextData
			                .getOutputConstraints(), problemTextData
			                .getIOTestCases()));

			counter++;
			System.out.println(counter);
		} finally {
			pm.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.Services.SubmissionService#getProblemText(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public ProblemTextData getProblemText(String problemCode, String judgeType) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ProblemTextData ret = new ProblemTextData();
		try {

			String select_query = "select from " + ProblemText.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("judgeType==jType && problemCode==pCode");
			query.declareParameters("java.lang.String jType,java.lang.String pCode");
			List<ProblemText> problemTexts = (List<ProblemText>) query.execute(
			        judgeType, problemCode);
			System.out.println(problemTexts.size());
			ret.setProblemCode(problemTexts.get(0).getProblemCode());
			ret.setJudgeType(problemTexts.get(0).getJudgeType());
			ret.setIsDividable(problemTexts.get(0).getIsDividable());
			ret.setFullText(problemTexts.get(0).getFullText().getValue());
			ret.setProblemStatement(problemTexts.get(0).getProblemStatement()
			        .getValue());
			ret.setInputConstraints(problemTexts.get(0).getInputConstraints()
			        .getValue());
			ret.setOutputConstraints(problemTexts.get(0).getOutputConstraints()
			        .getValue());
			ret.setIOTestCases(problemTexts.get(0).getIOTestCases().getValue());
		} finally {
			pm.close();
		}
		return ret;
	}

	

}
