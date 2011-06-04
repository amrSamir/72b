/**
 * 
 */
package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.ValueObjects.CoderData;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         May 13, 2011
 */
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
				// Window.alert("Success_CoderData");
				display.setCodersList(result);

				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failure_CoderData");
				// TODO Auto-generated method stub

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.OJToolkit.client.presenter.Presenter#go(com.google.gwt.user.client
	 * .ui.HasWidgets)
	 */
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		// TODO Auto-generated method stub
	}

}
