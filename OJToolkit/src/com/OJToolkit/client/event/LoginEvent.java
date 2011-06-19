/**
 * 
 */
package com.OJToolkit.client.event;

import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         June 4, 2011
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {

	public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
	public LoginInfo loginInfo;
	public LoginEvent(LoginInfo result) {
		
		super();
		this.loginInfo = result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LoginEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(LoginEventHandler handler) {

		handler.onLogin(this);

	}

}
