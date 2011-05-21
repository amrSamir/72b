package com.OJToolkit.client.Services;

import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void login(String requestUri, AsyncCallback<LoginInfo> callback);
}
