/**
 * 
 */
package com.OJToolkit.client.event;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 * Apr 26, 2011
 */
public class ViewProblemEvent extends GwtEvent<ViewProblemEventHandler>{

	
	public static Type<ViewProblemEventHandler> TYPE  = new Type<ViewProblemEventHandler>();
	
	public ProblemData problem;

	
	

	public ViewProblemEvent(ProblemData problem) {
	    super();
	    this.problem = problem;
    }

	/* (non-Javadoc)
     * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
     */
    @Override
    public Type<ViewProblemEventHandler> getAssociatedType() {
	    
	    return TYPE;
    }

	/* (non-Javadoc)
     * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
     */
    @Override
    protected void dispatch(ViewProblemEventHandler handler) {
	    handler.onViewProblem(this);
	    
    }

}
