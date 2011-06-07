package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SourceCodeServiceAsync {

	/**
	 * @see com.OJToolkit.client.Services.SourceCodeService#addCode(String, String, String, String)
	 */
	void addCode(String code, String problemCode, String problemName,
			String url, AsyncCallback<Void> callback);
	/**
	 * @see com.OJToolkit.client.Services.SourceCodeService#getCodes(Long, String)
	 */
	void getCodes(Long userID, String problemID,
			AsyncCallback<ArrayList<SourceCodeData>> callback);

}
