/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class RegistrationEvent extends GwtEvent<RegisterationEventHandler> {

	public static Type<RegisterationEventHandler> TYPE = new Type<RegisterationEventHandler>();

	// 3lshan tenady beeha service l submission
	public RegistrationEvent() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<RegisterationEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(RegisterationEventHandler handler) {

		handler.onRegistration(this);

	}

}
