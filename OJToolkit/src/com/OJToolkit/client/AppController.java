/**
 * 
 */
package com.OJToolkit.client;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.LoginServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEventHandler;
import com.OJToolkit.client.event.RegisterationEventHandler;
import com.OJToolkit.client.event.RegistrationEvent;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.OJToolkit.client.event.ViewCodersEventHandler;
import com.OJToolkit.client.event.ViewProblemEvent;
import com.OJToolkit.client.event.ViewProblemEventHandler;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEvent;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEventHandler;
import com.OJToolkit.client.presenter.CodersPresenter;
import com.OJToolkit.client.presenter.LoginPresenter;
import com.OJToolkit.client.presenter.Presenter;
import com.OJToolkit.client.presenter.ProblemListPresenter;
import com.OJToolkit.client.presenter.ProblemPresenter;
import com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter;
import com.OJToolkit.client.presenter.RegistrationPresenter;
import com.OJToolkit.client.view.CodersView;
import com.OJToolkit.client.view.LoginView;
import com.OJToolkit.client.view.ProblemListView;
import com.OJToolkit.client.view.ProblemSubmissionStatusView;
import com.OJToolkit.client.view.ProblemView;
import com.OJToolkit.client.view.RegistrationView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class AppController implements ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final SubmissionServiceAsync submissionService;
	private final LanguageServiceAsync languageService;
	private final LoginServiceAsync loginService;
	private final CoderServiceAsync coderService;
	private HasWidgets container;
	private HasWidgets topPanel;
	private HasWidgets leftPanel;
	private ProblemData problem;

	public AppController(HandlerManager eventBus,
	        SubmissionServiceAsync submissionService,
	        LanguageServiceAsync languageService,
	        LoginServiceAsync loginService, CoderServiceAsync coderService) {
		this.languageService = languageService;
		this.eventBus = eventBus;
		this.submissionService = submissionService;
		this.loginService = loginService;
		this.coderService = coderService;
		bind();
	}

	/**
     * 
     */
	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(ViewProblemSubmissionStatusEvent.TYPE,
		        new ViewProblemSubmissionStatusEventHandler() {

			        @Override
			        public void onSubmitProblem(
			                ViewProblemSubmissionStatusEvent event) {
				        doViewProblemSubmissionStatus();
				        // TODO Auto-generated method stub

			        }
		        });

		eventBus.addHandler(ViewProblemEvent.TYPE,
		        new ViewProblemEventHandler() {

			        @Override
			        public void onViewProblem(ViewProblemEvent event) {
				        doViewProblem(event.problem);

			        }
		        });

		eventBus.addHandler(AlreadyRegisteredEvent.TYPE,
		        new AlreadyRegisteredEventHandler() {

			        @Override
			        public void ifRegistered(AlreadyRegisteredEvent event) {
				        doIfRegistered();

			        }

		        });

		eventBus.addHandler(RegistrationEvent.TYPE,
		        new RegisterationEventHandler() {

			        @Override
			        public void onRegistration(RegistrationEvent event) {
				        doOnRegistration();

			        }
		        });
		
		eventBus.addHandler(ViewCodersEvent.TYPE,
		        new ViewCodersEventHandler() {

					@Override
                    public void onViewCoders(ViewCodersEvent event) {
	                    doOnViewCoders();
	                    
                    }

			   
		        });
		// TODO Auto-generated method stub
	}

	/**
     * 
     */
    protected void doOnViewCoders() {
    	History.newItem("viewCoders");
	    // TODO Auto-generated method stub
	    
    }

	/**
	 * @param problem
	 */
	protected void doViewProblem(ProblemData problem) {
		this.problem = problem;
		History.newItem("problem");

	}

	/**
     * 
     */
	protected void doOnRegistration() {
		History.newItem("Registration");

	}

	private void doIfRegistered() {
		History.newItem("alreadyRegistered");
	}

	private void doViewProblemSubmissionStatus() {
		History.newItem("problemSubmissionStatus");
	}

	public void go(Panel core, HasWidgets topPanel, HasWidgets leftPanel) {
		this.container = core;
		this.topPanel = topPanel;
		this.leftPanel = leftPanel;
		if ("".equals(History.getToken())) {
			History.newItem("login");
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
			if (token.equals("login")) {
				presenter = new LoginPresenter(loginService, coderService,
				        eventBus, new LoginView());
			} else if (token.equals("problem")) {
				presenter = new ProblemPresenter(problem, submissionService,
				        languageService, eventBus, new ProblemView());
			} else if (token.equals("problemSubmissionStatus")) {
				presenter = new ProblemSubmissionStatusPresenter(
				        submissionService, eventBus,
				        new ProblemSubmissionStatusView());
			} else if (token.equals("Registration")) {
				presenter = new RegistrationPresenter(coderService, eventBus,
				        new RegistrationView());
			} else if (token.equals("alreadyRegistered")) {
				presenter = new ProblemListPresenter(submissionService,
				        eventBus, new ProblemListView());
			}  else if (token.equals("viewCoders")) {
				presenter = new ProblemListPresenter(submissionService,
				        eventBus, new ProblemListView());
			}
			
			

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

}
