package com.OJToolkit.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import com.OJToolkit.client.Services.ContestServicesAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.event.ViewProblemEvent;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent.Handler;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

public class ContestProblemsPresenter implements Presenter {
	interface Binder extends UiBinder<Widget, ContestProblemsPresenter> {
	}

	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	CellTable<ProblemData> cellTable;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager pager;

	@UiField(provided = true)
	Label contestName;

	@UiField(provided = true)
	ListBox contestsList;

	/**
	 * Service to get populate list
	 */
	private final SubmissionServiceAsync submissionService;
	private final ContestServicesAsync contestService;
	/**
	 * Previous sorting and search Query
	 */
	private String previousSortingQuery;
	private String previousSearchQuery;

	/**
	 * The event bus to notify event changes
	 */
	private final HandlerManager eventBus;

	private ArrayList<ContestData> contestList;
	private ListDataProvider<ProblemData> problemsprovider;
	private ArrayList<ProblemData> chossenproblems;
	/**
	 * The map from column to its name, used in sending sorted column to RPC
	 */
	private HashMap<Column<ProblemData, String>, String> columnMap;

	public ContestProblemsPresenter(SubmissionServiceAsync submissionService,
			ContestServicesAsync contestService, HandlerManager eventBus) {
		this.contestService = contestService;
		this.submissionService = submissionService;
		this.eventBus = eventBus;
	}

	@Override
	public void go(HasWidgets container) {
		// Initialize previous Queries
		previousSearchQuery = "";
		previousSortingQuery = "";

		// Create the search box & button
		contestName = new Label("choose contest");
		contestList = new ArrayList<ContestData>();
		contestList = getcontests();
		contestsList = new ListBox();
		contestsList.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				
				int selected = contestsList.getSelectedIndex() ;
				// on change load problems for this contest
				if (selected == -1 || selected == 0 ) {
					Window.alert("Please Chosse Contest First");
					return;
				}
				contestService.getProblemForContest(
						contestList.get(contestsList.getSelectedIndex()-1).getContestName(),
						new AsyncCallback<ArrayList<ProblemData>>() {

							@Override
							public void onSuccess(ArrayList<ProblemData> result) {
								cellTable.setRowData(result);
							}

							@Override
							public void onFailure(Throwable caught) {
								System.out
										.println("Failure-to load problems for contest");
							}
						});

			}
		});

		// Create a CellTable.
		cellTable = new CellTable<ProblemData>();
		cellTable.setWidth("100%", true);

		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<ProblemData> selectionModel = new SingleSelectionModel<ProblemData>();
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						final ProblemData problemData = selectionModel
								.getSelectedObject();
						eventBus.fireEvent(new ViewProblemEvent(problemData));
					}
				});
		cellTable.setSelectionModel(selectionModel);

		// Initialize the columns.
		initTableColumns(selectionModel);

		// Create a AsyncData Provider to get Data from server
		AsyncDataProvider<ProblemData> dataProvider = new AsyncDataProvider<ProblemData>() {
			@Override
			protected void onRangeChanged(HasData<ProblemData> display) {
				final Range range = display.getVisibleRange();

				// Columns currently sorted
				String sortingQuery = getSortingQuery();

				// If searching or sorting Queries changed, the range is reset
				if (!sortingQuery.equals(previousSortingQuery)) {
					pager.setPage(0);
					previousSortingQuery = sortingQuery;

				}

				if (contestsList.getSelectedIndex() != -1)
					contestService.getProblemForContest(
							contestList.get(contestsList.getSelectedIndex()-1)
									.getContestName(),
							new AsyncCallback<ArrayList<ProblemData>>() {

								@Override
								public void onSuccess(
										ArrayList<ProblemData> result) {
									cellTable.setRowData(result);
								}

								@Override
								public void onFailure(Throwable caught) {
									System.out
											.println("Failure-to load problems for contest");
								}
							});
			}
		};

		// Add the CellTable to the adapter dataProvider.
		dataProvider.addDataDisplay(cellTable);

		// Create the UiBinder.
		Binder uiBinder = GWT.create(Binder.class);
		Widget widget = uiBinder.createAndBindUi(this);

		// Clear and draw on container
		container.clear();
		container.add(widget);
	}

	private ArrayList<ContestData> getcontests() {
		final ArrayList<ContestData> contests = new ArrayList<ContestData>();
		contestService.getContests(new AsyncCallback<ArrayList<ContestData>>() {
			@Override
			public void onSuccess(ArrayList<ContestData> result) {
				contests.addAll(result);
				contestsList.addItem("No Contest");
				for (ContestData contest : contests) {
					contestsList.addItem(contest.getContestName());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Magdi-Failed-toLoad contests");
			}
		});
		return contests;
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns(
			final SelectionModel<ProblemData> selectionModel) {
		columnMap = new HashMap<Column<ProblemData, String>, String>();

		// Problem Code.
		Column<ProblemData, String> problemCodeColumn = new Column<ProblemData, String>(
				new TextCell()) {
			@Override
			public String getValue(ProblemData object) {
				return object.getProblemCode();
			}
		};
		problemCodeColumn.setSortable(true);
		cellTable.addColumn(problemCodeColumn, "Problem Code");
		cellTable.setColumnWidth(problemCodeColumn, 20, Unit.PCT);
		columnMap.put(problemCodeColumn, "problemCode");

		// Problem Name.
		Column<ProblemData, String> problemNameColumn = new Column<ProblemData, String>(
				new TextCell()) {
			@Override
			public String getValue(ProblemData object) {
				return object.getProblemName();
			}
		};
		problemNameColumn.setSortable(true);
		cellTable.addColumn(problemNameColumn, "Problem Name");
		cellTable.setColumnWidth(problemNameColumn, 60, Unit.PCT);
		columnMap.put(problemNameColumn, "problemName");

		// Online Judge.
		Column<ProblemData, String> onlineJudgeColumn = new Column<ProblemData, String>(
				new TextCell()) {
			@Override
			public String getValue(ProblemData object) {
				return object.getOjType();
			}
		};
		onlineJudgeColumn.setSortable(true);
		cellTable.addColumn(onlineJudgeColumn, "Online Judge");
		cellTable.setColumnWidth(onlineJudgeColumn, 20, Unit.PCT);
		columnMap.put(onlineJudgeColumn, "ojType");
	}

	String getSortingQuery() {
		// Get the ColumnSortInfo from the table.
		final ColumnSortList sortList = cellTable.getColumnSortList();

		// Columns currently sorted
		String sortingQuery = "";

		// If different sorting than searching, disable sorting
		if (sortList.size() == 0)
			return sortingQuery;

		for (int i = 0; i < sortList.size(); i++) {
			if (i != 0)
				sortingQuery += ", ";
			sortingQuery += columnMap.get(sortList.get(i).getColumn());
			sortingQuery += " ";
			sortingQuery += sortList.get(i).isAscending() ? "asc" : "desc";
		}
		return sortingQuery;
	}

}
