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
					Window.alert("Registeration Failed!!");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Registeration Failed!!");
			}
		});
	}

	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				coderService.addCoder(display.getUsername().getValue(),
						new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.alert("Registraion succeded");
								Cookies.setCookie("isRegisteredCookie", "YES",
										AppController.COOKIES_EXPIRYDATE, null,
										"/", false);
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

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
