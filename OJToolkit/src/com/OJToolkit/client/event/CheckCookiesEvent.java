/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         June 4, 2011
 */
public class CheckCookiesEvent extends GwtEvent<CheckCookiesEventHandler> {

	public static Type<CheckCookiesEventHandler> TYPE = new Type<CheckCookiesEventHandler>();

	public CheckCookiesEvent() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<CheckCookiesEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(CheckCookiesEventHandler handler) {

		handler.onCheckCookies(this);

	}

}
