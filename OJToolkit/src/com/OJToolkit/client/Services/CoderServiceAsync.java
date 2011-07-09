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
 * @author 72B May 13, 2011
 */
public interface CoderServiceAsync {

	void addCoder(String username, AsyncCallback<Void> callback);

	/**
	 * @see com.OJToolkit.client.Services.CoderService#checkRegistered()
	 */
	void checkRegistered(AsyncCallback<Boolean> callback);

	void viewCoders(Range range, String sortingQuery,
			AsyncCallback<ArrayList<CoderData>> callback);

	void addAccount(String accountType, String username, String password,
			AsyncCallback<Void> callback);
	void getAddedAccounts(AsyncCallback<String> callback);

	void getUsername(String accountType, AsyncCallback<String> callback);

	void getCoderDetails(String username,
            AsyncCallback<CoderProfileData> callback);

	void getCoderSubmissions(String username, Range range, String sortingQuery,
            AsyncCallback<ArrayList<SubmissionData>> callback);

	void getUsername(AsyncCallback<String> callback);

	void isValidAccount(String username, String password, String judgeType,
            AsyncCallback<Boolean> callback);

}
