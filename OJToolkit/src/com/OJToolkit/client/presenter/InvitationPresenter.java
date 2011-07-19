/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.event.CheckCookiesEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         June 7, 2011
 */
public class InvitationPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSubmitButton();

		HasValue<String> getInvitationString();

		Widget asWidget();
	}

	private final Display display;
	private final HandlerManager eventBus;
	private final String invitationString = "x";

	/**
	 * Call registration page
	 * 
	 * @param coderService
	 * @param eventBus
	 * @param display
	 */
	public InvitationPresenter(HandlerManager eventBus, final Display display) {

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
				System.out.println("checking the status of the invitation string");
				if (display.getInvitationString().getValue()
				        .equals(invitationString)) {
					System.out.println("correct");
					Cookies.setCookie("isInvitedCookie", "YESAAA");
					eventBus.fireEvent(new CheckCookiesEvent());
				}
				else {
					Window.alert("Wrong invitation String!!!") ;
				}
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
