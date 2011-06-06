package com.OJToolkit.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ViewProblemSubmissionStatusEvent extends
        GwtEvent<ViewProblemSubmissionStatusEventHandler> {

	public static Type<ViewProblemSubmissionStatusEventHandler> TYPE = new Type<ViewProblemSubmissionStatusEventHandler>();

	@Override
	public Type<ViewProblemSubmissionStatusEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ViewProblemSubmissionStatusEventHandler handler) {
		handler.onSubmitProblem(this);
	}
}
