/**
 * 
 */
package com.OJToolkit.client.presenter;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.ValueObjects.SourceCodeData;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author 72B
 *         July 11, 2011
 */
public class SourceCodePresenter implements Presenter {

	public interface Display {
		void setSourceCode(SourceCodeData sourceCode);

		Widget asWidget();
	}

	private final Display display;
	private final SourceCodeServiceAsync sourceCodeService;
	private final HandlerManager eventBus;
	private final long submissionID;

	/**
	 * Call registration page
	 * 
	 * @param coderService
	 * @param eventBus
	 * @param display
	 */
	public SourceCodePresenter(long submissionID,
	        SourceCodeServiceAsync sourceCodeService, HandlerManager eventBus,
	        final Display display) {

		this.sourceCodeService = sourceCodeService;
		this.eventBus = eventBus;
		this.submissionID = submissionID;
		this.display = display;
		callGetSourceCode(submissionID);

	}

	/**
	 * @param submissionID2
	 */
	private void callGetSourceCode(long submissionID2) {
		sourceCodeService.getSourceCode(submissionID2,
		        new AsyncCallback<SourceCodeData>() {

			        @Override
			        public void onSuccess(SourceCodeData result) {
				        display.setSourceCode(result);

			        }

			        @Override
			        public void onFailure(Throwable caught) {
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
	}

}
