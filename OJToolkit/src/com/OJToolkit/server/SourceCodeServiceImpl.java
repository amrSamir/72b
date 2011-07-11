package com.OJToolkit.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.OJToolkit.client.Services.SourceCodeService;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

// Not reviewed
public class SourceCodeServiceImpl extends RemoteServiceServlet implements
        SourceCodeService {
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger
	        .getLogger(SourceCodeServiceImpl.class.getName());

	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.Services.SourceCodeService#isCodeVisible(long)
	 */
	@Override
	public boolean isCodeVisible(long submissionID) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			String select_query = "select from " + SourceCode.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("submissionID == sID");
			query.declareParameters("java.lang.Long sID");
			List<SourceCode> sourceCodes = (List<SourceCode>) query
			        .execute(submissionID);
			if (sourceCodes.get(0).isVisible() == true) {
				return true;
			} else {
				String select_query2 = "select from "
				        + UserSubmission.class.getName();
				Query query2 = pm.newQuery(select_query2);
				query2.setFilter("submissionID == sID");
				query2.declareParameters("java.lang.Long sID");
				List<UserSubmission> userSubmissons = (List<UserSubmission>) query2
				        .execute(submissionID);
				if (userSubmissons
				        .get(0)
				        .getUsername()
				        .equals(DataStoreHandler.getAllCoders().get(0)
				                .getUsername())) {
					return true;
				} else {
					return false;
				}
			}

		} finally {

			pm.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.OJToolkit.client.Services.SourceCodeService#getSourceCode(long)
	 */
	@Override
	public SourceCodeData getSourceCode(long submissionID) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		SourceCodeData sourceCodeData;
		try {
			String select_query = "select from " + SourceCode.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("submissionID == sID");
			query.declareParameters("java.lang.Long sID");
			List<SourceCode> sourceCodes = (List<SourceCode>) query
			        .execute(submissionID);

			String select_query2 = "select from "
			        + UserSubmission.class.getName();
			Query query2 = pm.newQuery(select_query2);
			query2.setFilter("submissionID == sID");
			query2.declareParameters("java.lang.Long sID");
			List<UserSubmission> userSubmissons = (List<UserSubmission>) query2
			        .execute(submissionID);
			UserSubmission userSubmission = userSubmissons.get(0);

			sourceCodeData = new SourceCodeData(
			        userSubmission.getProblemCode(),
			        userSubmission.getProblemCode(),
			        userSubmission.getUsername(),
			        userSubmission.getJudgeResult(),
			        userSubmission.getJudgeType(), userSubmission.getDate()
			                .toString(), userSubmission.getMemory(),
			        userSubmission.getTime(), sourceCodes.get(0)
			                .getSourceCode());

		} finally {
			pm.close();
		}
		return sourceCodeData;
	}

	/* (non-Javadoc)
     * @see com.OJToolkit.client.Services.SourceCodeService#addCategories(java.lang.String, java.lang.String, java.util.ArrayList)
     */
    @Override
    public void addCategories(String problemCode, String judgeType,
            ArrayList<String> categoriesList) {
    	PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try {
			Category cat;
			for(int i=0;i<categoriesList.size();i++){
				cat= new Category(problemCode, judgeType, categoriesList.get(i));
				pm.makePersistent(cat);
			}

		} finally {
			pm.close();
		}
	    
    }

	/* (non-Javadoc)
     * @see com.OJToolkit.client.Services.SourceCodeService#getCategories(java.lang.String, java.lang.String)
     */
    @Override
    public ArrayList<String> getCategories(String problemCode, String judgeType) {
    	PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		ArrayList<String> ret = new ArrayList<String>();
		try {
			String select_query = "select from " + Category.class.getName();
			Query query = pm.newQuery(select_query);
			query.setFilter("problemCode == probCode && judgeType == OJType");
			query.declareParameters("java.lang.String probCode, java.lang.String OJType");
			List<Category> categories = (List<Category>) query.execute(problemCode,
			        judgeType);
			for(int i=0;i<categories.size();i++){
				ret.add(categories.get(i).getCategory());
			}
		} finally {
			pm.close();
		}

	    return ret;
    }

}
