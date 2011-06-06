package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.HintData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HintServiceAsync {

	void addHint(String problemID, String hintString,
			AsyncCallback<Void> callback);

	void getHints(String problemID, AsyncCallback<ArrayList<HintData>> callback);

}
