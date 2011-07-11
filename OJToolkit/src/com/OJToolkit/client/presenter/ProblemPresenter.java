package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.HintServiceAsync;
import com.OJToolkit.client.Services.LanguageServiceAsync;
import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.LanguageData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemTextData;
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

		boolean isAnonymousSubmission();

		boolean isVisible();

		void setProblemText(ProblemTextData problemText);
		
		ArrayList<String> getCheckedCategories();
		
		void setCategories(ArrayList<String> categoriesList);
	}

	private final Display display;
	private final SubmissionServiceAsync submssionService;
	private final HandlerManager eventBus;
	private ProblemData problem;
	private final String problemCode;
	private final String ojType;
	private final LanguageServiceAsync languageService;
	private SourceCodeServiceAsync sourceCodeService;
	private HintServiceAsync hintService;

	public ProblemPresenter(String substring,
	        SubmissionServiceAsync submssionService,
	        LanguageServiceAsync languageService, HandlerManager eventBus,
	        SourceCodeServiceAsync sourceCodeService, final Display display,
	        HintServiceAsync hintService) {

		this.languageService = languageService;
		this.submssionService = submssionService;
		this.eventBus = eventBus;
		String[] tokensArr = substring.split("-");
		this.problemCode = tokensArr[0];
		this.ojType = tokensArr[1];
		this.display = display;
		this.sourceCodeService = sourceCodeService;
		this.hintService = hintService;

		// this.display.setProblem(problemCode);
		bind();

		getCategories();
		getProblem();
		getLanguages();
		getProblemText();

	}

	/**
     * 
     */
    private void getCategories() {
	    this.sourceCodeService.getCategories(problemCode, ojType, new AsyncCallback<ArrayList<String>>() {
			
			@Override
			public void onSuccess(ArrayList<String> result) {
				display.setCategories(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	    
    }

	private void getProblem() {
		this.submssionService.getProblem(problemCode, ojType,
		        new AsyncCallback<ProblemData>() {
			        @Override
			        public void onSuccess(ProblemData result) {
				        problem = result;
				        display.setProblem(problem);
			        }

			        @Override
			        public void onFailure(Throwable caught) {
				        System.out.println("Failed to load problem");
			        }
		        });
	}

	private void getLanguages() {
		this.languageService.getLanguages(ojType,
		        new AsyncCallback<ArrayList<LanguageData>>() {

			        @Override
			        public void onSuccess(ArrayList<LanguageData> result) {
				        ArrayList<LanguageData> languages = new ArrayList<LanguageData>();
				        for (int i = 0; i < result.size(); i++)
					        languages.add(new LanguageData(result.get(i)
					                .getLanguageName(), result.get(i)
					                .getLanguageValue(), result.get(i)
					                .getOjType()));
				        display.setLanguages(languages);
			        }

			        @Override
			        public void onFailure(Throwable caught) {
				        Window.alert("Failed to get list of languages!!");
			        }
		        });
	}

	protected void getProblemText() {
		this.submssionService.getProblemText(problemCode, ojType,
		        new AsyncCallback<ProblemTextData>() {

			        @Override
			        public void onSuccess(ProblemTextData result) {
				        display.setProblemText(result);

			        }

			        @Override
			        public void onFailure(Throwable caught) {
				        System.out.println("ae-probpres-getprobtext-failure");

			        }
		        });

	}

	private void bind() {

		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				submssionService.submitCode(display.isAnonymousSubmission(),
				        problem.getProblemCode(), problem.getOjType(),
				        display.getCode(), display.getSelectedLanguageValue(),
				        new AsyncCallback<Void>() {
					        @Override
					        public void onSuccess(Void result) {
					        	
						       eventBus.fireEvent(new ViewProblemSubmissionStatusEvent(
						                problem, display
						                        .isAnonymousSubmission(),
						                display.getCode(), display.isVisible(), display.getCheckedCategories()));
					        }

					        @Override
					        public void onFailure(Throwable caught) {
						        Window.alert("Failed to submit!!");
						        caught.printStackTrace();
					        }
				        });

				// String sourceCodeString = ;
				java.util.Date date = new java.util.Date();

			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
}
