/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class ViewProblemSubmissionStatusEvent extends
        GwtEvent<ViewProblemSubmissionStatusEventHandler> {

	public static Type<ViewProblemSubmissionStatusEventHandler> TYPE = new Type<ViewProblemSubmissionStatusEventHandler>();

	public String problemCode;
	/**
     * @param problemCode The problemcode of the submitted problem
     */
    public ViewProblemSubmissionStatusEvent(String problemCode) {
	    super();
    	this.problemCode = problemCode;
    }

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<ViewProblemSubmissionStatusEventHandler> getAssociatedType() {

		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
	 * .EventHandler)
	 */
	@Override
	protected void dispatch(ViewProblemSubmissionStatusEventHandler handler) {
		handler.onSubmitProblem(this);

	}

}
