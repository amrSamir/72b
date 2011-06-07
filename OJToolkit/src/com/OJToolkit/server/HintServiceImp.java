package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Services.HintService;
import com.OJToolkit.client.Services.LanguageService;
import com.OJToolkit.client.ValueObjects.HintData;
import com.OJToolkit.client.ValueObjects.LanguageData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class HintServiceImp extends RemoteServiceServlet implements
HintService {

	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;
	private static final Logger LOG = Logger
	        .getLogger(LanguageServiceImpl.class.getName());

	/* (non-Javadoc)
	 * @see com.OJToolkit.client.Services.HintService#addHint(java.lang.String, java.lang.String)
	 */
	@Override
	public void addHint(String problemID, String hintString) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			Hint h = new Hint();
			h.setHintString(hintString);
			h.setProblemCode(problemID);
			h.setUserID(DataStoreHandler.getAllCoders().get(0).getUserID());
			
			pm.makePersistent(h);
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.OJToolkit.client.Services.HintService#getHints(java.lang.String)
	 */
	@Override
	public ArrayList<HintData> getHints(String problemID) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ArrayList<HintData> hints = new ArrayList<HintData>();
		try {
			 
			String select_query = "select from " + SourceCode.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("problemCode  == problemID");
			query.declareParameters("java.lang.Long problemID");
	 
			List<Hint> hs = (List<Hint>) query.execute(problemID);
			
			
			
			for(Hint h: hs)
			{
				HintData hd = new HintData(h.getHintCode(), h.getProblemCode(),
						h.getHintString(), h.getUserID());
				hints.add(hd);
				
			}
			

		} finally {
			pm.close();
		}
		
		return hints;
	}

}
