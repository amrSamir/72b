package com.OJToolkit.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.OJToolkit.client.Services.ContestServicesAsync;
import com.OJToolkit.client.Services.SubmissionServiceAsync;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent.Handler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

public class ContestManagerPresenter implements Presenter {
	public interface Display {
		void setContests(ArrayList<ContestData> contests);

		Button getSubmissionButton();

		TextBox getContestName();

		TextBox getContestAccessCode();

		ContestData getChossenContest();

		DateBox getStartDate();

		DateBox getEndDate();

		Widget asWidget();

		ListBox getcontestlists();

		StackLayoutPanel getSplitPanel();

		CellList<ProblemData> getCellList();

		CellTable<ProblemData> getCellTable();

		void setProblemsForContest(ArrayList<ProblemData> problemsForContest) ;
		
		SimplePager getPager();

		ListBox getSearchType();

		TextBox getSearchBOX();
		Button getSearchButton();
	}

	private final Display display;
	private final ContestServicesAsync contestServises;
	private final SubmissionServiceAsync submissionServices ;
	private final HandlerManager eventBus;
	private ArrayList<ContestData> contests;
	private ArrayList<ProblemData> chossenproblems;
	private ListDataProvider<ProblemData> problemsprovider;
	private HashMap<Column<ProblemData, String>, String> columnMap;
	private String previousSortingQuery;
	private String previousSearchQuery;

	public ContestManagerPresenter(ContestServicesAsync contestServices,SubmissionServiceAsync submissionServices ,
			HandlerManager eventBus, final Display display) {
		contests = new ArrayList<ContestData>();
		chossenproblems = new ArrayList<ProblemData>();
		problemsprovider = new ListDataProvider<ProblemData>();
		this.display = display;
		this.contestServises = contestServices;
		this.eventBus = eventBus;
		this.submissionServices = submissionServices ;
		getContests();
		bind();
	}

