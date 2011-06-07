package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author 72B Contains all the submission and problems services
 */
@RemoteServiceRelativePath("submission")
public interface SubmissionService extends RemoteService {

	/**
	 * Submit Problem to SPOJ
	 * 
	 * @param prblmID
	 *            Problem ID
	 * @param code
	 *            The submitted code
	 * @param language
	 *            The language of the code
	 */
	public void submitCode(String problemCode, String code, String language);

	/**
	 * Gets the result of judging the problem
	 * @param problemCode the code of the last submitted problem
	 * @return the result of judging the problem
	 * @throws Exception
	 */
	public ProblemStatusData getLastProblemStatus(String problemCode) throws Exception;

	/**
	 * Save a problem to the datastore
	 * 
	 * @param problemData
	 *            the problem to be saved to the datastore
	 */
	public void saveSpojProblemtoDB(ProblemData problemData);

	/**
	 * Get 50 Problems from the datastore
	 * 
	 * @param start
	 *            the start index of the problems
	 * @return 50 Problems from the datastore
	 */
	public ArrayList<ProblemData> getProblems(long start);

	/**
	 * Fetch Problem from database
	 * 
	 * @param problemCode
	 *            the problem code of the problem to be fetched
	 * @return Problem
	 */
	public ProblemData getProblem(String problemCode);

}
