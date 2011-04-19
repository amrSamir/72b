package com.OJToolkit_2.client.Services;

import java.util.ArrayList;

import com.OJToolkit_2.client.ValueObjects.LanguageData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LanguageServiceAsync {
	void getLanguages(AsyncCallback<ArrayList<LanguageData>> callback);
	void addLanguages(AsyncCallback<Void> callback);
}
