package com.OJToolkit_2.server;

import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.OJToolkit_2.client.CoderData;
import com.OJToolkit_2.client.NotLoggedInException;
import com.OJToolkit_2.client.coderService;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class CoderServiceImpl extends RemoteServiceServlet implements coderService {
	
	@SuppressWarnings("unused")
	private static final Logger LOG =  Logger.getLogger(CoderServiceImpl.class.getName());
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	public void addCoder(String username, String SPOJUsername, String SPOJPassword) throws NotLoggedInException {
		checkLoggedIn();
		PersistenceManager pm = getPersistenceManager();
		try{
			User user = getUser();	
			Coder c = new Coder(username,user.getEmail(),SPOJUsername,BCrypt.hashpw(SPOJPassword, BCrypt.gensalt()));
			pm.makePersistent(c);
			
		} finally{
			pm.close();
		}
		
		// TODO Auto-generated method stub

	}
	
	  private void checkLoggedIn() throws NotLoggedInException {
		    if (getUser() == null) {
		      throw new NotLoggedInException("Not logged in.");
		    }
		  }
	  
	  private User getUser() {
		    UserService userService = UserServiceFactory.getUserService();
		    return userService.getCurrentUser();
		  }

		  private PersistenceManager getPersistenceManager() {
		    return PMF.getPersistenceManager();
		  }

		@Override
		@SuppressWarnings("unchecked")
		public ArrayList<CoderData> viewCoders() throws NotLoggedInException {
			checkLoggedIn();
			PersistenceManager pm = getPersistenceManager();
			ArrayList<CoderData> coders = new ArrayList<CoderData>();
			try{
				Query q = pm.newQuery(Coder.class);
				List<Coder> codersDB =  (List<Coder>) q.execute(); 
				for (Coder coder : codersDB) {
			        coders.add(new CoderData(coder.getUserID(), coder.getUsername(), coder.getEmail(), coder.getSPOJUsername(), coder.getSPOJPassword()));
			      }
				
			} finally{
				pm.close();
			}
			
			// TODO Auto-generated method stub
			return coders;
		}

		@Override
		@SuppressWarnings("unchecked")
		public boolean checkRegistered() throws NotLoggedInException {
			checkLoggedIn();
			boolean isRegistered = false;
			PersistenceManager pm = getPersistenceManager();
			try {
				//Query q = pm.newQuery(Coder.class, "email == e");
			//	String query = "select from " + Coder.class.getName() + " where email == '" + getUser().getEmail() + "'";
				
				// = (List<Coder>) pm.newQuery(query).execute();
				String select_query = "select from " + Coder.class.getName(); 
				Query query = pm.newQuery(select_query); 
				query.setFilter("email == userEmail"); 
				query.declareParameters("java.lang.String userEmail"); 
				List<Coder> coders = (List<Coder>) query.execute(getUser().getEmail());
				//List<Coder> coders = (List<Coder>) q.execute("e == getUser().getEmail()");
				int size = coders.size();
				
				if(size!=0){
					isRegistered = true;		
				}
				
			} finally {
				// TODO: handle exception
				pm.close();
			}
			// TODO Auto-generated method stub
			return isRegistered;
		}
		  


}
