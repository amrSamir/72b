package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SourceCodeServiceAsync {

	void isCodeVisible(long submissionID, AsyncCallback<Boolean> callback);

	void getSourceCode(long submissionID, AsyncCallback<SourceCodeData> callback);

	void addCategories(String problemCode, String judgeType,
            ArrayList<String> categoriesList, AsyncCallback<Void> callback);

	void getCategories(String problemCode, String judgeType,
            AsyncCallback<ArrayList<String>> callback);
 
}
