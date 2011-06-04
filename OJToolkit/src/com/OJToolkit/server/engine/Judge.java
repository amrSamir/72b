/**
 * 
 */
package com.OJToolkit.server.engine;

/**
 * @author 72B
 *         May 18, 2011
 */
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * @author 72B
 */
public interface Judge {

	/**
	 * @param coderId
	 *            : The ID of the coder.
	 * @param password
	 *            : The password of the coder.
	 * @param problemId
	 *            :The ID of the problem .
	 * @param language
	 *            : The ID of the language in this online judge.
	 * @param code
	 *            : The submitted code.
	 * @throws IOException
	 */

	public void submitProblem(String coderId, String password,
	        String problemId, String languageId, String code)
	        throws IOException;

	/**
	 * @param coderId
	 *            : The ID of the coder.
	 * @param password
	 *            : The password of the user.
	 * @return Instance of type Submission of the last submitted problem.
	 * @throws Exception
	 */
	public Submission getLastSubmission(String coderId, String password)
	        throws Exception;

	/**
	 * @return All the problems on the online judge , instances of type Problem.
	 * @throws Exception
	 */
	public ArrayList<Problem> getAllProblems() throws Exception;

	/**
	 * @param codeId
	 *            : The coder ID.
	 * @return All the problems solved by this coder.
	 * @throws Exception
	 */
	public ArrayList<String> getProblemsSolved(String coderId)
	        throws MalformedURLException, Exception;

	/**
	 * @param problemId
	 *            : The problem ID.
	 * @return Instance of type Problem.
	 * @throws Exception
	 */
	public Problem getProblemInfo(String problemId) throws Exception;
}
