package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class ContestAdminEvent extends GwtEvent<ContestAdminEventHandler> {

	public ContestAdminEvent() {
		super();
	}

	public static Type<ContestAdminEventHandler> TYPE = new Type<ContestAdminEventHandler>();

	@Override
	public Type<ContestAdminEventHandler> getAssociatedType() {

		return TYPE;
	}

	@Override
	protected void dispatch(ContestAdminEventHandler handler) {
		handler.onContestAdmin(this);

	}

}
