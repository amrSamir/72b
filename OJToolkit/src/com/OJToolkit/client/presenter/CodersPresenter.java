package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;


public class CodersPresenter implements Presenter {

	public interface Display {
		void setCodersList(ArrayList<CoderData> coders);
		Widget asWidget();
	}

	private final Display display;
	private final CoderServiceAsync coderService;
	private final HandlerManager eventBus;

	public CodersPresenter(CoderServiceAsync coderService,
	        HandlerManager eventBus, final Display display) {
		this.coderService = coderService;
		this.eventBus = eventBus;
		this.display = display;

		coderService.viewCoders(new AsyncCallback<ArrayList<CoderData>>() {

			@Override
			public void onSuccess(ArrayList<CoderData> result) {				
				display.setCodersList(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to load coder Data!");				
			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
