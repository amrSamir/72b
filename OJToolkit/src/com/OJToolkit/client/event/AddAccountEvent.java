
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class AddAccountEvent extends GwtEvent<AddAccountEventHandler> {

	public static Type<AddAccountEventHandler> TYPE = new Type<AddAccountEventHandler>();

	public AddAccountEvent() {
		super();
	}

	@Override
	public Type<AddAccountEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddAccountEventHandler handler) {
		handler.onAddAccount(this);
	}

}
