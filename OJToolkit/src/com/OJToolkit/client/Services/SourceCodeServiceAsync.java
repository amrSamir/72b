package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SourceCodeServiceAsync {

	void addCode(String code, String problemCode, String problemName,
			String url, AsyncCallback<Void> callback);

	void getCodes(Long userID, String problemID,
			AsyncCallback<ArrayList<SourceCodeData>> callback);

}
