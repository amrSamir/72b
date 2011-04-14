package com.OJToolkit_2.client;

import java.util.Date;

import com.OJToolkit_2.client.Contents.ContentLogin;
import com.OJToolkit_2.client.Contents.ContentProblemList;
import com.OJToolkit_2.client.Contents.ContentProblemPage;
import com.OJToolkit_2.client.Contents.ContentRegistration;
import com.OJToolkit_2.client.Services.LoginService;
import com.OJToolkit_2.client.Services.LoginServiceAsync;
import com.OJToolkit_2.client.Services.coderService;
import com.OJToolkit_2.client.Services.coderServiceAsync;
import com.OJToolkit_2.client.ValueObjects.LoginInfo;
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
		login();
	}

	public void login() {
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
						Window.alert("login_failed");
					}

					public void onSuccess(LoginInfo result) {
						// Window.alert("login_success");
						loginInfo = result;
						// Window.alert("logged in? " + loginInfo.isLoggedIn());
						if (!result.isLoggedIn()) {
							// Window.alert("Load Login Page");
							CoreContainer.getInstance().setContent(
									new ContentLogin(loginInfo.getLoginUrl()));
							// core.add(new FrmLogin(result.getLoginUrl()));
						} else { // core.clear();
						// Window.alert("Enter Check Registration");
							checkRegistered();
						}
					}
				});
	}

	public void checkRegistered() {
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				 Window.alert("Registered " + result);
				if (result == true) {
					 String sessionID = "Registered";
					    final long DURATION = 1000 * 60 * 60 * 24 * 14; //duration remembering login. 2 weeks in this example.
					    Date expires = new Date(System.currentTimeMillis() + DURATION);
					    Cookies.setCookie("reg", sessionID, expires, null, "/", false);
					CoreContainer.getInstance().setContent(
							new ContentProblemList());
					//CoreContainer.getInstance().setContent(new ContentProblemPage("problem id gdeda"));
					// viewCoders();

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
