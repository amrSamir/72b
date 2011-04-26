package com.OJToolkit.client.Services;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface coderServiceAsync {

	void addCoder(String username, String SPOJUsername, String SPOJPassword,
			AsyncCallback<Void> callback);

	void viewCoders(AsyncCallback<ArrayList<CoderData>> callback);

	void checkRegistered(AsyncCallback<Boolean> callback);



}
