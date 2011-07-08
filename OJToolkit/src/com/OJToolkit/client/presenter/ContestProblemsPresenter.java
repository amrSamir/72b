package com.OJToolkit.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import com.OJToolkit.client.Services.ContestServicesAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
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

	/**
	 * The secoundry CellTable.
	 */
	@UiField(provided = true)
	CellList<ProblemData> chossedproblemsTable;

	/**
	 * The search textbox.
	 */
	@UiField(provided = true)
	TextBox searchBox;

	/**
	 * The search button.
	 */
	@UiField(provided = true)
	Button searchButton;

	/**
	 * The search type.
	 */
	@UiField(provided = true)
	ListBox searchType;

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
				// TODO load all problems
				contestService.getProblemForContest(
						contestList.get(contestsList.getSelectedIndex() - 1)
								.getContestName(),
						new AsyncCallback<ArrayList<ProblemData>>() {
							@Override
							public void onSuccess(ArrayList<ProblemData> result) {
								chossenproblems.clear();
								chossenproblems.addAll(result);
								problemsprovider.setList(chossenproblems);
							}

							@Override
							public void onFailure(Throwable caught) {
								System.out
										.println("Magdi-can't load problems for contest");
							}
						});
			}
		});
		// add contests

		searchBox = new TextBox();
		searchType = new ListBox();
		searchType.addItem("Problem Code", "problemCode");
		searchType.addItem("Problem Name", "problemName");
		searchType.addItem("Online Judge", "ojType");
		searchButton = new Button();
		searchButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cellTable.setVisibleRangeAndClearData(
						cellTable.getVisibleRange(), true);
			}
		});

		// Create a CellTable.
		cellTable = new CellTable<ProblemData>();
		cellTable.setWidth("100%", true);

		problemsprovider = new ListDataProvider<ProblemData>();
		AbstractCell<ProblemData> chossenproblemList = new AbstractCell<ProblemData>() {

			@Override
			public void render(Context context, ProblemData value,
					SafeHtmlBuilder sb) {
				if (value == null)
					return;
				sb.appendHtmlConstant("<table>");
				sb.appendHtmlConstant("<tr><td>");
				sb.appendHtmlConstant(value.getProblemName() + "-"
						+ value.getOjType());
				sb.appendHtmlConstant("</td><tr>");
				sb.appendHtmlConstant("</table>");
			}
		};
		chossenproblems = new ArrayList<ProblemData>();
		chossedproblemsTable = new CellList<ProblemData>(chossenproblemList);
		makeSelectionModel() ;
		problemsprovider.addDataDisplay(chossedproblemsTable);
		problemsprovider.setList(chossenproblems);
		// Attach a Async sort handler to cellTable.
		AsyncHandler sortHandler = new AsyncHandler(cellTable);
		cellTable.addColumnSortHandler(new Handler() {
			@Override
			public void onColumnSort(ColumnSortEvent event) {
				if (event.getColumn() == null)
					return;

				String columnName = columnMap.get(event.getColumn());
				if (columnName.equals("problemCode"))
					searchType.setSelectedIndex(0);
				else if (columnName.equals("problemName"))
					searchType.setSelectedIndex(1);
				else
					searchType.setSelectedIndex(2);
			}
		});
		cellTable.addColumnSortHandler(sortHandler);

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
						System.out.println(contestList.get(
								contestsList.getSelectedIndex() - 1)
								.getContestName());
						contestService.addProblemToContest(contestList.get(contestsList.getSelectedIndex() - 1)
										.getContestName(), problemData
										.getProblemCode(), problemData
										.getOjType(),
								new AsyncCallback<Boolean>() {

									@Override
									public void onSuccess(Boolean result) {
										if(result){
											chossenproblems.add(problemData);
											problemsprovider.setList(chossenproblems);
											System.out.println("Magdi-Problem Added");
										}else{
											Window.alert("problem already exsist") ;
										}
									}

									@Override
									public void onFailure(Throwable caught) {
										System.out
												.println("Magdi-Problem failed to add");
									}
								});
					}
				});
		cellTable.setSelectionModel(selectionModel);

		// Initialize the columns.
		initTableColumns(selectionModel, sortHandler);

		// Create a AsyncData Provider to get Data from server
		AsyncDataProvider<ProblemData> dataProvider = new AsyncDataProvider<ProblemData>() {
			@Override
			protected void onRangeChanged(HasData<ProblemData> display) {
				final Range range = display.getVisibleRange();

				// Columns currently sorted
				String sortingQuery = getSortingQuery();

				// Current search string
				String searchQuery = getSearchQuery();

				// If searching or sorting Queries changed, the range is reset
				if (!sortingQuery.equals(previousSortingQuery)
						|| !searchQuery.equals(previousSearchQuery)) {
					pager.setPage(0);
					previousSortingQuery = sortingQuery;
					previousSearchQuery = searchQuery;
				}

				// Fetch problem from server
				submissionService.getProblems(range, sortingQuery, searchQuery,
						new AsyncCallback<ArrayList<ProblemData>>() {

							@Override
							public void onSuccess(ArrayList<ProblemData> result) {
								cellTable.setRowData(range.getStart(), result);
							}

							@Override
							public void onFailure(Throwable caught) {
								System.out.println("Failure");
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

	private void makeSelectionModel() {
		final SingleSelectionModel<ProblemData> selectionModel = new SingleSelectionModel<ProblemData>();
		chossedproblemsTable.setSelectionModel(selectionModel) ;
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				chossenproblems.remove(selectionModel.getSelectedObject()) ;
				problemsprovider.setList(chossenproblems);
				deleteProblem(selectionModel.getSelectedObject()) ;
			}		
		});
	}
	private void deleteProblem(ProblemData selectedObject) {
		contestService.deleteProblemFromContest(contestList.get(contestsList.getSelectedIndex() - 1)
				.getContestName(), selectedObject.getProblemCode(), selectedObject.getOjType(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						System.out.println("deleted from problem");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Failed to remove !!!");
					}
				});
	}
	private ArrayList<ContestData> getcontests() {
		final ArrayList<ContestData> contests = new ArrayList<ContestData>();
		contestService.getContests(new AsyncCallback<ArrayList<ContestData>>() {

			@Override
			public void onSuccess(ArrayList<ContestData> result) {
				contests.addAll(result);
				contestsList.addItem("No chontest");
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
			final SelectionModel<ProblemData> selectionModel,
			AsyncHandler sortHandler) {
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
		if (sortList.size() == 0
				|| !columnMap.get(sortList.get(0).getColumn()).equals(
						searchType.getValue(searchType.getSelectedIndex())))
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

	String getSearchQuery() {
		String type = searchType.getValue(searchType.getSelectedIndex());
		return type + " >= \"" + searchBox.getText() + "\" && " + type
				+ " < \"" + searchBox.getText() + "\ufffd\"";
	}

}
