package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Services.ContestServices;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ContestServicesImpl extends RemoteServiceServlet implements
		ContestServices {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ContestServices.class
			.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	public boolean addContest(String contestName, String contestAccessCode,
			Date startDate, Date endDate) {
		Contest con = getContest(contestName);
		if (con == null) {
			PersistenceManager pm = DataStoreHandler.getPersistenceManager();
			Long UserID = DataStoreHandler.getAllCoders().get(0).getUserID();
			try {
				Contest newContest = new Contest(contestName,
						contestAccessCode, UserID);
				newContest.setStartDate(startDate);
				newContest.setEndDate(endDate);
				pm.makePersistent(newContest);
			} finally {
				pm.close();
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean addUserToContest(String contestName , String contestAccessCode) {
		Contest con = getContest(contestName.substring(1));
		if (con == null)
			return false;
		if (contestName.charAt(0) == '-' && !con.getContestAccessCode().equals(contestAccessCode))
			return false;
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		Long UserID = DataStoreHandler.getAllCoders().get(0).getUserID();
		if (isUserAlreadyExsist(con.getContestID(), UserID))
			return false;
		try {
			ContestUsers contestUser = new ContestUsers(con.getContestID(),
					UserID);
			pm.makePersistent(contestUser);
		} finally {
			pm.close();
		}
		return true;
	}

	@Override
	public boolean addProblemToContest(String contestName, String problemCode,
			String problemOJ) {
		Contest con = getContest(contestName);
		if (con == null)
			return false;
		SubmissionServiceImpl services = new SubmissionServiceImpl();
		ProblemData pd = services.getProblem(problemCode, problemOJ);
		Long problemID = pd.getProblemID();
		if (isProblemAlreadyExsist(con.getContestID(), problemID))
			return false;
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			ContestProblems contestProblem = new ContestProblems(
					con.getContestID(), problemID);
			pm.makePersistent(contestProblem);
		} finally {
			pm.close();
		}

		return true;
	}

	@Override
	public boolean changeAccessCode(String contestName, String newAccessCode) {
		System.out.println("Magdi-contestService-change accessCode !!!!!!");
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		Contest con = null;
		try {
			String select_query = "select from " + Contest.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("ContestName  == contestName");
			query.declareParameters("java.lang.String contestName");
			@SuppressWarnings("unchecked")
			List<Contest> co = (List<Contest>) query.execute(contestName);
			co.get(0).setContestAccessCode(newAccessCode);
			pm.makePersistent(co.get(0));
		} finally {
			pm.close();
		}
		return true;
	}

	@Override
	public boolean changeContestName(String contestName, String newContestName) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from " + Contest.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("ContestName  == contestName");
			query.declareParameters("java.lang.String contestName");
			@SuppressWarnings("unchecked")
			List<Contest> co = (List<Contest>) query.execute(contestName);
			co.get(0).setContestName(newContestName);
			pm.makePersistent(co.get(0));
		} finally {
			pm.close();
		}
		return true;
	}

	@Override
	public boolean EditContest(ContestData contest) {

		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from " + Contest.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("ContestName  == contestName");
			query.declareParameters("java.lang.String contestName");
			@SuppressWarnings("unchecked")
			List<Contest> co = (List<Contest>) query.execute(contest
					.getContestName());
			co.get(0).setContestName(contest.getContestName());
			co.get(0).setContestAccessCode(contest.getContestAccessCode());
			co.get(0).setStartDate(contest.getStartTime());
			co.get(0).setEndDate(contest.getEndTime());
			pm.makePersistent(co.get(0));
		} finally {
			pm.close();
		}
		return true;
	}

	@Override
	public ArrayList<ContestData> getContestForAdmin() {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		Long UserID = DataStoreHandler.getAllCoders().get(0).getUserID();
		ArrayList<ContestData> con = new ArrayList<ContestData>();
		try {
			String select_query = "select from " + Contest.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("contestOwnerID  == UserID");
			query.declareParameters("java.lang.Long UserID");
			List<Contest> allcontest = (List<Contest>) query.execute(UserID);
			for (Contest c : allcontest) {
				ContestData curC = new ContestData(c.getContestID(),
						c.getContestName(), c.getContestAccessCode(),c.getStartDate(),c.getEndDate(),0);
				con.add(curC);
			}
		} finally {
			pm.close();
		}
		return con;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<ContestData> getContests() {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ArrayList<ContestData> con = new ArrayList<ContestData>();
		try {
			String select_query = "select from " + Contest.class.getName();
			Query query = pm.newQuery(select_query);
			List<Contest> allcontest = (List<Contest>) query.execute();
			System.out.println("Magdi-server-contest- Number of contests is"
					+ allcontest.size());
			for (Contest c : allcontest) {
				ContestData curC = new ContestData(c.getContestID(),
						c.getContestName(), c.getContestAccessCode());
				curC.setStartTime(c.getStartDate());
				curC.setEndTime(c.getEndDate());
				con.add(curC);
			}
		} finally {
			pm.close();
		}
		return con;
	}

	public Contest getContest(String contestName) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		Long UserID = DataStoreHandler.getAllCoders().get(0).getUserID();
		Contest con = null;
		try {
			String select_query = "select from " + Contest.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("ContestName  == contestName");
			query.declareParameters("java.lang.String contestName");
			@SuppressWarnings("unchecked")
			List<Contest> co = (List<Contest>) query.execute(contestName);
			for (Contest c : co) {
				con = new Contest(c.getContestName(), c.getContestAccessCode(),
						UserID);
				con.setStartDate(c.getStartDate());
				con.setEndDate(c.getEndDate());
				con.setContestID(c.getContestID());
			}
		} finally {
			pm.close();
		}
		return con;
	}

	@Override
	public ArrayList<CoderData> getCodersForContest(String contestName) {
		Contest con = getContest(contestName);
		ArrayList<CoderData> coders = new ArrayList<CoderData>();
		;
		if (con == null)
			return coders;
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		Long contestid = con.getContestID();
		try {
			String select_query = "select from " + ContestUsers.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("ContestID  == contestid");
			query.declareParameters("java.lang.Long contestid");
			List<ContestUsers> cod = (List<ContestUsers>) query
					.execute(contestid);
			for (ContestUsers contestCoder : cod) {
				coders.add(CoderServiceImpl.getCoder(contestCoder.getuserID()));
			}
		} finally {
			pm.close();
		}
		return coders;
	}

	@Override
	public ArrayList<ProblemData> getProblemForContest(String contestName) {
		Contest con = getContest(contestName);
		ArrayList<ProblemData> problems = new ArrayList<ProblemData>();
		if (con == null)
			return problems;
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		Long contestid = con.getContestID();
		try {
			String select_query = "select from "
					+ ContestProblems.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("contestID  == contestid");
			query.declareParameters("java.lang.Long contestid");
			List<ContestProblems> pod = (List<ContestProblems>) query
					.execute(contestid);
			for (ContestProblems problem : pod) {
				problems.add(SubmissionServiceImpl.getProblem(problem
						.getProblemID()));
			}
		} finally {
			pm.close();
		}
		return problems;
	}

	@Override
	public ArrayList<SubmissionData> getContestSubmissions(String contestName) {
		ArrayList<SubmissionData> submissions = new ArrayList<SubmissionData>();
		Contest con = getContest(contestName);
		ArrayList<SubmissionData> subInContestTime = SubmissionServiceImpl
				.getSubmissionByDate(con.getStartDate().getTime(), con
						.getEndDate().getTime());
		ArrayList<CoderData> coders = getCodersForContest(contestName);
		ArrayList<ProblemData> problems = getProblemForContest(contestName);
		for (SubmissionData sub : subInContestTime) {
			for (CoderData coder : coders) {
				for (ProblemData problem : problems) {
					if (sub.getUsername().equals(coder.getUsername())
							&& sub.getProblemCode().equals(
									problem.getProblemCode())) {
						submissions.add(sub);
					}
				}
			}
		}
		return submissions;
	}

	@Override
	public void deleteProblemFromContest(String contestName,
			String problemCode, String problemOJ) {
		Contest con = getContest(contestName);
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		Long contestid = con.getContestID();
		SubmissionServiceImpl services = new SubmissionServiceImpl();
		ProblemData pd = services.getProblem(problemCode, problemOJ);
		Long problemId = pd.getProblemID();
		try {
			String select_query = "select from "
					+ ContestProblems.class.getName();
			Query query = pm.newQuery(select_query);

			query.setFilter("contestID  == contestid && problemID == problemId");
			query.declareParameters("java.lang.Long contestid,java.lang.Long problemId");
			List<ContestProblems> pod = (List<ContestProblems>) query.execute(
					contestid, problemId);
			pm.deletePersistentAll(pod);
		} finally {
			pm.close();
		}
	}

	private boolean isProblemAlreadyExsist(Long contestid, Long problemId) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from "
					+ ContestProblems.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("contestID  == contestid && problemID == problemId");
			query.declareParameters("java.lang.Long contestid,java.lang.Long problemId");
			List<ContestProblems> pod = (List<ContestProblems>) query.execute(
					contestid, problemId);
			if (pod.size() > 0)
				return true;
		} finally {
			pm.close();
		}
		return false;
	}

	private boolean isUserAlreadyExsist(Long contestid, Long userID) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from " + ContestUsers.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("ContestID  == contestid && UserID == userID");
			query.declareParameters("java.lang.Long contestid,java.lang.Long userID");
			List<ContestProblems> pod = (List<ContestProblems>) query.execute(
					contestid, userID);
			if (pod.size() > 0)
				return true;
		} finally {
			pm.close();
		}
		return false;
	}
}
