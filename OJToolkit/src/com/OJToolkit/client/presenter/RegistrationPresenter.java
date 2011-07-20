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

	/**
	 * Call registration  page 
	 * @param coderService
	 * @param eventBus
	 * @param display
	 */
	public RegistrationPresenter(CoderServiceAsync coderService,
	        HandlerManager eventBus, final Display display) {

		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;

		bind();

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
						     //   Window.alert("Added to datastore");
						        Cookies.setCookie("isRegisteredCookie", "YES",AppController.COOKIES_EXPIRYDATE);
						        eventBus.fireEvent(new CheckCookiesEvent());
					        }

					        @Override
					        public void onFailure(Throwable caught) {
						        Window.alert("Username already taken");
					        }
				        });

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
		container.clear();
		container.add(display.asWidget());
	}

}
