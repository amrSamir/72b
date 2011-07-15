package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class ManageContestEvent  extends GwtEvent<ManageContestEventHandler>{

	
	public ManageContestEvent() {
		super();
	}
	public static Type<ManageContestEventHandler> TYPE = new Type<ManageContestEventHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ManageContestEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ManageContestEventHandler handler) {
		handler.onManageContest(this);
		
	}

}
