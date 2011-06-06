/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.AppController;
import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.LoginEvent;
import com.OJToolkit.client.event.RegistrationEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author 72B
 *         June 4, 2011
 */
public class CheckCookiesPresenter implements Presenter {

	private final HandlerManager eventBus;

	public CheckCookiesPresenter(HandlerManager eventBus) {

		this.eventBus = eventBus;
		checkCookies();

	}

	public void checkCookies() {
		String isLoggedInCookie = Cookies.getCookie("isLoggedInCookie");
		String isRegisteredCookie = Cookies.getCookie("isRegisteredCookie");
		String addedAccountsCookie = Cookies.getCookie("addedAccountsCookie");
	//	System.out.println(AppController.isEnabled);
		if (isLoggedInCookie != null) {
			if (isRegisteredCookie != null) {
				//AppController.isEnabled = true;
				if (addedAccountsCookie == null) {
					eventBus.fireEvent(new AddAccountEvent());
				} else
					eventBus.fireEvent(new AlreadyRegisteredEvent());
			} else {
				// History.newItem("Registration"); //RegistrationEvent
				eventBus.fireEvent(new RegistrationEvent());
			}
		} else {
			// History.newItem("login");
			eventBus.fireEvent(new LoginEvent());
		}
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
