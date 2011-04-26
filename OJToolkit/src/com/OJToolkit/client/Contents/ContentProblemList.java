package com.OJToolkit.client.Contents;

import java.util.ArrayList;

import com.OJToolkit.client.CoreContainer;
import com.OJToolkit.client.Services.SubmissionService;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * @author 72B Apr 22, 2011 
 * This is a view class which is
 *         responsible for displaying the problems from the datastore in pages.
 *         Fifty problems are displayed on each page
 */
public class ContentProblemList extends Content {

	/**
	 * The Service for getting the problems from the server
	 */
	private final SubmissionServiceAsync submissionService = GWT
			.create(SubmissionService.class);

	/**
	 * Number of problems in database. It's constant as it costs much time to
	 * query all problems in the database so
	 */
	int numberOfProblems = 6684;

	/**
	 * The starting index of the page
	 */
	int pageStart = 0;

	/**
	 * ArrayList that contains the fetched problems from database
	 */
	ArrayList<ProblemData> problemsList;

	/**
	 * The widget which displays the problems
	 */
	CellTable<ProblemData> table;

	/**
	 * Display the problems in pages
	 */
	public ContentProblemList() {
		super();
		VerticalPanel vPanel = new VerticalPanel();
		ScrollPanel scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);
		scrollPanel.add(vPanel);
		table = new CellTable<ProblemData>();
		final ListDataProvider<ProblemData> dataProvider = new ListDataProvider<ProblemData>();

		// Set the columns
		TextColumn<ProblemData> problemCodeColumn = new TextColumn<ProblemData>() {
			@Override
			public String getValue(ProblemData problem) {
				return problem.getProblemCode();
			}
		};
		TextColumn<ProblemData> problemTitleColumn = new TextColumn<ProblemData>() {
			@Override
			public String getValue(ProblemData problem) {
				return problem.getProblemName();
			}
		};
		TextColumn<ProblemData> problemTypeColumn = new TextColumn<ProblemData>() {
			@Override
			public String getValue(ProblemData problem) {
				return problem.getType();
			}
		};

		// Add the columns to the table
		table.addColumn(problemCodeColumn, "Problem Code");
		table.addColumn(problemTitleColumn, "Problem Title");
		table.addColumn(problemTypeColumn, "Problem Type");

		// Set the total row count. This isn't strictly necessary, but it
		// affects paging calculations, so its good habit to keep the row count
		// up to date.
		table.setRowCount(numberOfProblems, true);

		SimplePager pager = new SimplePager();
		pager.setDisplay(table);
		pager.setPageSize(50);
		problemsList = new ArrayList<ProblemData>();
		// for caching purpose
		for (int i = 0; i <= numberOfProblems; i++) {
			problemsList.add(null);
		}

		dataProvider.setList(problemsList);

		// We need to fetch the first 50 problems from the database as they are
		// the first problems loaded
		submissionService.getProblems(pageStart,
				new AsyncCallback<ArrayList<ProblemData>>() {

					@Override
					public void onSuccess(ArrayList<ProblemData> result) {

						for (int i = 0; i < result.size(); i++) {
							problemsList.set(pageStart + i, result.get(i));

						}
						table.setRowData(0, problemsList);
					}

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Failure");
						// TODO Auto-generated method stub

					}
				});

		onClickHandler();
		onPageChangeHandler();
		dataProvider.addDataDisplay(table);
		vPanel.add(table);
		vPanel.add(pager);
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
				CoreContainer.getInstance().setContent(
						new ContentProblemPage(problemData));

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
					submissionService.getProblems(pageStart,
							new AsyncCallback<ArrayList<ProblemData>>() {

								@Override
								public void onSuccess(
										ArrayList<ProblemData> result) {

									for (int i = 0; i < result.size(); i++) {
										problemsList.set(pageStart + i,
												result.get(i));

									}
									table.setRowData(0, problemsList);
								}

								@Override
								public void onFailure(Throwable caught) {
									System.out.println("Failure");
									// TODO Auto-generated method stub

								}
							});
				}
			}
		});
	}
}
