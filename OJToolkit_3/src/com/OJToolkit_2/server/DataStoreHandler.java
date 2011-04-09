package com.OJToolkit_2.server;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit_2.client.NotLoggedInException;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public  class DataStoreHandler {
	
		private static final PersistenceManagerFactory PMF = CoderServiceImpl.PMF;

		static PersistenceManager pm;
	public DataStoreHandler() {
			
			// TODO Auto-generated constructor stub
		}

	public  static List<Coder> getAllCoders() {
		 pm = getPersistenceManager();
		String select_query = "select from " + Coder.class.getName(); 
		Query query = pm.newQuery(select_query); 
		query.setFilter("email == userEmail"); 
		query.declareParameters("java.lang.String userEmail"); 
		List<Coder> coders = (List<Coder>) query.execute(getUser().getEmail());
		return coders;	
	}
	
	  static void checkLoggedIn() throws NotLoggedInException {
		    if (getUser() == null) {
		      throw new NotLoggedInException("Not logged in.");
		    }
		  }
	  
	  
	   static User getUser() {
		    UserService userService = UserServiceFactory.getUserService();
		    return userService.getCurrentUser();
		  }

	  
		    static PersistenceManager getPersistenceManager() {
		    return PMF.getPersistenceManager();
		  }

}
