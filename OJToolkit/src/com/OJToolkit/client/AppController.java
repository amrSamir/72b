/**
 * 
 */
package com.OJToolkit.client;

import java.util.ArrayList;

import com.OJToolkit.client.Contents.MyResource;
import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.Services.ContestServicesAsync;
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
import com.OJToolkit.client.event.ContestAdminEvent;
import com.OJToolkit.client.event.ContestAdminEventHandler;
import com.OJToolkit.client.event.ContestProblemEvent;
import com.OJToolkit.client.event.ContestProblemEventHandler;
import com.OJToolkit.client.event.JoinContestEvent;
import com.OJToolkit.client.event.JoinContestEventHandler;
import com.OJToolkit.client.event.LeftPanelEvent;
import com.OJToolkit.client.event.LeftPanelEventHandler;
import com.OJToolkit.client.event.LoginEvent;
import com.OJToolkit.client.event.LoginEventHandler;
import com.OJToolkit.client.event.ManageContestEvent;
import com.OJToolkit.client.event.ManageContestEventHandler;
import com.OJToolkit.client.event.RegisterationEventHandler;
import com.OJToolkit.client.event.RegistrationEvent;
import com.OJToolkit.client.event.TopPanelEvent;
import com.OJToolkit.client.event.TopPanelEventHandler;
import com.OJToolkit.client.event.ViewCoderProfileEvent;
import com.OJToolkit.client.event.ViewCoderProfileEventHandler;
import com.OJToolkit.client.event.ViewCodersEvent;
import com.OJToolkit.client.event.ViewCodersEventHandler;
import com.OJToolkit.client.event.ViewContestEvent;
import com.OJToolkit.client.event.ViewContestEventHandler;
import com.OJToolkit.client.event.ViewProblemEvent;
import com.OJToolkit.client.event.ViewProblemEventHandler;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEvent;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEventHandler;
import com.OJToolkit.client.event.ViewSourceCodeEvent;
import com.OJToolkit.client.event.ViewSourceCodeEventHandler;
import com.OJToolkit.client.event.ViewSubmissionsEvent;
import com.OJToolkit.client.event.ViewSubmissionsEventHandler;
import com.OJToolkit.client.presenter.AboutPresenter;
import com.OJToolkit.client.presenter.AddAccountPresenter;
import com.OJToolkit.client.presenter.CheckCookiesPresenter;
import com.OJToolkit.client.presenter.CoderListPresenter;
import com.OJToolkit.client.presenter.CoderProfilePresenter;
import com.OJToolkit.client.presenter.ContestManagerPresenter;
import com.OJToolkit.client.presenter.ContestProblemsPresenter;
import com.OJToolkit.client.presenter.HelpPresenter;
import com.OJToolkit.client.presenter.InvitationPresenter;
import com.OJToolkit.client.presenter.JoinContestPresenter;
import com.OJToolkit.client.presenter.LeftPanelPresenter;
import com.OJToolkit.client.presenter.LoginPresenter;
import com.OJToolkit.client.presenter.Presenter;
import com.OJToolkit.client.presenter.ProblemList2Presenter;
import com.OJToolkit.client.presenter.ProblemPresenter;
import com.OJToolkit.client.presenter.ProblemSubmissionStatusPresenter;
import com.OJToolkit.client.presenter.RegistrationPresenter;
import com.OJToolkit.client.presenter.SourceCodePresenter;
import com.OJToolkit.client.presenter.SubmissionStatusPresenter;
import com.OJToolkit.client.presenter.TopPanelPresenter;
import com.OJToolkit.client.presenter.ViewContestPresenter;
import com.OJToolkit.client.presenter.WelcomePresenter;
import com.OJToolkit.client.view.AddAccountView;
import com.OJToolkit.client.view.ContestMangerView;
import com.OJToolkit.client.view.InvitationView;
import com.OJToolkit.client.view.JoinContestView;
import com.OJToolkit.client.view.LeftPanelView;
import com.OJToolkit.client.view.LoginView;
import com.OJToolkit.client.view.ProblemSubmissionStatusView;
import com.OJToolkit.client.view.RegistrationView;
import com.OJToolkit.client.view.TopPanelView;
import com.OJToolkit.client.view.ViewContestView;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author 72B Apr 26, 2011
 */
