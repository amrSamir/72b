package com.OJToolkit_2.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.OJToolkit_2.client.Exceptions.NotLoggedInException;
import com.OJToolkit_2.client.Services.ProblemService;
import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.jdo.Query;


public class ProblemServiceImpl extends RemoteServiceServlet implements ProblemService{

	@Override
	public ArrayList<ProblemData> getAllProblems() throws NotLoggedInException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProblemData getPorblem(String probID) throws NotLoggedInException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProblem(ProblemData probData) throws NotLoggedInException {
		// TODO Auto-generated method stub
		
	}
	
	/*private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	@Override
	public ArrayList<ProblemData> getAllProblems() throws NotLoggedInException {
		ArrayList<ProblemData > allProblems = new ArrayList<ProblemData>();
		PersistenceManager pm =getPersistenceManager();
		try {			
			Query allProbQ = pm.newQuery(Problem.class);
			List<Problem> problemsDB = (List<Problem>) allProbQ.execute();
			for (Problem problem : problemsDB) {
				allProblems.add(new ProblemData(problem.getProbCode(), problem.getProbName(), problem.getURL(), problem.getOJ()));
			}
			
		} finally {
			pm.close();
		}
		return allProblems;
	}

	@Override
	public ProblemData getPorblem(String probID) throws NotLoggedInException {
		ArrayList<ProblemData> allProb = getAllProblems();
		for (ProblemData problemData : allProb) {
			if(problemData.getProbCode().equals(probID))
				return problemData;
		}
		return null;
	}
	 private PersistenceManager getPersistenceManager() {
		 return PMF.getPersistenceManager();
	}

	@Override
	public void addProblem(ProblemData probData) throws NotLoggedInException {
		PersistenceManager pm = getPersistenceManager();
		try {
			Problem problem = new Problem(probData.getProbCode(), probData.getProbName(), probData.getURL(), probData.getOJ());
			pm.makePersistent(problem);
		} finally {
			// TODO: handle exception
		}
		
	}*/

}
