package com.OJToolkit_2.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.apache.tools.ant.taskdefs.Sleep;

import com.OJToolkit_2.client.Services.SubmissionService;
import com.OJToolkit_2.client.ValueObjects.LanguageData;
import com.OJToolkit_2.client.ValueObjects.ProblemData;
import com.OJToolkit_2.client.ValueObjects.ProblemStatusData;
import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SubmissionServiceImpl extends RemoteServiceServlet implements
		SubmissionService {
	private static final Logger LOG = Logger
			.getLogger(SubmissionServiceImpl.class.getName());
	public static final PersistenceManagerFactory PMF = DataStoreHandler.PMF;

	@Override
	public void submitCode(String prblmID, String code, String language) {
		// TODO Auto-generated method stub
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("file", code);
		hashMap.put("lang", language);
		hashMap.put("problemcode", prblmID);
		String spojUsername = DataStoreHandler.getAllCoders().get(0)
				.getSPOJUsername();
		String spojPassword = DataStoreHandler.getAllCoders().get(0)
				.getSPOJPassword();
		hashMap.put("login_user", spojUsername);
		hashMap.put("password", spojPassword);
		try {
			Engine.spojSubmit(hashMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// * KEY VALUE
	// * -------- DATE --> Date and time for the submitted problem.
	// * -------- PROBLEM --> Link of the problem submitted.
	// * -------- RESULT --> The response of the judge.
	// * -------- TIME --> The running time of the solution.
	// * -------- MEM --> The memory used by the solution.
	// *
	//
	@Override
	public ProblemStatusData getLastProblemStatus() throws Exception {
		// ArrayList<ProblemSpoj> vd = Engine.getAllProblemsSpoj();
		// getSpoJProblems();
		//saveSpojProblemstoDB();
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("login_user", DataStoreHandler.getAllCoders().get(0)
				.getSPOJUsername());
		HashMap<String, String> output = Engine
				.getLastProblemStatus_Spoj(input);
		ProblemStatusData dpStatus = new ProblemStatusData(output.get("DATE"),
				output.get("PROBLEM"), output.get("RESULT"),
				output.get("TIME"), output.get("MEM"));
		return dpStatus;
		// TODO Auto-generated method stub

	}

/*	public static void saveSpojProblemstoDB() {
		Scanner cin;
		LOG.log(Level.WARNING, "Entered function");
		try {
			cin = new Scanner(new File(System.getProperty("user.dir")
					+ "/Resources/problems_SPOJ.txt"));
			// PersistenceManager pm = DataStoreHandler.getPersistenceManager();
			Problem problemSpoj = new Problem();
			String curLine;
			String[] splitted;
			// String select_query = "delete from " +
			// Problem2.class.getName();
			// Query query = pm.newQuery(select_query);
			// query.execute();
			// Query q = pm.newQuery(Problem.class);
			// List<Problem> languagesDB = (List<Problem>) q.execute();
			
			 * for (Problem language : languagesDB) {
			 * pm.deletePersistent(language); }
			 

			int i = 0;
			while (cin.hasNextLine()) {
				i++;
				curLine = cin.nextLine();

				LOG.log(Level.WARNING, String.valueOf(i));
				try {
					splitted = curLine.split("\" ");
					problemSpoj
							.setProblemCode(splitted[0].replaceAll("\"", ""));
					problemSpoj
							.setProblemName(splitted[1].replaceAll("\"", ""));
					problemSpoj.setType(splitted[2].replaceAll("\"", ""));
					problemSpoj.setUrl(splitted[3].replaceAll("\"", "")
							.replaceAll("https", "http"));
					
					 * pm.makePersistent(new Problem(problemSpoj.getUrl(),
					 * problemSpoj .getType(), problemSpoj.getProblemCode(),
					 * problemSpoj .getProblemName()));
					 
					// pm.deletePersistent(problemSpoj);

					LOG.log(Level.WARNING,
							" Problem Code " + problemSpoj.getProblemCode()
									+ " Problem Name "
									+ problemSpoj.getProblemName()
									+ " Problem Type " + problemSpoj.getType()
									+ "Problem URL " + problemSpoj.getUrl());

					// System.out.println(problemSpoj.getProblemCode() +
					// " f  "
					// +
					// problemSpoj.getProblemName() + " f  " +
					// problemSpoj.getType()
					// + " f " + problemSpoj.getUrl());
				} catch (Exception e) {
					LOG.log(Level.SEVERE, i
							+ "The following line has an error " + curLine);
				}
			}
			cin.close();

		} catch (FileNotFoundException e1) {
			LOG.log(Level.SEVERE, "FILE NOT FOUND");
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
	}*/
	
	public  void saveSpojProblemtoDB(ProblemData problemData) {
		PersistenceManager pm = DataStoreHandler.getPersistenceManager();
		try{
			/*Query qq = pm.newQuery(Problem.class);
			 List<Problem> ae = (List<Problem>)qq.execute();
			 pm.deletePersistentAll(ae);*/
			Problem problem = new Problem(problemData.getUrl(), problemData.getType(), problemData.getProblemCode(), problemData.getProblemName());
			
			pm.makePersistent(problem);
			
		} finally{
			pm.close();
		}
		
		// TODO Auto-generated method stub
		
	}

}