	private void getContests() {
		contestServises.getContestForAdmin(new AsyncCallback<ArrayList<ContestData>>() {
					@Override
					public void onSuccess(ArrayList<ContestData> result) {
						contests.clear();
						contests.addAll(result);
						display.setContests(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Magdi-contestmanger-Failed to load contests for admin");
					}
				});
	}

	private void bind() {
		makeSelectionModel(display.getCellList());
		doOnchange(display.getcontestlists());
		onChangeSplit(display.getSplitPanel());
		onSubmission();
		onSearchPRoblem();
		initCellTable();
	}

	private void initCellTable() {
		AsyncHandler sortHandler = new AsyncHandler(display.getCellTable());
		display.getCellTable().addColumnSortHandler(new Handler() {
			@Override
			public void onColumnSort(ColumnSortEvent event) {
				if (event.getColumn() == null)
					return;

				String columnName = columnMap.get(event.getColumn());
				if (columnName.equals("problemCode"))
					display.getSearchType().setSelectedIndex(0);
				else if (columnName.equals("problemName"))
					display.getSearchType().setSelectedIndex(1);
				else
					display.getSearchType().setSelectedIndex(2);
			}
		});
		display.getCellTable().addColumnSortHandler(sortHandler);
		//seclection mode 
		final SingleSelectionModel<ProblemData> selectionModel = new SingleSelectionModel<ProblemData>();
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						final ProblemData problemData = selectionModel
								.getSelectedObject();
						contestServises.addProblemToContest(display.getChossenContest().getContestName()
										, problemData
										.getProblemCode(), problemData
										.getOjType(),
								new AsyncCallback<Boolean>() {

									@Override
									public void onSuccess(Boolean result) {
										if(result){
											chossenproblems.add(problemData);
											problemsprovider.setList(chossenproblems);
											display.setProblemsForContest(chossenproblems) ;
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
		display.getCellTable().setSelectionModel(selectionModel);
		// Initialize the columns.
				initTableColumns(selectionModel, sortHandler);

				// Create a AsyncData Provider to get Data from server
				AsyncDataProvider<ProblemData> dataProvider = new AsyncDataProvider<ProblemData>() {
					@Override
					protected void onRangeChanged(HasData<ProblemData> displayd) {
						final Range range = displayd.getVisibleRange();

						// Columns currently sorted
						String sortingQuery = getSortingQuery();

						// Current search string
						String searchQuery = getSearchQuery();

						// If searching or sorting Queries changed, the range is reset
						if (!sortingQuery.equals(previousSortingQuery)
								|| !searchQuery.equals(previousSearchQuery)) {
							display.getPager().setPage(0);
							previousSortingQuery = sortingQuery;
							previousSearchQuery = searchQuery;
						}

						// Fetch problem from server
						submissionServices.getProblems(range, sortingQuery, searchQuery,
								new AsyncCallback<ArrayList<ProblemData>>() {

									@Override
									public void onSuccess(ArrayList<ProblemData> result) {
										display.getCellTable().setRowData(range.getStart(), result);
									}

									@Override
									public void onFailure(Throwable caught) {
										System.out.println("Failure");
									}
								});
					}
				};

				// Add the CellTable to the adapter dataProvider.
				dataProvider.addDataDisplay(display.getCellTable());

	}

	String getSortingQuery() {
		// Get the ColumnSortInfo from the table.
		final ColumnSortList sortList = display.getCellTable().getColumnSortList();

		// Columns currently sorted
		String sortingQuery = "";

		// If different sorting than searching, disable sorting
		if (sortList.size() == 0
				|| !columnMap.get(sortList.get(0).getColumn()).equals(
						display.getSearchType().getValue(display.getSearchType().getSelectedIndex())))
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
		String type = display.getSearchType().getValue(display.getSearchType().getSelectedIndex());
		return type + " >= \"" + display.getSearchBOX().getText() + "\" && " + type
				+ " < \"" + display.getSearchBOX().getText() + "\ufffd\"";
	}

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
		display.getCellTable().addColumn(problemCodeColumn, "Problem Code");
		display.getCellTable().setColumnWidth(problemCodeColumn, 20, Unit.PCT);
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
		display.getCellTable().addColumn(problemNameColumn, "Problem Name");
		display.getCellTable().setColumnWidth(problemNameColumn, 60, Unit.PCT);
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
		display.getCellTable().addColumn(onlineJudgeColumn, "Online Judge");
		display.getCellTable().setColumnWidth(onlineJudgeColumn, 20, Unit.PCT);
		columnMap.put(onlineJudgeColumn, "ojType");
	}

	private void onSearchPRoblem() {
		display.getSearchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.getCellTable().setVisibleRangeAndClearData(
						display.getCellTable().getVisibleRange(), true);
			}
		});
	}

	private void makeSelectionModel(CellList<ProblemData> chossedproblemsTable) {
		final SingleSelectionModel<ProblemData> selectionModel = new SingleSelectionModel<ProblemData>();
		chossedproblemsTable.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						chossenproblems.remove(selectionModel
								.getSelectedObject());
						problemsprovider.setList(chossenproblems);
						display.setProblemsForContest(chossenproblems) ;
						deleteProblem(selectionModel.getSelectedObject());
					}
				});
	}

	private void deleteProblem(ProblemData selectedObject) {
		contestServises.deleteProblemFromContest(display.getChossenContest()
				.getContestName(), selectedObject.getProblemCode(),
				selectedObject.getOjType(), new AsyncCallback<Void>() {

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

	private void onSubmission() {
		display.getSubmissionButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContestData currentContest = display.getChossenContest();
				if (currentContest == null)
					addContest();
				else {
					editContest(currentContest);
				}
			}

			private void editContest(ContestData currentContest) {
				String contestAccessCode = display.getContestAccessCode()
						.getValue();
				Date contestStartDate = display.getStartDate().getValue();
				Date contestEndDate = display.getEndDate().getValue();
				ContestData contest = new ContestData(currentContest
						.getContestName(), contestAccessCode, contestStartDate,
						contestEndDate);
				contestServises.EditContest(contest,
						new AsyncCallback<Boolean>() {
							@Override
							public void onSuccess(Boolean result) {
								System.out
										.println("Magdi-edit contest has done successfuly");
							}

							@Override
							public void onFailure(Throwable caught) {
								System.out
										.println("Magdi-edit contest has failed :( ");
							}
						});
			}

			private void addContest() {
				String contestName = display.getContestName().getValue();
				String contestAccessCode ;
				if(display.getContestAccessCode() == null)
					contestAccessCode = "" ;
				else
					contestAccessCode = display.getContestAccessCode()
						.getValue();
				Date contestStartDate = display.getStartDate().getValue();
				Date contestEndDate = display.getEndDate().getValue();
				contestServises.addContest(contestName, contestAccessCode,
						contestStartDate, contestEndDate,
						new AsyncCallback<Boolean>() {
							@Override
							public void onSuccess(Boolean result) {
								if (result) {
									// alert in alert part
									System.out
											.println("Contest Add succsefuly ");
									getContests() ;
								} else {
									// alert in alert part
									System.out.println("Contest Name Already Exsist");
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								// alert in alert part
								System.out.println("Sorry :( My bad Can't Load Contests");
							}
						});
			}
		});

	}

	void onChangeSplit(final StackLayoutPanel splitPanel) {
		splitPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				int selectedPanel = splitPanel.getVisibleIndex();
				int selected = display.getcontestlists().getSelectedIndex();
				if (selectedPanel == 1 && (selected == 0 || selected == -1)) {
					Window.alert("Please chosse contest first");
					splitPanel.showWidget(0);
				} else if(selected != 0 && selected != -1) {
					// Load problems for this contest
					loadproblems(selected - 1);
				}
			}

			private void loadproblems(int selected) {
				contestServises.getProblemForContest(contests.get(selected)
						.getContestName(),
						new AsyncCallback<ArrayList<ProblemData>>() {

							@Override
							public void onSuccess(ArrayList<ProblemData> result) {
								chossenproblems.clear();
								chossenproblems.addAll(result);
								display.setProblemsForContest(chossenproblems) ;
								// TODO load problems in table
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Can't Load Problems!!");
							}
						});

			}
		});
	}

	private void doOnchange(final ListBox listContests) {
		listContests.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selected = listContests.getSelectedIndex();
				String name = "", Accesscode = "";
				Date start = new Date(), end = new Date();
				if (selected == 0) {
					display.getSubmissionButton().setText("Add Contest");
					start = null;
					end = null;
				} else {
					LoadproblemsForcontest(selected - 1);
					display.getSubmissionButton().setText("Edit Contest");
					ContestData contest = contests.get(selected - 1);
					name = contest.getContestName();
					Accesscode = contest.getContestAccessCode();
					start = contest.getStartTime();
					end = contest.getEndTime();
				}
				display.getContestName().setText(name);
				display.getContestAccessCode().setText(Accesscode);
				display.getStartDate().setValue(start);
				display.getEndDate().setValue(end);
			}

			private void LoadproblemsForcontest(int selected) {

			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
