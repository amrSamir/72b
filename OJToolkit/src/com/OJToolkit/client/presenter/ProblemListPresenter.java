/**
 * 
 */
package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.ViewProblemEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * @author 72B
 *         Apr 26, 2011
 */
public class ProblemListPresenter implements Presenter {

	public interface Display {
		/*
		 * HasClickHandlers getSubmitButton();
		 * void setProblemData(ProblemData problem);
		 * void setLanguages(ArrayList<LanguageData> languages);
		 * String getCode();
		 * String getSelectedLanguageValue();
		 */

		AbstractHasData<ProblemData> getTable();

		void setProblemList(ArrayList<ProblemData> problemsList);

		void setNumberOfProblems(int numberOfProblems);

		void setPageStart(int pageStart);

		Widget asWidget();

		// HasValue<String> getProblemTitle();

	}

	private final Display display;
	private final SubmissionServiceAsync submissionService;
	private final HandlerManager eventBus;
	private final AbstractHasData<ProblemData> table;
	private final ArrayList<ProblemData> problemsList;
	private int numberOfProblems = 2141 + 595;
	private  int pageStart = 0;
	String addedAccountsCookie = Cookies.getCookie("addedAccountsCookie");

	
	/**
	 * Genrate the Problem list presenter
	 * @param submissionService
	 * @param eventBus
	 * @param display
	 */
	public ProblemListPresenter(SubmissionServiceAsync submissionService,
	        HandlerManager eventBus, final Display display) {

		this.submissionService = submissionService;
		this.eventBus = eventBus;
		this.display = display;
		table = display.getTable();
		this.display.setNumberOfProblems(numberOfProblems);

		this.problemsList = new ArrayList<ProblemData>();
		for (int i = 0; i <= numberOfProblems; i++) {
			problemsList.add(null);
		}
		this.display.setProblemList(problemsList);
		this.display.setPageStart(pageStart);
		bind();
		fitchFiftyProblems();

	}

	/**
	 * fetch fifty problem in the problem table 
	 */
	public void fitchFiftyProblems() {
		submissionService.getProblems(pageStart,
		        new AsyncCallback<ArrayList<ProblemData>>() {

			        @Override
			        public void onSuccess(ArrayList<ProblemData> result) {

				        for (int i = 0; i < result.size(); i++) {
					        problemsList.set(pageStart + i, result.get(i));

				        }
				        table.setRowData(0, problemsList);
				        // display.setProblemList(problemsList);

			        }

			        @Override
			        public void onFailure(Throwable caught) {
				        System.out.println("Failure");
				        // TODO Auto-generated method stub

			        }
		        });

	}

	/**
	 * Listens to onclick event. If a problem is clicked, it opens the
	 * corresponding problem page for submission
	 */
	private void onClickHandler() {
		final SingleSelectionModel mySelectionModel = new SingleSelectionModel<ProblemData>();
		table.setSelectionModel(mySelectionModel);
		mySelectionModel.addSelectionChangeHandler(new Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				ProblemData problemData = (ProblemData) mySelectionModel
				        .getSelectedObject();
				eventBus.fireEvent(new ViewProblemEvent(problemData));

			}
		});
	}

	/**
	 * Listens to OnPageChange Event When the current page changes, it fetches
	 * the 50 problems of the corresponding index from the databse
	 */
	private void onPageChangeHandler() {
		table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
			@Override
			public void onRangeChange(RangeChangeEvent event) {
				pageStart = table.getPageStart();
				if (problemsList.get(pageStart) == null) {
					fitchFiftyProblems();
				}
			}
		});
	}

	/**
     * 
     */
	private void bind() {
		onClickHandler();
		onPageChangeHandler();
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
