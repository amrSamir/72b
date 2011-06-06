/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ProblemSubmissionStatusPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getRefreshButton();

		void setSubmissionResult(ProblemStatusData problemStatus);

		Widget asWidget();
	}
	private final Display display;
	private final SubmissionServiceAsync submssionService;
	private final HandlerManager eventBus;
	private ProblemStatusData problemStatus;

	public ProblemSubmissionStatusPresenter(
	        SubmissionServiceAsync submssionService, HandlerManager eventBus,
	        final Display display) {
		this.submssionService = submssionService;
		this.eventBus = eventBus;
		this.problemStatus = new ProblemStatusData();
		this.display = display;

		bind();
		callGetLastProblemStatusService();
	}

	void callGetLastProblemStatusService() {
		submssionService
		        .getLastProblemStatus(new AsyncCallback<ProblemStatusData>() {

			        @Override
			        public void onSuccess(ProblemStatusData result) {
				        problemStatus = result;
				        display.setSubmissionResult(result);
			        }

			        @Override
			        public void onFailure(Throwable caught) {
			        	Window.alert("Failed to get last problem status") ;
			        }
		        });
	}
	private void bind() {
		display.getRefreshButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callGetLastProblemStatusService();
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
}
