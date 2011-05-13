/**
 * 
 */
package com.OJToolkit.client;

import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.LoginServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEvent;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEventHandler;
import com.OJToolkit.client.presenter.Presenter;
import com.OJToolkit.client.presenter.ProblemPresenter;
import com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter;
import com.OJToolkit.client.view.ProblemSubmissionStatusView;
import com.OJToolkit.client.view.ProblemView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class AppController implements ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final SubmissionServiceAsync submissionService;
	private final LanguageServiceAsync languageService;
	private HasWidgets container;
	private HasWidgets topPanel;
	private HasWidgets leftPanel;

	public AppController(HandlerManager eventBus,
	        SubmissionServiceAsync submissionService, LanguageServiceAsync languageService) {
		this.languageService = languageService;
		this.eventBus = eventBus;
		this.submissionService = submissionService;
		bind();
	}

	/**
     * 
     */
	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(ViewProblemSubmissionStatusEvent.TYPE, new ViewProblemSubmissionStatusEventHandler() {
			
			@Override
			public void onSubmitProblem(ViewProblemSubmissionStatusEvent event) {
				doViewProblemSubmissionStatus(event.problem);
				// TODO Auto-generated method stub
				
			}
		});
		// TODO Auto-generated method stub
	}

	  private void doViewProblemSubmissionStatus(ProblemData problem) {
		    History.newItem("problemSubmissionStatus");
		  }
	
	public void go(HasWidgets core, HasWidgets topPanel, HasWidgets leftPanel) {
		this.container = core;
		this.topPanel = topPanel;
		this.leftPanel = leftPanel;
		if ("".equals(History.getToken())) {
			History.newItem("problem");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.google.gwt.event.logical.shared.ValueChangeHandler#onValueChange(
	 * com.google.gwt.event.logical.shared.ValueChangeEvent)
	 */
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("problem")) {
				 presenter = new ProblemPresenter(submissionService, languageService, eventBus,new ProblemView());
			} else if (token.equals("problemSubmissionStatus")) {
				presenter = new ProblemSubmissionStatusPresenter(submissionService, eventBus, new ProblemSubmissionStatusView());
			} else if (token.equals("edit")) {
				// presenter = new EditContactPresenter(rpcService, eventBus,
				// new EditContactView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}



}
