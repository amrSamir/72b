package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class JoinContestEvent extends GwtEvent<JoinContestEventHandler> {
	public JoinContestEvent() {
		super();
	}

	public static Type<JoinContestEventHandler> TYPE = new Type<JoinContestEventHandler>() ;
	@Override
	public Type<JoinContestEventHandler> getAssociatedType() {
		
		return TYPE;
	}

	@Override
	protected void dispatch(JoinContestEventHandler handler) {
		handler.onJoinContest(this);
	}
}
