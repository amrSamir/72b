package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.ScoreBoardRow;
import com.OJToolkit.client.ValueObjects.ScoreboardData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.OJToolkit.client.presenter.ViewContestPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class ViewContestView extends Composite implements
		ViewContestPresenter.Display {

	ListBox listContests;
	Button btnJoinContest;
	int contestsSize;
	ArrayList<ContestData> contests;
	ArrayList<CoderData> coders;
	ArrayList<ProblemData> problems;
	ArrayList<SubmissionData> submissions;
	VerticalPanel verticalPanel;
	CellTable<ScoreBoardRow> scoreBoardTable;
	ListDataProvider<ScoreBoardRow> scoreBoardProvider;
	ScoreboardData scoreBoard;
	ArrayList<ScoreBoardRow> scoreboardRows;

	

	public ViewContestView() {
		scoreBoard = new ScoreboardData();
		scoreBoardTable = new CellTable<ScoreBoardRow>();
		scoreboardRows = new ArrayList<ScoreBoardRow>();
		scoreBoardProvider = new ListDataProvider<ScoreBoardRow>();
		scoreBoardProvider.addDataDisplay(scoreBoardTable);
		coders = new ArrayList<CoderData>();
		problems = new ArrayList<ProblemData>();
		contests = new ArrayList<ContestData>();
		submissions = new ArrayList<SubmissionData>();
		verticalPanel = new VerticalPanel();
		verticalPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		initWidget(verticalPanel);

		HorizontalPanel pnlJoinContest = new HorizontalPanel();
		Label lblContestName = new Label("choose contest");
		listContests = new ListBox();
		pnlJoinContest.add(lblContestName);
		pnlJoinContest.add(listContests);

		verticalPanel.add(pnlJoinContest);
		HorizontalPanel pnlAccessCodeContest = new HorizontalPanel();

		verticalPanel.add(pnlAccessCodeContest);

		btnJoinContest = new Button("view contest");
		btnJoinContest.setSize("100px", "28px");
		verticalPanel.add(btnJoinContest);
		verticalPanel.setCellHorizontalAlignment(btnJoinContest,
				HasHorizontalAlignment.ALIGN_CENTER);

		verticalPanel.add(scoreBoardTable);
	}

	@Override
	public void setCoders(ArrayList<CoderData> coders) {
		this.coders = coders;
		scoreBoard.setCoders(coders);
	}

	@Override
	public void setProblems(ArrayList<ProblemData> problems) {
		this.problems = problems;
		scoreBoard.setProblems(problems);
		viewProblems();
	}

	private void viewProblems() {
		// add coder coulmn
		String coulmnName = "Coders";
		scoreBoardTable.addColumn(new TextColumn<ScoreBoardRow>() {
			@Override
			public String getValue(ScoreBoardRow scoreboardrow) {
				return scoreboardrow.getCoder().getUsername();
			}
		}, coulmnName);
		for (ProblemData c : problems) {
			addColumn(c.getProblemCode(), c.getOjType());
		}
		coulmnName = "solved";
		scoreBoardTable.addColumn(new TextColumn<ScoreBoardRow>() {
			@Override
			public String getValue(ScoreBoardRow scoreboardrow) {
				return String.valueOf(scoreboardrow.getAC()) ;
			}
		}, coulmnName);
		coulmnName = "totalTime";
		scoreBoardTable.addColumn(new TextColumn<ScoreBoardRow>() {
			@Override
			public String getValue(ScoreBoardRow scoreboardrow) {
				return scoreboardrow.getTotaltime() ;
			}
		}, coulmnName);
	}

	private void addColumn(final String problemcode, final String problemOJ) {
		String AC = scoreBoard.get(problemcode, problemOJ, true) ;
		String WA = scoreBoard.get(problemcode, problemOJ, false) ;
		String coulmnName = problemcode + "-" + problemOJ +"(" + AC + "-" + WA + ")";
		scoreBoardTable.addColumn(new TextColumn<ScoreBoardRow>() {
			@Override
			public String getValue(ScoreBoardRow scoreboardrow) {
//				System.out.println(contests.get(listContests.getSelectedIndex()).getContestName());
				return scoreboardrow.getSubmission(problemcode, problemOJ,contests.get(listContests.getSelectedIndex()).getStartTime());
			}
		}, coulmnName);
	}

	@Override
	public void setContests(ArrayList<ContestData> contests) {
		this.contests = contests;
		System.out.println("Magdi-presnter-join- Number of contests = "
				+ contests.size());
		listContests.clear();
		for (int i = 0; i < contests.size(); i++) {
			listContests.addItem(contests.get(i).getContestName());
		}
	}

	@Override
	public void setSubmissions(ArrayList<SubmissionData> submissions) {
		this.submissions = submissions;
		scoreBoard.setContest(contests.get(listContests.getSelectedIndex())) ;
		scoreBoard.setSubmissions(submissions);
		scoreboardRows = scoreBoard.loadScoreboardRows();
		
		 //TODO update
		updateScoreboardTable();
		scoreBoardProvider.setList(scoreboardRows);
//		scoreBoardTable.redraw();
	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return btnJoinContest;
	}

	@Override
	public String getContestName() {
		return listContests.getValue(listContests.getSelectedIndex());
	}

	@Override
	public void setScoreboardTable() {
		int k = scoreBoardTable.getColumnCount()  ;
		for(int i = 0 ; i < k ; i++){
			scoreBoardTable.removeColumn(scoreBoardTable.getColumn(0));
		}
	}
	@SuppressWarnings("rawtypes")
	public void updateScoreboardTable() {
		int k = scoreBoardTable.getColumnCount()  ;
		for(int i = 0 ; i < k ; i++){
			scoreBoardTable.removeColumn(scoreBoardTable.getColumn(0));
		}
		viewProblems() ;
	}
}
