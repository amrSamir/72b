package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jws.soap.SOAPBinding.Use;

import com.OJToolkit.client.Exceptions.NotLoggedInException;
import com.OJToolkit.client.Services.SourceCodeService;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


// Not reviewed 
public class SourceCodeServiceImpl  extends RemoteServiceServlet implements SourceCodeService 
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(SourceCodeServiceImpl.class
	        .getName());

	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	@SuppressWarnings("unchecked")
	public 	void addCode (String code, String problemCode, String problemName,String url ) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			   LOG.log(Level.WARNING,"Z" );
			Long myUserID = DataStoreHandler.getAllCoders().get(0).getUserID();
		 
			SourceCode sc = new SourceCode();
			sc.setCode(code);
			sc.setProblemCode(problemCode);
			sc.setProblemName(problemName);
			sc.setUrl(url);
			
			pm.makePersistent(sc);
			} finally {
			pm.close();
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public  ArrayList<SourceCodeData> getCodes(Long myuserID, String myproblemID) {
		// TODO Auto-generated method stub
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		 ArrayList<SourceCodeData> sourceCodes = new  ArrayList<SourceCodeData>(); 
		 
			try {
			 
				String select_query = "select from " + SourceCode.class.getName();
				Query query = pm.newQuery(select_query);
				query.setFilter("userID  == myuserID && problemID == myproblemID");
				query.declareParameters("java.lang.Long myuserID");
				query.declareParameters("java.lang.String myproblemID");
				List<SourceCode> sc = (List<SourceCode>) query.execute(myuserID);
				
				for(SourceCode sc_ob : sc )
				{
					SourceCodeData scd = new SourceCodeData(sc_ob.getCodeID(),
							sc_ob.getUserID(), sc_ob.getProblemCode()
							, sc_ob.getProblemName(),
							sc_ob.getUrl(), sc_ob.getCode(), sc_ob.getNote(), sc_ob.getJudgeResult()
							, sc_ob.getDate());
					sourceCodes.add(scd);
				}

			} finally {
				pm.close();
			}
			
		return sourceCodes;
	}

 

}
