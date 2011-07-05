package com.OJToolkit.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import com.OJToolkit.client.Services.CoderServiceAsync;
import com.OJToolkit.client.ValueObjects.CoderData;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

public class CoderListPresenter implements Presenter {
	interface Binder extends UiBinder<Widget, CoderListPresenter> {
	}

	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	CellTable<CoderData> cellTable;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager pager;

	/**
	 * Service to get populate list
	 */
	private final CoderServiceAsync coderService;

	/**
	 * The event bus to notify event changes
	 */
	private final HandlerManager eventBus;

	/**
	 * The map from column to its name, used in sending sorted column to RPC
	 */
	private HashMap<Column<CoderData, String>, String> columnMap;

	public CoderListPresenter(CoderServiceAsync coderService,
			HandlerManager eventBus) {
		this.coderService = coderService;
		this.eventBus = eventBus;
	}

	@Override
	public void go(HasWidgets container) {
		// Create a CellTable.
		cellTable = new CellTable<CoderData>();
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
		final SingleSelectionModel<CoderData> selectionModel = new SingleSelectionModel<CoderData>();
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						CoderData coderData = selectionModel
								.getSelectedObject();
						// eventBus.fireEvent(new
						// ViewProblemEvent(problemData));
						// TODO(ahmedazraq): fire open profile event
					}
				});
		cellTable.setSelectionModel(selectionModel);

		// Initialize the columns.
		initTableColumns(selectionModel, sortHandler);

		// Create a AsyncData Provider to get Data from server
		AsyncDataProvider<CoderData> dataProvider = new AsyncDataProvider<CoderData>() {
			@Override
			protected void onRangeChanged(HasData<CoderData> display) {
				final Range range = display.getVisibleRange();

				// Columns currently sorted
				String sortingQuery = getSortingQuery();

				// Fetch problem from server
				coderService.viewCoders(range, sortingQuery,
						new AsyncCallback<ArrayList<CoderData>>() {

							@Override
							public void onSuccess(ArrayList<CoderData> result) {
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

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns(
			final SelectionModel<CoderData> selectionModel,
			AsyncHandler sortHandler) {
		columnMap = new HashMap<Column<CoderData, String>, String>();

		// Username.
		Column<CoderData, String> usernameColumn = new Column<CoderData, String>(
				new TextCell()) {
			@Override
			public String getValue(CoderData object) {
				return object.getUsername();
			}
		};
		usernameColumn.setSortable(true);
		cellTable.addColumn(usernameColumn, "Username");
		cellTable.setColumnWidth(usernameColumn, 20, Unit.PCT);
		columnMap.put(usernameColumn, "username");

		// Spoj Username.
		Column<CoderData, String> SPOJusernameColumn = new Column<CoderData, String>(
				new TextCell()) {
			@Override
			public String getValue(CoderData object) {
				return object.getSPOJUsername();
			}
		};
		SPOJusernameColumn.setSortable(true);
		cellTable.addColumn(SPOJusernameColumn, "SPOJ");
		cellTable.setColumnWidth(SPOJusernameColumn, 20, Unit.PCT);
		columnMap.put(SPOJusernameColumn, "SPOJusername");
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
