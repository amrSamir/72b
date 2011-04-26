package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.LanguageData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LanguageServiceAsync {
	void getLanguages(AsyncCallback<ArrayList<LanguageData>> callback);
	void addLanguages(AsyncCallback<Void> callback);
}
