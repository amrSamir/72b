package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class CheckCookiesEvent extends GwtEvent<CheckCookiesEventHandler> {

	public static Type<CheckCookiesEventHandler> TYPE = new Type<CheckCookiesEventHandler>();

	public CheckCookiesEvent() {
		super();
	}

	@Override
	public Type<CheckCookiesEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CheckCookiesEventHandler handler) {
		handler.onCheckCookies(this);
	}

}
