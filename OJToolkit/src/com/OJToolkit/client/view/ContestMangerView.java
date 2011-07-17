package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.presenter.ContestManagerPresenter.Display;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import com.google.gwt.view.client.ListDataProvider;

public class ContestMangerView extends Composite implements Display {

	ListBox listContests;
	StackLayoutPanel splitPanel;
	TextBox contestName;
	TextBox contestAccessCode;
	DateBox contestStartDate;
	DateBox contestEndDate;
	Button btnSubmission;
	CellList ContestProblemsList;
	TextBox searchBox;
	ListBox searchType;
	Button searchButton;
	CellTable<ProblemData> cellTable;
	SimplePager pager;

	ArrayList<ContestData> contests;
	private ArrayList<ProblemData> chossenproblems;
	private ListDataProvider<ProblemData> problemsprovider;

	public ContestMangerView() {
		// init
		searchBox = new TextBox();
		searchType = new ListBox();
		searchType.addItem("Problem Code", "problemCode");
		searchType.addItem("Problem Name", "problemName");
		searchType.addItem("Online Judge", "ojType");
		searchButton = new Button("search");
		cellTable = new CellTable<ProblemData>();
		pager = new SimplePager();
		//
		VerticalPanel masterPanel = new VerticalPanel();
		masterPanel.setSize("100%", "100%");

		splitPanel = new StackLayoutPanel(Unit.EM);
		splitPanel.setSize("100%", "100%");

		masterPanel.add(splitPanel);

		initWidget(masterPanel);

		VerticalPanel MainPanel = new VerticalPanel();
		MainPanel.setSize("100%", "100%");
		// Label lblManageContest = new Label("Manage Contest");
		// MainPanel.add(lblManageContest) ;
		// MainPanel.setCellHeight(lblManageContest, "5%");
		MainPanel.add(Hpanal1());

		VerticalPanel chosseproblemPanel = new VerticalPanel();
		chosseproblemPanel.setSize("100%", "100%");

		splitPanel.add(MainPanel, "Manage Contest", 2);
		// *************second panel(chossen problem)***********
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
		ContestProblemsList = new CellList<ProblemData>(chossenproblemList);
		chossenproblems = new ArrayList<ProblemData>();
		problemsprovider = new ListDataProvider<ProblemData>();
		problemsprovider.addDataDisplay(ContestProblemsList);
		problemsprovider.setList(chossenproblems);
		// TODO add cell list to the second panel
		// ////////////////

		chosseproblemPanel.add(Hpanal2());
		splitPanel.add(chosseproblemPanel, "Chosse Problem", 2);
	}

	VerticalPanel Hpanal2() {
		VerticalPanel MainPanel = new VerticalPanel();
		MainPanel.setSize("100%", "100%") ;
		HorizontalPanel searchPanel = new HorizontalPanel();
		searchPanel.add(searchType);
		searchPanel.add(searchBox);
		searchPanel.add(searchButton);
		MainPanel.add(searchPanel);
		MainPanel.setCellHeight(searchPanel, "5%");
		HorizontalPanel MainsplitPanel = new HorizontalPanel();
		MainsplitPanel.setSize("100%", "100%");
		// TODO d
		VerticalPanel panelAllproblems = getAllproblemsPanel();
		ScrollPanel panelchossenProblems = getchossenProblemsPanel();
		MainsplitPanel.add(panelAllproblems);
		MainsplitPanel.setCellWidth(panelAllproblems, "60%");
		MainsplitPanel.add(panelchossenProblems);
		MainsplitPanel.setCellWidth(panelchossenProblems, "40%");
		MainPanel.add(MainsplitPanel);
		return MainPanel;
	}

	private ScrollPanel getchossenProblemsPanel() {
		ScrollPanel chossenproblemspanel = new ScrollPanel();
		chossenproblemspanel.add(ContestProblemsList);
		ContestProblemsList.setHeight("100%");
		ContestProblemsList.setWidth("100%");
		chossenproblemspanel.setHeight("100%");
		chossenproblemspanel.setWidth("100%");
		return chossenproblemspanel;
	}

	private VerticalPanel getAllproblemsPanel() {
		VerticalPanel allproblemsPanel = new VerticalPanel();
		allproblemsPanel.setSize("100%", "100%"	);
		cellTable.setSize("100%", "100%");
		allproblemsPanel.add(cellTable);
		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(cellTable);

		allproblemsPanel.add(pager);
		return allproblemsPanel;
	}

