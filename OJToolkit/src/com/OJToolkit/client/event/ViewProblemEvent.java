package com.OJToolkit.client.event;

import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.event.shared.GwtEvent;

public class ViewProblemEvent extends GwtEvent<ViewProblemEventHandler> {

	public static Type<ViewProblemEventHandler> TYPE = new Type<ViewProblemEventHandler>();

	public ProblemData problem;

	public ViewProblemEvent(ProblemData problem) {
		super();
		this.problem = problem;
	}

	@Override
	public Type<ViewProblemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ViewProblemEventHandler handler) {
		handler.onViewProblem(this);
	}

}
