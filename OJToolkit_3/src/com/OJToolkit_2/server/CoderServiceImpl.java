package com.OJToolkit_2.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit_2.client.Exceptions.NotLoggedInException;
import com.OJToolkit_2.client.Services.coderService;
import com.OJToolkit_2.client.ValueObjects.CoderData;
import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class CoderServiceImpl extends RemoteServiceServlet implements coderService {
	
	@SuppressWarnings("unused")
	private static final Logger LOG =  Logger.getLogger(CoderServiceImpl.class.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;
	
	public void addCoder(String username, String SPOJUsername, String SPOJPassword) throws NotLoggedInException {
		//DataStoreHandler.checkLoggedIn();
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try{
			User user = DataStoreHandler.getUser();	
			Coder c = new Coder(username,user.getEmail(),SPOJUsername,SPOJPassword);
			pm.makePersistent(c);
			
		} finally{
			pm.close();
		}
		
		// TODO Auto-generated method stub

	}
	
	
	  


		@Override
		@SuppressWarnings("unchecked")
		public ArrayList<CoderData> viewCoders() throws NotLoggedInException {
			//DataStoreHandler.checkLoggedIn();
			LOG.log(Level.SEVERE, "SPOJ_Username2");
		//	DataStoreHandler dsh = new DataStoreHandler();
			LOG.log(Level.SEVERE,DataStoreHandler.getAllCoders().get(0).getSPOJUsername());
			
			
		//	List<Coder> coders2 = DataStoreHandler.getAllCoders();
			PersistenceManager pm = DataStoreHandler.getPersistenceManager();
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
			DataStoreHandler.checkLoggedIn();
			boolean isRegistered = false;
			PersistenceManager pm = DataStoreHandler.getPersistenceManager();
			try {
				//Query q = pm.newQuery(Coder.class, "email == e");
			//	String query = "select from " + Coder.class.getName() + " where email == '" + getUser().getEmail() + "'";
				
				// = (List<Coder>) pm.newQuery(query).execute();
				String select_query = "select from " + Coder.class.getName(); 
				Query query = pm.newQuery(select_query); 
				query.setFilter("email == userEmail"); 
				query.declareParameters("java.lang.String userEmail"); 
				List<Coder> coders = (List<Coder>) query.execute(DataStoreHandler.getUser().getEmail());
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
