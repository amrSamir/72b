package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ViewCodersEvent extends GwtEvent<ViewCodersEventHandler> {

	public static Type<ViewCodersEventHandler> TYPE = new Type<ViewCodersEventHandler>();

	public ViewCodersEvent() {
		super();
	}

	@Override
	public Type<ViewCodersEventHandler> getAssociatedType() {

		return TYPE;
	}

	@Override
	protected void dispatch(ViewCodersEventHandler handler) {
		handler.onViewCoders(this);
	}

}
