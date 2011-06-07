package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.LanguageData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LanguageServiceAsync {
	/**
	 * @see com.OJToolkit.client.Services.LanguageService#getLanguages(String)
	 */
	void getLanguages(String OJType,
            AsyncCallback<ArrayList<LanguageData>> callback);

	/**
	 * @see com.OJToolkit.client.Services.LanguageService#addLanguages()
	 */
	void addLanguages(AsyncCallback<Void> callback);
}
