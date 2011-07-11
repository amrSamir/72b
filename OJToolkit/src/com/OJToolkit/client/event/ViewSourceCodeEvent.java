/**
 * 
 */
package com.OJToolkit.client.event;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         July 11, 2011
 */
public class ViewSourceCodeEvent extends GwtEvent<ViewSourceCodeEventHandler> {

	public static Type<ViewSourceCodeEventHandler> TYPE = new Type<ViewSourceCodeEventHandler>();

	public long submissionID;

	public ViewSourceCodeEvent(long submissionID) {
		super();
		this.submissionID = submissionID;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ViewSourceCodeEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(ViewSourceCodeEventHandler handler) {
		handler.onViewSourceCode(this);

	}

}
