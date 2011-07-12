/**
 * 
 */
package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ProblemStatusData;
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
 *         May 10, 2011
 */
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
	//private final String problemCode;
	private final ProblemData problem;
	
	private final boolean isVisible;
	
	private final String sourceCode;
	
	private final SourceCodeServiceAsync sourceCodeService;
	
	private final ArrayList<String> categoriesList;
	
	private final Long judgeSubmissionID;
	
	private  boolean isAnonymousSubmission = false;
	/**
	 * Generate Problem statue page
	 * @param categoriesList 
	 * @param submssionService
	 * @param eventBus
	 * @param display
	 */
	public ProblemSubmissionStatusPresenter(ProblemData problem, boolean isAnonymousSubmission, String sourceCode, boolean isVisible,
	        ArrayList<String> categoriesList, Long judgeSubmissionID, SubmissionServiceAsync submssionService, SourceCodeServiceAsync sourceCodeService, HandlerManager eventBus,
	        final Display display) {
		this.submssionService = submssionService;
		this.sourceCodeService = sourceCodeService;
		this.eventBus = eventBus;
		this.problemStatus = new ProblemStatusData();
		this.problem = problem;
		this.isAnonymousSubmission = isAnonymousSubmission;
		this.display = display;
		this.sourceCode = sourceCode;
		this.isVisible = isVisible;
		this.categoriesList = categoriesList;
		this.judgeSubmissionID = judgeSubmissionID;

		bind();
		addCategoriesToDB(problem.getProblemCode(),problem.getOjType(), categoriesList);
		callGetLastProblemStatusService();

	}
	

	
	/**
     * @param problemCode
     * @param ojType
     * @param categoriesList2
     */
    private void addCategoriesToDB(String problemCode, String ojType,
            ArrayList<String> categoriesList2) {
	   sourceCodeService.addCategories(problemCode, ojType, categoriesList2, new AsyncCallback<Void>() {
		
		@Override
		public void onSuccess(Void result) {
			System.out.println("submstatuspres-categoriesadded");
			
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}
	});
	    
    }



	/**
	 * get last problem statue 
	 */
	void callGetLastProblemStatusService() {
		submssionService
		        .getLastProblemStatus(isAnonymousSubmission, problem.getProblemCode(),problem.getOjType(),sourceCode, isVisible, judgeSubmissionID, new AsyncCallback<ProblemStatusData>() {

			        @Override
			        public void onSuccess(ProblemStatusData result) {
				        problemStatus = result;
				        display.setSubmissionResult(result);
				        
			        }

			        @Override
			        public void onFailure(Throwable caught) {
			        	Window.alert("Failed to get last problem results!!");
			        }
		        });
	}

	/**
     * 
     */
	private void bind() {
		display.getRefreshButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				callGetLastProblemStatusService();
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
