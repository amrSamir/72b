/**
 * 
 */
package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.CoderProfileData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.Range;

/**
 * @author 72B
 * Jul 17, 2011
 */
public interface CoderServiceAsync {

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#addAccount(java.lang.String, java.lang.String, java.lang.String)
	 */
	void addAccount(String accountType, String username, String password,
	        AsyncCallback<Void> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#addCoder(java.lang.String)
	 */
	void addCoder(String username, AsyncCallback<Void> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#checkRegistered()
	 */
	void checkRegistered(AsyncCallback<Boolean> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#getAddedAccounts()
	 */
	void getAddedAccounts(AsyncCallback<String> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#getCoderDetails(java.lang.String)
	 */
	void getCoderDetails(String username,
	        AsyncCallback<CoderProfileData> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#getCoderSubmissions(java.lang.String, com.google.gwt.view.client.Range, java.lang.String)
	 */
	void getCoderSubmissions(String username, Range range, String sortingQuery,
	        AsyncCallback<ArrayList<SubmissionData>> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#getUsername(java.lang.String)
	 */
	void getUsername(String accountType, AsyncCallback<String> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#getUsername()
	 */
	void getUsername(AsyncCallback<String> callback);

	void isValidAccount(String username, String password, String judgeType,
            AsyncCallback<Integer> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#viewCoders(com.google.gwt.view.client.Range, java.lang.String)
	 */
	void viewCoders(Range range, String sortingQuery,
	        AsyncCallback<ArrayList<CoderData>> callback);

	void viewCodersCount(AsyncCallback<Integer> callback);

	void getCoderSubmissionsCount(String username,
            AsyncCallback<Integer> callback);

}
