/**
 * 
 */
package com.OJToolkit.client.presenter;

import java.util.Date;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.Services.LoginServiceAsync;
import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.RegistrationEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 * May 13, 2011
 */
public class LoginPresenter implements Presenter{
	
	public interface Display {
		

		void setLoginURL(String loginURL);


		Widget asWidget();


	}
	
	private final Display display;
	private final LoginServiceAsync loginService;
	private final CoderServiceAsync coderService;
	private final HandlerManager eventBus;



	public LoginPresenter(LoginServiceAsync loginService, CoderServiceAsync coderService,
	        HandlerManager eventBus, final Display display) {
		this.loginService = loginService;
		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;
		
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
						Window.alert("login_failed");
					}

					public void onSuccess(LoginInfo result) {
						display.setLoginURL(result.getLoginUrl());
						if (!result.isLoggedIn()) {
							
							
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
					eventBus.fireEvent(new AlreadyRegisteredEvent());
				} else {
					eventBus.fireEvent(new RegistrationEvent());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// show error and remain on the same page
			}
		});
	}
	

	/* (non-Javadoc)
     * @see com.OJToolkit.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
     */
    @Override
    public void go(HasWidgets container) {
    	container.clear();
		container.add(display.asWidget());
	    // TODO Auto-generated method stub   
    }

}
