package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.HintData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HintServiceAsync {

	/**
	 * @see com.OJToolkit.client.Services.HintService#addHint(String, String)
	 */
	void addHint(String problemID, String hintString,
			AsyncCallback<Void> callback);
	/**
	 * @see com.OJToolkit.client.Services.HintService#getHints(String)
	 */
	void getHints(String problemID, AsyncCallback<ArrayList<HintData>> callback);

}
