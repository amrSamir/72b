package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RegistrationEvent extends GwtEvent<RegisterationEventHandler> {

	public static Type<RegisterationEventHandler> TYPE = new Type<RegisterationEventHandler>();

	public RegistrationEvent() {
		super();
	}

	@Override
	public Type<RegisterationEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RegisterationEventHandler handler) {
		handler.onRegistration(this);
	}
}
