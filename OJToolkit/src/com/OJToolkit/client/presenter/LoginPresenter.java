/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.AppController;
import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.Services.LoginServiceAsync;
import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.OJToolkit.client.event.CheckCookiesEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         May 13, 2011
 */
public class LoginPresenter implements Presenter {

	public interface Display {
		void setLoginURL(String loginURL);
		Widget asWidget();
	}

	private final Display display;
	private final LoginServiceAsync loginService;
	private final CoderServiceAsync coderService;
	private final HandlerManager eventBus;
	//private final LoginInfo loginInfo;

	/**
	 * 
	 * @param loginService
	 * @param coderService
	 * @param eventBus
	 * @param display
	 */
	public LoginPresenter(LoginInfo result, LoginServiceAsync loginService,
	        CoderServiceAsync coderService, final HandlerManager eventBus,
	        final Display display) {
		this.loginService = loginService;
		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;
		 display.setLoginURL(result.getLoginUrl());
		
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.Presenter#go(com.google.gwt.user.client
	 * .ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
