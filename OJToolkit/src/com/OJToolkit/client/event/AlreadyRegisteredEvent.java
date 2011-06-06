package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AlreadyRegisteredEvent extends
        GwtEvent<AlreadyRegisteredEventHandler> {

	public static Type<AlreadyRegisteredEventHandler> TYPE = new Type<AlreadyRegisteredEventHandler>();

	public AlreadyRegisteredEvent() {
		super();
	}
	
	@Override
	public Type<AlreadyRegisteredEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AlreadyRegisteredEventHandler handler) {
		handler.ifRegistered(this);
	}

}
