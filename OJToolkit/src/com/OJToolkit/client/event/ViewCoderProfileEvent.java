/**
 * 
 */
package com.OJToolkit.client.event;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class ViewCoderProfileEvent extends GwtEvent<ViewCoderProfileEventHandler> {

	public static Type<ViewCoderProfileEventHandler> TYPE = new Type<ViewCoderProfileEventHandler>();

	public String username;

	public ViewCoderProfileEvent(String username) {
		super();
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ViewCoderProfileEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(ViewCoderProfileEventHandler handler) {
		handler.onViewCoderProfile(this);

	}

}
