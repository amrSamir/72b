package com.OJToolkit.client;

import java.util.Date;

import com.OJToolkit.client.Contents.ContentLogin;
import com.OJToolkit.client.Contents.ContentProblemList;
import com.OJToolkit.client.Contents.ContentProblemPage;
import com.OJToolkit.client.Contents.ContentRegistration;
import com.OJToolkit.client.Services.LoginService;
import com.OJToolkit.client.Services.LoginServiceAsync;
import com.OJToolkit.client.Services.coderService;
import com.OJToolkit.client.Services.coderServiceAsync;
import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoginHelper {
	private final coderServiceAsync coderService = GWT
			.create(coderService.class);

	private final LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	private LoginInfo loginInfo;

	public LoginHelper() {
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
						Window.alert("login_failed");
					}

					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						if (!result.isLoggedIn()) {
							CoreContainer.getInstance().setContent(
									new ContentLogin(loginInfo.getLoginUrl()));
						} else {
							checkRegistered();
						}
					}
				});
	}

	public void checkRegistered() {
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
            if (result == true) {
               String sessionID = "Registered";
               //duration remembering login for 2 weeks.
					final long DURATION = 1000 * 60 * 60;
					Date expires = new Date(System.currentTimeMillis() + DURATION);
					Cookies.setCookie("reg", sessionID, expires, null, "/", false);
					CoreContainer.getInstance().setContent(
							new ContentProblemList());
				} else {
					CoreContainer.getInstance().setContent(
							new ContentRegistration());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				CoreContainer.getInstance().setContent(
						new ContentLogin(loginInfo.getLoginUrl()));
			}
		});
	}
}
