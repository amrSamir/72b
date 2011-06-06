package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.HintServiceAsync;
import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.LanguageData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.ViewProblemSubmissionStatusEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ProblemPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSubmitButton();
		void setProblem(ProblemData problem);
		void setLanguages(ArrayList<LanguageData> languages);
		String getCode();
		String getSelectedLanguageValue();
		Widget asWidget();
	}

	private final Display display;
	private final SubmissionServiceAsync submssionService;
	private final HandlerManager eventBus;
	private ProblemData problem;
	private final String problemCode;
	private final LanguageServiceAsync languageService;
	private SourceCodeServiceAsync sourceCodeService ;
	private HintServiceAsync hintService;

	public ProblemPresenter(String problemCode,
	        SubmissionServiceAsync submssionService,
	        LanguageServiceAsync languageService, HandlerManager eventBus,
	        SourceCodeServiceAsync sourceCodeService,
	        final Display display,
	        HintServiceAsync hintService) {
		
	
		this.languageService = languageService;
		this.submssionService = submssionService;
		this.eventBus = eventBus;
		this.problemCode = problemCode;
		this.display = display;
		this.sourceCodeService = sourceCodeService ;
		this.hintService = hintService;
		
		// this.display.setProblem(problemCode);
		bind();

		this.submssionService.getProblem(problemCode,
		        new AsyncCallback<ProblemData>() {
			        @Override
			        public void onSuccess(ProblemData result) {
				        problem = result;
				        getLanguages();
				        display.setProblem(problem);				        
			        }
			        @Override
			        public void onFailure(Throwable caught) {
				        System.out.println("Failed to load problem");
			        }
		        });
		
	}
	
	private void getLanguages(){
		  this.languageService
	        .getLanguages(problem.getOjType(),new AsyncCallback<ArrayList<LanguageData>>() {

		        @Override
		        public void onSuccess(ArrayList<LanguageData> result) {
			        ArrayList<LanguageData> languages = new ArrayList<LanguageData>();
			        for (int i = 0; i < result.size(); i++)
				        languages.add(new LanguageData(result.get(i)
				                .getLanguageName(), result.get(i)
				                .getLanguageValue(), "SPOJ"));
			        display.setLanguages(languages);
		        }

		        @Override
		        public void onFailure(Throwable caught) {
			        Window.alert("Failed to get list of languages!!");
		        }
	        });
	}

	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("aah");
			//	String sourceCodeString = ;
				java.util.Date date = new java.util.Date();
				
				/// Delete this >>>>> Just for testing
				hintService.addHint("X", "eshtaaaaaaa3aaaal", new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						 Window.alert("Good!");
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						 Window.alert("NO");
					}
				});
				
				
				// Review your code data paramters again 
				sourceCodeService.addCode(display.getCode(), problem.getProblemCode()
						, problem.getProblemName(), problem.getUrl()
						, new AsyncCallback<Void>()   
						{

							@Override
							public void onFailure(Throwable caught) {
								 Window.alert("Oh! Failed to save code!");
								
							}

							@Override
							public void onSuccess(Void result) {
								 Window.alert("your code has been saved!");
									
							}
					
				});
				
			 
				
				submssionService.submitCode(problem.getProblemCode(),
				        display.getCode(), display.getSelectedLanguageValue(),
				        new AsyncCallback<Void>() {
					        @Override
					        public void onSuccess(Void result) {
						        eventBus.fireEvent(new ViewProblemSubmissionStatusEvent());
					        }

					        @Override
					        public void onFailure(Throwable caught) {
						        Window.alert("Failed to submit!!");
					        }
				        });
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
}
