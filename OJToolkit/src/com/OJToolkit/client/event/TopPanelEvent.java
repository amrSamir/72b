/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         June 15, 2011
 */
public class TopPanelEvent extends GwtEvent<TopPanelEventHandler> {

	public static Type<TopPanelEventHandler> TYPE = new Type<TopPanelEventHandler>();
	public String logoutURL;
	
	public TopPanelEvent(String logoutURL) {
		super();
		this.logoutURL = logoutURL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<TopPanelEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(TopPanelEventHandler handler) {

		handler.onTopPanel(this);

	}

}
