/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.AppController;
import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.Services.LoginServiceAsync;
import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.CheckCookiesEvent;
import com.OJToolkit.client.event.LeftPanelEvent;
import com.OJToolkit.client.event.LoginEvent;
import com.OJToolkit.client.event.RegistrationEvent;
import com.OJToolkit.client.event.TopPanelEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author 72B
 *         June 4, 2011
 */
public class CheckCookiesPresenter implements Presenter {

	private final HandlerManager eventBus;
	private final CoderServiceAsync coderService;

	/**
	 * @param eventBus
	 */
	public CheckCookiesPresenter(CoderServiceAsync coderService,
	        LoginServiceAsync loginService, HandlerManager eventBus) {
		this.coderService = coderService;
		this.eventBus = eventBus;
		checkCookies(loginService);

	}

	public void login(LoginServiceAsync loginService) {
		loginService.login(GWT.getHostPageBaseURL()
		        + "OJToolkit.html?gwt.codesvr=127.0.0.1:9997",
		        new AsyncCallback<LoginInfo>() {
			        @Override
			        public void onFailure(Throwable error) {
				        Window.alert("login_failed");
			        }

			        @Override
			        public void onSuccess(LoginInfo result) {

				        if (!result.isLoggedIn()) {
					        eventBus.fireEvent(new LoginEvent(result));

				        } else {
					        // create logged in cookie
					        Cookies.setCookie("isLoggedInCookie", "YES",AppController.COOKIES_EXPIRYDATE);
					        Cookies.setCookie("logoutURL",
					                result.getLogoutUrl(),AppController.COOKIES_EXPIRYDATE);
					        eventBus.fireEvent(new TopPanelEvent(result
					                .getLogoutUrl()));

					        eventBus.fireEvent(new CheckCookiesEvent());

				        }
			        }
		        });

	}

	/**
	 * @param loginService
	 */
	public void checkCookies(LoginServiceAsync loginService) {
		String isLoggedInCookie = Cookies.getCookie("isLoggedInCookie");
		String isRegisteredCookie = Cookies.getCookie("isRegisteredCookie");
		
		// System.out.println(AppController.isEnabled);
		if (isLoggedInCookie == null) {
			login(loginService);
		} else {
			System.out.println("AE-CheckCookiesPresenter-Logged in");
			if (isRegisteredCookie == null) {
				checkRegistered();
			} else
				callPresenters();
		}
	}

	public void callPresenters() {

		String addedAccountsCookie = Cookies.getCookie("addedAccountsCookie");
		String isRegisteredCookie = Cookies.getCookie("isRegisteredCookie");
		if (isRegisteredCookie != null) {
			Cookies.setCookie("isEnabledCookie", "YES",AppController.COOKIES_EXPIRYDATE);
			System.out.println("AE-CheckCookiesPresenter-Enableeeeeed");

			eventBus.fireEvent(new LeftPanelEvent());

			// AppController.isEnabled = true;
			if (addedAccountsCookie == null) {
				eventBus.fireEvent(new AddAccountEvent());
			} else
				eventBus.fireEvent(new AlreadyRegisteredEvent());
		} else {
			System.out
			        .println("AE-CheckCookiesPresenter-I am calling the registration event");
			eventBus.fireEvent(new RegistrationEvent());
		}

	}

	/**
	 * check if already registered
	 */
	public void checkRegistered() {
		System.out.println("Checking if registered");
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result == true) {
					System.out
					        .println("AE-CheckCookiesPresenter-I am registered");
					Cookies.setCookie("isRegisteredCookie", "YES",AppController.COOKIES_EXPIRYDATE);
				} else {
					System.out.println("AE-CheckCookiesPresenter-I am NOT registered");
				}
				callPresenters();
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out
				        .println("AE-CheckCookiesPresenter-Check Reg. Exception");
				// show error and remain on the same page
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.Presenter#go(com.google.gwt.user.client
	 * .ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		// container.clear();
	}

}
