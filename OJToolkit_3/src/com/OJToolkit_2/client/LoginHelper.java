package com.OJToolkit_2.client;

import com.google.gwt.core.client.GWT;
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
									new FrmLogin(loginInfo.getLoginUrl()));
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
					/*CoreContainer.getInstance().setContent(
							new ContentProblemList());*/
					CoreContainer.getInstance().setContent(new ContentProblemPage("problem id gdeda"));
					// viewCoders();

				} else {
					CoreContainer.getInstance().setContent(
							new FrmRegistration());
					// core.clear();
					// core.add(new FrmRegistration());

				}

			}

			@Override
			public void onFailure(Throwable caught) {
				// Window.alert("Registration Failed");
				CoreContainer.getInstance().setContent(
						new FrmLogin(loginInfo.getLoginUrl()));
				// core.clear();
				// core.add(new FrmLogin(loginInfo.getLoginUrl()));
				// TODO Auto-generated method stub

			}
		});
	}

}
