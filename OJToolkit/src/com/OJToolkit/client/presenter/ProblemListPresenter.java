package com.OJToolkit.client.presenter;

import java.util.ArrayList;

import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.ViewProblemEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class ProblemListPresenter implements Presenter {

	public interface Display {
		/*
		 * HasClickHandlers getSubmitButton();
		 * void setProblemData(ProblemData problem);
		 * void setLanguages(ArrayList<LanguageData> languages);
		 * String getCode();
		 * String getSelectedLanguageValue();
		 * HasValue<String> getProblemTitle();
		 */
		AbstractHasData<ProblemData> getTable();
		void setProblemList(ArrayList<ProblemData> problemsList);
		void setNumberOfProblems(int numberOfProblems);
		void setPageStart(int pageStart);
		Widget asWidget();
	}

	private final Display display;
	private final SubmissionServiceAsync submissionService;
	private final HandlerManager eventBus;
	private final AbstractHasData<ProblemData> table;
	private final ArrayList<ProblemData> problemsList;
	private final int numberOfProblems = 6684;
	private int pageStart = 0;

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
				        System.out.println("Failed to load problems set!!");
			        }
		        });
	}

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


	private void bind() {
		onClickHandler();
		onPageChangeHandler();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
