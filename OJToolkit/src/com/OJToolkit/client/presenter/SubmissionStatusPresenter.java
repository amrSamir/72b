package com.OJToolkit.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import com.OJToolkit.client.Services.SourceCodeServiceAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.OJToolkit.client.event.ViewCoderProfileEvent;
import com.OJToolkit.client.event.ViewSourceCodeEvent;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

public class SubmissionStatusPresenter implements Presenter {
	interface Binder extends UiBinder<Widget, SubmissionStatusPresenter> {
	}

	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	CellTable<SubmissionData> cellTable;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager pager;

	/**
	 * Service to get populate list
	 */
	private final SubmissionServiceAsync submissionService;

	/**
	 * The event bus to notify event changes
	 */
	private final HandlerManager eventBus;

	/**
	 * The map from column to its name, used in sending sorted column to RPC
	 */
	private HashMap<Column<SubmissionData, String>, String> columnMap;

	/**
	 * Previous sorting and search Query
	 */
	private String previousSortingQuery;
	
	private final SourceCodeServiceAsync sourceCodeService;

	public SubmissionStatusPresenter(SubmissionServiceAsync submissionService, SourceCodeServiceAsync sourceCodeService,
	        HandlerManager eventBus) {
		this.submissionService = submissionService;
		this.sourceCodeService = sourceCodeService;
		this.eventBus = eventBus;
	}
	
	void callIsCodeVisible(final long submissionID){
		sourceCodeService.isCodeVisible(submissionID, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result==true){
					eventBus.fireEvent(new ViewSourceCodeEvent(submissionID));
				} else{
					Window.alert("Sorry, This code is private to its writer");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		// Initialize previous Queries
		previousSortingQuery = "";

		// Create a CellTable.
		cellTable = new CellTable<SubmissionData>();
		cellTable.setWidth("100%", true);

		// Attach a Async sort handler to cellTable.
		AsyncHandler sortHandler = new AsyncHandler(cellTable);
		cellTable.addColumnSortHandler(sortHandler);

		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
		        .create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
		        true);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<SubmissionData> selectionModel = new SingleSelectionModel<SubmissionData>();
		selectionModel
		        .addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			        @Override
			        public void onSelectionChange(SelectionChangeEvent event) {
				        SubmissionData submissionData = selectionModel
				                .getSelectedObject();
				        callIsCodeVisible(submissionData.getSubmissionID());
			        }
		        });
		cellTable.setSelectionModel(selectionModel);

		// Initialize the columns.
		initTableColumns(selectionModel, sortHandler);

		// Create a AsyncData Provider to get Data from server
		AsyncDataProvider<SubmissionData> dataProvider = new AsyncDataProvider<SubmissionData>() {
			@Override
			protected void onRangeChanged(HasData<SubmissionData> display) {
				final Range range = display.getVisibleRange();

				// Columns currently sorted
				String sortingQuery = getSortingQuery();

				// If searching or sorting Queries changed, the range is reset
				if (!sortingQuery.equals(previousSortingQuery)) {
					pager.setPage(0);
					previousSortingQuery = sortingQuery;
				}

				// Fetch problem from server
				submissionService.getSubmissions(range, sortingQuery,
				        new AsyncCallback<ArrayList<SubmissionData>>() {

					        @Override
					        public void onSuccess(
					                ArrayList<SubmissionData> result) {
						        cellTable.setRowData(range.getStart(), result);

					        }

					        @Override
					        public void onFailure(Throwable caught) {
						        // TODO Auto-generated method stub

					        }
				        });
				
	
			}
		};
		// Fetch problem count from server
		submissionService.getSubmissionsCount(
				new AsyncCallback<Integer>() {

					@Override
					public void onSuccess(Integer size) {
						cellTable.setRowCount(size, true);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});


		// Add the CellTable to the adapter dataProvider.
		dataProvider.addDataDisplay(cellTable);

		// Create the UiBinder.
		Binder uiBinder = GWT.create(Binder.class);
		Widget widget = uiBinder.createAndBindUi(this);

		// Clear and draw on container
		container.clear();
		container.add(widget);
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns(
	        final SelectionModel<SubmissionData> selectionModel,
	        AsyncHandler sortHandler) {
		columnMap = new HashMap<Column<SubmissionData, String>, String>();

		// Username.
		Column<SubmissionData, String> usernameColumn = new Column<SubmissionData, String>(
		        new TextCell()) {
			@Override
			public String getValue(SubmissionData object) {
				return object.getUsername();
			}
		};
		usernameColumn.setSortable(true);
		cellTable.addColumn(usernameColumn, "username");
		cellTable.setColumnWidth(usernameColumn, 20, Unit.PCT);
		columnMap.put(usernameColumn, "username");

		
		Column<SubmissionData, String> problemTitleColumn = new Column<SubmissionData, String>(
		        new TextCell()) {
			@Override
			public String getValue(SubmissionData object) {
				return object.getProblemTitle();
			}
		};
		problemTitleColumn.setSortable(false);
		cellTable.addColumn(problemTitleColumn, "Problem Title");
		cellTable.setColumnWidth(problemTitleColumn, 20, Unit.PCT);
		
		
		
		Column<SubmissionData, String> judgeTypeColumn = new Column<SubmissionData, String>(
				new TextCell()) {
			@Override
			public String getValue(SubmissionData object) {
				return object.getJudgeType();
			}
		};
		judgeTypeColumn.setSortable(true);
		cellTable.addColumn(judgeTypeColumn, "Judge");
		cellTable.setColumnWidth(judgeTypeColumn, 20, Unit.PCT);
		columnMap.put(judgeTypeColumn, "judgeType");
		
		Column<SubmissionData, String> dateColumn = new Column<SubmissionData, String>(
				new TextCell()) {
			@Override
			public String getValue(SubmissionData object) {
				return object.getDate();
			}
		};
		dateColumn.setSortable(true);
		cellTable.addColumn(dateColumn, "Submission Date");
		cellTable.setColumnWidth(dateColumn, 20, Unit.PCT);
		columnMap.put(dateColumn, "date");
		
		Column<SubmissionData, String> judgeResultColumn = new Column<SubmissionData, String>(
				new TextCell()) {
			@Override
			public String getValue(SubmissionData object) {
				return object.getJudgeResult();
			}
		};
		judgeResultColumn.setSortable(true);
		cellTable.addColumn(judgeResultColumn, "Judge Result");
		cellTable.setColumnWidth(judgeResultColumn, 20, Unit.PCT);
		columnMap.put(judgeResultColumn, "judgeResult");
		
		Column<SubmissionData, String> memoryColumn = new Column<SubmissionData, String>(
				new TextCell()) {
			@Override
			public String getValue(SubmissionData object) {
				return object.getMemory();
			}
		};
		memoryColumn.setSortable(true);
		cellTable.addColumn(memoryColumn, "Memory");
		cellTable.setColumnWidth(memoryColumn, 20, Unit.PCT);
		columnMap.put(memoryColumn, "memory");
		
		Column<SubmissionData, String> timeColumn = new Column<SubmissionData, String>(
				new TextCell()) {
			@Override
			public String getValue(SubmissionData object) {
				return object.getTime();
			}
		};
		timeColumn.setSortable(true);
		cellTable.addColumn(timeColumn, "Time");
		cellTable.setColumnWidth(timeColumn, 20, Unit.PCT);
		columnMap.put(timeColumn, "time");
		
	}

	String getSortingQuery() {
		// Get the ColumnSortInfo from the table.
		final ColumnSortList sortList = cellTable.getColumnSortList();

		// Columns currently sorted
		String sortingQuery = "";

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
