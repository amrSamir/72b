/**
 * 
 */
package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author 72B May 13, 2011
 */
public interface CoderServiceAsync {

	void addCoder(String username, AsyncCallback<Void> callback);

	/**
	 * @see com.OJToolkit.client.Services.CoderService#checkRegistered()
	 */
	void checkRegistered(AsyncCallback<Boolean> callback);

	/**
	 * @see com.OJToolkit.client.Services.CoderService#viewCoders()
	 */
	void viewCoders(AsyncCallback<ArrayList<CoderData>> callback);

	void addAccount(String accountType, String username, String password,
			AsyncCallback<Void> callback);
	void getAddedAccounts(AsyncCallback<String> callback);

	void getUsername(String accountType, AsyncCallback<String> callback);

}
