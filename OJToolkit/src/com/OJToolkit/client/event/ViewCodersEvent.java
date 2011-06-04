/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class ViewCodersEvent extends GwtEvent<ViewCodersEventHandler> {

	public static Type<ViewCodersEventHandler> TYPE = new Type<ViewCodersEventHandler>();

	public ViewCodersEvent() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ViewCodersEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(ViewCodersEventHandler handler) {

		handler.onViewCoders(this);

	}

}
