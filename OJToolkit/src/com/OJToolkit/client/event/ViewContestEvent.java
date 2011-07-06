package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class ViewContestEvent extends GwtEvent<ViewContestEventHandler> {

	public ViewContestEvent() {
		super();
	}
	public static Type<ViewContestEventHandler> TYPE = new Type<ViewContestEventHandler>();

	@Override
	public Type<ViewContestEventHandler> getAssociatedType() {
		return TYPE ;
	}

	@Override
	protected void dispatch(ViewContestEventHandler handler) {
		handler.onViewContest(this);
	}

}
