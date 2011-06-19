package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Exceptions.NotLoggedInException;
import com.OJToolkit.client.Services.CoderService;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CoderServiceImpl extends RemoteServiceServlet implements
        CoderService {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(CoderServiceImpl.class
	        .getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<CoderData> viewCoders() throws NotLoggedInException {
		// DataStoreHandler.checkLoggedIn();
		LOG.log(Level.SEVERE, "SPOJ_Username2");
		// DataStoreHandler dsh = new DataStoreHandler();
		LOG.log(Level.SEVERE, DataStoreHandler.getAllCoders().get(0)
		        .getSPOJUsername());

		// List<Coder> coders2 = DataStoreHandler.getAllCoders();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ArrayList<CoderData> coders = new ArrayList<CoderData>();
		try {
			Query q = pm.newQuery(Coder.class);
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
			List<Coder> coders = (List<Coder>) query.execute(DataStoreHandler
			        .getUser().getEmail());
			// List<Coder> coders = (List<Coder>)
			// q.execute("e == getUser().getEmail()");
			int size = coders.size();

			if (size != 0) {
				isRegistered = true;
			}

		} finally {			
			pm.close();
		}
		return isRegistered;
	}

	/*
	 * (non-Javadoc)
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
			}
			System.out.println("5alstoo");
		} finally {
			pm.close();
		}
	}

	/*
	 * (non-Javadoc)
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

	/* (non-Javadoc)
     * @see com.OJToolkit.client.Services.CoderService#getUsername(java.lang.String)
     */
    @Override
    public String getUsername(String accountType) {
    	PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		String ret = null;
		try {

			String select_query = "select from " + Coder.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("email == userEmail");
			query.declareParameters("java.lang.String userEmail");
			List<Coder> coders = (List<Coder>) query.execute(DataStoreHandler
			        .getUser().getEmail());
			if (accountType.equals("SPOJ")) {
				ret = coders.get(0).getSPOJUsername();
			}
			else if (accountType.equals("Timus")) {
				ret = coders.get(0).getTimusUsername();
			}
			else if (accountType.equals("UVA")) {
				ret = coders.get(0).getUVAUsername();
			}
		} finally {
			pm.close();
		}
		
		return ret;
    }
}
