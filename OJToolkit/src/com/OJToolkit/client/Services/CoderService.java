package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.Exceptions.NotLoggedInException;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.CoderProfileData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.view.client.Range;

/**
 * @author 72B All the services related to the coder
 */
@RemoteServiceRelativePath("coder")
public interface CoderService extends RemoteService {
	/**
	 * Add Coder to the datastore
	 * 
	 * @param username
	 *            Username
	 * @throws NotLoggedInException
	 *             Thrown if the user is not logged in
	 */
	public void addCoder(String username) throws NotLoggedInException;

	/**
	 * Add SpojDetails to the coder
	 * 
	 * @param SPOJUsername
	 *            The SPOJ Username of the user
	 * @param SPOJPassword
	 *            The SPOJ password of the user
	 * @throws NotLoggedInException
	 *             Thrown if the user is not logged in
	 */
	public void addAccount(String accountType, String username, String password)
			throws NotLoggedInException;

	/**
	 * Get All the coders from database
	 * 
	 * @return All the coders from database
	 * @throws NotLoggedInException
	 *             Thrown if the user is not logged in
	 */
	public ArrayList<CoderData> viewCoders(Range range, String sortingQuery) throws NotLoggedInException;

	/**
	 * Checks whether the user is registered or not
	 * 
	 * @return whether the user is registered or not
	 * @throws NotLoggedInException
	 *             Thrown if the user is not logged in
	 */
	public boolean checkRegistered() throws NotLoggedInException;

	/**
	 * gets the added Account for the logged in user
	 * 
	 * @return String with all the added accounts types
	 */
	public String getAddedAccounts();
	
	/**
	 * Gets the username of the logged in user
	 * @param accountType the account type
	 * @return the username of the logged in user for the specific account type
	 */
	public String getUsername(String accountType);
	
	public CoderProfileData getCoderDetails(String username);
	
	public ArrayList<SubmissionData> getCoderSubmissions(String username, Range range, String sortingQuery);

}
