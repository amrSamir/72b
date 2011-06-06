/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.AppController;
import com.OJToolkit.client.Services.CoderServiceAsync;
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
 * @author 72B May 13, 2011
 */

public class AddAccountPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getAddSpojAccountButton();

		HasClickHandlers getAddTimusAccountButton();

		HasClickHandlers getAddUVAAccountButton();

		HasClickHandlers getAddAccountButton();

		HasValue<String> getAccountUserName();

		HasValue<String> getAccountPassword();

		void setAccountType(String accountType);

		void setAddedAccounts(String addedAccounts);

		Widget asWidget();
	}

	private final Display display;
	private final HandlerManager eventBus;
	private String accountType;
	private String addedAccounts;
	private final CoderServiceAsync coderService;

	/**
	 * add account presenter
	 * 
	 * @param coderService
	 * @param eventBus
	 * @param display
	 */
	public AddAccountPresenter(CoderServiceAsync coderService,
			HandlerManager eventBus, final Display display) {
		// System.out.println("Ana 3nd Add Account Presenter...");
		// LOG.log(Level.SEVERE, "Ana 3nd Add Account Presenter...");

		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		this.coderService.getAddedAccounts(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("can't get an account!!");
			}

			@Override
			public void onSuccess(String result) {
				addedAccounts = result;
				Cookies.setCookie("addedAccountsCookie", addedAccounts,
						AppController.COOKIES_EXPIRYDATE, null, "/", false);
				display.setAddedAccounts(addedAccounts);
			}
		});

	}

	/**
     * 
     */
	private void bind() {
		display.getAddSpojAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				accountType = "SPOJ";
				display.setAccountType(accountType);
			}
		});

		display.getAddTimusAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				accountType = "Timus";
				display.setAccountType(accountType);
				// eventBus.fireEvent(new AddAccountDetailsEvent("X"));

			}
		});

		display.getAddUVAAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				accountType = "UVA";
				display.setAccountType(accountType);
				// eventBus.fireEvent(new AddAccountDetailsEvent("X"));
			}
		});

		display.getAddAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// System.out.println(accountType);
				// eventBus.fireEvent(new AddAccountDetailsEvent("SPOJ"));
				coderService.addAccount(accountType, display
						.getAccountUserName().getValue(), display
						.getAccountPassword().getValue(),
						new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								// eventBus.fireEvent(new
								// AlreadyRegisteredEvent());
								addedAccounts += "-" + accountType;
								Cookies.setCookie("addedAccountsCookie",
										addedAccounts,
										AppController.COOKIES_EXPIRYDATE, null,
										"/", false);
								display.setAddedAccounts(addedAccounts);

							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("add account failer!!!");
							}
						});

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
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
