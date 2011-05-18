/**
 * 
 */
package com.OJToolkit.client.presenter;

import java.util.Date;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.RegistrationEvent;
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

		HasValue<String> getSPOJUsername();

		HasValue<String> getSPOJPassword();

		Widget asWidget();

	}

	private final Display display;
	private final CoderServiceAsync coderService;
	private final HandlerManager eventBus;

	public RegistrationPresenter(CoderServiceAsync coderService,
	        HandlerManager eventBus, final Display display) {
		System.out.println("Ana 3nd l registration...");
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
				coderService.addCoder(display.getUsername().getValue(), display
				        .getSPOJUsername().getValue(), display
				        .getSPOJPassword().getValue(),
				        new AsyncCallback<Void>() {

					        @Override
					        public void onSuccess(Void result) {
						        Window.alert("Added to datastore");
						        String sessionID = "Registered";
						        final long DURATION = 1000 * 60 * 60 * 24 * 14; // duration
																				// remembering
																				// login.
																				// 2
																				// weeks
																				// in
																				// this
																				// example.
						        Date expires = new Date(System
						                .currentTimeMillis() + DURATION);
						        Cookies.setCookie("reg", sessionID, expires,
						                null, "/", false);
						        // TODO Auto-generated method stub
						        eventBus.fireEvent(new AlreadyRegisteredEvent());
					        }

					        @Override
					        public void onFailure(Throwable caught) {
						        Window.alert("Added to datastore Failed");
						        // TODO Auto-generated method stub

					        }
				        });
				// TODO Auto-generated method stub

			}
		});

		// TODO Auto-generated method stub

	}
/*
	public void checkRegistered() {
		coderService.checkRegistered(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (result == true) {
					String sessionID = "Registered";
					// duration remembering login for 2 weeks.
					final long DURATION = 1000 * 60 * 60;
					Date expires = new Date(System.currentTimeMillis()
					        + DURATION);
					Cookies.setCookie("reg", sessionID, expires, null, "/",
					        false);
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
	}*/

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
