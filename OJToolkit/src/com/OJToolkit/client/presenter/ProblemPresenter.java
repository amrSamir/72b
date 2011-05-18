/**
 * 
 */
package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.LanguageServiceAsync;
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

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class ProblemPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSubmitButton();

		void setProblem(ProblemData problem);

		void setLanguages(ArrayList<LanguageData> languages);

		String getCode();

	//	String getProblemCode();

		String getSelectedLanguageValue();

		Widget asWidget();

		//HasValue<String> getProblemTitle();

	}

	private final Display display;
	private final SubmissionServiceAsync submssionService;
	private final HandlerManager eventBus;
	private ProblemData problem;
	private final String problemCode;
	private final LanguageServiceAsync languageService;


	public ProblemPresenter(String problemCode, SubmissionServiceAsync submssionService, LanguageServiceAsync languageService,
	        HandlerManager eventBus, final Display display) {
		this.languageService = languageService;
		this.submssionService = submssionService;
		this.eventBus = eventBus;
		this.problemCode = problemCode;	
		this.display = display;
	//	this.display.setProblem(problemCode);
		bind();

		this.submssionService.getProblem(problemCode, new AsyncCallback<ProblemData>() {
			
			@Override
			public void onSuccess(ProblemData result) {
				problem = result;
				display.setProblem(problem);
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("ProblemPresenterFailure");
				
			}
		});
		this.languageService
		        .getLanguages(new AsyncCallback<ArrayList<LanguageData>>() {

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
				        Window.alert("Failed to get list of languages");
				        // TODO Auto-generated method stub

			        }
		        });
	}

	/**
     * 
     */
	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				submssionService.submitCode(problem.getProblemCode(),
				        display.getCode(), display.getSelectedLanguageValue(),
				        new AsyncCallback<Void>() {

					        @Override
					        public void onSuccess(Void result) {
					        	eventBus.fireEvent(new ViewProblemSubmissionStatusEvent());
					        	//add code to db
						        // TODO Auto-generated method stub

					        }

					        @Override
					        public void onFailure(Throwable caught) {
					        	Window.alert("Failure");
						        // TODO Auto-generated method stub

					        }
				        });
				
				// TODO Auto-generated method stub

			}
		});
		// TODO Auto-generated method stub

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
