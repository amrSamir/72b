package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.view.client.Range;

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
	public void submitCode(String problemCode, String ojType, String code, String language);

	/**
	 * Gets the result of judging the problem
	 * @param problemCode the code of the last submitted problem
	 * @return the result of judging the problem
	 * @throws Exception
	 */
	public ProblemStatusData getLastProblemStatus(String problemCode, String ojType) throws Exception;

	/**
	 * Save a problem to the datastore
	 * 
	 * @param problemData
	 *            the problem to be saved to the datastore
	 */
	public void saveSpojProblemtoDB(ProblemData problemData);

	/**
	 * Get Problems from the datastore
	 * 
	 * @param range
	 *            the range of the problems
	 * @param
	 * @return 50 Problems from the datastore
	 */
	/**
	 * Get Problems from the datastore
	 * 
	 * @param range
	 *            the range of the problems
	 * @param sortingQuery
	 *            to sort ascending or descending different columns
	 * @param searchQuery
	 *            filters for values in a certaing column
	 * @return
	 */
	public ArrayList<ProblemData> getProblems(Range range, String sortingQuery,
			String searchQuery);

	/**
	 * Fetch Problem from database
	 * 
	 * @param problemCode
	 *            the problem code of the problem to be fetched
	 * @return Problem
	 */
	public ProblemData getProblem(String problemCode, String ojType);

}
