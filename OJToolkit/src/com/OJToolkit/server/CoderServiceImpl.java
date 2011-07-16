package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Exceptions.NotLoggedInException;
import com.OJToolkit.client.Services.CoderService;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.CoderProfileData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.OJToolkit.server.engine.Judge;
import com.OJToolkit.server.engine.LiveArchive;
import com.OJToolkit.server.engine.SPOJ;
import com.OJToolkit.server.engine.Timus;
import com.OJToolkit.server.engine.UVA;
import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.view.client.Range;

@SuppressWarnings("serial")
public class CoderServiceImpl extends RemoteServiceServlet implements
		CoderService {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(CoderServiceImpl.class
			.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<CoderData> viewCoders(Range range, String sortingQuery)
			throws NotLoggedInException {
		// DataStoreHandler.checkLoggedIn();
		// LOG.log(Level.SEVERE, "SPOJ_Username2");
		// DataStoreHandler dsh = new DataStoreHandler();
		// LOG.log(Level.SEVERE, DataStoreHandler.getAllCoders().get(0)
		// .getSPOJUsername());

		// List<Coder> coders2 = DataStoreHandler.getAllCoders();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ArrayList<CoderData> coders = new ArrayList<CoderData>();
		try {
			Query q = pm.newQuery(Coder.class);
			if (!sortingQuery.equals(""))
				q.setOrdering(sortingQuery);
			q.setRange(range.getStart(), range.getStart() + range.getLength());
			List<Coder> codersDB = (List<Coder>) q.execute();
			for (Coder coder : codersDB) {
				coders.add(new CoderData(coder.getUserID(),
						coder.getUsername(), coder.getEmail(), coder
								.getSPOJUsername(), coder.getSPOJPassword()));
			}

		} finally {
			pm.close();
		}

		return coders;
	}

	public static CoderData getCoder(Long coderID) {
		CoderData cd = new CoderData();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			Query query = pm.newQuery(Coder.class);
			query.setFilter("userID  == coderID");
			query.declareParameters("java.lang.Long coderID");
			@SuppressWarnings("unchecked")
			List<Coder> codersDB = (List<Coder>) query.execute(coderID);
			for (Coder coder : codersDB) {
				cd = new CoderData(coder.getUserID(), coder.getUsername(),
						coder.getEmail(), coder.getSPOJUsername(),
						coder.getSPOJPassword());
			}
		} finally {
			pm.close();
		}
		return cd;
	}

	public static Long getCoderID(String Email) {
		Long l = 0L;
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			Query query = pm.newQuery(Coder.class);
			query.setFilter("email  == Email");
			query.declareParameters("java.lang.String Email");
			List<Coder> codersDB = (List<Coder>) query.execute(Email);
			for (Coder coder : codersDB) {
				l = coder.getUserID();
			}
		} finally {
			pm.close();
		}
		return l;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean checkRegistered() throws NotLoggedInException {
		DataStoreHandler.checkLoggedIn();
		boolean isRegistered = false;
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			// Query q = pm.newQuery(Coder.class, "email == e");
			// String query = "select from " + Coder.class.getName() +
			// " where email == '" + getUser().getEmail() + "'";

			// = (List<Coder>) pm.newQuery(query).execute();
			String select_query = "select from " + Coder.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("email == userEmail");
			query.declareParameters("java.lang.String userEmail");
			if (DataStoreHandler.getUser() == null) {
				isRegistered = false;
			} else {
				List<Coder> coders = (List<Coder>) query
						.execute(DataStoreHandler.getUser().getEmail());
				// List<Coder> coders = (List<Coder>)
				// q.execute("e == getUser().getEmail()");
				int size = coders.size();

				if (size != 0) {
					isRegistered = true;
				}
			}

		} finally {
			pm.close();
		}
		return isRegistered;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.Services.CoderService#addCoder(java.lang.String)
	 */
	@Override
	public void addCoder(String username) throws NotLoggedInException {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			if (isAlreadyTaken(pm, username)) {
				throw new NotLoggedInException();
			} else {
				User user = DataStoreHandler.getUser();
				Coder c = new Coder();
				c.setEmail(user.getEmail());
				c.setUsername(username);

				pm.makePersistent(c);
			}
		} finally {
			pm.close();
		}

	}

	/**
	 * Check if the coder name is already taken
	 * 
	 * @param pm
	 * @param username
	 * @return
	 */
	boolean isAlreadyTaken(PersistenceManager pm, String username) {
		String select_query = "select from " + Coder.class.getName();
		Query query = pm.newQuery(select_query);
		query.setFilter("username == userName");
		query.declareParameters("java.lang.String userName");
		List<Coder> coders = (List<Coder>) query.execute(username);

		return coders.size() == 0 ? false : true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.Services.CoderService#addAccount(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void addAccount(String accountType, String username, String password)
			throws NotLoggedInException {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {

			String select_query = "select from " + Coder.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("email == userEmail");
			query.declareParameters("java.lang.String userEmail");
			List<Coder> coders = (List<Coder>) query.execute(DataStoreHandler
					.getUser().getEmail());

			if (accountType.equals("SPOJ")) {
				coders.get(0).setSPOJUsername(username);
				coders.get(0).setSPOJPassword(password);
				pm.makePersistent(coders.get(0));
			} else if (accountType.equals("Timus")) {
				coders.get(0).setTimusUsername(username);
				coders.get(0).setTimusPassword(password);
				pm.makePersistent(coders.get(0));
			} else if (accountType.equals("UVA")) {
				coders.get(0).setUVAUsername(username);
				coders.get(0).setUVAPassword(password);
				pm.makePersistent(coders.get(0));
			} else if (accountType.equals("LiveArchive")) {
				System.out.println("LIVE ARCHIVE SAVE USERNAME" + username);
				coders.get(0).setLiveArchiveUsername(username);
				coders.get(0).setLiveArchivePassword(password);
				pm.makePersistent(coders.get(0));
			}
			System.out.println("5alstoo");
		} finally {
			pm.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#getAddedAccounts()
	 */
	@Override
	public String getAddedAccounts() {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		String ret = "";
		try {

			String select_query = "select from " + Coder.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("email == userEmail");
			query.declareParameters("java.lang.String userEmail");
			List<Coder> coders = (List<Coder>) query.execute(DataStoreHandler
					.getUser().getEmail());
			if (coders.get(0).getSPOJUsername() != null) {
				ret += "SPOJ-";
			}
			if (coders.get(0).getTimusUsername() != null) {
				ret += "Timus-";
			}
			if (coders.get(0).getUVAUsername() != null) {
				ret += "UVA-";
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
	 * com.OJToolkit.client.Services.CoderService#getUsername(java.lang.String)
	 */
	@Override
	public String getUsername(String accountType) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		String ret = null;
		try {
			String userEmail = DataStoreHandler.getUser().getEmail();
			String select_query = "select from " + Coder.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("email == userEmail");
			query.declareParameters("java.lang.String userEmail");
			List<Coder> coders = (List<Coder>) query.execute(userEmail);
			if (accountType.equals("SPOJ")) {
				ret = coders.get(0).getSPOJUsername();
			} else if (accountType.equals("Timus")) {
				ret = coders.get(0).getTimusUsername();
			} else if (accountType.equals("UVA")) {
				ret = coders.get(0).getUVAUsername();
			} else if (accountType.equals("LiveArchive")) {
				System.out.println("LIVE ARCHIVE username");
				ret = coders.get(0).getLiveArchiveUsername();
				System.out.println(ret);
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
	 * com.OJToolkit.client.Services.CoderService#getCoderDetails(java.lang.
	 * String)
	 */
	@Override
	public CoderProfileData getCoderDetails(String username) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		CoderProfileData ret = new CoderProfileData();
		try {
			String select_query = "select from " + Coder.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("username == userName");
			query.declareParameters("java.lang.String userName");
			List<Coder> coders = (List<Coder>) query.execute(username);
			ret.setEmail(coders.get(0).getEmail());
			ret.setSPOJUsername(coders.get(0).getSPOJUsername());
			ret.setUVAUsername(coders.get(0).getUVAUsername());
			ret.setTimusUsername(coders.get(0).getTimusUsername());
			ret.setUsername(username);

			select_query = "select from " + UserSubmission.class.getName();
			query = pm.newQuery(select_query);
			query.setFilter("username == userName");
			query.declareParameters("java.lang.String userName");
			List<UserSubmission> userSubmissions = (List<UserSubmission>) query
					.execute(username);
			ret.setNumberOfSubmission(userSubmissions.size());

			select_query = "select from " + UserSubmission.class.getName();
			query = pm.newQuery(select_query);
			query.setFilter("username == userName && judgeResult == jResult");
			query.declareParameters("java.lang.String userName, java.lang.String jResult");
			userSubmissions = (List<UserSubmission>) query.execute(username,
					"Accepted");
			ret.setNumberOfSolved(userSubmissions.size());

		} finally {
			pm.close();
		}
		return ret;
	}

	@Override
	public ArrayList<SubmissionData> getCoderSubmissions(String username,
			Range range, String sortingQuery) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ArrayList<SubmissionData> ret = new ArrayList<SubmissionData>();
		SubmissionData submissionData;
		try {
			String select_query = "select from "
					+ UserSubmission.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("username == userName");
			query.declareParameters("java.lang.String userName");
			if (!sortingQuery.equals("")) {
				query.setOrdering(sortingQuery);
			} else
				query.setOrdering("date desc");
			query.setRange(range.getStart(),
					range.getStart() + range.getLength());
			List<UserSubmission> submissions = (List<UserSubmission>) query
					.execute(username);

			for (UserSubmission userSubmission : submissions) {
				submissionData = new SubmissionData();
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
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#getUsername()
	 */
	@Override
	public String getUsername() {
		return DataStoreHandler.getAllCoders().get(0).getUsername();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.OJToolkit.client.Services.CoderService#isValidAccount(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public boolean isValidAccount(String username, String password,
			String judgeType) throws Exception {
		boolean ret = false;
		Judge judge = null;
		if (judgeType.equals("SPOJ")) {
			judge = new SPOJ();
		} else if (judgeType.equals("Timus")) {
			judge = new Timus();
		} else if (judgeType.equals("UVA")) {
			judge = new UVA();
		} else if (judgeType.equals("LiveArchive")) {
			judge = new LiveArchive();
		}
		System.out.println(judge.getClass().getName().toString());
		if (judge != null) {
			ret = judge.signIn(username, password);
		}
		System.out.println(ret);
		return ret;
	}
}