public class AppController implements ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final SubmissionServiceAsync submissionService;
	private final LanguageServiceAsync languageService;
	private final LoginServiceAsync loginService;
	private final CoderServiceAsync coderService;
	private final SourceCodeServiceAsync sourceCodeService;
	private final HintServiceAsync hintService;
	private final ContestServicesAsync contestServices;

	private String problemStr = "problem";

	private ArrayList<String> categoriesList;

	private boolean isAnonymousSubmission = false;
	// public static boolean isEnabled;

	/**
	 * remembers cookie for 2 weeks.
	 */
	/*
	 * public static final Date COOKIES_EXPIRYDATE = new Date(
	 * System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 14);
	 */

	private RootPanel rootPanel;
	private Panel dockPanel;
	private HasWidgets container;
	private HasWidgets topPanel;
	private HasWidgets leftPanel;
	private LeftPanelPresenter leftPanelPresenter;
	private ProblemData problem;
	private String OJType;
	private String problemCode;

	private boolean isVisible;

	private String sourceCode;

	private LoginInfo loginInfo;
	private Long judgeSubmissionID;

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
	        HintServiceAsync hintService, ContestServicesAsync contestService) {
		this.languageService = languageService;
		this.eventBus = eventBus;
		this.submissionService = submissionService;
		this.loginService = loginService;
		this.coderService = coderService;
		this.sourceCodeService = sourceCodeService;
		this.hintService = hintService;
		this.contestServices = contestService;
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
				        doViewProblemSubmissionStatus(event.problem,
				                event.isAnonymousSubmission, event.sourceCode,
				                event.isVisible, event.categoriesList,
				                event.judgeSubmissionID);
			        }
		        });
		eventBus.addHandler(ContestProblemEvent.TYPE,
		        new ContestProblemEventHandler() {

			        @Override
			        public void onContestProblems(
			                ContestProblemEvent contestProblemEvent) {
				        doViewProblemContest();
			        }

		        });
		eventBus.addHandler(JoinContestEvent.TYPE,
		        new JoinContestEventHandler() {

			        @Override
			        public void onJoinContest(JoinContestEvent joinContestEvent) {
				        doJoinContest();

					}
				});

		eventBus.addHandler(ViewContestEvent.TYPE,
				new ViewContestEventHandler() {

					@Override
					public void onViewContest(ViewContestEvent viewContestEvent) {
						doViewContest();
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

		eventBus.addHandler(ContestAdminEvent.TYPE,
				new ContestAdminEventHandler() {

					@Override
					public void onContestAdmin(
							ContestAdminEvent contestAdminEvent) {
						doContestAdmin();
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
		
		eventBus.addHandler(ManageContestEvent.TYPE, new ManageContestEventHandler() {
			
			@Override
			public void onManageContest(ManageContestEvent event) {
				doOnManageContest() ;
			}
		});

		eventBus.addHandler(AddAccountEvent.TYPE, new AddAccountEventHandler() {

			@Override
			public void onAddAccount(AddAccountEvent event) {
				doOnAddAccount();
			}

		});

		eventBus.addHandler(ViewCoderProfileEvent.TYPE,
				new ViewCoderProfileEventHandler() {

					@Override
					public void onViewCoderProfile(ViewCoderProfileEvent event) {
						doOnViewCoderProfileEvent(event.username);
					}

				});

		eventBus.addHandler(ViewSubmissionsEvent.TYPE,
				new ViewSubmissionsEventHandler() {

					@Override
					public void onViewSubmissions(ViewSubmissionsEvent event) {
						doOnViewCoderProfileEvent(); //TODO:(azraq) sounds weird!!
					}

				});
		eventBus.addHandler(ViewSourceCodeEvent.TYPE, new ViewSourceCodeEventHandler() {
			
			@Override
			public void onViewSourceCode(ViewSourceCodeEvent event) {
				doOnViewSourceCode(event.submissionID);
				
			}
		});

	}

	/**
     * @param submissionID
     */
    protected void doOnViewSourceCode(long submissionID) {
	    History.newItem("sourceCode_"+submissionID);
    }

	/**
     * 
     */
	protected void doOnViewCoderProfileEvent() {
		History.newItem("status");
	}

	/**
	 * @param username
	 */
	protected void doOnViewCoderProfileEvent(String username) {
		History.newItem("profile=" + username);
	}

	/**
	 * @param logoutURL2
	 */
	protected void doOnTopPanel(String logoutURL) {
		Presenter presenter = new TopPanelPresenter(logoutURL, coderService,
				new TopPanelView());
		presenter.go(this.topPanel);
	}

	private void doViewProblemContest() {
		History.newItem("ProblemsContest");
	}

	/**
     * 
     */
	protected void doOnLeftPanel() {
		leftPanelPresenter = new LeftPanelPresenter(new LeftPanelView(eventBus));
		leftPanelPresenter.go(this.leftPanel);

	}

	private void doJoinContest() {
		History.newItem("joinContest");
	}

	private void doViewContest() {
		History.newItem("ViewContest");

	}

	/**
	 * add cookies to history
	 */
	protected void doOnCheckCookies() {
		History.newItem("checkCookies");

	}

	/**
	 * add login in history
	 * 
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

	private void doContestAdmin() {
		History.newItem("contestAdmin");
	}

	/**
	 * view coders even
	 */
	protected void doOnViewCoders() {
		History.newItem("viewCoders");

	}
	protected void doOnManageContest() {
		History.newItem("ContestManger");
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
		History.newItem("problemList");
	}

	/**
	 * view problem submission
	 * 
	 * @param isAnonymousSubmission2
	 * @param isVisible2 
	 * @param sourceCode2 
	 * @param categoriesList2 
	 * @param judgeSubmissionID 
	 * @param problemCode
	 */
	private void doViewProblemSubmissionStatus(ProblemData problem,
	        boolean isAnonymousSubmission2, String sourceCode2,
	        boolean isVisible2, ArrayList<String> categoriesList2,
	        Long judgeSubmissionID) {
		this.problem = problem;
		this.isAnonymousSubmission = isAnonymousSubmission2;
		this.sourceCode = sourceCode2;
		this.isVisible = isVisible2;
		this.categoriesList = categoriesList2;
		this.judgeSubmissionID = judgeSubmissionID;
		History.newItem("problemSubmissionStatus");
	}

	public void go(RootPanel rootPanel) {
		this.rootPanel = rootPanel ;
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		this.dockPanel = dockLayoutPanel;
		dockLayoutPanel.setSize("100%", "100%");

		/**
		 * North panel
		 */
		AbsolutePanel topPanel = new AbsolutePanel();
		dockLayoutPanel.addNorth(topPanel, 5);
		topPanel.setSize("100%", "100%");

		/**
		 * Footer
		 */
		// TODO(amrsamir): Refactor to footer presenter
		HorizontalPanel footer = new HorizontalPanel();
		footer.setStyleName("footer");
		footer.setSize("100%", "100%");
		Label copyrights = new Label("©All Copyright Reserved 2011 CodeMashup");
		footer.add(copyrights);
		Label contactUs = new Label("Contact Us");
		contactUs.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		// <iframe
		// src="https://spreadsheets.google.com/spreadsheet/embeddedform?formkey=dDBzWW5PNkZ6RlJIZ2lGcHlUek5fS0E6MQ"
		// width="760" height="1214" frameborder="0" marginheight="0"
		// marginwidth="0">Loading...</iframe>
		final DialogBox dlgfeedback = createFeedback();
		contactUs.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dlgfeedback.center();
				dlgfeedback.show();
			}
		});
		footer.add(copyrights);
		footer.add(contactUs);

		dockLayoutPanel.addSouth(footer, 1.5);

		/**
		 * West panel
		 */
		AbsolutePanel leftPanel = new AbsolutePanel();
		leftPanel.setStyleName("LeftPanel");
		dockLayoutPanel.addWest(leftPanel, 11);
		leftPanel.setSize("100%", "100%");

		/**
		 * Center panel
		 */
		AbsolutePanel core = new AbsolutePanel();
		core.setStylePrimaryName("CorePanel");
		core.setSize("100%", "100%");
		// ScrollPanel scrollcore = new ScrollPanel(core);
		// scrollcore.setSize("100%", "100%");
		dockLayoutPanel.add(core);

		this.container = core;
		this.topPanel = topPanel;
		this.leftPanel = leftPanel;
		Presenter presenter = null;
		String logoutURLCookie = Cookies.getCookie("logoutURL");

		presenter = new TopPanelPresenter(logoutURLCookie == null ? ""
		        : logoutURLCookie, coderService, new TopPanelView());
		presenter.go(this.topPanel);

		presenter = new LeftPanelPresenter(new LeftPanelView(eventBus));
		presenter.go(this.leftPanel);
		leftPanelPresenter = (LeftPanelPresenter) presenter;
		if ("".equals(History.getToken())) {
			History.newItem("welcome");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	private DialogBox createFeedback() {
		final DialogBox dlgfeedback = new DialogBox();
		dlgfeedback.setGlassEnabled(true);
		// feedback.setAnimationEnabled(true);
		dlgfeedback.setText("Feedback");
		// feedback.
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Button("Close", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dlgfeedback.hide();
			}
		}));
		TabLayoutPanel tp = new TabLayoutPanel(2, Unit.EM);
		HTML html = new HTML(
		        "<iframe src=\"https://spreadsheets.google.com/spreadsheet/embeddedform?formkey=dDBzWW5PNkZ6RlJIZ2lGcHlUek5fS0E6MQ\" width=\"760\" height=\"1214\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\">Loading...</iframe>");
		tp.add(new ScrollPanel(html), "Feedback");
		html = new HTML(
		        "<iframe src=\"https://spreadsheets0.google.com/spreadsheet/embeddedform?formkey=dEU2SEtsSXg4ZE5xaUJ5LWlIaDdsRlE6MQ\" width=\"760\" height=\"623\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\">Loading...</iframe>");
		tp.add(new ScrollPanel(html), "Bug Report");
		tp.setSize("750px", "500px");
		tp.setAnimationDuration(500);

		vp.add(tp);
		// feedback.setSize("100px","500px");
		dlgfeedback.add(vp);
		return dlgfeedback;
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
		        + (isInvitedCookie != null ? isInvitedCookie : "NO"));
		if (token != null) {
			Presenter presenter = null;
			if (isInvitedCookie == null) {
				// Not invited
				if (token.equals("invitation")) {
					presenter = new InvitationPresenter(eventBus,
					        new InvitationView());
				} else if (token.equals("welcome")) {
					presenter = new WelcomePresenter(eventBus);
				} else {
					History.newItem("welcome");
				}
			} else if (isInvitedCookie != null && isEnabledCookie == null) {
				// Invited but not enabled
				if (token.equals("login")) {
					presenter = new LoginPresenter(loginInfo, loginService,
							coderService, eventBus, new LoginView());
				} else if (token.equals("Registration")) {
					presenter = new RegistrationPresenter(coderService,
							eventBus, new RegistrationView());
				} else {
					presenter = new CheckCookiesPresenter(coderService,
							loginService, eventBus);
				}
			} else if (isInvitedCookie != null && isEnabledCookie != null) {
				// Invited & Enabled
				if (token.equals("problemSubmissionStatus")) {
					// TODO(ahmedazraq): leeh yakhod problem ya man?
					if (problem != null) {
						presenter = new ProblemSubmissionStatusPresenter(
						        problem, isAnonymousSubmission, sourceCode,
						        isVisible, categoriesList, judgeSubmissionID,
						        submissionService, sourceCodeService, eventBus,
						        new ProblemSubmissionStatusView());
						leftPanelPresenter
						        .setSelected(LeftPanelView.Labels.ViewProblems);
					}
				} else if (token.equals("problemList")) {
					presenter = new ProblemList2Presenter(submissionService,
					        eventBus);
					leftPanelPresenter
					        .setSelected(LeftPanelView.Labels.ViewProblems);
				} else if (token.startsWith("problem")) {
					if (problem == null) {
						presenter = new ProblemPresenter(token.substring(7),
						        submissionService, languageService, eventBus,
						        sourceCodeService,
						        hintService);
						// TODO(ahmedazraq): why use 2 calls when you use 1 call
						presenter = new ProblemPresenter(
						        problem.getProblemCode() + "-"
						                + problem.getOjType(),
						        submissionService, languageService, eventBus,
						        sourceCodeService,
						        hintService);
					}
					leftPanelPresenter
					        .setSelected(LeftPanelView.Labels.ViewProblems);
				} else if (token.equals("viewCoders")) {
					presenter = new CoderListPresenter(coderService, eventBus);
					leftPanelPresenter
					        .setSelected(LeftPanelView.Labels.ViewCoders);
				} else if (token.equals("addAccount")) {
					presenter = new AddAccountPresenter(coderService, eventBus,
					        new AddAccountView());
					leftPanelPresenter
					        .setSelected(LeftPanelView.Labels.AddAccounts);
				} 
				else if (token.equals("ContestManger")){
					presenter = new ContestManagerPresenter(contestServices,submissionService, eventBus, new ContestMangerView()) ;
					leftPanelPresenter.setSelected(LeftPanelView.Labels.ContestAdmin);
				}
				else if (token.equals("joinContest")) {
					presenter = new JoinContestPresenter(contestServices,
					        eventBus, new JoinContestView());
					leftPanelPresenter
							.setSelected(LeftPanelView.Labels.JoinContest);
				} else if (token.equals("ViewContest")) {
					presenter = new ViewContestPresenter(contestServices,
							eventBus, new ViewContestView());
					leftPanelPresenter
							.setSelected(LeftPanelView.Labels.ViewContest);
				} else if (token.equals("ProblemsContest")) {
					presenter = new ContestProblemsPresenter(submissionService,
							contestServices, eventBus);
					leftPanelPresenter
							.setSelected(LeftPanelView.Labels.AddProblemToContest);
				} else if (token.startsWith("profile")) {
					presenter = new CoderProfilePresenter(coderService, sourceCodeService,
							token.substring(8), eventBus);
					leftPanelPresenter
							.setSelected(LeftPanelView.Labels.ViewCoders);
				} else if (token.equals("status")) {
					presenter = new SubmissionStatusPresenter(
					        submissionService, sourceCodeService, eventBus);
					leftPanelPresenter.setSelected(LeftPanelView.Labels.Status);
				} else if (token.startsWith("sourceCode_")) {
					presenter = new SourceCodePresenter(Long.valueOf(token
					        .substring(11)), sourceCodeService);

				} else if (token.startsWith("help")) {
					presenter = new HelpPresenter(eventBus);
				}else if (token.startsWith("about")) {
					presenter = new AboutPresenter(eventBus);
				} else {
					presenter = new CheckCookiesPresenter(coderService,
					        loginService, eventBus);
				}
			}
			if (presenter != null) {
				// Make sure its split view
				// TODO: check the speed issue here
				if (token.equals("welcome") && isInvitedCookie == null && isEnabledCookie == null ) {
					rootPanel.clear();
					presenter.go(rootPanel);
				} else {
					rootPanel.clear();
					rootPanel.add(dockPanel, 0, 0);
					/**
					 * Feedback
					 */
					ImageResource img = MyResource.INSTANCE.imgFeedback();
					final Image lblfeedback = new Image(img.getURL());

					lblfeedback.setSize("30px", "113px");
					final DialogBox dlgfeedback = createFeedback();
					lblfeedback.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							lblfeedback.setUrl(MyResource.INSTANCE
									.imgFeedback().getURL());
							dlgfeedback.center();
							dlgfeedback.show();
						}
					});
					lblfeedback.addMouseOverHandler(new MouseOverHandler() {

						@Override
						public void onMouseOver(MouseOverEvent event) {
							lblfeedback.setUrl(MyResource.INSTANCE
									.imgFeedback2().getURL());
						}
					});
					lblfeedback.addMouseOutHandler(new MouseOutHandler() {

						@Override
						public void onMouseOut(MouseOutEvent event) {
							lblfeedback.setUrl(MyResource.INSTANCE
							        .imgFeedback().getURL());
						}
					});
					rootPanel.add(lblfeedback, Window.getClientWidth() - 30,
					        (Window.getClientHeight() / 2 - 113 / 2));
					Window.addResizeHandler(new ResizeHandler() {

						 public void onResize(ResizeEvent event) {
							 rootPanel.add(lblfeedback, event.getWidth() - 30,
										(event.getHeight() / 2 - 113 / 2));
						 }
						});
					
					presenter.go(container);
				}
			}
		}
	}
}
