/**
 * 
 */
package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author 72B
 * May 13, 2011
 */
public interface CoderServiceAsync {

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#addCoder(java.lang.String, java.lang.String, java.lang.String)
	 */
	void addCoder(String username, String SPOJUsername, String SPOJPassword,
	        AsyncCallback<Void> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#checkRegistered()
	 */
	void checkRegistered(AsyncCallback<Boolean> callback);

	/**
	 * 
	 * @see com.OJToolkit.client.Services.CoderService#viewCoders()
	 */
	void viewCoders(AsyncCallback<ArrayList<CoderData>> callback);

}
