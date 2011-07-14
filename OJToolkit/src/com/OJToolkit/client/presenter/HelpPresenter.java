package com.OJToolkit.client.presenter;

import com.OJToolkit.client.Contents.MyResource;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HelpPresenter implements Presenter {
	interface Binder extends UiBinder<Widget, HelpPresenter> {
	}

	

	/**
	 * The event bus to notify event changes
	 */
	private final HandlerManager eventBus;

	public HelpPresenter(HandlerManager eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public void go(HasWidgets container) {		
		
		// Create the UiBinder.
		Binder uiBinder = GWT.create(Binder.class);
		Widget widget = uiBinder.createAndBindUi(this);

		// Clear and draw on container
		container.clear();
		container.add(widget);
	}
}
