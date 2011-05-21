/**
 * 
 */
package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author 72B
 * Apr 26, 2011
 */
public class AlreadyRegisteredEvent extends GwtEvent<AlreadyRegisteredEventHandler>{

	
	public static Type<AlreadyRegisteredEventHandler> TYPE  = new Type<AlreadyRegisteredEventHandler>();
	

	
	

	public AlreadyRegisteredEvent() {
	    super();
    }

	/* (non-Javadoc)
     * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
     */
    @Override
    public Type<AlreadyRegisteredEventHandler> getAssociatedType() {
	    
	    return TYPE;
    }

	/* (non-Javadoc)
     * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
     */
    @Override
    protected void dispatch(AlreadyRegisteredEventHandler handler) {
    	
	    handler.ifRegistered(this);
	    
    }

}
