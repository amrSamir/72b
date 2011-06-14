/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         June 14, 2011
 */
public class LeftPanelEvent extends GwtEvent<LeftPanelEventHandler> {

	public static Type<LeftPanelEventHandler> TYPE = new Type<LeftPanelEventHandler>();

	public LeftPanelEvent() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LeftPanelEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(LeftPanelEventHandler handler) {

		handler.onLeftPanel(this);

	}

}
