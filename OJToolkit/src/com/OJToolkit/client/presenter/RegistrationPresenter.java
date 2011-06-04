/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.AppController;
import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.event.CheckCookiesEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         May 13, 2011
 */
public class RegistrationPresenter implements Presenter {

	public interface Display {

		HasClickHandlers getSubmitButton();

		HasValue<String> getUsername();

		Widget asWidget();

	}

	private final Display display;
	private final CoderServiceAsync coderService;
	private final HandlerManager eventBus;

	public RegistrationPresenter(CoderServiceAsync coderService,
	        HandlerManager eventBus, final Display display) {

		this.coderService = coderService;
		this.eventBus = eventBus;
		checkRegistered();
		this.display = display;

		bind();

	}

	public void checkRegistered() {
		System.out.println("Checking if registered");
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result == true) {
					Cookies.setCookie("isRegisteredCookie", "YES",
					        AppController.COOKIES_EXPIRYDATE, null, "/", false);
					eventBus.fireEvent(new CheckCookiesEvent());
				} else {
					// do nothing as you are already in RegistrationPresenter

				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// show error and remain on the same page
			}
		});
	}

	/**
     * 
     */
	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				coderService.addCoder(display.getUsername().getValue(),
				        new AsyncCallback<Void>() {

					        @Override
					        public void onSuccess(Void result) {
						        Window.alert("Added to datastore");
						        Cookies.setCookie("isRegisteredCookie", "YES",
						                AppController.COOKIES_EXPIRYDATE, null,
						                "/", false);
						        eventBus.fireEvent(new CheckCookiesEvent());
					        }

					        @Override
					        public void onFailure(Throwable caught) {
						        Window.alert("Username already taken");
						        // TODO Auto-generated method stub

					        }
				        });
				// TODO Auto-generated method stub

			}
		});

		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
	}

}