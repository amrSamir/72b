/**
 * 
 */
package com.OJToolkit.client;

import java.util.Date;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.Services.HintServiceAsync;
import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.LoginServiceAsync;
import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.LoginInfo;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.AddAccountEvent;
import com.OJToolkit.client.event.AddAccountEventHandler;
import com.OJToolkit.client.event.AlreadyRegisteredEvent;
import com.OJToolkit.client.event.AlreadyRegisteredEventHandler;
import com.OJToolkit.client.event.CheckCookiesEvent;
import com.OJToolkit.client.event.CheckCookiesEventHandler;
import com.OJToolkit.client.event.LeftPanelEvent;
import com.OJToolkit.client.event.LeftPanelEventHandler;
import com.OJToolkit.client.event.LoginEvent;
import com.OJToolkit.client.event.LoginEventHandler;
import com.OJToolkit.client.event.RegisterationEventHandler;
import com.OJToolkit.client.event.RegistrationEvent;
import com.OJToolkit.client.event.TopPanelEvent;
import com.OJToolkit.client.event.TopPanelEventHandler;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.OJToolkit.client.event.ViewCodersEventHandler;
import com.OJToolkit.client.event.ViewProblemEvent;
import com.OJToolkit.client.event.ViewProblemEventHandler;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEvent;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEventHandler;
import com.OJToolkit.client.presenter.AddAccountPresenter;
import com.OJToolkit.client.presenter.CheckCookiesPresenter;
import com.OJToolkit.client.presenter.CodersPresenter;
import com.OJToolkit.client.presenter.InvitationPresenter;
import com.OJToolkit.client.presenter.LeftPanelPresenter;
import com.OJToolkit.client.presenter.LoginPresenter;
import com.OJToolkit.client.presenter.Presenter;
import com.OJToolkit.client.presenter.ProblemListPresenter;
import com.OJToolkit.client.presenter.ProblemPresenter;
import com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter;
import com.OJToolkit.client.presenter.RegistrationPresenter;
import com.OJToolkit.client.presenter.TopPanelPresenter;
import com.OJToolkit.client.view.AddAccountView;
import com.OJToolkit.client.view.CodersView;
import com.OJToolkit.client.view.InvitationView;
import com.OJToolkit.client.view.LeftPanelView;
import com.OJToolkit.client.view.LoginView;
import com.OJToolkit.client.view.ProblemListView;
import com.OJToolkit.client.view.ProblemSubmissionStatusView;
import com.OJToolkit.client.view.ProblemView;
import com.OJToolkit.client.view.RegistrationView;
import com.OJToolkit.client.view.TopPanelView;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
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
	private final SourceCodeServiceAsync sourceCodeService;
	private final HintServiceAsync hintService;

	private String problemStr = "problem";
	// public static boolean isEnabled;

	/**
	 * remembers cookie for 2 weeks.
	 */
	public static final Date COOKIES_EXPIRYDATE = new Date(
	        System.currentTimeMillis() + 1000 * 60 * 60);

	private HasWidgets container;
	private HasWidgets topPanel;
	private HasWidgets leftPanel;
	private ProblemData problem;
	private String OJType;
	private String problemCode;
	
	private LoginInfo loginInfo;

	/**
	 * control the site and changes the pages
	 * 
	 * @param eventBus
	 * @param submissionService
	 * @param languageService
	 * @param loginService
	 * @param coderService
	 * @param sourceCodeService
	 * @param hintService
	 */
	public AppController(HandlerManager eventBus,
	        SubmissionServiceAsync submissionService,
	        LanguageServiceAsync languageService,
	        LoginServiceAsync loginService, CoderServiceAsync coderService,
	        SourceCodeServiceAsync sourceCodeService,
	        HintServiceAsync hintService) {
		this.languageService = languageService;
		this.eventBus = eventBus;
		this.submissionService = submissionService;
		this.loginService = loginService;
		this.coderService = coderService;
		this.sourceCodeService = sourceCodeService;
		this.hintService = hintService;

		// checkCookies();
		bind();
	}

	/**
	 * bind all button with actions
	 */
	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(ViewProblemSubmissionStatusEvent.TYPE,
		        new ViewProblemSubmissionStatusEventHandler() {

			        @Override
			        public void onSubmitProblem(
			                ViewProblemSubmissionStatusEvent event) {
				        doViewProblemSubmissionStatus(event.problem);
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

		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {

			@Override
			public void onLogin(LoginEvent event) {
				doOnLogin(event.loginInfo);
			}

		});

		eventBus.addHandler(CheckCookiesEvent.TYPE,
		        new CheckCookiesEventHandler() {

			        @Override
			        public void onCheckCookies(CheckCookiesEvent event) {
				        doOnCheckCookies();

			        }

		        });

		eventBus.addHandler(LeftPanelEvent.TYPE, new LeftPanelEventHandler() {

			@Override
			public void onLeftPanel(LeftPanelEvent event) {
				doOnLeftPanel();

			}

		});
		
		eventBus.addHandler(TopPanelEvent.TYPE, new TopPanelEventHandler() {

	
			@Override
            public void onTopPanel(TopPanelEvent event) {
				doOnTopPanel(event.logoutURL);
	            
            }

		});

		eventBus.addHandler(ViewCodersEvent.TYPE, new ViewCodersEventHandler() {

			@Override
			public void onViewCoders(ViewCodersEvent event) {
				doOnViewCoders();

			}

		});

		eventBus.addHandler(AddAccountEvent.TYPE, new AddAccountEventHandler() {

			@Override
			public void onAddAccount(AddAccountEvent event) {
				doOnAddAccount();
			}

		});

	}

	/**
     * @param logoutURL2
     */
    protected void doOnTopPanel(String logoutURL) {
    	Presenter presenter = new TopPanelPresenter(
		        logoutURL, new TopPanelView());
		presenter.go(this.topPanel);
    }

	/**
     * 
     */
	protected void doOnLeftPanel() {
		Presenter presenter = new LeftPanelPresenter(
		        new LeftPanelView(eventBus));
		presenter.go(this.leftPanel);
		// TODO Auto-generated method stub

	}

	/**
	 * add cookies to history
	 */
	protected void doOnCheckCookies() {
		History.newItem("checkCookies");

	}

	/**
	 * add login in history
	 * @param loginInfo 
	 */
	protected void doOnLogin(LoginInfo result) {
		loginInfo = result;
		History.newItem("login");

	}

	/**
	 * add an account details event
	 * 
	 * @param oJType
	 */
	protected void doOnAddAccountDetails(String oJType) {
		this.OJType = oJType;
		History.newItem("addAccountDetails");

	}

	/**
	 * add an account
	 */
	protected void doOnAddAccount() {
		History.newItem("addAccount");

	}

	/**
	 * view coders even
	 */
	protected void doOnViewCoders() {
		History.newItem("viewCoders");

	}

	/**
	 * view problem
	 * 
	 * @param problem
	 *            to be shown
	 */
	protected void doViewProblem(ProblemData problem) {
		this.problem = problem;
		problemStr = "problem" + problem.getProblemCode() + "-"
		        + problem.getOjType();
		History.newItem(problemStr);

	}

	/**
	 * do register
	 */
	protected void doOnRegistration() {
		History.newItem("Registration");

	}

	/**
	 * already registered
	 */
	private void doIfRegistered() {
		History.newItem("alreadyRegistered");
	}

	/**
	 * view problem submission
	 * 
	 * @param problemCode
	 */
	private void doViewProblemSubmissionStatus(ProblemData problem) {
		this.problem = problem;
		History.newItem("problemSubmissionStatus");
	}

	public void go(Panel core, HasWidgets topPanel, HasWidgets leftPanel) {
		this.container = core;
		this.topPanel = topPanel;
		this.leftPanel = leftPanel;
		Presenter presenter = null;
		String logoutURLCookie = Cookies.getCookie("logoutURL");

		presenter = new TopPanelPresenter(logoutURLCookie==null?"":logoutURLCookie,new TopPanelView());
		presenter.go(this.topPanel);

		presenter = new LeftPanelPresenter(new LeftPanelView(eventBus));
		presenter.go(this.leftPanel);
		if ("".equals(History.getToken())) {

			// String sessionID = Cookies.getCookie("reg");

			// if (sessionID != null) {
			// History.newItem("alreadyRegistered");
			// } else {
			History.newItem("invitation");
			// }
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
		String isEnabledCookie = Cookies.getCookie("isEnabledCookie");
		String isInvitedCookie = Cookies.getCookie("isInvitedCookie");
		System.out.println("AE-AppController-Status:token=" + token
		        + "&isEnabled=" + (isEnabledCookie != null) + "&isInvited="
		        + (isInvitedCookie != null));
		if (token != null) {

			Presenter presenter = null;
			if ((isInvitedCookie == null)) {
				presenter = new InvitationPresenter(eventBus,
				        new InvitationView());
			}

			// System.out.println(isInvitedCookie);
			if (isInvitedCookie != null && isEnabledCookie != null) {
				if (token.equals("invitation")) {
					presenter = new CheckCookiesPresenter(coderService, loginService,
					        eventBus);
				}
				if (token.equals("login")) {
					presenter = new LoginPresenter(loginInfo, loginService, coderService,
					        eventBus, new LoginView());
				} else if (token.equals("Registration")) {
					presenter = new RegistrationPresenter(coderService,
					        eventBus, new RegistrationView());
				} else if (token.equals("problemSubmissionStatus")) {
					presenter = new ProblemSubmissionStatusPresenter(problem,
					        submissionService, eventBus,
					        new ProblemSubmissionStatusView());
				} else if (token.startsWith("problem")) {
					if (problem == null) {
						presenter = new ProblemPresenter(token.substring(7),
						        submissionService, languageService, eventBus,
						        sourceCodeService, new ProblemView(),
						        hintService);
					} else {
						presenter = new ProblemPresenter(
						        problem.getProblemCode() + "-"
						                + problem.getOjType(),
						        submissionService, languageService, eventBus,
						        sourceCodeService, new ProblemView(),
						        hintService);
					}
				} else if (token.equals("alreadyRegistered")) {
					presenter = new ProblemListPresenter(submissionService,
					        eventBus, new ProblemListView());

				} else if (token.equals("viewCoders")) {
					presenter = new CodersPresenter(coderService, eventBus,
					        new CodersView());
				} else if (token.equals("addAccount")) {
					presenter = new AddAccountPresenter(coderService, eventBus,
					        new AddAccountView());
				} else if (token.equals("checkCookies")) {
					presenter = new CheckCookiesPresenter(coderService, loginService,
					        eventBus);
				}
			} else if (isInvitedCookie != null) {
				if (token.equals("login")) {
					presenter = new LoginPresenter(loginInfo, loginService, coderService,
					        eventBus, new LoginView());
				} else if (token.equals("Registration")) {
					presenter = new RegistrationPresenter(coderService,
					        eventBus, new RegistrationView());
				} else if (token.equals("invitation")) {
					presenter = new CheckCookiesPresenter(coderService, loginService,
					        eventBus);
				} else {
					presenter = new CheckCookiesPresenter(coderService, loginService,
					        eventBus);
				}

			}
			if (presenter != null) {
				presenter.go(container);
			}

		}
	}
}
