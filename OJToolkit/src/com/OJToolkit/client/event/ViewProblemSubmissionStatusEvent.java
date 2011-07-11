/**
 * 
 */
package com.OJToolkit.client.event;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class ViewProblemSubmissionStatusEvent extends
        GwtEvent<ViewProblemSubmissionStatusEventHandler> {

	public static Type<ViewProblemSubmissionStatusEventHandler> TYPE = new Type<ViewProblemSubmissionStatusEventHandler>();

	public ProblemData problem;

	public boolean isAnonymousSubmission;
	public boolean isVisible;
	public String sourceCode;
	 public ArrayList<String> categoriesList;
	
	
	/**
     * @param categoriesList 
	 * @param b 
	 * @param string 
	 * @param problemCode The problemcode of the submitted problem
     */
    public ViewProblemSubmissionStatusEvent(ProblemData problem, boolean isAnonymousSubmission, String sourceCode, boolean isVisible, ArrayList<String> categoriesList) {
	    super();
    	this.problem = problem;
    	this.isAnonymousSubmission = isAnonymousSubmission;
    	this.isVisible = isVisible;
    	this.sourceCode = sourceCode;
    	this.categoriesList = categoriesList;
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