	HorizontalPanel Hpanal1() {
		HorizontalPanel MainsplitPanel = new HorizontalPanel();
		MainsplitPanel.setSize("100%", "100%");

		// init listBox of contests
		initlistContests();
		// init listBox of Info
		VerticalPanel ContestInfoPanel = initContestInfo();

		MainsplitPanel.add(listContests);
		MainsplitPanel.setCellWidth(listContests, "10%");
		MainsplitPanel.add(ContestInfoPanel);
		MainsplitPanel.setCellWidth(ContestInfoPanel, "100%");
		return MainsplitPanel;
	}

	@Override
	public void setContests(ArrayList<ContestData> contests) {
		listContests.clear() ;
		this.contests = contests;
		listContests.addItem("New Contest");
		for (ContestData contets : contests) {
			String s = "-" ;
			if(contets.getContestAccessCode().equalsIgnoreCase(""))
				s = "+" ;
			listContests.addItem(contets.getContestName()+s);
		}
	}

	private void initlistContests() {
		listContests = new ListBox();
		listContests.setSize("100%", "100%");
		listContests.setTitle("Chosse Problem");
		listContests.setVisibleItemCount(36);
		//
	}

	private VerticalPanel initContestInfo() {
		VerticalPanel ContestInfoPanel = new VerticalPanel();
		ContestInfoPanel.setTitle("Contest INFO");
		ContestInfoPanel.setHeight("100%");

		HorizontalPanel contestNamePanel = new HorizontalPanel();
		Label name = new Label("Name");
		contestName = new TextBox();
		contestNamePanel.add(name);
		contestNamePanel.add(contestName);
		//
		HorizontalPanel contestAccessCodePanel = new HorizontalPanel();
		Label AccessCode = new Label("Access Code");
		contestAccessCode = new TextBox();
		contestAccessCodePanel.add(AccessCode);
		contestAccessCodePanel.add(contestAccessCode);
		//
		HorizontalPanel contestStartDatePanel = new HorizontalPanel();
		Label startDate = new Label("Start Date");
		contestStartDate = new DateBox();
		contestStartDatePanel.add(startDate);
		contestStartDatePanel.add(contestStartDate);
		//
		HorizontalPanel contestEndDatePanel = new HorizontalPanel();
		Label endDate = new Label("End Date");
		contestEndDate = new DateBox();
		contestEndDatePanel.add(endDate);
		contestEndDatePanel.add(contestEndDate);
		//
		HorizontalPanel contestSumissionPanel = new HorizontalPanel();
		btnSubmission = new Button("Add Contest");
		contestSumissionPanel.add(btnSubmission);

		// add 5 panels
		ContestInfoPanel.add(contestNamePanel);
		ContestInfoPanel.setCellHeight(contestNamePanel, "20%");
		ContestInfoPanel.add(contestAccessCodePanel);
		ContestInfoPanel.setCellHeight(contestAccessCodePanel, "20%");
		ContestInfoPanel.add(contestStartDatePanel);
		ContestInfoPanel.setCellHeight(contestStartDatePanel, "20%");
		ContestInfoPanel.add(contestEndDatePanel);
		ContestInfoPanel.setCellHeight(contestEndDatePanel, "20%");
		ContestInfoPanel.add(contestSumissionPanel);
		ContestInfoPanel.setCellHeight(contestSumissionPanel, "20%");
		return ContestInfoPanel;
	}

	@Override
	public Button getSubmissionButton() {
		return btnSubmission;
	}

	@Override
	public TextBox getContestName() {
		return contestName;
	}

	@Override
	public TextBox getContestAccessCode() {
		return contestAccessCode;
	}

	@Override
	public ContestData getChossenContest() {
		int selected = listContests.getSelectedIndex();
		if (selected == 0 || selected == -1)
			return null;
		else
			return contests.get(selected - 1);
	}

	@Override
	public DateBox getStartDate() {
		return contestStartDate;
	}

	@Override
	public DateBox getEndDate() {
		return contestEndDate;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public ListBox getcontestlists() {
		return listContests;
	}

	@Override
	public StackLayoutPanel getSplitPanel() {
		return splitPanel;
	}

	@Override
	public CellList<ProblemData> getCellList() {
		return ContestProblemsList;
	}

	@Override
	public CellTable<ProblemData> getCellTable() {
		return cellTable;
	}

	@Override
	public SimplePager getPager() {
		return pager;
	}

	@Override
	public ListBox getSearchType() {
		return searchType;
	}

	@Override
	public TextBox getSearchBOX() {
		return searchBox;
	}

	@Override
	public Button getSearchButton() {
		return searchButton;
	}

	@Override
	public void setProblemsForContest(ArrayList<ProblemData> problemsForContest) {
		chossenproblems.clear();
		chossenproblems.addAll(problemsForContest);
		problemsprovider.setList(chossenproblems);
	}

}
