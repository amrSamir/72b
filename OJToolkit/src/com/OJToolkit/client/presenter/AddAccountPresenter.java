/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
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

		HasClickHandlers getAddLiveArchiveAccountButton();

		HasValue<String> getAccountUserName();

		HasValue<String> getAccountPassword();

		HasValue<String> getAlreadyRegisteredMessage();

		HasSelectionHandlers<Integer> getSelectionHandler();

		void notifyUser(String msg);

		Widget asWidget();

		void setVisible();

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

		setUsername("SPOJ");
		this.coderService.getAddedAccounts(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("can't get an account!!");
			}

			@Override
			public void onSuccess(String result) {
				addedAccounts = result;
				Cookies.setCookie("addedAccountsCookie", addedAccounts);
			}
		});

	}

	/**
	 * @param accountType
	 */
	private void setUsername(String accountType) {
		this.coderService.getUsername(accountType, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				if (result != null) {
					display.getAccountUserName().setValue(result);
					display.getAlreadyRegisteredMessage()
					        .setValue(
					                "You have already added this account. If you want to modify it, make the changes then save!");
					display.setVisible();
				} else {
					display.getAlreadyRegisteredMessage()
					        .setValue(
					                "You have already added this account, modify it or delete it by clearing the text fields then save");
				}

			}

			@Override
			public void onFailure(Throwable caught) {

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
				isValidAccount(accountType);
				// display.setAccountType(accountType);
			}
		});

		display.getAddTimusAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				accountType = "Timus";
				isValidAccount(accountType);
				// display.setAccountType(accountType);
				// eventBus.fireEvent(new AddAccountDetailsEvent("X"));

			}
		});

		display.getAddUVAAccountButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				accountType = "UVA";
				isValidAccount(accountType);
			}
		});

		display.getAddLiveArchiveAccountButton().addClickHandler(
		        new ClickHandler() {

			        @Override
			        public void onClick(ClickEvent event) {
				        accountType = "LiveArchive";
				        isValidAccount(accountType);
			        }
		        });

		display.getSelectionHandler().addSelectionHandler(
		        new SelectionHandler<Integer>() {

			        @Override
			        public void onSelection(SelectionEvent<Integer> event) {
				        if (event.getSelectedItem() == 1) {
					        setUsername("UVA");
				        } else if (event.getSelectedItem() == 2) {
					        setUsername("Timus");
				        } else if (event.getSelectedItem() == 3) {
					        setUsername("LiveArchive");
				        }

			        }
		        });

	}

	private void isValidAccount(final String ojType) {
		coderService.isValidAccount(display.getAccountUserName().getValue(),
		        display.getAccountPassword().getValue(), ojType,
		        new AsyncCallback<Integer>() {

			        @Override
			        public void onSuccess(Integer result) {
				        System.out.println("Sign in");
				        if (result == -1) {
					        new Timer() {
						        public void run() {
							        isValidAccount(ojType);
						        }

					        }.schedule(3000);
				        } else {
					        addAccount(ojType, result == 1 ? true : false);
				        }

			        }

			        @Override
			        public void onFailure(Throwable caught) {
				        // TODO Auto-generated method stub

			        }
		        });

	}

	private void addAccount(String ojType, boolean isValidAccount) {

		if (isValidAccount == false) {
			display.notifyUser("Invalid Account. Please recheck the username and password");
		} else {
			coderService.addAccount(ojType, display.getAccountUserName()
			        .getValue(), display.getAccountPassword().getValue(),
			        new AsyncCallback<Void>() {

				        @Override
				        public void onSuccess(Void result) {
					        // eventBus.fireEvent(new
					        // AlreadyRegisteredEvent());
					        addedAccounts += "-" + accountType;
					        Cookies.setCookie("addedAccountsCookie",
					                addedAccounts);
					        System.out.println("addedAccountsCookie"
					                + addedAccounts);
					        display.notifyUser("Account is added/updated successfully");
				        }

				        @Override
				        public void onFailure(Throwable caught) {
					        Window.alert("add account failer!!!");
				        }
			        });
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
		container.clear();
		container.add(display.asWidget());
	}

}
