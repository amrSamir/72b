/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         July 7, 2011
 */
public class ViewSubmissionsEvent extends GwtEvent<ViewSubmissionsEventHandler> {

	public static Type<ViewSubmissionsEventHandler> TYPE = new Type<ViewSubmissionsEventHandler>();

	public ViewSubmissionsEvent() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ViewSubmissionsEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(ViewSubmissionsEventHandler handler) {

		handler.onViewSubmissions(this);

	}

}
